package CovidMonitorApp.CovidApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import CovidMonitorApp.CovidApp.state_manager.StateManagerActivity;
import CovidMonitorApp.CovidApp.dbCovid.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivityCovid extends AppCompatActivity implements View.OnClickListener{
    ImageView addStateIv,moreIv;
    LinearLayout pointLayout;
    RelativeLayout outLayout;
    ViewPager mainVp;
//  fragment in view pager
    List<Fragment>fragmentList;
//  a list of all states saved in the database
    List<String>stateList;
//  Indicator of different page
    List<ImageView>imgList;


    private StateFragmentPagerAdapter adapter;
    private SharedPreferences pref;
    private int bgNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addStateIv = findViewById(R.id.main_iv_add);
        moreIv = findViewById(R.id.main_iv_more);
        pointLayout = findViewById(R.id.main_layout_point);
        outLayout = findViewById(R.id.main_out_layout);
        exchangeBg();
        mainVp = findViewById(R.id.main_vp);
//        /*add more state in the main page*/
        addStateIv.setOnClickListener(this);
        moreIv.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        stateList = DBManager.queryAllStateName();
        imgList = new ArrayList<>();
//        /* add default state*/
        if (stateList.size()==0) {
            stateList.add("IL");
            stateList.add("WI");
        }
//        /* get values from searching page*/
        try {
            Intent intent = getIntent();
            String state = intent.getStringExtra("state");
//            Log.d("QTag", "state in Main" + state);
            if (!stateList.contains(state)&&!TextUtils.isEmpty(state)) {
                stateList.add(state);
            }
        }catch (Exception e){
            Log.i("Error","Problem here");
        }
//        /*initialize view pager*/
        initPager();
        adapter = new StateFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);

        mainVp.setAdapter(adapter);
        /*create indicator points*/
        initPoint();
        /*set the last state information*/
        mainVp.setCurrentItem(fragmentList.size()-1);
        /*set listener for view pager*/
        setPagerListener();
    }
    /*
    initial pages according to the state lists in the database
     */
    private void initPager() {
        /* create fragment objects and add it to the view pager*/
        for (int i = 0; i < stateList.size(); i++) {
            StateCovidFragment cwFrag = new StateCovidFragment();
            Bundle bundle = new Bundle();
            bundle.putString("state",stateList.get(i));
            cwFrag.setArguments(bundle);
            fragmentList.add(cwFrag);
        }
    }
    /*
    initial point indicator at the bottom of main page
    */
    private void initPoint() {
//       add point indicator on the bottom of main page, indicating each page
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pIv = new ImageView(this);
            pIv.setImageResource(R.mipmap.a1);
            pIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pIv.getLayoutParams();
            lp.setMargins(0,0,20,0);
            imgList.add(pIv);
            pointLayout.addView(pIv);
        }
        // activate the last point, this corresponds to the added page always at last
        imgList.get(imgList.size()-1).setImageResource(R.mipmap.a2);

    }
    /*
    adjust point indicators according to which page we are on
     */
    private void setPagerListener() {
        /* set listener*/
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.a1);
                }
                imgList.get(position).setImageResource(R.mipmap.a2);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //  change background
    public void exchangeBg(){
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
        bgNum = pref.getInt("bg", 2);
        switch (bgNum) {
            case 0:
                outLayout.setBackgroundResource(R.mipmap.bg);
                break;
            case 1:
                outLayout.setBackgroundResource(R.mipmap.bg2);
                break;
            case 2:
                outLayout.setBackgroundResource(R.mipmap.bg3);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            // add more state
            case R.id.main_iv_add:
                intent.setClass(this,StateManagerActivity.class);
                break;
            // go to the setting page
            case R.id.main_iv_more:
                intent.setClass(this, SettingActivity.class);
                break;
        }
        startActivity(intent);
    }

    /* update viewpage*/
    @Override
    protected void onRestart() {
        super.onRestart();
        // et all state name
        List<String> list = DBManager.queryAllStateName();
        if (list.size()==0) {
            // add default state
            list.add("IL");
        }
        // clear and reset state list
        stateList.clear();
        stateList.addAll(list);
        // change fragment
        fragmentList.clear();
        initPager();
        adapter.notifyDataSetChanged();
        // change point indicators
        imgList.clear();
        pointLayout.removeAllViews();
        initPoint();
        mainVp.setCurrentItem(fragmentList.size()-1);
    }
}
