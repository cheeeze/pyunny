package com.example.myapplication.activity;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class ProductDetailActivity extends Activity {

    private ImageView image_productdetail_product, image_productdetail_conv;
    private TextView txt_productdetail_name, txt_productdetail_description, txt_productdetail_price, txt_productdetail_rebuy;
    private ImageButton btn_productdetail_add_product,btn_productdetail_rebuy_yes,btn_productdetail_rebuy_no;
    private ProgressBar progressbar_productdetail;
    private EditText edt_productdetail_reply;
    private Button btn_productdetail_add_reply;
    private RecyclerView recyclerView_productdetail_comment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();

    }

    private void init(){
        image_productdetail_product = findViewById(R.id.image_productdetail_product);
        image_productdetail_conv = findViewById(R.id.image_productdetail_conv);
        txt_productdetail_name = findViewById(R.id.txt_productdetail_name);
        txt_productdetail_description = findViewById(R.id.txt_productdetail_description);
        txt_productdetail_price = findViewById(R.id.txt_productdetail_price);
        txt_productdetail_rebuy = findViewById(R.id.txt_productdetail_rebuy);
        btn_productdetail_add_product = findViewById(R.id.btn_productdetail_add_product);
        btn_productdetail_rebuy_yes = findViewById(R.id.btn_productdetail_rebuy_yes);
        btn_productdetail_rebuy_no = findViewById(R.id.btn_productdetail_rebuy_no);
        progressbar_productdetail = findViewById(R.id.progressbar_productdetail);
        edt_productdetail_reply = findViewById(R.id.edt_productdetail_reply);
        btn_productdetail_add_reply = findViewById(R.id.btn_productdetail_add_reply);
        recyclerView_productdetail_comment = findViewById(R.id.recyclerView_productdetail_comment);

        Intent i = getIntent();
        int productId = i.getIntExtra("productId",3);

        new RestApiTask("http://k02d1021.p.ssafy.io:8080/api/product/"+productId).execute();


    }

    private class RestApiTask extends AsyncTask<Integer, Void, String> {
        private String mURL;
        private String result;
        private int id;
        private String name;
        private int franchiseId;
        private int price;
        private String category;
        private String description;
        private String image;
        public RestApiTask(String mURL) {
            this.mURL = mURL;
        }

        @Override
        protected String doInBackground(Integer... integers) {

            try{
                URL url = new URL(mURL);
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
                id = product.getInt("id");
                name = product.getString("name");
                franchiseId = product.getInt("franchiseId");
                price = product.getInt("price");
                category = product.getString("category");
                description = product.getString("description");
                image = product.getString("image");

                Log.d("productDetail",id+" "+name+" "+franchiseId+" "+price+" "+category+" "+description+" "+image);

            } catch(Exception e){
                result = e.toString();
                Log.d("ERROR", e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Glide.with(getApplicationContext()).load(image).override(2000,2000).error(R.drawable.defaultproduct).into(image_productdetail_product);
            switch (franchiseId){
                case 0: case 646:
                    image_productdetail_conv.setImageResource(R.drawable.gs25);
                    break;
                case 1: case 682:
                    image_productdetail_conv.setImageResource(R.drawable.cu);
                    break;
                case 2: case 970:
                    image_productdetail_conv.setImageResource(R.drawable.seven);
                    break;
                case 3: case 936:
                    image_productdetail_conv.setImageResource(R.drawable.emart);
                    break;
                case  4: case 756:
                    image_productdetail_conv.setImageResource(R.drawable.ministop);
                    break;
                default:
                    image_productdetail_conv.setImageResource(R.drawable.marker_default);
                    break;
            }
            txt_productdetail_name.setText(name);
            txt_productdetail_description.setText(description);
            DecimalFormat format = new DecimalFormat("###,###");
            txt_productdetail_price.setText(format.format(price)+"원");
        }
    }

}
