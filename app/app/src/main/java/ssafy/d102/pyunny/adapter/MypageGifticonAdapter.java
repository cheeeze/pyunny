package ssafy.d102.pyunny.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.d102.pyunny.R;

import java.util.ArrayList;

public class MypageGifticonAdapter extends RecyclerView.Adapter<MypageGifticonAdapter.ViewHolder> {

    private ArrayList<Bitmap> imageList;
    private Context mContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gifticon_image,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bitmap bitmap = imageList.get(position);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public MypageGifticonAdapter(ArrayList<Bitmap> imageList, Context mContext) {
        this.imageList = imageList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            super(view);

            imageView = view.findViewById(R.id.image_mypage_gifticon);

        }
    }
}
