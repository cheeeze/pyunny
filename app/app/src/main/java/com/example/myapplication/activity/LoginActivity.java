package com.example.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends Activity  {

    private ImageButton img_btn_login_kakao, img_btn_login_google, img_btn_login_naver;
    private ImageButton btn_login, btn_signup;
    private EditText edt_login_email, edt_login_password;
    private TextView txt_login_forget_password;
    private MyClickListener myClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        btn_login = (ImageButton) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                //new restMethod().execute();
            }
        });

    }

    public class restMethod extends AsyncTask<String, Void, String> {

        String result;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url;
                String urlString = "";
                JSONObject json = new JSONObject();
                json.put("email",edt_login_email.getText());
                json.put("password", edt_login_password.getText());
                String body = json.toString();

                url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept","application/json");
                OutputStream os = conn.getOutputStream();
                os.write(body.getBytes());
                os.flush();

                conn.connect();

                int responseStatusCode = conn.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == conn.HTTP_OK) {
                    inputStream = conn.getInputStream();
                }else{
                    inputStream = conn.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader br = new BufferedReader(inputStreamReader);
                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

    private void init(){
        //btn_login = findViewById(R.id.btn_login);
        img_btn_login_google = findViewById(R.id.img_btn_login_google);
        img_btn_login_kakao = findViewById(R.id.img_btn_login_kakao);
        img_btn_login_naver = findViewById(R.id.img_btn_login_naver);
        btn_signup = findViewById(R.id.btn_signup);
        txt_login_forget_password = findViewById(R.id.txt_login_forget_password);

        myClickListener = new MyClickListener();

        //btn_login.setOnClickListener(myClickListener);
        img_btn_login_google.setOnClickListener(myClickListener);
        img_btn_login_kakao.setOnClickListener(myClickListener);
        img_btn_login_naver.setOnClickListener(myClickListener);
        btn_signup.setOnClickListener(myClickListener);
        txt_login_forget_password.setOnClickListener(myClickListener);
    }
    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i;
            switch (v.getId()){
                case R.id.btn_login:
                    i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.img_btn_login_kakao:
                    i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.img_btn_login_google:
                    i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.img_btn_login_naver:
                    i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                    break;
                case R.id.btn_signup:
                    i = new Intent(getApplicationContext(),SignUpActivity.class);
                    startActivity(i);
                    break;
                case R.id.txt_login_forget_password:
                    i = new Intent(getApplicationContext(),ForgetPasswordActivity.class);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    }

}
