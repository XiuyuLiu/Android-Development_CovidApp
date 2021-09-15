package CovidMonitorApp.CovidApp.base;

import android.app.Application;

import CovidMonitorApp.CovidApp.dbCovid.DBManager;

import org.xutils.x;

public class UniteAppCovid extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        DBManager.initDB(this);
    }
}
