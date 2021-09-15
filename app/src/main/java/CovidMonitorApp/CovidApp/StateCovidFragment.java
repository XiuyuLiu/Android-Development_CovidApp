package CovidMonitorApp.CovidApp;

import static android.content.Context.MODE_PRIVATE;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import CovidMonitorApp.CovidApp.base.BaseFragment;
import CovidMonitorApp.CovidApp.dbCovid.DBManager;
import CovidMonitorApp.CovidApp.covidAPI.CovidBean;
import CovidMonitorApp.CovidApp.covidAPI.StateMap;
import CovidMonitorApp.CovidApp.covidAPI.URLUtils;
import com.google.gson.Gson;

import java.util.Map;

/**
 * Fragment embedded in ViewPager in the main page
 */
public class StateCovidFragment extends BaseFragment implements View.OnClickListener{
    TextView stateTv,newCasesTv,infectionRateTv,dateTv,vaccinationTv,positiveRateTv,popTv,posCasesTv,deathTv;
    TextView maskReTv, indoorReTv, schoolReTv, travelReTv, hospitalReTv, vaccinationReTv;
    ImageView dayIv;
    LinearLayout futureLayout;
    ScrollView outLayout;
    String state;
    String dataUrl;
    String jsonContent;
    private SharedPreferences pref;
    private int bgNum;
    CovidBean covidBean;
    //change background
    public void exchangeBg(){
        pref = getActivity().getSharedPreferences("bg_pref", MODE_PRIVATE);
        bgNum = pref.getInt("bg", 2);
        System.out.println(bgNum);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_covid, container, false);
        initView(view);
        exchangeBg();
//      get most updated data
        Bundle bundle = getArguments();
        state = bundle.getString("state");
        dataUrl = URLUtils.getJson_url(state);
        loadData(dataUrl);
        //loadCovidData(dataUrl);
        Log.d("myTag", "onCreateView");
        return view;
    }


    @Override
    public void onSuccess(String result) {
//      log success
        Log.d("myTag", "OnSuccess: " + result);

        parseShowData(result);
//      update info
        int i = DBManager.updateInfoByState(state, result);
        if (i<=0) {
//      update fail, add state info to database
            DBManager.addStateInfo(state,result);
        }
    }
    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
