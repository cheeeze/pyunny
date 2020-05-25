package com.example.myapplication.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FragmentMain extends Fragment {

    private TabLayout mTabLayout;
    private Context mContext;
    private FragmentActivity fragmentActivity;

    private ViewPager mViewPager;
    private MainPagerAdapter mMainPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        fragmentActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        mContext = view.getContext();
        mTabLayout = view.findViewById(R.id.layout_tab);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("GS25")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("CU")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("7/11")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("Emart24")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("MiniStop")));

        mViewPager = container.findViewById(R.id.pager_content);

        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();

        mMainPagerAdapter = new MainPagerAdapter(fragmentManager,mTabLayout.getTabCount());
        mViewPager.setAdapter(mMainPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
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

        return view;
    }


    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab,null);
        TextView txt_name = tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }
}
