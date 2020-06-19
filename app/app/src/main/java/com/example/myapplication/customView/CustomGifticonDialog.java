package com.example.myapplication.customView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.myapplication.R;

public class CustomGifticonDialog {
    private Context mContext;

    public CustomGifticonDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void gifticonCallFunction(final Bitmap bitmap){
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.gifticon_image_big);
        dialog.show();

        final ImageView image_gifticon = dialog.findViewById(R.id.image_gifticon);
        image_gifticon.setImageBitmap(bitmap);
    }

}
