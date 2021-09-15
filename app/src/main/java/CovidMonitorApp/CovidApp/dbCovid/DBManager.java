package CovidMonitorApp.CovidApp.dbCovid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    public static SQLiteDatabase database;
    /* initialize a database*/
    public static void initDB(Context context){
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    /* query all state names*/
    public static List<String>queryAllStateName(){
        Cursor cursor = database.query("info", null, null, null, null, null,null);
        List<String>stateList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String state = cursor.getString(cursor.getColumnIndex("state"));
            stateList.add(state);
        }
        return stateList;
    }
    /* update info by state name*/
    public static int updateInfoByState(String state,String content){
        ContentValues values = new ContentValues();
        values.put("content",content);
        return database.update("info",values,"state=?",new String[]{state});
    }
    /* add a state record*/
    public static long addStateInfo(String state,String content){
        ContentValues values = new ContentValues();
        values.put("state",state);
        values.put("content",content);
        return database.insert("info",null,values);
    }
    /* query info by state*/
    public static String queryInfoByState(String state){
        Cursor cursor = database.query("info", null, "state=?", new String[]{state}, null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            String content = cursor.getString(cursor.getColumnIndex("content"));
            return content;
        }
        return null;
    }

    /* get number of states*/
    public static int getStateCount(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        int count = cursor.getCount();
        return count;
    }

    /* query all information in the database*/
    public static List<DatabaseBean>queryAllInfo(){
        Cursor cursor = database.query("info", null, null, null, null, null, null);
        List<DatabaseBean>list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String state = cursor.getString(cursor.getColumnIndex("state"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            DatabaseBean bean = new DatabaseBean(id, state, content);
            list.add(bean);
        }
        return list;
    }

    /* delete record by state name*/
    public static int deleteInfoByState(String state){
        return database.delete("info","state=?",new String[]{state});
    }

    /* clear info*/
    public static void deleteAllInfo(){
        String sql = "delete from info";
        database.execSQL(sql);
    }
}
