package com.example.myapplication.fragment.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainAllPagerAdapter;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.vo.Sale;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentMainAll extends Fragment {

    private Context mContext;
    private ViewPager mViewPager;
    private MainAllPagerAdapter mainAllPagerAdapter;

    private TabLayout mTabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_all, container, false);

        init(view);

        return view;
    }
    private void init(View view){
        mContext = view.getContext();
//        recyclerView = view.findViewById(R.id.recyclerView_event);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
//        recyclerView.setLayoutManager(mLayoutManager);
//        mainSaleAdapter = new MainSaleAdapter(mContext,getList());
//        recyclerView.setAdapter(mainSaleAdapter);

        mTabLayout = view.findViewById(R.id.tab_main_all);

        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("할인 정보")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("상품 정보")));

        mViewPager = view.findViewById(R.id.main_all_pager_content);
        mainAllPagerAdapter = new MainAllPagerAdapter(getActivity().getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mainAllPagerAdapter);
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
    private void refresh(){
        mainAllPagerAdapter.notifyDataSetChanged();

    }

    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.main_all_custom_tab,null);
        TextView tv = tabView.findViewById(R.id.txt_main_all_custom_tab);
        tv.setText(tabName);
        return tabView;
    }


}
