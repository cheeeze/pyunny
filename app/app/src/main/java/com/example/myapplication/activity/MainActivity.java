package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.customView.CustomActionBar;
import com.example.myapplication.fragment.main.FragmentMain;
import com.example.myapplication.fragment.map.FragmentMap;
import com.example.myapplication.fragment.mypage.FragmentMypage;
import com.example.myapplication.fragment.recipe.FragmentRecipe;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //footer fragment
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentRecipe fragmentRecipe = new FragmentRecipe();
    private FragmentMap fragmentMap = new FragmentMap();
    private FragmentMypage fragmentMypage = new FragmentMypage();

    private BottomNavigationView bottomNavigationView;

    private static int REQUEST_ACCESS_FINE_LOCATION = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeFooter();
        setActionBar();


        permCheck();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
//          //권한 획득
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
}
