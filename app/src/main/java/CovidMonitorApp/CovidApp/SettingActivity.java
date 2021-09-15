package CovidMonitorApp.CovidApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import CovidMonitorApp.CovidApp.dbCovid.DBManager;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView bgTv,cacheTv,versionTv,shareTv;
    RadioGroup exbgRg;
    ImageView backIv;
    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        bgTv = findViewById(R.id.setting_tv_exchangebg);
        cacheTv = findViewById(R.id.setting_tv_cache);
        versionTv = findViewById(R.id.setting_tv_version);
        shareTv = findViewById(R.id.setting_tv_share);
        backIv = findViewById(R.id.setting_iv_back);
        exbgRg = findViewById(R.id.setting_rg);
        bgTv.setOnClickListener(this);
        cacheTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        String versionName = getVersionName();
        versionTv.setText("Current version:    v"+versionName);
        setRGListener();
    }
    /*
    set listener for changing background radio button
     */
    private void setRGListener() {
        exbgRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // current background from sharedpreferences
                int bg = pref.getInt("bg", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(SettingActivity.this, MainActivityCovid.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                switch (checkedId) {
                    case R.id.setting_rb_green:
                        if (bg==0) {
                            Toast.makeText(SettingActivity.this,"Current background!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",0);
                        editor.commit();
                        break;
                    case R.id.setting_rb_pink:
                        if (bg==1) {
                            Toast.makeText(SettingActivity.this,"Current background!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",1);
                        editor.commit();
                        break;
                    case R.id.setting_rb_blue:
                        if (bg==2) {
                            Toast.makeText(SettingActivity.this,"Current background!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",2);
                        editor.commit();
                        break;
                }
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_iv_back:
                finish();
                break;
            case R.id.setting_tv_cache:
                clearCache();
                break;
            case R.id.setting_tv_share:
                shareSoftwareMsg("Covid Now tracks the real time data for each state. Share with your friends!");
                break;
            case R.id.setting_tv_exchangebg:
                if (exbgRg.getVisibility() == View.VISIBLE) {
                    exbgRg.setVisibility(View.GONE);
                }else{
                    exbgRg.setVisibility(View.VISIBLE);
                }
                break;
        }
    }
    /*
    use mobile default sharing tools
     */
    private void shareSoftwareMsg(String s) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(intent,"Covid Now"));
    }

    /*
    clear database
     */
    private void clearCache() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert: ").setMessage("Comfirm to clear all data?");
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager.deleteAllInfo();
                Toast.makeText(SettingActivity.this,"Clear all data!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SettingActivity.this, MainActivityCovid.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton("Think again",null);
        builder.create().show();
    }
    /*
    Current version
    */
    public String getVersionName() {
        PackageManager manager = getPackageManager();
        String versionName = null;
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
