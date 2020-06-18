package ssafy.d102.pyunny.fragment.main;

import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.d102.pyunny.R;
import ssafy.d102.pyunny.adapter.MainAllAdapter;
import ssafy.d102.pyunny.adapter.MainSaleAdapter;
import ssafy.d102.pyunny.vo.Sale;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
    private MainAllAdapter mainAllAdapter;

    private ImageView image_main_nodata;

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
                        urlString += "1";
                        break;
                    case 4: //emart 936
                        urlString += "936";
                        break;
                    case 5: //ministop
                        urlString += "1";
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
                jsonReader.beginArray();
                saleList.clear();
                while(jsonReader.hasNext()){
                    int id = 0;
                    String name = "";
                    int franchiseId = 0;
                    String type ="";
                    int price = 0;
                    String image = "";
                    int productId = 0;
                    String dumName = "";
                    String dumImage = "";
                    int dumPrice = 0;

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
                                } else if (title.equals("image") && jsonReader.peek() != JsonToken.NULL) {
                                    image = jsonReader.nextString();
                                } else if(title.equals("id")){
                                    productId = jsonReader.nextInt();
                                } else {
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                        } else if (title.equals("price")) {
                            price = jsonReader.nextInt();
                        } else if (title.equals("image") && jsonReader.peek() != JsonToken.NULL) {
                            image = jsonReader.nextString();
                        } else if (title.equals("dumImage") && jsonReader.peek() != JsonToken.NULL) {
                            dumImage = jsonReader.nextString();
                        } else if (title.equals("dumName") && jsonReader.peek() != JsonToken.NULL) {
                            dumName = jsonReader.nextString();
                        } else if (title.equals("dumPrice") && jsonReader.peek() != JsonToken.NULL) {
                            dumPrice = jsonReader.nextInt();
                        } else {
                            jsonReader.skipValue();
                        }
                    }
                    jsonReader.endObject();
                    switch (btnSaleProductIdx){
                        case 0: //세일
                            saleList.add(new Sale(id,productId,name,franchiseId,type,dumImage,dumName,dumPrice,name,price,image));
                            break;
                        case 1: //전체product
                            saleList.add(new Sale(id,id,name,franchiseId,type,dumImage,dumName,dumPrice,name,price,image));
                            break;
                    }

                }
                jsonReader.endArray();
                jsonReader.close();


                conn.disconnect();

            } catch (Exception e) {
                Log.d("ERROR", e.toString());
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            setBorderToButton();
            //getSaleList();
            switch(btnSaleProductIdx) {
                case 0: //세일
                    mainSaleAdapter = new MainSaleAdapter(mContext,saleList);
                    recyclerView.setAdapter(mainSaleAdapter);
                    break;
                case 1: //프로덕트
                    mainAllAdapter = new MainAllAdapter(mContext,saleList);
                    recyclerView.setAdapter(mainAllAdapter);
                    break;
            }

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

        image_main_nodata = view.findViewById(R.id.image_main_nodata);

        recyclerView = view.findViewById(R.id.recyclerView_event);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        new restMethod().execute();


        btn_main_all.setOnClickListener(this);
        btn_main_gs.setOnClickListener(this);
        btn_main_cu.setOnClickListener(this);
        btn_main_seven.setOnClickListener(this);
        btn_main_emart.setOnClickListener(this);
        btn_main_ministop.setOnClickListener(this);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                intent = new Intent(mContext, ProductDetailActivity.class);
//                intent.putExtra("productId",saleList.get(position).getProduct_id());
//                mContext.startActivity(intent);
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
                image_main_nodata.setImageResource(0);
                break;
            case R.id.btn_main_gs:
                btnConvIdx=1;
                btnSaleProductIdx=0;
                image_main_nodata.setImageResource(0);
                break;
            case R.id.btn_main_cu:
                btnConvIdx=2;
                btnSaleProductIdx=0;
                image_main_nodata.setImageResource(0);
                break;
            case R.id.btn_main_seven:
                btnConvIdx=3;
                btnSaleProductIdx=0;
                image_main_nodata.setImageResource(R.drawable.none_main);
                break;
            case R.id.btn_main_emart:
                btnConvIdx=4;
                btnSaleProductIdx=0;
                image_main_nodata.setImageResource(0);
                break;
            case R.id.btn_main_ministop:
                btnConvIdx=5;
                btnSaleProductIdx=0;
                image_main_nodata.setImageResource(R.drawable.none_main);
                break;
            case R.id.btn_main_sale:
                btnSaleProductIdx=0;
                break;
            case R.id.btn_main_product:
                btnSaleProductIdx=1;
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

//    private void getProductDetail(final int productId){
//        Thread thread = new Thread(new Runnable() {
//            String result;
//            @Override
//            public void run() {
//                try{
//                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/product/"+productId);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.setReadTimeout(3000);
//                    conn.setConnectTimeout(3000);
//                    //conn.setDoOutput(true); //이거  있으면 무조건 POST로 메소드 변경됨!! 주의!
//                    conn.setDoInput(true);
//
//                    conn.setUseCaches(false);
//                    conn.connect();
//
//                    int responseStatusCode = conn.getResponseCode();
//                    Log.i("CHECK", "thread run");
//                    InputStream inputStream;
//                    if(responseStatusCode == conn.HTTP_OK) {
//                        inputStream = conn.getInputStream();
//                    }else{
//                        inputStream = conn.getErrorStream();
//                    }
//                    Log.d("REQEUSTMETHOD",conn.getRequestMethod());
//
//                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                    StringBuilder sb = new StringBuilder();
//                    String line;
//                    while((line=bufferedReader.readLine())!=null) {
//                        sb.append(line);
//                    }
//                    bufferedReader.close();
//
//
//                    conn.disconnect();
//                    result = sb.toString();
//                    Log.d("productDetail",result);
//                    JSONObject product = new JSONObject(result);
//                    int id = product.getInt("id");
//                    String name = product.getString("name");
//                    int franchiseId = product.getInt("franchiseId");
//                    int price = product.getInt("price");
//                    String category = product.getString("category");
//                    String description = product.getString("description");
//                    String image = product.getString("image");
//                    intent.putExtra("id",id);
//                    intent.putExtra("name",name);
//                    intent.putExtra("franchiseId",franchiseId);
//                    intent.putExtra("price",price);
//                    intent.putExtra("category",category);
//                    intent.putExtra("description",description);
//                    intent.putExtra("image",image);
//
//                } catch(Exception e){
//                    result = e.toString();
//                    Log.d("ERROR", e.toString());
//                }
//
//
//            }
//        });
//        thread.start();
//    }

}
