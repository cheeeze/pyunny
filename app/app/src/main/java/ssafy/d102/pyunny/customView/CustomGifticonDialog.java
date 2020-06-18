package ssafy.d102.pyunny.customView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.d102.pyunny.R;

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
