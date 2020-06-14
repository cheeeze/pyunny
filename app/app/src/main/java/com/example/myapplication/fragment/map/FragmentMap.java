package com.example.myapplication.fragment.map;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.customView.CustomDialog;
import com.example.myapplication.vo.Sale;
import com.example.myapplication.vo.Store;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentMap extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener, View.OnClickListener {


    private GoogleApiClient mGoogleApiClient = null;
    private GoogleMap mGoogleMap = null;
    private MapView mapView=null;
    private Marker currentMarker = null;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;

    boolean askPermissionOnceAgain = false;
    boolean mRequestingLocationUpdates = false;
    Location mCurrentLocation;
    boolean mMoveMapByUser = true;
    boolean mMoveMapByAPI = true;
    LatLng currentPosition;

    LocationRequest locationRequest = new LocationRequest()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS)
            .setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);


    private List<Store> stores;

    private ImageButton btn_map_get_conv;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_map_get_conv:
                for(Marker m:arrayMarker){
                    m.remove();
                }
                arrayMarker = new ArrayList<>();
                stores = new ArrayList<>();
                isEnd=false;
                getStoreList(mGoogleMap.getCameraPosition().target.latitude,mGoogleMap.getCameraPosition().target.longitude,0.5f,"");
                Log.d("pospos", mGoogleMap.getCameraPosition().target.latitude+" "+mGoogleMap.getCameraPosition().target.longitude);
                while (!isEnd){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("afterRest",stores.size()+"");

                addStores(stores);
                Log.d("afteraddStores",stores.size()+" ");


                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(mRequestingLocationUpdates == false){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION);
                if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                }else{
                    startLocationUpdates();
                    mGoogleMap.setMyLocationEnabled(true);
                }
            }
        }else {
            startLocationUpdates();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        setDefaultLocation();

    }

    @Override
    public void onLocationChanged(Location location) {
        currentPosition = new LatLng(location.getLatitude(),location.getLongitude());

        String markerTitle = getCurrentAddress(currentPosition);
        String markerSnippet = "위도:"+ String.valueOf(location.getLatitude()) + " 경도:"+String.valueOf(location.getLongitude());

        setCurrentLocation(location,markerTitle,markerSnippet);
        mCurrentLocation = location;
    }

    private ArrayList<Marker> arrayMarker;
    private void addStores(List<Store> stores){
        for(Store s: stores){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions
                    .position(new LatLng(s.getLatitude(),s.getLongitude()))
                    .title(s.getStore_name())
                    .snippet(s.getId()+"");
            BitmapDrawable bitmapDrawable = null;
            switch (s.getFranchise_id()){
                case 0:
                case 646:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_gs);
                    break;
                case 1:
                case 682:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_cu);
                    break;
                case 2:
                case 970:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_seven);
                    break;
                case 3:
                case 936:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_emart);
                    break;
                case 4:
                case 756:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_mini);
                    break;
                default:
                    bitmapDrawable = (BitmapDrawable)getResources().getDrawable(R.drawable.marker_default);
                    break;
            }



            Bitmap b = bitmapDrawable.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b,160,160,false);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            Marker marker = mGoogleMap.addMarker(markerOptions);
            arrayMarker.add(marker);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        setDefaultLocation();

        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                mMoveMapByAPI = true;
                return true;
            }
        });


        mGoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                if(mMoveMapByUser == true && mRequestingLocationUpdates)
                    mMoveMapByAPI = false;

                mMoveMapByUser = true;
            }
        });

        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

            }
        });

