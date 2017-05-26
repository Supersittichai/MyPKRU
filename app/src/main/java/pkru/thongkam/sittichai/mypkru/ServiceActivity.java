package pkru.thongkam.sittichai.mypkru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Initial View
        initialView();

    }   //Main Method

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        listView = (ListView) findViewById(R.id.livFriend);
    }


}   //Main Class
