package bi.projet;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

public class Menu extends AppCompatActivity {



        Button Accueil,TrierDate,TrierDuree,FiltrerNumTel,FiltrerSortant,FiltrerEntrant,supprimer,moisC ;
        DBase con;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);
            Accueil = (Button) findViewById(R.id.Accueil);
            TrierDate = (Button) findViewById(R.id.TrierDate);
            TrierDuree = (Button) findViewById(R.id.TrierDuree);
            FiltrerNumTel = (Button) findViewById(R.id.FiltrerNumTel);
            FiltrerSortant = (Button) findViewById(R.id.FiltrerSortant);
            FiltrerEntrant = (Button) findViewById(R.id.FiltrerEntrant);
            moisC =(Button)findViewById(R.id.FiltrerMois);
            supprimer= (Button) findViewById(R.id.sup);
            con = new DBase(getApplicationContext());
            try {
                con.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            Accueil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent AccueilIntent = new Intent(Menu.this, ListeTel.class);
                    startActivity(AccueilIntent);


                }
            });

            TrierDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TrierDateIntent = new Intent(Menu.this, TrierDate.class);
                    startActivity(TrierDateIntent);


                }
            });

            TrierDuree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent TrierDureeIntent = new Intent(Menu.this, TrierDuree.class);
                    startActivity(TrierDureeIntent);


                }
            });

            FiltrerNumTel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent FiltrerNumTelIntent = new Intent(Menu.this, FiltrerNumTel.class);
                    startActivity(FiltrerNumTelIntent);


                }
            });

            FiltrerSortant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent FiltrerSortantIntent = new Intent(Menu.this, FiltrerSortant.class);
                    startActivity(FiltrerSortantIntent);


                }
            });

            FiltrerEntrant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent FiltrerSortantIntent = new Intent(Menu.this, FiltrerEntrant.class);
                    startActivity(FiltrerSortantIntent);


                }
            });
            moisC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent AccueilIntent = new Intent(Menu.this, MoisCourant.class);
                    startActivity(AccueilIntent);


                }
            });


        supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    con.delall();
                }
            });









        }

    }

