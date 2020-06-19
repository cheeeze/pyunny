package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.customView.CustomActionBar;
import com.example.myapplication.customView.CustomBarcodeDialog;
import com.example.myapplication.fragment.main.FragmentMain;
import com.example.myapplication.fragment.map.FragmentMap;
import com.example.myapplication.fragment.mypage.FragmentMypage;
import com.example.myapplication.fragment.recipe.FragmentRecipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentRecipe fragmentRecipe = new FragmentRecipe();
    private FragmentMap fragmentMap = new FragmentMap();
    private FragmentMypage fragmentMypage = new FragmentMypage();

    private BottomNavigationView bottomNavigationView;

    private static int REQUEST_ACCESS_FINE_LOCATION = 1000;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    public boolean isShowDialog;

    private CustomBarcodeDialog customBarcodeDialog;

    private SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeFooter();
        setActionBar();
        permCheck();
        sf = getSharedPreferences("barcode", Context.MODE_PRIVATE);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        isShowDialog = false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //권한 획득
        }else{
            //권한 거부
        }
    }

    private void permCheck(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_ACCESS_FINE_LOCATION);

            }else{

            }
        }
    }

    private void setActionBar(){
        // 커스텀 엑션바 적용
        CustomActionBar customActionBar = new CustomActionBar(this,getSupportActionBar());
        customActionBar.setActionBar();
    }


    void makeFooter(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (menuItem.getItemId()){
                case R.id.mainItem:
                    transaction.replace(R.id.frameLayout,fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.recipeItem:
                    transaction.replace(R.id.frameLayout,fragmentRecipe).commitAllowingStateLoss();
                    break;
                case R.id.mapItem:
                    transaction.replace(R.id.frameLayout,fragmentMap).commitAllowingStateLoss();
                    break;
                case R.id.mypageItem:
                    transaction.replace(R.id.frameLayout,fragmentMypage).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }

    private long mShakeTome;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private int mShakeCnt;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];

            float gravityX = axisX / SensorManager.GRAVITY_EARTH;
            float gravityY = axisY / SensorManager.GRAVITY_EARTH;
            float gravityZ = axisZ / SensorManager.GRAVITY_EARTH;

            Float f = gravityX*gravityX+gravityY*gravityY+gravityZ*gravityZ;
            double squaredD = Math.sqrt(f.doubleValue());
            float gForce = (float) squaredD;
            if(gForce > SHAKE_THRESHOLD_GRAVITY){
                long currentTime = System.currentTimeMillis();
                if(mShakeTome + SHAKE_SKIP_TIME > currentTime){
                    return;
                }

                mShakeTome = currentTime;
                mShakeCnt++;
                if(sf.getBoolean("isDismiss",true)){
                    SharedPreferences.Editor editor = sf.edit();
                    editor.putBoolean("isDismiss",false);
                    editor.commit();
                    Log.d("isDismiss",sf.getBoolean("isDismiss",true)+"");
                    Toast.makeText(getApplicationContext(),"편의점을 선택해주세요.",Toast.LENGTH_SHORT).show();
                    customBarcodeDialog = new CustomBarcodeDialog(this);
                    customBarcodeDialog.callFunction(this);

                }

            }

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
