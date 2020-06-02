package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

public class LoginActivity extends Activity {

    private ImageButton img_btn_login_kakao, img_btn_login_google, img_btn_login_naver;
    private ImageButton btn_login, btn_signup;

    private TextView txt_login_forget_password;
    private MyClickListener myClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        btn_login = findViewById(R.id.btn_login);
        img_btn_login_google = findViewById(R.id.img_btn_login_google);
        img_btn_login_kakao = findViewById(R.id.img_btn_login_kakao);
        img_btn_login_naver = findViewById(R.id.img_btn_login_naver);
        btn_signup = findViewById(R.id.btn_signup);
        txt_login_forget_password = findViewById(R.id.txt_login_forget_password);

        myClickListener = new MyClickListener();

        btn_login.setOnClickListener(myClickListener);
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
