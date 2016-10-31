package antarych.app.doubleadapter.network;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Antarych on 25.10.2016.
 */

public class Connection {
    public final static  String host = "95.85.2.42:3000";

    public static String get(String address)
    throws  Exception {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream in = new BufferedInputStream(
                connection.getInputStream()
        );
        String response = convertStreamToString(in);
        connection.disconnect();
        return  response;
    }

    public static String withJson(String address, String type, JSONObject jsonObject)
    throws  Exception{
        String json = "";
        json = jsonObject.toString();

        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(type);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(json);

        outputStreamWriter.flush();
        outputStreamWriter.close();

        Log.d("kek", "Response code: "+connection.getResponseCode());

        InputStream in = connection.getInputStream();

        return  convertStreamToString(in);

    }

    static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