//      get data from database and show in fragment
        Log.d("myTag", "OnError");
        String s = DBManager.queryInfoByState(state);
        if (!TextUtils.isEmpty(s)) {
            parseShowData(s);
        }

    }
    private void parseShowData(String result) {
        /*  use gson to parse data */
        Log.d("myTag", "parseShowData");
        covidBean  = new Gson().fromJson(result, CovidBean.class);
        /*  set up textview */
        Map<String, String> stateMap =  new StateMap().getStates();
        for(String state : stateMap.keySet()){
            if(covidBean.getState().compareTo(stateMap.get(state)) == 0){
                stateTv.setText(state);
            }
        }
        String dataString = "Last updated: " + covidBean.lastUpdatedDate;
        Log.d("myTag", "GsonDate: " + dataString);
        dateTv.setText(dataString);
        String dailyNew = "Daily new cases: " + String.valueOf(covidBean.getActuals().getNewCases());
        newCasesTv.setText(dailyNew);
        String infectionRate = "Infection rate: " + covidBean.getMetrics().getInfectionRate().toString();
        infectionRateTv.setText(infectionRate);
        String vaccinationRate = "Vaccination rate: " + covidBean.getMetrics().getVaccinationsCompletedRatio().toString();
        vaccinationTv.setText(vaccinationRate);
        String positiveRate = "Positive rate: " + covidBean.getMetrics().getTestPositivityRatio().toString();
        positiveRateTv.setText(positiveRate);

        popTv.setText(covidBean.getPopulation().toString());
        posCasesTv.setText(covidBean.getActuals().getCases().toString());
        deathTv.setText(covidBean.getActuals().getDeaths().toString());
    }




    private void initView(View view) {
        /* initialize text view */
        stateTv = view.findViewById(R.id.frag_tv_state);
        newCasesTv = view.findViewById(R.id.frag_tv_newcases);
        infectionRateTv = view.findViewById(R.id.frag_tv_infection_rate);
        dateTv = view.findViewById(R.id.frag_tv_date);
        vaccinationTv = view.findViewById(R.id.frag_tv_vaccination);
        positiveRateTv = view.findViewById(R.id.frag_tv_positive_rate);
        popTv = view.findViewById(R.id.item_center_tv_pop);
        posCasesTv = view.findViewById(R.id.item_center_tv_poscases);
        deathTv = view.findViewById(R.id.item_center_tv_death);

        maskReTv = view.findViewById(R.id.frag_index_tv_mask);
        indoorReTv = view.findViewById(R.id.frag_index_tv_indoor);
        schoolReTv = view.findViewById(R.id.frag_index_tv_school);
        travelReTv = view.findViewById(R.id.frag_index_tv_travel);
        hospitalReTv = view.findViewById(R.id.frag_index_tv_hospital);
        vaccinationReTv = view.findViewById(R.id.frag_index_tv_vaccination);

//        futureLayout = view.findViewById(R.id.frag_center_layout);
        outLayout = view.findViewById(R.id.out_layout);
        /* set on click listener */
        maskReTv.setOnClickListener(this);
        indoorReTv.setOnClickListener(this);
        schoolReTv.setOnClickListener(this);
        travelReTv.setOnClickListener(this);
        hospitalReTv.setOnClickListener(this);
        vaccinationReTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        switch (v.getId()) {
            case R.id.frag_index_tv_mask:
                builder.setTitle("Mask Recommendations");
                String msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, no need for masks";
                    }else{
                        msg = "Masks are now recommended for vaccinated individuals in public indoor ";
                        msg += "spaces to reduce the spread of the Delta variant. ";
                        msg += "Unvaccinated people should continue to mask in all public spaces.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
            case R.id.frag_index_tv_indoor:
                builder.setTitle("Indoor Recommendations");
                msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, no need for indoor restrictions";
                    }else {
                        msg = "Indoor gatherings should be avoided with people outside the immediate household, ";
                        msg += "unless you are fully vaccinated. ";
                        msg += "Outdoor gatherings with masks and distancing are a safer alternative.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
            case R.id.frag_index_tv_school:
                builder.setTitle("School Recommendations");
                msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, children are safe in school";
                    }else {
                        msg = "Schools can safely offer in-person learning only when infection control measures are in place.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
            case R.id.frag_index_tv_travel:
                builder.setTitle("Travel Recommendations");
                msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, no need for travel restrictions!";
                    }else {
                        msg = "Travel should be avoided unless it is necessary or you are fully vaccinated.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
            case R.id.frag_index_tv_hospital:
                builder.setTitle("Hospital Recommendations");
                msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, no need for hospital restrictions";
                    }else{
                    msg = "Visitors will be screened for symptoms (including but not limited to cough, shortness of breath, and fever) ";
                    msg += "and contact with an individual confirmed to have COVID-19. Anyone who has a fever or is showing signs of illness during screening ";
                    msg += "or during visitation will not be allowed to visit or accompany the patient.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
            case R.id.frag_index_tv_vaccination:
                builder.setTitle("Vaccination Recommendations");
                msg = "Not available";
                if (covidBean != null){
                    if(covidBean.getActuals().getDeaths() < 10){
                        msg = "The pandemic is over, no need for vaccination!";
                    }else{
                    msg = "State laws establish vaccination requirements for school children. These laws often apply not only to ";
                    msg += "children attending public schools but also to those attending private schools and day care facilities. ";
                    msg += "States may also require immunization of healthcare workers and of patients/residents of healthcare facilities.";
                    }
                }
                builder.setMessage(msg);
                builder.setPositiveButton("OKay",null);
                break;
        }
        builder.create().show();
    }
}
