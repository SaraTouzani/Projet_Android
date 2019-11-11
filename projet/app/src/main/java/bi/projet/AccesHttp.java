package bi.projet;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by SARA on 12/05/2018.
 */

public class AccesHttp extends AsyncTask<String ,Integer,Long> {

    private ArrayList<NameValuePair> parametres;
    private String retour=null;
    protected AsyncResponse delegate=null;
    public AccesHttp(){
        parametres=new ArrayList<NameValuePair>();

    }
    /**Ajout parametre post
     * @param nom
     * @param valeur
     */

    public void addParam(String nom,String valeur){
        parametres.add(new BasicNameValuePair(nom,valeur));
    }

    /**
     * cnx au thread séparé
     * @param strings
     * @return
     */


    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp=new DefaultHttpClient();
        HttpPost paramCnx=new HttpPost(strings[0]);
        try {
            //encodage des parametres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //connexin et envoi des param, attente de reponse
            HttpResponse response=cnxHttp.execute((paramCnx));
            //Transformation de la reponse
            retour= EntityUtils.toString(response.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("Err encodage:","!!!!!"+e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Err protocole:","!!!!!"+e.toString());
        } catch (IOException e) {
            Log.d("Err IO:","!!!!!"+e.toString());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish(retour.toString());
    }
}
