package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.vo.StoreProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapProductAdapter extends RecyclerView.Adapter<MapProductAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<StoreProduct> list;

    public MapProductAdapter(Context mContext, ArrayList<StoreProduct> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View converterView = LayoutInflater.from(mContext).inflate(R.layout.storeproduct_card_view,parent,false);
        return new ViewHolder(converterView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoreProduct storeProduct = list.get(position);
        holder.txt_storeproduct_cardview_name.setText(storeProduct.getName());
        DecimalFormat format = new DecimalFormat("###,###");
        holder.txt_storeproduct_cardview_price.setText(format.format(storeProduct.getPrice())+"원");
        holder.txt_storeproduct_cardview_stockamount.setText("재고: "+storeProduct.getStockAmount()+"개");
        Glide.with(mContext).load(storeProduct.getImage()).error(R.drawable.defaultproduct).into(holder.image_storeproduct_cardview_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image_storeproduct_cardview_image;
        private TextView txt_storeproduct_cardview_name, txt_storeproduct_cardview_stockamount, txt_storeproduct_cardview_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_storeproduct_cardview_image = itemView.findViewById(R.id.image_storeproduct_cardview_image);
            txt_storeproduct_cardview_name = itemView.findViewById(R.id.txt_storeproduct_cardview_name);
            txt_storeproduct_cardview_stockamount = itemView.findViewById(R.id.txt_storeproduct_cardview_stockamount);
            txt_storeproduct_cardview_price=itemView.findViewById(R.id.txt_storeproduct_cardview_price);


        }
    }
}
