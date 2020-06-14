package com.example.myapplication.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.activity.ProductDetailActivity;
import com.example.myapplication.adapter.MainPagerAdapter;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.customView.CustomDialog;
import com.example.myapplication.vo.Sale;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentMain extends Fragment implements View.OnClickListener{


    private Context mContext;


    private ImageButton btn_main_all,btn_main_gs,btn_main_cu,btn_main_seven,btn_main_emart,btn_main_ministop;

    private ArrayList<Sale> saleList = new ArrayList<>();

    private Button btn_main_sale;

    private Button btn_main_product;

    private int btnConvIdx = 0;
    private int btnSaleProductIdx = 0;

    private RecyclerView recyclerView;
    private MainSaleAdapter mainSaleAdapter;

    private boolean isProductDetailEnd;


    private Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        new restMethod().execute();

        mContext = view.getContext();

        init(view);

        return view;
    }

    public class restMethod extends AsyncTask<String, Void, String> {

        String result;
        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.d("RESULT", "in DoinBackground");
                //btnConvIdx 편의점별 id
                //btnSaleProductIdx 0:세일품목 1:전체품목
                URL url;
                String urlString = "";
                switch (btnSaleProductIdx){
                    case 0:
                        urlString = "http://k02d1021.p.ssafy.io:8080/api/sale/search/?keyword=&franchise=";
                        break;
                    case 1:
                        urlString = "http://k02d1021.p.ssafy.io:8080/api/product/search/?keyword=&franchise=";
                        break;
                }

                switch (btnConvIdx){
                    case 0:
                        urlString += "646,682,936";
                        break;
                    case 1: //gs 646
                        urlString += "646";
                        break;
                    case 2: //cu 682
                        urlString += "682";
                        break;
                    case 3: //7/11
                        break;
                    case 4: //emart 936
                        urlString += "936";
                        break;
                    case 5: //ministop
                        break;
                }

                Log.d("RESULT", urlString);
                url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(3000);
                conn.setConnectTimeout(3000);
                conn.setDoInput(true);
                conn.connect();

                int responseStatusCode = conn.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == conn.HTTP_OK) {
                    inputStream = conn.getInputStream();
                }else{
                    inputStream = conn.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                JsonReader jsonReader = new JsonReader(inputStreamReader);
                //jsonReader.beginObject();
                jsonReader.beginArray();
                saleList.clear();
                while(jsonReader.hasNext()){
                    int id;
                    String name = "";
                    int franchiseId = 0;
                    String type ="";
                    int price = 0;
                    String image = "";

                    jsonReader.beginObject();
                    while(jsonReader.hasNext()){
                        String title = jsonReader.nextName();
                        if (title.equals("id")) {
                            id = jsonReader.nextInt();
                        } else if (title.equals("name")) {
                            name = jsonReader.nextString();
                        } else if (title.equals("type")) {
                            type = jsonReader.nextString();
                        } else if (title.equals("franchiseId")) {
                            franchiseId = jsonReader.nextInt();
                        } else if (title.equals("product")) {
                            jsonReader.beginObject();
                            while(jsonReader.hasNext()){
                                title = jsonReader.nextName();
                                if (title.equals("price")) {
                                    price = jsonReader.nextInt();
                                } else if (title.equals("image")) {
                                    image = jsonReader.nextString();
                                } else {
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                        } else if (title.equals("price")) {
                            price = jsonReader.nextInt();
                        } else if (title.equals("image")) {
                            image = jsonReader.nextString();
                        }else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    saleList.add(new Sale(franchiseId,type,name,price,image));
                }
                jsonReader.endArray();
                jsonReader.close();


                //check용 br string 출력
//                BufferedReader br = new BufferedReader(inputStreamReader);
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while((line=br.readLine())!=null) {
//                    sb.append(line);
//                }
//                br.close();
                conn.disconnect();
                //result = sb.toString();
                //Log.d("RESULT", result);

            } catch (Exception e) {
                Log.d("ERROR", e.toString());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("RESULT", saleList.toString());
            //super.onPostExecute(s);
            setBorderToButton();
            //getSaleList();
            mainSaleAdapter = new MainSaleAdapter(mContext,saleList);
            recyclerView.setAdapter(mainSaleAdapter);
            Log.d("RESULT", "onPostExecute");
        }
    }

    private void init(View view){
        btn_main_all = view.findViewById(R.id.btn_main_all);
        btn_main_gs = view.findViewById(R.id.btn_main_gs);
        btn_main_cu = view.findViewById(R.id.btn_main_cu);
        btn_main_seven = view.findViewById(R.id.btn_main_seven);
        btn_main_emart = view.findViewById(R.id.btn_main_emart);
        btn_main_ministop = view.findViewById(R.id.btn_main_ministop);

        btn_main_sale = view.findViewById(R.id.btn_main_sale);
        btn_main_product = view.findViewById(R.id.btn_main_product);

        recyclerView = view.findViewById(R.id.recyclerView_event);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        new restMethod().execute();
//        mainSaleAdapter = new MainSaleAdapter(mContext,saleList);
//
//        recyclerView.setAdapter(mainSaleAdapter);
//        if(btnConvIdx==0&&btnSaleProductIdx==0)
//            getSaleList();
//        setBorderToButton();


        btn_main_all.setOnClickListener(this);
        btn_main_gs.setOnClickListener(this);
        btn_main_cu.setOnClickListener(this);
        btn_main_seven.setOnClickListener(this);
        btn_main_emart.setOnClickListener(this);
        btn_main_ministop.setOnClickListener(this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                intent = new Intent(mContext, ProductDetailActivity.class);
//                isProductDetailEnd=false;
//                Log.d("TTTTT",saleList.get(position).getProduct_id()+"");
//                getProductDetail(saleList.get(position).getProduct_id());
//                while (!isProductDetailEnd){
//                    try {
//
//                        Thread.sleep(500);
//                    }catch (Exception e){
//
//                    }
//                }
                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        btn_main_sale.setOnClickListener(this);
        btn_main_product.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_all:
                btnConvIdx=0;
                btnSaleProductIdx=0;
                //new restMethod().execute();
                //setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_gs:
                btnConvIdx=1;
                btnSaleProductIdx=0;
                //setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_cu:
                btnConvIdx=2;
                btnSaleProductIdx=0;
                //setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_seven:
                btnConvIdx=3;
                btnSaleProductIdx=0;
                //setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_emart:
                btnConvIdx=4;
                btnSaleProductIdx=0;
                //setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_ministop:
                btnConvIdx=5;
                btnSaleProductIdx=0;
               // setBorderToButton();
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_sale:
                btnSaleProductIdx=0;
                //getSaleList();
                //refresh();
                break;
            case R.id.btn_main_product:
                btnSaleProductIdx=1;
                //getProductList();
                //refresh();
                break;
        }
        new restMethod().execute();
    }

    private void setBorderToButton(){
        switch (btnConvIdx){
            case 0:
                btn_main_all.setBackgroundResource(R.drawable.border_main_conv);
                btn_main_gs.setBackgroundResource(R.drawable.border_unclick);
                btn_main_cu.setBackgroundResource(R.drawable.border_unclick);
                btn_main_seven.setBackgroundResource(R.drawable.border_unclick);
                btn_main_emart.setBackgroundResource(R.drawable.border_unclick);
                btn_main_ministop.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 1:
                btn_main_all.setBackgroundResource(R.drawable.border_unclick);
                btn_main_gs.setBackgroundResource(R.drawable.border_main_conv);
                btn_main_cu.setBackgroundResource(R.drawable.border_unclick);
                btn_main_seven.setBackgroundResource(R.drawable.border_unclick);
                btn_main_emart.setBackgroundResource(R.drawable.border_unclick);
                btn_main_ministop.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 2:
                btn_main_all.setBackgroundResource(R.drawable.border_unclick);
                btn_main_gs.setBackgroundResource(R.drawable.border_unclick);
                btn_main_cu.setBackgroundResource(R.drawable.border_main_conv);
                btn_main_seven.setBackgroundResource(R.drawable.border_unclick);
                btn_main_emart.setBackgroundResource(R.drawable.border_unclick);
                btn_main_ministop.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 3:
                btn_main_all.setBackgroundResource(R.drawable.border_unclick);
                btn_main_gs.setBackgroundResource(R.drawable.border_unclick);
                btn_main_cu.setBackgroundResource(R.drawable.border_unclick);
                btn_main_seven.setBackgroundResource(R.drawable.border_main_conv);
                btn_main_emart.setBackgroundResource(R.drawable.border_unclick);
                btn_main_ministop.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 4:
                btn_main_all.setBackgroundResource(R.drawable.border_unclick);
                btn_main_gs.setBackgroundResource(R.drawable.border_unclick);
                btn_main_cu.setBackgroundResource(R.drawable.border_unclick);
                btn_main_seven.setBackgroundResource(R.drawable.border_unclick);
                btn_main_emart.setBackgroundResource(R.drawable.border_main_conv);
                btn_main_ministop.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 5:
                btn_main_all.setBackgroundResource(R.drawable.border_unclick);
                btn_main_gs.setBackgroundResource(R.drawable.border_unclick);
                btn_main_cu.setBackgroundResource(R.drawable.border_unclick);
                btn_main_seven.setBackgroundResource(R.drawable.border_unclick);
                btn_main_emart.setBackgroundResource(R.drawable.border_unclick);
                btn_main_ministop.setBackgroundResource(R.drawable.border_main_conv);
                break;
        }

        switch (btnSaleProductIdx){
            case 0:
                btn_main_sale.setBackgroundResource(R.drawable.border_main_sale_product);
                btn_main_product.setBackgroundResource(R.drawable.border_unclick);
                break;
            case 1:
                btn_main_sale.setBackgroundResource(R.drawable.border_unclick);
                btn_main_product.setBackgroundResource(R.drawable.border_main_sale_product);
                break;
        }
    }


    private void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
    }

//    할인정보 목록 생성
    private ArrayList<Sale> getSaleList(){
        saleList.clear();
        switch (btnConvIdx){
//            전체
            case 0:
                saleList.add(new Sale(682,"1+1","농심)멘토스레인보우",800,"http://bgf-cu.xcache.kinxcdn.com/product/6921211104292.jpg"));
                saleList.add(new Sale(682,"1+1","롯)의성마늘프랑크2입",3300,"http://bgf-cu.xcache.kinxcdn.com/product/8801123308910.jpg"));
                saleList.add(new Sale(682,"1+1","롯데)트로피애플캔355ml",1200,"http://bgf-cu.xcache.kinxcdn.com/product/8801056035570.jpg"));
                saleList.add(new Sale(682,"1+1","동아)데미소다피치캔250ml",1200,"http://cdn2.bgfretail.com/bgfbrand/files/product/95C1BC12E8A74F62A04B3A4C1A3F9C0C.jpg"));
                saleList.add(new Sale(682,"1+1","동아)데미소다애플캔250ml",1200,"http://cdn2.bgfretail.com/bgfbrand/files/product/B9054EE9A43448F58142E54DFA667DC0.jpg"));
                saleList.add(new Sale(1,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
                saleList.add(new Sale(3,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
                saleList.add(new Sale(646,"1+1","동원)상상바삭디핑칩고소한맛",3000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801047184126_001.jpg"));
                saleList.add(new Sale(646,"1+1","동원)상상바삭디핑칩매콤한맛",3000,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801047184133_001.jpg"));
                saleList.add(new Sale(646,"1+1","해태)과일사이다딸기키위380ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801105916157_002.jpg"));
                saleList.add(new Sale(646,"1+1","해태)과일사이다파인애플380ML",1500,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8801105916133_002.jpg"));
                saleList.add(new Sale(646,"1+1","한끼불고기150G(달콤한맛)",4900,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809152414794_001.jpg"));
                saleList.add(new Sale(646,"1+1","한끼불고기150G(매콤한맛)",4900,"http://gs25appimg.gsretail.com/imgsvr/item/GD_8809152414800_001.jpg"));
                break;
//                gs
            case 1:
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
                break;
//                cu
            case 2:
                break;
//                seven
            case 3:
                break;
//                emart
            case 4:
                break;
//                mini
            case 5:
                break;

        }
        //int franchise_id, String type, String product_name, int product_price, String product_image
        return saleList;
    }
//    상품정보 목록
    private ArrayList<Sale> getProductList(){
        //int franchise_id, String type, String product_name, int product_price, String product_image
        saleList.clear();
        switch (btnConvIdx){
            case 0:
                saleList.add(new Sale(682,null,"HEYROO콘치즈그라탕",3800,"http://bgf-cu.xcache.kinxcdn.com/product/8805684007732.jpg"));
                saleList.add(new Sale(682,null,"튀김)핫도그",1000,"http://cdn2.bgfretail.com/bgfbrand/files/product/227743CDDAC242C59668F4E1087B7842.jpg"));
                saleList.add(new Sale(682,null,"HEYROO뻥이요스낵1200",1200,"http://bgf-cu.xcache.kinxcdn.com/product/8801151112039.jpg"));
                saleList.add(new Sale(682,null,"20델)베리베리에이드",700,"http://bgf-cu.xcache.kinxcdn.com/product/8809184806192.jpg"));
                saleList.add(new Sale(682,null,"GET허쉬티라미수라떼30g",600,"http://cdn2.bgfretail.com/bgfbrand/files/product/7EA7380FC81B4ACF92F9007FA62B4D21.jpg"));
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

                break;
            case 1:
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;

        }

        return saleList;
    }


    public interface ClickListener{
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            this.clickListener=clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null&&clickListener!=null){
                        clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null&&clickListener!=null&&gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void getProductDetail(final int productId){
        Thread thread = new Thread(new Runnable() {
            String result;
            @Override
            public void run() {
                try{
                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/product/"+productId);
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
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null) {
                        sb.append(line);
                    }
                    bufferedReader.close();


                    conn.disconnect();
                    result = sb.toString();
                    Log.d("productDetail",result);
                    JSONObject product = new JSONObject(result);
                    int id = product.getInt("id");
                    String name = product.getString("name");
                    int franchiseId = product.getInt("franchiseId");
                    int price = product.getInt("price");
                    String category = product.getString("category");
                    String description = product.getString("description");
                    String image = product.getString("image");
                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    intent.putExtra("franchiseId",franchiseId);
                    intent.putExtra("price",price);
                    intent.putExtra("category",category);
                    intent.putExtra("description",description);
                    intent.putExtra("image",image);
                    isProductDetailEnd=true;

                } catch(Exception e){
                    result = e.toString();
                    Log.d("ERROR", e.toString());
                }


            }
        });
        thread.start();
    }

}
