package com.example.myapplication.customView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

public class CustomBarcodeDialog extends Dialog implements View.OnClickListener, Dialog.OnDismissListener, Dialog.OnCancelListener {
    private Context mContext;

    private ImageButton barcode_image_btn_gs,barcode_image_btn_cu,barcode_image_btn_seven,barcode_image_btn_emart,barcode_image_btn_mini;
    private ImageView image_barcode_conv, image_barcode_membership;
    private TextView txt_barcode_conv_num, txt_barcode_membership_num, txt_barcode_membership;

    private static final int WHITE = 0xFFFFFFFF;

    private static final int BLACK = 0xFF000000;

    private SharedPreferences sf;


    public CustomBarcodeDialog(Context mContext) {
        super(mContext);
        this.mContext = mContext;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        sf = mContext.getSharedPreferences("barcode",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("isDismiss", true);
        editor.commit();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        sf = mContext.getSharedPreferences("barcode",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putBoolean("isDismiss", true);
        editor.commit();

    }

    public void callFunction(Context context){
        Dialog dig = new Dialog(context);

        dig.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dig.setContentView(R.layout.barcod_dialog);
        dig.setOnDismissListener(this);
        dig.setOnCancelListener(this);
        dig.show();

        sf = mContext.getSharedPreferences("barcode",Context.MODE_PRIVATE);

        barcode_image_btn_gs = dig.findViewById(R.id.barcode_image_btn_gs);
        barcode_image_btn_cu = dig.findViewById(R.id.barcode_image_btn_cu);
        barcode_image_btn_seven = dig.findViewById(R.id.barcode_image_btn_seven);
        barcode_image_btn_emart = dig.findViewById(R.id.barcode_image_btn_emart);
        barcode_image_btn_mini = dig.findViewById(R.id.barcode_image_btn_mini);
        image_barcode_conv = dig.findViewById(R.id.image_barcode_conv);
        image_barcode_membership = dig.findViewById(R.id.image_barcode_membership);
        txt_barcode_conv_num =  dig.findViewById(R.id.txt_barcode_conv_num);
        txt_barcode_membership_num = dig.findViewById(R.id.txt_barcode_membership_num);
        txt_barcode_membership = dig.findViewById(R.id.txt_barcode_membership);

        init();
        barcode_image_btn_gs.setOnClickListener(this);
        barcode_image_btn_cu.setOnClickListener(this);
        barcode_image_btn_seven.setOnClickListener(this);
        barcode_image_btn_emart.setOnClickListener(this);
        barcode_image_btn_mini.setOnClickListener(this);

    }

    private void imageDefault(){
        Log.d("tttttt",txt_barcode_conv_num.getText().length()+"");
        if(txt_barcode_conv_num.getText().length()<=0){
            image_barcode_conv.setImageResource(R.drawable.empty_barcode);
        }

        if(txt_barcode_membership_num.getText().length()<=0){
            image_barcode_membership.setImageResource(R.drawable.empty_barcode);
        }
    }

    private void init(){
        barcode_image_btn_gs.setBackgroundResource(R.drawable.border);
        barcode_image_btn_cu.setBackgroundResource(R.drawable.border_unclick);
        barcode_image_btn_seven.setBackgroundResource(R.drawable.border_unclick);
        barcode_image_btn_emart.setBackgroundResource(R.drawable.border_unclick);
        barcode_image_btn_mini.setBackgroundResource(R.drawable.border_unclick);
        txt_barcode_membership.setText("GS25");
        String code = sf.getString("gs","");
        txt_barcode_conv_num.setText(code);

        try {
            Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
            image_barcode_conv.setImageBitmap(bitmap);
        }catch (Exception e){

        }
        code = sf.getString("kt","");
        txt_barcode_membership.setText("KT");
        txt_barcode_membership_num.setText(code);
        try {
            Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
            image_barcode_membership.setImageBitmap(bitmap);
        }catch (Exception e){

        }

        imageDefault();
    }
    @Override
    public void onClick(View v) {
        String code="";
        switch (v.getId()){
            case R.id.barcode_image_btn_gs:
                barcode_image_btn_gs.setBackgroundResource(R.drawable.border);
                barcode_image_btn_cu.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_seven.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_emart.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_mini.setBackgroundResource(R.drawable.border_unclick);
                txt_barcode_membership.setText("GS25");
                code = sf.getString("gs","");
                txt_barcode_conv_num.setText(code);

                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_conv.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                code = sf.getString("kt","");
                txt_barcode_membership.setText("KT");
                txt_barcode_membership_num.setText(code);
                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_membership.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                break;
            case R.id.barcode_image_btn_cu:
                barcode_image_btn_gs.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_cu.setBackgroundResource(R.drawable.border);
                barcode_image_btn_seven.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_emart.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_mini.setBackgroundResource(R.drawable.border_unclick);
                txt_barcode_membership.setText("CU");
                code = sf.getString("cu", "");
                txt_barcode_conv_num.setText(code);

                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_conv.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                code = sf.getString("skt","");
                txt_barcode_membership.setText("SKT");
                txt_barcode_membership_num.setText(code);
                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.ITF,800,300);
                    image_barcode_membership.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                break;
            case R.id.barcode_image_btn_seven:
                barcode_image_btn_gs.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_cu.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_seven.setBackgroundResource(R.drawable.border);
                barcode_image_btn_emart.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_mini.setBackgroundResource(R.drawable.border_unclick);
                txt_barcode_membership.setText("세븐일레븐");
                code = sf.getString("seven", "");
                txt_barcode_conv_num.setText(code);

                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_conv.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                code = sf.getString("skt","");
                txt_barcode_membership.setText("SKT");
                txt_barcode_membership_num.setText(code);
                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.ITF,800,300);
                    image_barcode_membership.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                break;
            case R.id.barcode_image_btn_emart:
                barcode_image_btn_gs.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_cu.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_seven.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_emart.setBackgroundResource(R.drawable.border);
                barcode_image_btn_mini.setBackgroundResource(R.drawable.border_unclick);
                txt_barcode_membership.setText("emart24");
                code = sf.getString("emart","");
                txt_barcode_conv_num.setText(code);

                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_conv.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                code = sf.getString("kt","");
                txt_barcode_membership.setText("KT");
                txt_barcode_membership_num.setText(code);
                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_membership.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                break;
            case R.id.barcode_image_btn_mini:
                barcode_image_btn_gs.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_cu.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_seven.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_emart.setBackgroundResource(R.drawable.border_unclick);
                barcode_image_btn_mini.setBackgroundResource(R.drawable.border);
                txt_barcode_membership.setText("MINISTOP");
                code = sf.getString("mini","");
                txt_barcode_conv_num.setText(code);

                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_conv_num.getText().toString(), BarcodeFormat.CODE_128,700,300);
                    image_barcode_conv.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                code = sf.getString("skt","");
                txt_barcode_membership.setText("SKT");
                txt_barcode_membership_num.setText(code);
                try {
                    Bitmap bitmap = encodeAsBitmap(txt_barcode_membership_num.getText().toString(), BarcodeFormat.ITF,800,300);
                    image_barcode_membership.setImageBitmap(bitmap);
                }catch (Exception e){

                }
                break;
        }

        imageDefault();
    }




    //    바코드

    private Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int img_width, int img_height) throws WriterException {
        String contentsToEncode = contents;
        if (contentsToEncode == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contentsToEncode);
        if (encoding != null) {
            hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();

        BitMatrix result;
        try {
            result = writer.encode(contentsToEncode, format, img_width, img_height, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }
}
