package bi.projet;

/**
 * Created by SARA on 13/05/2018.
 */

public interface AsyncResponse {
    //doit s'executer à chaque fois qu'on a retur du serveur
    void processFinish(String output);
}
