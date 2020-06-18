package ssafy.d102.pyunny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.d102.pyunny.R;
import ssafy.d102.pyunny.vo.MapSearchResult;

import java.text.DecimalFormat;
import java.util.List;

public class MapSearchResultAdapter extends RecyclerView.Adapter<MapSearchResultAdapter.ViewHolder>{

    private Context mContext;
    private List<MapSearchResult> list;

    public MapSearchResultAdapter(Context mContext, List<MapSearchResult> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mapsearch_card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MapSearchResult mapSearchResult = list.get(position);
        Glide.with(mContext).load(mapSearchResult.getImage()).error(R.drawable.defaultproduct).into(holder.image_mapsearch_cardview_image);
        holder.txt_mapsearch_cardview_name.setText(mapSearchResult.getName());
        DecimalFormat format = new DecimalFormat("###,###");
        holder.txt_mapsearch_cardview_price.setText("가격: "+format.format(mapSearchResult.getPrice())+"원");
        holder.txt_mapsearch_cardview_stockamount.setText("재고: "+mapSearchResult.getStockAmount());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image_mapsearch_cardview_image;
        private TextView txt_mapsearch_cardview_name, txt_mapsearch_cardview_stockamount, txt_mapsearch_cardview_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_mapsearch_cardview_image = itemView.findViewById(R.id.image_mapsearch_cardview_image);
            txt_mapsearch_cardview_name = itemView.findViewById(R.id.txt_mapsearch_cardview_name);
            txt_mapsearch_cardview_stockamount = itemView.findViewById(R.id.txt_mapsearch_cardview_stockamount);
            txt_mapsearch_cardview_price=itemView.findViewById(R.id.txt_mapsearch_cardview_price);


        }
    }
}
