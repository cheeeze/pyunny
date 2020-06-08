package com.example.myapplication.fragment.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FragmentMainAllSale extends Fragment {


    private Context mContext;
    private RecyclerView recyclerView;
    private MainSaleAdapter mainSaleAdapter;
    private ArrayList<Sale> saleList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // 자동으로 스레드 유지

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_all_sale, container, false);
        init(view);
        Log.d("TEST", "in fragmemnta create");
        Log.d("TEST", "finish getSaleList");
        return view;
    }


    private void init(View view){

        mContext = view.getContext();
        recyclerView = view.findViewById(R.id.recyclerView_event);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        getSaleList();
        mainSaleAdapter = new MainSaleAdapter(mContext,getList());
        //mainSaleAdapter = new MainSaleAdapter(mContext,saleList);



//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                recyclerView.setAdapter(mainSaleAdapter);
//            }
//        });

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
