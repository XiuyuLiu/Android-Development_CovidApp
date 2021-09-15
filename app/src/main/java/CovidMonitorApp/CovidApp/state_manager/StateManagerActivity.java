package CovidMonitorApp.CovidApp.state_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import CovidMonitorApp.CovidApp.R;
import CovidMonitorApp.CovidApp.dbCovid.DBManager;
import CovidMonitorApp.CovidApp.dbCovid.DatabaseBean;

import java.util.ArrayList;
import java.util.List;

public class StateManagerActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView addIv,backIv,deleteIv;
    ListView stateLv;
    List<DatabaseBean>mDatas;  //databasebean in list
    private StateManagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_manager);
        addIv = findViewById(R.id.state_iv_add);
        backIv = findViewById(R.id.state_iv_back);
        deleteIv = findViewById(R.id.state_iv_delete);
        stateLv = findViewById(R.id.state_lv);
        mDatas = new ArrayList<>();
//      add onclick listener
        addIv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
        backIv.setOnClickListener(this);
//      set adapters
        adapter = new StateManagerAdapter(this, mDatas);
        stateLv.setAdapter(adapter);
    }
/*  fetch all info from database, add to the current data and remind adapter updates*/
    @Override
    protected void onResume() {
        super.onResume();
        List<DatabaseBean> list = DBManager.queryAllInfo();
        mDatas.clear();
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.state_iv_add:
                int stateCount = DBManager.getStateCount();
                if (stateCount<6) {
                    Intent intent = new Intent(this, SearchStateActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this,"You have reached storage limit, please delete records before adding",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.state_iv_back:
                finish();
                break;
            case R.id.state_iv_delete:
                Intent intent1 = new Intent(this, DeleteStateActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
