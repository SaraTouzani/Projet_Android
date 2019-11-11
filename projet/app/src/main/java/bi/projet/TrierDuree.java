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

public class TrierDuree extends AppCompatActivity {

    EditText number;
    Button call, delete;
    ListView lvAppels;
    Button more;
    private CallAdapter monAdaptateur;
    int positionselectionnee = -1;
    Cursor cursor = null;
    DBase con;
    String s=null;
    Appel appelCourant, appel1,appel0;
    List<AppelWS> appels;
    private static AccesDistant accesDistant;
    private static DistantAcces acces;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trier_duree);
        number = (EditText) findViewById(R.id.tel);
        call = (Button) findViewById(R.id.call);
        delete = (Button) findViewById(R.id.delete);
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

        cursor = con.selectionnerTrierDuration();
        monAdaptateur = new CallAdapter(this, cursor);
        lvAppels.setAdapter(monAdaptateur);

        //Accés à la base de données distante
        accesDistant=new AccesDistant();
        //acces=new DistantAcces();

        lvAppels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                cursor.moveToPosition(position);
                appelCourant = con.element(cursor);
                positionselectionnee = position;
                number.setText(appelCourant.getNum());
            }
        });
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
                        appel0=new Appel(incomingNumber,0,0);
                        //enregistrement de l'appel dans la BD locale avec s un String en format Json de l'appel enregistré
                        s=con.ajouter(appel0);
                        //enregistrement de l'appel dans la BD distante
                        accesDistant.send("enreg",s);
                        cursor.requery();
                        break;
                }
            }
        };

//pour ajouter les appels sortants à la base de données
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number.getText().toString()));


                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    Toast.makeText(TrierDuree.this, "Vérifiez vos permissions", Toast.LENGTH_LONG).show();

                    return;
                }
                startActivity(intent);
                appel1 =new Appel(number.getText().toString(),0,1);
                //enregistrement de l'appel dans la BD locale avec s un String en format Json de l'appel enregistré
                s=con.ajouter(appel1);
                //enregistrement de l'appel dans la BD distante
                accesDistant.send("enreg",s);
                //acces.send(s);
                cursor.requery();}

        });

        //appel1.setDuree(Integer.parseInt(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION))));
        //Log.d("msg", "durée"+cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION))+"Sec");


        //redirection vers le menu
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(TrierDuree.this,Menu.class);
                startActivity(intent1);
            }
        });

        //supression d'un appel
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con.deleteCall(appelCourant);
                number.setText("");
                cursor.requery();

            }
        });
        cursor.requery();
        //con.close();



    }


}
