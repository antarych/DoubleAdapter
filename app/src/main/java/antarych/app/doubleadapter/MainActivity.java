package antarych.app.doubleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import antarych.app.doubleadapter.adapters.MessengerAdapter;
import antarych.app.doubleadapter.models.MessengerObject;
import antarych.app.doubleadapter.network.AuthAtask;
import antarych.app.doubleadapter.network.MessageAtask;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MessengerAdapter adapter;
    ArrayList<MessengerObject> arrayList;
    Button bSend;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AuthAtask().execute(this);
        new MessageAtask().execute(this);
    }

    public void initArray(ArrayList<MessengerObject> list){
        arrayList = list;
        bindViews();
        initAdapter();
    }

    private void bindViews() {

        listView = (ListView) findViewById(R.id.lv_messages);
        bSend = (Button) findViewById(R.id.b_send);
        etMessage = (EditText) findViewById(R.id.et_message_text);
    }

    private void initAdapter(){
        adapter = new MessengerAdapter(this, 0, arrayList);
        listView.setAdapter(adapter);

    }
}
