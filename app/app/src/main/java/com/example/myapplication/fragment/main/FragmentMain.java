package com.example.myapplication.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentMain extends Fragment {

    private TabLayout mTabLayout;

    private Context mContext;

    private ViewPager mViewPager;

    private MainPagerAdapter mMainPagerAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mContext = view.getContext();
        init(view);

        return view;
    }

    private void init(View view){
        mTabLayout = view.findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("GS25")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("CU")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("7/11")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Emart24")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("MiniStop")));
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));

        mTabLayout.setSelectedTabIndicatorHeight((int)(5 * getResources().getDisplayMetrics().density));


        mViewPager = view.findViewById(R.id.pager_content);



        mMainPagerAdapter = new MainPagerAdapter(getActivity().getSupportFragmentManager(),mTabLayout.getTabCount());

        mViewPager.setAdapter(mMainPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                refresh();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class MypageChangedListener extends TabLayout.TabLayoutOnPageChangeListener{

        public MypageChangedListener(TabLayout tabLayout) {
            super(tabLayout);
        }
    }

    private void refresh(){
        mMainPagerAdapter.notifyDataSetChanged();
    }


    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab,null);
        ImageView image_tab = tabView.findViewById(R.id.tab_image);
        switch (tabName){
            case "전체":
                image_tab.setImageResource(R.drawable.all2);
                break;
            case "GS25":
                image_tab.setImageResource(R.drawable.gs25);
                break;
            case "CU":
                image_tab.setImageResource(R.drawable.cu);
                break;
            case "7/11":
                image_tab.setImageResource(R.drawable.seven);
                break;
            case "Emart24":
                image_tab.setImageResource(R.drawable.emart);
                break;
            case "MiniStop":
                image_tab.setImageResource(R.drawable.ministop);
                break;
        }

        return tabView;
    }
}