//        getStoreList(googleMap.getCameraPosition().target.latitude,googleMap.getCameraPosition().target.longitude,0.3f,"");
//
//
//        addStores(stores);
        mGoogleMap.setOnMarkerClickListener(this);


    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        stores = new ArrayList<>();
        arrayMarker = new ArrayList<>();

        btn_map_get_conv = view.findViewById(R.id.btn_map_get_conv);
        btn_map_get_conv.setOnClickListener(this);
        mapView = (MapView)view.findViewById(R.id.map_view);
        mapView.getMapAsync(this);




        return view;
    }

    @Override
    public void onStart() {
        if(mGoogleApiClient != null && mGoogleApiClient.isConnected() == false){
            mGoogleApiClient.connect();
        }
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        if(mRequestingLocationUpdates){
            stopLocationUpdates();
        }

        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }

        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mGoogleApiClient.isConnected()){
            if(!mRequestingLocationUpdates) startLocationUpdates();
        }

        if(askPermissionOnceAgain){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                askPermissionOnceAgain = false;
                checkPermissions();
            }
        }
        mapView.onResume();
    }

    public String getCurrentAddress(LatLng latLng){
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1
            );

        } catch (IOException e) {
            return "지오코더 서비스 사용 불가";
        } catch (IllegalArgumentException ill){
            return "잘못된 GPS 좌표";
        }

        if(addresses == null || addresses.size() == 0){
            return "주소 미발견";
        }else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }
    }

    public boolean checkLocationServicesStatus(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet){
        mMoveMapByUser=false;
        if(currentMarker !=null)    currentMarker.remove();

        LatLng currentLatLng = new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        Log.d("currentLoc",location.getLatitude()+" "+location.getLongitude());





        if(mMoveMapByAPI){

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }

    public void setDefaultLocation(){
        mMoveMapByUser = false;

        LatLng DEFAULT_LOCATION = new LatLng(37.56,126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 여부 확인하세요";

        if(currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,15);
        mGoogleMap.moveCamera(cameraUpdate);
    }


    private void startLocationUpdates(){
        if(!checkLocationServicesStatus()){

            showDialogForLocationServiceSetting();
        }else{
            if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

                return;
            }
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,locationRequest, this);

        mRequestingLocationUpdates = true;

        mGoogleMap.setMyLocationEnabled(true);

    }
    private void stopLocationUpdates(){
    LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
    mRequestingLocationUpdates = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(mapView !=null){
            mapView.onCreate(savedInstanceState);
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions(){
        boolean fineLocationRationale = ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION);

        int hasFineLocationPermission = ContextCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION);

        if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED && fineLocationRationale)
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
        else if(hasFineLocationPermission == PackageManager.PERMISSION_DENIED && !fineLocationRationale) {
            showDialogForPermissionSetting("퍼미션 거부 + Don`t ask again(다시 뭇지 않음) 체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        }else if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED){
            if(mGoogleApiClient.isConnected() == false){
                mGoogleApiClient.connect();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        builder.create().show();

    }

    private void showDialogForPermissionSetting(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getActivity().getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(myAppSettings);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });

        builder.create().show();
    }

    private void showDialogForLocationServiceSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다. \n 위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGpsSettionIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGpsSettionIntent,GPS_ENABLE_REQUEST_CODE);

            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case GPS_ENABLE_REQUEST_CODE:
                if(checkLocationServicesStatus()){
                    if(checkLocationServicesStatus()){
                        if(mGoogleApiClient.isConnected() == false){
                            mGoogleApiClient.connect();
                        }
                            return;
                    }
                }

                break;
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Store store=null;
        for(Store s : stores){
            if(s.getId()==Integer.parseInt(marker.getSnippet())){
                store=s;
                break;
            }
        }

        CustomDialog customDialog = new CustomDialog(getContext());
        customDialog.callFunction(store);

        return true;
    }

//    marker rest

    private boolean isEnd;

    private void getStoreList(final double latitude, final double longitude, final float distance, final String keyword) {
        Log.d("TEST", "in getSaleList()");
        Thread thread = new Thread(new Runnable() {
            String result;
            @Override
            public void run() {
                try{
                    URL url = new URL("http://k02d1021.p.ssafy.io:8080/api/store?latitude="+latitude+"&longitude="+longitude+"&distance="+distance+"&keyword="+keyword);
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
//                    JsonReader jsonReader = new JsonReader(inputStreamReader);
//                    jsonReader.beginObject();
//
//                    while(jsonReader.hasNext()){
//                        Log.d(jsonReader.nextName(),jsonReader.nextString());
//                    }
//                    jsonReader.close();
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null) {
                        sb.append(line);
                        //Log.d("THREAD",line);
                    }
                    bufferedReader.close();


                    conn.disconnect();
                    result = sb.toString();
                    Log.d("storeList",result);
                    stores = getRest(result);
                } catch(Exception e){
                    result = e.toString();
                    Log.d("ERROR", e.toString());
                }


            }
        });
        thread.start();


    }


    private List<Store> getRest(String value) {
        //json parsing
        //saleList.clear();
        isEnd=false;
        Log.d("TEST","in getRest");
        Log.d("TEST", value);
        try {
            JSONArray jsonArray = new JSONArray(value);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject store = jsonArray.getJSONObject(i);
                int id = store.getInt("id");
                int franchiseId = store.getInt("franchiseId");
                String franchiseName = store.getString("franchiseName");
                String storeName = store.getString("storeName");
                double latitude=store.getDouble("latitude");
                double longitude =store.getDouble("longitude");
                String address = store.getString("address");
                String city = store.getString("city");
                String tel = store.getString("tel");
                int isatm = store.getInt("isatm");
                int islottery=store.getInt("islottery");
                int isdelivery=store.getInt("isdelivery");
                int ismedicine=store.getInt("ismedicine");
                int isfulltime=store.getInt("isfulltime");
                String logoUrl=store.getString("logoUrl");
                String deliveryBegin = store.getString("deliveryBegin");
                String deliveryEnd = store.getString("deliveryEnd");

                stores.add(new Store(id,franchiseId,franchiseName,storeName,latitude,longitude,address,city,tel,isatm,islottery,isdelivery,isfulltime,logoUrl,deliveryBegin,deliveryEnd));
                Log.d("TEST", stores.get(i).toString());
            }
        } catch(Exception e){

        }

        isEnd=true;
        return stores;
    }




}
