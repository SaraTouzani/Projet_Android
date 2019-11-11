package bi.projet;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SARA on 11/05/2018.
 */

public class AppelWS {

    private long id;
    private String num;
    private int duree;
    private int statut;
    private String sDate;

    public AppelWS(long id,String num, int duree, int statut,String date) {
        this.id=id;
        this.num = num;
        this.duree = duree;
        this.statut = statut;
        this.sDate=date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String FormatDuree(){
        int x =this.getDuree();
        int h=(int) (x/3600);
        int m=(int) ((x%3600)/60);
        int s=(int) ((x%3600)%60);
        String d="Durée: "+h+":"+m+":"+s;
        return d;
    }
}
