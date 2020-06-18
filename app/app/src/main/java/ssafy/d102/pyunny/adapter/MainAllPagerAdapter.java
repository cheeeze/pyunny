package ssafy.d102.pyunny.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ssafy.d102.pyunny.fragment.main.FragmentMainAllProduct;
import ssafy.d102.pyunny.fragment.main.FragmentMainAllSale;

public class MainAllPagerAdapter extends FragmentPagerAdapter {
    private int mPageCount;


    public MainAllPagerAdapter(FragmentManager fm, int mPageCount){
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
                FragmentMainAllProduct fragmentMainAllProduct = new FragmentMainAllProduct();
                return fragmentMainAllProduct;
        }

        return null;
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
