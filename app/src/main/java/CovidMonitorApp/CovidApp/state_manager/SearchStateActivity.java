package CovidMonitorApp.CovidApp.state_manager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import CovidMonitorApp.CovidApp.MainActivityCovid;
import CovidMonitorApp.CovidApp.R;
import CovidMonitorApp.CovidApp.base.BaseActivity;
import CovidMonitorApp.CovidApp.covidAPI.CovidBean;
import CovidMonitorApp.CovidApp.covidAPI.StateMap;
import CovidMonitorApp.CovidApp.covidAPI.URLUtils;
import com.google.gson.Gson;

import java.util.Map;

public class SearchStateActivity extends BaseActivity implements View.OnClickListener{
    EditText searchEt;
    ImageView submitIv;
    GridView searchGv;
    StateMap stateMap;
    Map<String, String> map;
    String[] hotStates= {"Illinois","Pennsylvania", "New York", "California", "Texas", "Georgia","Florida", "Louisiana"};
    private ArrayAdapter<String> adapter;
    String state;
    String stateabb; // state abbreviation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateMap = new StateMap();
        map = stateMap.getStates();
        setContentView(R.layout.activity_search_state);
        searchEt = findViewById(R.id.search_et);
        submitIv = findViewById(R.id.search_iv_submit);
        searchGv = findViewById(R.id.search_gv);
        submitIv.setOnClickListener(this);
//      set adapter for hot states
        adapter = new ArrayAdapter<>(this, R.layout.item_hotstates, hotStates);
        searchGv.setAdapter(adapter);
        setListener();
    }
/* load data for a state when click a hot state item*/

    private void setListener() {

        searchGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            state = hotStates[position];
            stateabb = map.get(state);
            String url = URLUtils.getJson_url(stateabb);
            loadData(url);
            }
        });
//        Toast.makeText(this, pos[0],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_iv_submit:
                state = searchEt.getText().toString().trim().toLowerCase();
                String temp = state.substring(0, 1).toUpperCase() + state.substring(1);
                // convert state name to 2-letter ANSI state code
                stateabb = map.get(temp);

                if (!TextUtils.isEmpty(stateabb)) {
                    String url = URLUtils.getJson_url(stateabb);
                    loadData(url);
                }else {
                    Toast.makeText(this,"Please enter a correct state name!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        try{
            CovidBean covidBean = gson.fromJson(result, CovidBean.class);
        }
        catch (Exception e) {
            Toast.makeText(this,"Sorry, cannot find this state...",Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivityCovid.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("state",stateabb);
        startActivity(intent);

    }
}
