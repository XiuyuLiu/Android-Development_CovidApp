package CovidMonitorApp.CovidApp.state_manager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import CovidMonitorApp.CovidApp.R;
import CovidMonitorApp.CovidApp.dbCovid.DBManager;

import java.util.ArrayList;
import java.util.List;

public class DeleteStateActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView errorIv,rightIv;
    ListView deleteLv;
    List<String>mDatas;
    List<String>deleteStates;
    private DeleteStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_state);
        errorIv = findViewById(R.id.delete_iv_error);
        rightIv = findViewById(R.id.delete_iv_right);
        deleteLv = findViewById(R.id.delete_lv);
        mDatas = DBManager.queryAllStateName();
        deleteStates = new ArrayList<>();
//      set listener
        errorIv.setOnClickListener(this);
        rightIv.setOnClickListener(this);
//      set adapter
        adapter = new DeleteStateAdapter(this, mDatas, deleteStates);
        deleteLv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.delete_iv_error:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert").setMessage("Abandon your change?")
                        .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    finish();
                            }
                        });
                builder.setNegativeButton("Think again",null);
                builder.create().show();
                break;
            case R.id.delete_iv_right:
                for (int i = 0; i < deleteStates.size(); i++) {
                    String state = deleteStates.get(i);

                    int i1 = DBManager.deleteInfoByState(state);
                }
                finish();
                break;
        }
    }
}
