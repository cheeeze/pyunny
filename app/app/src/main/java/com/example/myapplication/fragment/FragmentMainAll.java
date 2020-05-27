package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.vo.Sale;

import java.util.ArrayList;
import java.util.List;

public class FragmentMainAll extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    private MainSaleAdapter mainSaleAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_all, container, false);

        init(view);


        return view;
    }
    private void init(View view){
        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView_event);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mainSaleAdapter = new MainSaleAdapter(mContext,getList());
        recyclerView.setAdapter(mainSaleAdapter);

    }

    private ArrayList<Sale> getList(){
        ArrayList<Sale> list = new ArrayList<>();
        //int franchise_id, String type, String product_name, int product_price, String product_image
        list.add(new Sale(0,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(1,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
        list.add(new Sale(3,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
        list.add(new Sale(0,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(1,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
        list.add(new Sale(3,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
        list.add(new Sale(0,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(1,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
        list.add(new Sale(3,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));


        return list;
    }
}
