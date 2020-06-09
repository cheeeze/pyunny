package com.example.myapplication.fragment.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.vo.Sale;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentMainGS extends Fragment {

    private Context mContext;
    private RecyclerView recyclerView;
    private MainSaleAdapter mainSaleAdapter;
    private ArrayList<Sale> saleList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true); // 자동으로 스레드 유지

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_g_s, container, false);
        init(view);
        Button saleButton = (Button)view.findViewById(R.id.button_sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mainSaleAdapter = new MainSaleAdapter(mContext,getList());
                //recyclerView.setAdapter(mainSaleAdapter);
                //mainSaleAdapter.notifyDataSetChanged();
                getList();
                refresh();
            }
        });
        Button productButton = (Button)view.findViewById(R.id.button_all);
        productButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getProductList();
                //mainSaleAdapter = new MainSaleAdapter(mContext,getProductList());
                //recyclerView.setAdapter(mainSaleAdapter);
                //mainSaleAdapter.notifyDataSetChanged();
                refresh();
            }
        });
        return view;
    }

    private void refresh() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }


    private void init(View view){

        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView_event);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        mainSaleAdapter = new MainSaleAdapter(mContext,saleList);
        recyclerView.setAdapter(mainSaleAdapter);

    }

    private void getSaleList() {
        Log.d("TEST", "in getSaleList()");
        Thread thread = new Thread(new Runnable() {
            String result;
            @Override
            public void run() {
                try{
                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/sale");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(3000);
                    conn.setConnectTimeout(3000);
                    //conn.setDoOutput(true); //이거  있으면 무조건 POST로 메소드 변경됨!! 주의!
                    conn.setDoInput(true);

                    conn.setUseCaches(false);
                    conn.connect();

                    int responseStatusCode = conn.getResponseCode();
                    Log.i("CHECK", "thread run");
                    InputStream inputStream;
                    if(responseStatusCode == conn.HTTP_OK) {
                        inputStream = conn.getInputStream();
                    }else{
                        inputStream = conn.getErrorStream();
                    }
                    Log.d("REQEUSTMETHOD",conn.getRequestMethod());

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//                    JsonReader jsonReader = new JsonReader(inputStreamReader);
//                    jsonReader.beginObject();
//
//                    while(jsonReader.hasNext()){
//                        Log.d(jsonReader.nextName(),jsonReader.nextString());
//                    }
//                    jsonReader.close();
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null) {
                        sb.append(line);
                        //Log.d("THREAD",line);
                    }
                    bufferedReader.close();


                    conn.disconnect();
                    result = sb.toString();

                    saleList = getRest(result);
                    mainSaleAdapter.notifyDataSetChanged();
                } catch(Exception e){
                    result = e.toString();
                    Log.d("ERROR", e.toString());
                }


            }
        });
        thread.start();
    }


    private ArrayList<Sale> getList(){
        ArrayList<Sale> list = new ArrayList<>();
        //int franchise_id, String type, String product_name, int product_price, String product_image
        saleList.clear();
        saleList.add(new Sale(646,"1+1","Y)의성마늘매콤왕교자140G",3800,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801123726905_003.jpg"));
        saleList.add(new Sale(646,"2+1","서울)달고나우유300ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801115137306_001.jpg"));
        saleList.add(new Sale(646,"2+1","해태)초코홈런볼1500",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801019306495_496.jpg"));
        saleList.add(new Sale(646,"2+1","칸타타)콘트라베이스블랙PET500",2500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801056094591_001.jpg"));
        saleList.add(new Sale(646,"2+1","해태)맛동산2000",2000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801019608766_001.jpg"));
        saleList.add(new Sale(646,"2+1","프링글스양파맛(대)",3500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8886467100024_025.jpg"));
        saleList.add(new Sale(646,"1+1","동원)상상바삭디핑칩고소한맛",3000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801047184126_001.jpg"));
        saleList.add(new Sale(646,"1+1","동원)상상바삭디핑칩매콤한맛",3000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801047184133_001.jpg"));
        saleList.add(new Sale(646,"1+1","해태)과일사이다딸기키위380ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801105916157_002.jpg"));
        saleList.add(new Sale(646,"1+1","해태)과일사이다파인애플380ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801105916133_002.jpg"));
        saleList.add(new Sale(646,"1+1","한끼불고기150G(달콤한맛)",4900,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809152414794_001.jpg"));
        saleList.add(new Sale(646,"1+1","한끼불고기150G(매콤한맛)",4900,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809152414800_001.jpg"));

        return list;
    }


    private ArrayList<Sale> getProductList(){
        ArrayList<Sale> list = new ArrayList<>();
        //int franchise_id, String type, String product_name, int product_price, String product_image
        saleList.clear();
        saleList.add(new Sale(646,null,"유어스)풀무원촉촉란100G",1900,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        saleList.add(new Sale(646,"2+1","유어스)펭럽유초코우유300ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801121027868_001.JPG"));
        saleList.add(new Sale(646,"2+1","유어스)타이거슈가흑당밀크티300",2500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801155741280_001.jpg"));
        saleList.add(new Sale(646,"1+1","유어스)젤리얌리치340ML[D2]",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809329050411_002.jpg"));
        saleList.add(new Sale(646,null,"케이준치킨텐더샌드위치1편",2500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_2700038824477_001.jpg"));
        saleList.add(new Sale(646,null,"NEW고진많도시락1편",4500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_2700038824354_001.jpg"));
        saleList.add(new Sale(646,null,"왕비빔면1편",4000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_2700038824101_001.jpg"));
        saleList.add(new Sale(646,null,"군(軍)모닝버거1편",2800,"http://gs25appimg.gsretail.com/imgsvr/item/GD_2700038824378_001.JPG"));
        saleList.add(new Sale(646,null,"유어스)돌덩이얼음3KG",3950,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809197840275_001.jpg"));
        saleList.add(new Sale(646,null,"유어스)돌덩이얼음1KG",1850,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809197840268_001.jpg"));
        saleList.add(new Sale(646,null,"유어스)소고기미역국500G",3900,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809665555311_001.JPG"));
        saleList.add(new Sale(646,null,"유어스)야쿠르트그랜드수박280ML",1300,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801128280709_001.JPG"));
        saleList.add(new Sale(646,null,"유어스(P)얼그레이초코케이크",3000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801062882090_001.jpg"));

        return list;
    }

    private ArrayList<Sale> getRest(String value) {
        //json parsing
        //saleList.clear();
        Log.d("TEST","in getRest");
        Log.d("TEST", value);
        try {
            JSONArray jsonArray = new JSONArray(value);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject product = jsonArray.getJSONObject(i);
                int franchiseId = product.getInt("franchiseId");
                String name = product.getString("name");
                String type = product.getString("type");
                JSONObject info = product.getJSONObject("product");
                int price = info.getInt("price");

                saleList.add(new Sale(franchiseId,type,name,price,""));
                Log.d("TEST", saleList.get(i).toString());
            }
        } catch(Exception e){

        }


        return saleList;
    }
}
