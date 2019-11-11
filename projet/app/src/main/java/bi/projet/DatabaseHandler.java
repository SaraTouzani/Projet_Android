package bi.projet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SARA on 06/05/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String APPEL_KEY = "_id";
    public static final String APPEL_NUM = "numero";
    public static final String APPEL_DURATION = "duree";
    public static final String APPEL_STATE = "statut";
    public static final String APPEL_DATE = "date";
    public static final String APPEL_TABLE_NAME = "Appel";
    public static final String APPEL_TABLE_CREATE =
            "CREATE TABLE " + APPEL_TABLE_NAME + " (" +
                    APPEL_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    APPEL_NUM + " TEXT, " +
                    APPEL_DURATION + " INTEGER,"+
                    APPEL_STATE + " INTEGER,"+
                    APPEL_DATE + " TEXT"+
                    ");";
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(APPEL_TABLE_CREATE);
    }

    public static final String APPEL_TABLE_DROP = "DROP TABLE IF EXISTS" + APPEL_TABLE_NAME + ";";
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL(APPEL_TABLE_DROP);
        onCreate(db);
    }
}
