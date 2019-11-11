package bi.projet;


import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SARA on 13/05/2018.
 */

public class DistantAcces {

    private static final String SERVERADDR="http://192.168.13.1:9000/Projet/server1.php";
    public DistantAcces(){
        super();
    }


    public void send(String donnJson) {

        String param="lesdonnees="+donnJson;
       // Map<String, String> postData = new HashMap<>();
        //postData.put("lesdonnees", donnJson);
        HttpPostRequest task = new HttpPostRequest(param);
        //task.addParam("lesdonnees",donnJson);
        task.execute(SERVERADDR);

    }
}
