package com.example.myapplication.customView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activity.ProductDetailActivity;
import com.example.myapplication.activity.RecipeDetailActivity;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.adapter.MapProductAdapter;
import com.example.myapplication.fragment.recipe.FragmentRecipe;
import com.example.myapplication.vo.Sale;
import com.example.myapplication.vo.Store;
import com.example.myapplication.vo.StoreProduct;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomDialog {
    private Context mContext;

    public CustomDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void callFunction(final Store store){
        final Dialog dig = new Dialog(mContext);

        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.marker_custom_dialog);
        dig.show();

        final ImageView image_dialog_franchise = dig.findViewById(R.id.image_dialog_franchise);
        final TextView txt_dialog_address = dig.findViewById(R.id.txt_dialog_address);
        final TextView txt_dialog_tell = dig.findViewById(R.id.txt_dialog_tell);
        final RecyclerView recyclerView = dig.findViewById(R.id.recyclerView_dialog);
        final Button btn_map_marker_dialog_atm = dig.findViewById(R.id.btn_map_marker_dialog_atm);
        final Button btn_map_marker_dialog_lottery = dig.findViewById(R.id.btn_map_marker_dialog_lottery);
        final Button btn_map_marker_dialog_delivery = dig.findViewById(R.id.btn_map_marker_dialog_delivery);
        final Button btn_map_marker_dialog_fulltime = dig.findViewById(R.id.btn_map_marker_dialog_fulltime);
        switch (store.getFranchise_id()){
            case 0:
            case 646:
                image_dialog_franchise.setImageResource(R.drawable.gs25);
                break;
            case 1:
            case 682:
                image_dialog_franchise.setImageResource(R.drawable.cu);
                break;
            case 2:
            case 970:
                image_dialog_franchise.setImageResource(R.drawable.seven);
                break;
            case 3:
            case 936:
                image_dialog_franchise.setImageResource(R.drawable.emart);
                break;
            case  4:
            case 756:
                image_dialog_franchise.setImageResource(R.drawable.ministop);
                break;
            default:
                image_dialog_franchise.setImageResource(R.drawable.marker_default);
                break;
        }
        if(store.getIsatm()==1){
            btn_map_marker_dialog_atm.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
//            btn_map_marker_dialog_atm.setBackgroundResource(R.drawable.border);
        }
        if(store.getIslottery()==1){
            btn_map_marker_dialog_lottery.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
//            btn_map_marker_dialog_lottery.setBackgroundResource(R.drawable.border);
        }
        if(store.getIsdelivery()==1){
            btn_map_marker_dialog_delivery.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
//            btn_map_marker_dialog_delivery.setBackgroundResource(R.drawable.border);
        }
        if(store.getIsfulltime()==1){
            btn_map_marker_dialog_fulltime.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
//            btn_map_marker_dialog_fulltime.setBackgroundResource(R.drawable.border);
        }

        txt_dialog_address.setText("주소: "+store.getAddress());
        txt_dialog_tell.setText("전화번호: "+ store.getTel());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        storeProductList=new ArrayList<>();
        getList(store);
        isEnd=false;
        while (!isEnd){
            try{
                Thread.sleep(500);
            }catch (Exception e){

            }
        }

        mapProductAdapter = new MapProductAdapter(mContext,storeProductList);
        recyclerView.setAdapter(mapProductAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
//                intent = new Intent(mContext, ProductDetailActivity.class);
//                isProductDetailEnd=false;
//                getProductDetail(storeProductList.get(position).getProductId());
//                while (!isProductDetailEnd){
//                    try {
//                        Thread.sleep(500);
//                    }catch (Exception e){
//                        Log.d("Errorerror",e.toString());
//                    }
//                }
//                mContext.startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private ArrayList<StoreProduct> storeProductList;
    private MapProductAdapter mapProductAdapter;
    private boolean isEnd;

    private Intent intent;

    private boolean isProductDetailEnd;
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
                    Log.d("testtesttest",result);
                    JSONObject product = new JSONObject(result);
                    int id = product.getInt("id");
                    String name = product.getString("name");
                    int franchiseId = product.getInt("franchiseId");
                    int price = product.getInt("price");
                    String category = product.getString("category");
                    String description = product.getString("description");
                    String image = product.getString("image");

                    intent.putExtra("id",id);
                    Log.d("productDetail",id+" "+name+" "+franchiseId+" "+price+" "+category+" "+description+" "+image);
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
        isProductDetailEnd=true;
    }


    private void getList(final Store store){
        Log.d("제고",store.getId()+"");

        Thread thread = new Thread(new Runnable() {
            String result;
            @Override
            public void run() {
                try{
                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/store_product/"+store.getId());
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
                    Log.d("storeList",result);
                    storeProductList = getRest(result);
                } catch(Exception e){
                    result = e.toString();
                    Log.d("ERROR", e.toString());
                }


            }
        });
        thread.start();



    }

    private ArrayList<StoreProduct> getRest(String value) {
        //json parsing
        //saleList.clear();
        isEnd=false;
        Log.d("TEST","in getRest");
        Log.d("TEST", value);
        try {
            JSONArray jsonArray = new JSONArray(value);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject storeProduct = jsonArray.getJSONObject(i);


                int id = storeProduct.getInt("id");
                int storeId = storeProduct.getInt("storeId");
                int productId = storeProduct.getInt("productId");
                String name = storeProduct.getString("name");
                String image = storeProduct.getString("image");
                String description = storeProduct.getString("description");
                int price = storeProduct.getInt("price");
                int stockAmount = storeProduct.getInt("stockAmount");

                storeProductList.add(new StoreProduct(id,storeId,productId,name,image,description,price,stockAmount));
                Log.d("storeProductList",storeProductList.get(i).toString());
            }
        } catch(Exception e){

        }

        isEnd=true;
        return storeProductList;
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
}
