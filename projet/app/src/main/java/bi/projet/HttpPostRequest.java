package bi.projet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by SARA on 13/05/2018.
 */

public class HttpPostRequest extends AsyncTask<String, Void, Void> {

    //private Map<String, String> parametres;
   // JSONObject postData;
   /* public HttpPostRequest(String donnJson) {
       // parametres.put("lesdonnees",donnJson);
    }*/
   String parametres;

    public HttpPostRequest(String param) {
        parametres = param;
        //postData=new JSONObject(parametres);

    }



    /** public void addParam(String nom,String valeur){
     parametres.put(nom, valeur);
     }
     *
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/text;charset=utf-8");

           // String data = URLEncoder.encode("lesdonnees", "UTF-8") + "=" + this.parametres.get("lesdonnees");

            //Log.d("url",data);
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(parametres);
                Log.d("url",parametres);
                writer.flush();

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line=reader.readLine();
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    Log.d("serveur","!!!!!!!!!!!!!!!!!!!!!!"+line);
                }

                writer.close();
                reader.close();

            } else {
                Log.d("status code","NOT 200**");
            }

        }catch (Exception e){e.printStackTrace();}
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("server","Done");
    }

}
