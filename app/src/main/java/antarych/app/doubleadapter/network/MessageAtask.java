package antarych.app.doubleadapter.network;

import android.os.AsyncTask;
import android.os.Messenger;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import antarych.app.doubleadapter.MainActivity;
import antarych.app.doubleadapter.models.MessageModel;
import antarych.app.doubleadapter.models.MessengerObject;

/**
 * Created by Antarych on 25.10.2016.
 */

public class MessageAtask extends AsyncTask {


    String response;
    ArrayList<MessengerObject> list = new ArrayList<>();
    MainActivity activity;

    @Override
    protected Object doInBackground(Object[] params) {
        try
        {
            activity = (MainActivity) params[0];
            response = Connection.get("http://" + Connection.host + "/messages/100");
            Log.d("lol", response);

            JSONArray messages = new JSONArray(response);
            for (int i = 0; i < messages.length(); i++){
                JSONObject message = messages.getJSONObject(i);
                list.add(new MessageModel(
                        message.getString("text"),
                        message.getString("senderName"),
                        ""
                ));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        super.onPostExecute(o);
        activity.initArray(list);
    }
}
