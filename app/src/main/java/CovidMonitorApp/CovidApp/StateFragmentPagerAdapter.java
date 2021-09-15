package CovidMonitorApp.CovidApp;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class StateFragmentPagerAdapter extends FragmentStatePagerAdapter{
    List<Fragment>fragmentList;
    public StateFragmentPagerAdapter(FragmentManager fm, List<Fragment>fragmentLis) {
        super(fm);
        this.fragmentList = fragmentLis;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
//  page number
    int childCount = 0;
//  if the dataset change, i.e. page number changes
    @Override
    public void notifyDataSetChanged() {
        this.childCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (childCount>0) {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
