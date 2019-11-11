package bi.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SARA on 06/05/2018.
 */

public  class DBase {
    protected final static int VERSION =1;
    protected final static String NOM="appels.db";
    protected SQLiteDatabase mDB=null;
    protected DatabaseHandler mHandler=null;

    public static final String TABLE_NAME = "Appel";
    public static final String KEY = "_id";
    public static final String NUM = "numero";
    public static final String DURATION = "duree";
    public static final String STATE = "statut";
    public static final String DATE = "date";
    private String[] cols={KEY,NUM,DURATION,STATE,DATE};



    public DBase(Context pContext) {
        this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() throws SQLException{
     mDB = mHandler.getWritableDatabase();

        return mDB;
    }
    public void close() {
        mDB.close();
    }

    public SQLiteDatabase getDb() {
        return mDB;
    }


    public String ajouter(Appel m) {

        ContentValues value = new ContentValues();
        value.put(DBase.NUM, m.getNum());
        value.put(DBase.DURATION, m.getDuree());
        value.put(DBase.STATE, m.getStatut());
        value.put(DBase.DATE, m.getsDate());
        long id=mDB.insert(DBase.TABLE_NAME, null, value);
        m.setId(id);
        AppelWS a=new AppelWS(id,m.getNum(),m.getDuree(),m.getStatut(),m.getsDate());
        Gson gson=new GsonBuilder().create();
        String fluxJson=gson.toJson(a);Log.d("json:",fluxJson);
        return fluxJson;

    }


    public void deleteCall(int id) {
        mDB.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }
    public void delall(){
        mDB.delete(TABLE_NAME,null,null);
    }

    public void deleteCall(Appel a){
        long id=a.getId();
        mDB.delete(TABLE_NAME,KEY+"="+ id,null);
    }

    /**

     */
    public Cursor selectionner() {
        Cursor c = mDB.rawQuery("select * from " + TABLE_NAME+";" ,null);
        c.moveToFirst();
        return c;
    }

    public Cursor selectionnerTrierDate() {
        Cursor c = mDB.rawQuery("select * from " + TABLE_NAME+" order by "+DATE+";" ,null);
        c.moveToFirst();
        return c;
    }

    public Cursor selectionnerTrierDuration() {
        Cursor c = mDB.query(true,TABLE_NAME,cols,null,null,null,null,DURATION,null);
        c.moveToFirst();
        return c;
    }

    public Cursor selectionnerFiltrerSortant() {
        Cursor c = mDB.rawQuery("select * from " + TABLE_NAME+ " where "+ STATE + "=1;",null);
        c.moveToFirst();
        return c;
    }

    public Cursor selectionnerFiltrerEntrant() {
        Cursor c = mDB.rawQuery("select * from " + TABLE_NAME+ " where "+ STATE + "=0;",null);
        c.moveToFirst();
        return c;
    }

    public Cursor selectionnerFiltrerNumTel(String tel) {
        Cursor c =null;
        if (tel==null) { return c;}
        Log.d("DBase",tel);
            c = mDB.query(true,TABLE_NAME,cols,NUM+ " = ?",new String[] {tel },null,null,null,null);
            c.moveToFirst();
        return c;
    }
    public Cursor selectionnerMoisCourant() {
        Date d=new Date();
        String mois  = (String) DateFormat.format("MM",   d);
        Cursor c =mDB.query(true,TABLE_NAME,cols,DATE+ " LIKE ?",new String[] {"%-"+ mois+ "-%" },null,null,null,null);
        c.moveToFirst();
        return c;
    }

    public Appel element(Cursor cursor){
        long id = cursor.getLong(0);
        String num =cursor.getString(1);
        int duree=cursor.getInt(2);
        int statut=cursor.getInt(3);
        String sDate=cursor.getString(4);
        Appel app=new Appel(id,num,duree,statut,sDate);
        return app;

    }

    public List<AppelWS> getAppels(){
        List<AppelWS> lst=new ArrayList<AppelWS>();
        Cursor c=mDB.rawQuery("select * from " + TABLE_NAME+";" ,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            lst.add(new AppelWS(c.getLong(0),c.getString(1),c.getInt(2),c.getInt(3),c.getString(4)));
            c.moveToNext();
        }
        c.close();
        return lst;
    }









}
