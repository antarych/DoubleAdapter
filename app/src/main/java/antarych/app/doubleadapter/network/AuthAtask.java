package antarych.app.doubleadapter.network;

import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Antarych on 25.10.2016.
 */

public class AuthAtask extends AsyncTask {

    Context context;
    @Override
    protected Object doInBackground(Object[] params) {
        JSONObject authJSON = new JSONObject();
        try
        {
            authJSON.put("name", "keka");
            authJSON.put("password", "keka123");
            String response = Connection.withJson("http://"+Connection.host+"/auth", "POST", authJSON);
            if (response.length()>0 && response.length()<100){
                PreferenceManager.getDefaultSharedPreferences(context)
                        .edit()
                        .putString("access_token", response)
                        .apply();

            }
            Log.d("lol", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void edit() {
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
