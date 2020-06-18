package ssafy.d102.pyunny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.d102.pyunny.R;
import ssafy.d102.pyunny.vo.Sale;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAllAdapter extends RecyclerView.Adapter<MainAllAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Sale> list;

    public MainAllAdapter(Context mContext, ArrayList<Sale> list) {
        this.mContext = mContext;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View converterView = LayoutInflater.from(mContext).inflate(R.layout.sale_all_card_view,parent,false);
        return new ViewHolder(converterView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sale sale = list.get(position);
        //holder.sale_type.setText(sale.getType());
        DecimalFormat format = new DecimalFormat("###,###");
        holder.sale_product_price.setText(format.format(sale.getProduct_price())+"원");
        holder.sale_product_name.setText(sale.getProduct_name());
        Glide.with(mContext).load(sale.getProduct_image()).into(holder.sale_product_image);
        switch (sale.getFranchise_id()){
            case 0:
            case 646:
                holder.sale_franchise_image.setImageResource(R.drawable.gs25);
                break;
            case 1:
            case 682:
                holder.sale_franchise_image.setImageResource(R.drawable.cu);
                break;
            case 2:
                holder.sale_franchise_image.setImageResource(R.drawable.seven);
                break;
            case 3:
            case 936:
                holder.sale_franchise_image.setImageResource(R.drawable.emart);
                break;
            case 4:
                holder.sale_franchise_image.setImageResource(R.drawable.ministop);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView sale_product_image;
       // private TextView sale_type;
        private TextView sale_product_name;
        private TextView sale_product_price;
        private ImageView sale_franchise_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sale_product_image = itemView.findViewById(R.id.image_sale_cardview_product_image);
            //sale_type = itemView.findViewById(R.id.txt_sale_cardview_type);
            sale_product_name = itemView.findViewById(R.id.txt_sale_cardview_product_name);
            sale_product_price = itemView.findViewById(R.id.txt_sale_cardview_product_price);
            sale_franchise_image = itemView.findViewById(R.id.image_sale_cardview_franchise);
        }
    }
}
