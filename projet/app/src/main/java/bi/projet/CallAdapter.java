package bi.projet;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SARA on 06/05/2018.
 */

public class CallAdapter extends CursorAdapter {



    public CallAdapter(@NonNull Context context, Cursor cursor){
        super(context, cursor, true);

    }

    @NonNull
    @Override
    public void bindView( @Nullable View view, Context context, Cursor cursor) {

       // if(listItem == null)
          //  listItem = LayoutInflater.from(aContext).inflate(R.layout.layout_appels,parent,false);
        long id = cursor.getLong(0);
        String num =cursor.getString(1);
        //int duree=Integer.parseInt(cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION)));
        int duree=cursor.getInt(2);
        int statut=cursor.getInt(3);
        String sDate=cursor.getString(4);


        Appel app=new Appel(id,num,duree,statut,sDate);

        ImageView image = (ImageView)view.findViewById(R.id.image);
        if (app.getStatut()==1){
            image.setImageResource(android.R.drawable.sym_call_outgoing);
        }
        else if (app.getStatut()==0) image.setImageResource(android.R.drawable.sym_call_incoming);

        TextView numero = (TextView) view.findViewById(R.id.num);
        numero.setText(app.getNum());

        TextView dure = (TextView) view.findViewById(R.id.duree);
        dure.setText(app.FormatDuree());

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(app.getsDate());
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_appels,parent,false);
        return view;
    }

}
