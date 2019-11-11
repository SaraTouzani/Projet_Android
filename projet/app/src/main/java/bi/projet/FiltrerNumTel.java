package bi.projet;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class FiltrerNumTel extends AppCompatActivity {

    EditText number;
    Button filter;
    ListView lvAppels;
    Button more;
    private CallAdapter monAdaptateur;
    Cursor cursor = null;
    DBase con;
    String s=null;
    String num=null;
    private static AccesDistant accesDistant;
    private static DistantAcces acces;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrer_num_tel);
        number = (EditText) findViewById(R.id.tel);
        filter = (Button) findViewById(R.id.filter);
        lvAppels = (ListView) findViewById(R.id.appel);
        more = (Button) findViewById(R.id.plus);


        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(new PhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);

        //Accés à la base de données locale
        con = new DBase(getApplicationContext());
        try {
            con.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }




        //pour ajouter les appels entrants à la base de données

        PhoneStateListener stateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d("msg", "Pas d'appel en cours");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d("msg", "Une communication téléphonique en cours");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Intent intent1=new Intent(FiltrerNumTel.this,ListeTel.class);
                        startActivity(intent1);
                        break;
                }
            }
        };
//pour filtrer par le numéro entré
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=number.getText().toString();
                if(num==null)Toast.makeText(getApplicationContext(),"Veuillez entrer un numéro valide!",Toast.LENGTH_LONG).show();
                else {
                    cursor = con.selectionnerFiltrerNumTel(num);
                    monAdaptateur = new CallAdapter(getApplicationContext(), cursor);
                    lvAppels.setAdapter(monAdaptateur);
                    if (cursor==null){
                        Toast.makeText(getApplicationContext(),"Ce numéro n'est pas dans l'historique!",Toast.LENGTH_LONG).show();
                    }


                }
            }
        });




        //redirection vers le menu
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(FiltrerNumTel.this,Menu.class);
                startActivity(intent1);
            }
        });


        //con.close();



    }


}
