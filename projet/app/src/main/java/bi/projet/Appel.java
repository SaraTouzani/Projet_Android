package bi.projet;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */

public class Appel {
    private long id;
    private String num;
    private int duree;
    private int statut;
    private String sDate;

    private Date date_app;
    SimpleDateFormat ft = new SimpleDateFormat (" yyyy-MM-dd 'à' hh:mm:ss a");

    public Appel(String num, int duree, int statut) {
        this.num = num;
        this.duree = duree;
        this.statut = statut;
        this.date_app = new Date();
        this.sDate=ft.format(this.date_app);
    }

    public Appel(long id,String num, int duree, int statut,String date) {
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

    public Date getDate_app() {
        return date_app;
    }

    public void setDate_app(Date date_app) {
        this.date_app = date_app;
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
    /*public String FormatDate(){
        return ft.format(this.date_app);
    }*/

    public String toString(){
        String statut;
        if (this.getStatut()==1) statut="Appel sortant";
        else statut="Appel entrant";
        return "Num:"+this.getNum()+"\n "+statut+"\t"+this.FormatDuree()+"\n Date:"+this.getsDate()+"\n------------------------------------------------------";
    }
    public void duration(Date deb ){
        Date fin=new Date();
        int d=(int) (fin.getTime()-deb.getTime());
        this.setDuree(d);
    }
}
