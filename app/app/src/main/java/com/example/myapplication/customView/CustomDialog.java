package com.example.myapplication.customView;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MainSaleAdapter;
import com.example.myapplication.vo.Sale;
import com.example.myapplication.vo.Store;

import java.util.ArrayList;

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
                image_dialog_franchise.setImageResource(R.drawable.gs25);
                break;
            case 1:
                image_dialog_franchise.setImageResource(R.drawable.cu);
                break;
            case 2:
                image_dialog_franchise.setImageResource(R.drawable.seven);
                break;
            case 3:
                image_dialog_franchise.setImageResource(R.drawable.emart);
                break;
            case  4:
                image_dialog_franchise.setImageResource(R.drawable.ministop);
                break;
        }
        if(store.getIsatm()==1){
            btn_map_marker_dialog_atm.setBackgroundResource(R.drawable.border);
        }
        if(store.getIslottery()==1){
            btn_map_marker_dialog_lottery.setBackgroundResource(R.drawable.border);
        }
        if(store.getIsdelivery()==1){
            btn_map_marker_dialog_delivery.setBackgroundResource(R.drawable.border);
        }
        if(store.getIsfulltime()==1){
            btn_map_marker_dialog_fulltime.setBackgroundResource(R.drawable.border);
        }

        txt_dialog_address.setText("주소: "+store.getAddress());
        txt_dialog_tell.setText("전화번호: "+ store.getTel());

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(mLayoutManager);
        ArrayList<Sale> arr=new ArrayList<>();
        for(Sale sale:getList()){
            if(sale.getFranchise_id()==store.getFranchise_id()){
                arr.add(sale);
            }
        }
        final MainSaleAdapter mainSaleAdapter = new MainSaleAdapter(mContext,arr);
        recyclerView.setAdapter(mainSaleAdapter);

    }

    private ArrayList<Sale> getList(){
        ArrayList<Sale> list = new ArrayList<>();
        //int franchise_id, String type, String product_name, int product_price, String product_image
        list.add(new Sale(0,"1+1","농심)멘토스레인보우",700,"https://bgf-cu.xcache.kinxcdn.com/product/6921211104292.jpg"));
        list.add(new Sale(1,"1+1","롯)의성마늘프랑크2입",3300,"https://bgf-cu.xcache.kinxcdn.com/product/8801123308910.jpg"));
        list.add(new Sale(2,"2+1","롯데)허쉬쿠키초콜릿",1500,"https://bgf-cu.xcache.kinxcdn.com/product/0003400010253.jpg"));
        list.add(new Sale(3,"2+1","빙그레)메로나",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801104123280.jpg"));
        list.add(new Sale(4,"2+1","오뚜기)참깨라면컵",1200,"https://bgf-cu.xcache.kinxcdn.com/product/8801045570419.jpg"));
        list.add(new Sale(0,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
        list.add(new Sale(1,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(2,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
        list.add(new Sale(3,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
        list.add(new Sale(4,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(1,"1+1","농심)멘토스레인보우",700,"https://bgf-cu.xcache.kinxcdn.com/product/6921211104292.jpg"));
        list.add(new Sale(2,"1+1","롯)의성마늘프랑크2입",3300,"https://bgf-cu.xcache.kinxcdn.com/product/8801123308910.jpg"));
        list.add(new Sale(3,"2+1","롯데)허쉬쿠키초콜릿",1500,"https://bgf-cu.xcache.kinxcdn.com/product/0003400010253.jpg"));
        list.add(new Sale(4,"2+1","빙그레)메로나",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801104123280.jpg"));
        list.add(new Sale(0,"2+1","오뚜기)참깨라면컵",1200,"https://bgf-cu.xcache.kinxcdn.com/product/8801045570419.jpg"));
        list.add(new Sale(2,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(3,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));
        list.add(new Sale(4,"2+1","롯데)롱에센뽀득프랑크100g",2400,"https://emart24.co.kr/upload/eventProduct/FOOD/8801123310234.jpg"));
        list.add(new Sale(0,"1+1","동아)데미소다레몬캔250ML",1200,"https://gs25appimg.gsretail.com/imgsvr/item/GD_8801097160071.jpg"));
        list.add(new Sale(1,"2+1","롯데)크런키초콜릿",1000,"https://bgf-cu.xcache.kinxcdn.com/product/8801062628476.jpg"));


        return list;
    }
}
