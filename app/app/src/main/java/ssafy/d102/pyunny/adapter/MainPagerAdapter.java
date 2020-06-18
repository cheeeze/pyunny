package ssafy.d102.pyunny.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ssafy.d102.pyunny.fragment.main.FragmentMainAllSale;
import ssafy.d102.pyunny.fragment.main.FragmentMainCU;
import ssafy.d102.pyunny.fragment.main.FragmentMainEmart;
import ssafy.d102.pyunny.fragment.main.FragmentMainGS;
import ssafy.d102.pyunny.fragment.main.FragmentMainMinistop;
import ssafy.d102.pyunny.fragment.main.FragmentMainSeven;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private int mPageCount;

    public MainPagerAdapter(FragmentManager fm, int mPageCount){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mPageCount=mPageCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentMainAllSale fragmentMainAllSale = new FragmentMainAllSale();
                return fragmentMainAllSale;
            case 1:
                FragmentMainGS fragmentMainGS = new FragmentMainGS();
                return fragmentMainGS;
            case 2:
                FragmentMainCU fragmentMainCU = new FragmentMainCU();
                return fragmentMainCU;
            case 3:
                FragmentMainSeven fragmentMainSeven = new FragmentMainSeven();
                return fragmentMainSeven;
            case 4:
                FragmentMainEmart fragmentMainEmart = new FragmentMainEmart();
                return fragmentMainEmart;
            case 5:
                FragmentMainMinistop fragmentMainMinistop = new FragmentMainMinistop();
                return fragmentMainMinistop;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
