package CovidMonitorApp.CovidApp.state_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import CovidMonitorApp.CovidApp.R;
import CovidMonitorApp.CovidApp.dbCovid.DatabaseBean;
import CovidMonitorApp.CovidApp.covidAPI.CovidBean;
import com.google.gson.Gson;

import java.util.List;

public class StateManagerAdapter extends BaseAdapter{
    Context context;
    List<DatabaseBean>mDatas;

    public StateManagerAdapter(Context context, List<DatabaseBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_state_manager,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DatabaseBean bean = mDatas.get(position);
        holder.stateTv.setText(bean.getState());

        CovidBean covidBean = new Gson().fromJson(bean.getContent(), CovidBean.class);
        CovidBean.ActualsDTO actualsDTO = covidBean.getActuals();
        CovidBean.MetricsDTO metricsDTO = covidBean.getMetrics();

        // get infos
        holder.newcaseTv.setText("Daily new cases: \n" + actualsDTO.getNewCases());
        holder.posTv.setText("%Positive:" + metricsDTO.getTestPositivityRatio());
        holder.stateTv.setText(covidBean.getState());
        holder.vaccTv.setText("%Vacc:" + metricsDTO.getVaccinationsInitiatedRatio());
        holder.infectionTv.setText("Infection rate: " + metricsDTO.getInfectionRate());
        return convertView;
    }

    class ViewHolder{
        TextView stateTv,posTv,vaccTv,infectionTv,newcaseTv;
        public ViewHolder(View itemView){
            stateTv = itemView.findViewById(R.id.item_state_tv_state);
            posTv = itemView.findViewById(R.id.item_state_tv_pos);
            vaccTv = itemView.findViewById(R.id.item_state_tv_vacc);
            infectionTv = itemView.findViewById(R.id.item_state_tv_infection);
            newcaseTv = itemView.findViewById(R.id.item_state_tv_newcases);

        }
    }
}
