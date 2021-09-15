package CovidMonitorApp.CovidApp.state_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import CovidMonitorApp.CovidApp.R;

import java.util.List;

public class DeleteStateAdapter extends BaseAdapter{
    Context context;
    List<String>mDatas;
    List<String>deleteStates;

    public DeleteStateAdapter(Context context, List<String> mDatas, List<String>deleteCitys) {
        this.context = context;
        this.mDatas = mDatas;
        this.deleteStates = deleteCitys;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_deletestate,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final String state = mDatas.get(position);
        holder.tv.setText(state);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(state);
                deleteStates.add(state);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv;
        ImageView iv;
        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.item_delete_tv);
            iv = itemView.findViewById(R.id.item_delete_iv);
        }
    }
}
