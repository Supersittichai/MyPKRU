package pkru.thongkam.sittichai.mypkru;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private String[] loginStrings;
    private LocationManager locationManager;
    private Criteria criteria;
    private double latADouble = 7.909667, lngADouble = 98.387601;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Show Name
        showName();

        //Initial View
        initialView();

        //Create ListView
        createListView();

    }   //Main Method

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
        }

        return location;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latADouble = location.getLatitude();
            lngADouble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void showName() {

        //Get Value From Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        textView.setText(loginStrings[1]);
    }

    private void createListView() {

        try {

            String urlJSON = "http://swiftcodingthai.com/pkru/GetUserMaster.php";
            GetAllData getAllData = new GetAllData(this);
            getAllData.execute(urlJSON);

            String strJSON = getAllData.get();
            Log.d("26MayV1", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                imageStrings[i] = jsonObject.getString("Image");
                Log.d("26MayV1", "Result (" + i + ")==> " + nameStrings[i] + ":" + imageStrings[i]);

            }   //for



            FriendAdapter friendAdapter = new FriendAdapter(this, nameStrings, imageStrings);
            listView.setAdapter(friendAdapter);

        } catch (Exception e) {
            Log.d("26MayV1", "e createListView ==> " + e.toString());
        }

    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        listView = (ListView) findViewById(R.id.livFriend);

        //Setup
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
    }


}   //Main Class
