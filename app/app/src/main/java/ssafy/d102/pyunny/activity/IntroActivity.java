package ssafy.d102.pyunny.activity;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.d102.pyunny.R;
import ssafy.d102.pyunny.thread.IntroThread;

public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();
    }

    private void init(){
        IntroThread introThread = new IntroThread(handler);
        introThread.start();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what==1){
                Intent intent = new Intent(IntroActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
