package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.auth.SaveSharedPreference;

import org.json.JSONObject;

import java.io.InputStream;
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
                Log.d("TEST", edt_login_email.getText().toString());
                checkLogin();

            }
        });

    }

    private void checkLogin() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject json = new JSONObject();
                    json.put("email",edt_login_email.getText());
                    json.put("password", edt_login_password.getText());
                    String body = json.toString();

                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/user/applogin");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept","application/json");
                    conn.connect();

                    OutputStream os = conn.getOutputStream();
                    os.write(body.getBytes());
                    os.flush();

                    int responseStatusCode = conn.getResponseCode();
                    Log.d("TEST", responseStatusCode+"");
                    //Looper.prepare();
                    //InputStream inputStream;
                    if(responseStatusCode == conn.HTTP_OK) {
                        //login 성공
                        //Toast.makeText(LoginActivity.this, "login성공", Toast.LENGTH_SHORT).show();
                        Log.d("TEST", "login");
                        SaveSharedPreference.setNickname(LoginActivity.this,edt_login_email.getText().toString());
                    }else{
                        Log.d("TEST", "loginfail");
                        //Toast.makeText(LoginActivity.this, "login실패", Toast.LENGTH_SHORT).show();
                    }
                    //Looper.loop();

                    conn.disconnect();

                    //임시로 모두 메인으로 넘어가게 작성함
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch(Exception e) {
                    Log.d("REST ERRER",e.toString());
                }
            }
        });
        thread.start();
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
