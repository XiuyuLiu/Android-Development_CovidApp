package CovidMonitorApp.CovidApp.dbCovid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context){
        super(context,"covid.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//        Create a table
        String sql = "create table info(_id integer primary key autoincrement,state varchar(20) unique not null,content text not null)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
