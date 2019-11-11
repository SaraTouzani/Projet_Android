package bi.projet;

import android.util.Log;


/**
 * Created by SARA on 13/05/2018.
 */

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR="http://192.168.13.1:9000/Projet/server.php";
    public AccesDistant(){
        super();
    }
    /**
     * retour du serveur distant
     * @param output
     */

    @Override
    public void processFinish(String output) {
        Log.d("server:","!!!!!!!!!!"+output);
        }

    public void send(String operation, String donnJson){


        AccesHttp accesDonnees=new AccesHttp();
        // lien de délégation càd j'envoie mon accés distant actuel
        accesDonnees.delegate=this;
        //ajout des parametres
        accesDonnees.addParam("operation",operation);
        accesDonnees.addParam("lesdonnees",donnJson);
        //appel au serveur
        accesDonnees.execute(SERVERADDR);

    }

}
