package com.example.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.fragment.main.FragmentMainAll;
import com.example.myapplication.fragment.main.FragmentMainCU;
import com.example.myapplication.fragment.main.FragmentMainEmart;
import com.example.myapplication.fragment.main.FragmentMainGS;
import com.example.myapplication.fragment.main.FragmentMainMinistop;
import com.example.myapplication.fragment.main.FragmentMainSeven;

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
                FragmentMainAll fragmentMainAll = new FragmentMainAll();
                return fragmentMainAll;
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
