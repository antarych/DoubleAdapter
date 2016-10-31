package antarych.app.doubleadapter.network;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Antarych on 25.10.2016.
 */

public class SendAtask extends AsyncTask {
    Context context;
    @Override
    protected Object doInBackground(Object[] params) {
        context = (Context) params[0];
        JSONObject sendJSON = new JSONObject();
        try
        {
            sendJSON.put("text", params[1]);

            String access_token = PreferenceManager
                    .getDefaultSharedPreferences(context)
                    .getString("access_token", "");

            sendJSON.put("access_token", access_token);
            String response = Connection.withJson("http://"+Connection.host+"/messages", "POST", sendJSON);

            Log.d("lol", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
