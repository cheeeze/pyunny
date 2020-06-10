package com.example.myapplication.fragment.mypage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MypageGifticonAdapter;
import com.example.myapplication.customView.CustomGifticonDialog;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.EnumMap;
import java.util.Map;

public class FragmentMypage extends Fragment {

    private RecyclerView recyclerView;
    private MypageGifticonAdapter mypageGifticonAdapter;
    private ArrayList<Bitmap> imageList = new ArrayList<>();

    private ImageButton btn_mypage_add_gifticon;
    private static final int REQUEST_CODE = 0;

    private static final int WHITE = 0xFFFFFFFF;
    private static final int BLACK = 0xFF000000;

    private ImageButton btn_mypage_add_barcode;
    private ImageView image_mypage_barcode;
    private EditText edt_mypage_agency_num;

    private ImageButton image_btn_sk;
    private ImageButton image_btn_kt;
    private ImageButton image_btn_lg;

    private TextView txt_mypage_agency;

    private TextView txt_mypage_barcode;
    private TextView txt_mypage_discount_info;


    private int agencyNum=1;
    private SharedPreferences sf;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_mypage,container,false);
        init(view);

        return view;
    }

    private void init(final View view){
        recyclerView = view.findViewById(R.id.recyclerView_mypage_gifticon);
        btn_mypage_add_gifticon = view.findViewById(R.id.btn_mypage_add_gifticon);
        btn_mypage_add_gifticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        mypageGifticonAdapter = new MypageGifticonAdapter(imageList,getContext());
        recyclerView.setAdapter(mypageGifticonAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bitmap bitmap = imageList.get(position);
                CustomGifticonDialog customGifticonDialog = new CustomGifticonDialog(getContext());
                customGifticonDialog.gifticonCallFunction(bitmap);
            }

            @Override
            public void onLongClick(View view, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("삭제");
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"삭제했습니다.",Toast.LENGTH_SHORT).show();
                        imageList.remove(position);
                        mypageGifticonAdapter = new MypageGifticonAdapter(imageList,getContext());
                        recyclerView.setAdapter(mypageGifticonAdapter);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        }));

        txt_mypage_barcode = view.findViewById(R.id.txt_mypage_barcode);
        txt_mypage_discount_info = view.findViewById(R.id.txt_mypage_discount_info);
        image_btn_sk = view.findViewById(R.id.image_btn_sk);
        image_btn_kt = view.findViewById(R.id.image_btn_kt);
        image_btn_lg = view.findViewById(R.id.image_btn_lg);
        txt_mypage_agency = view.findViewById(R.id.txt_mypage_agency);
        btn_mypage_add_barcode = view.findViewById(R.id.btn_mypage_add_barcode);
        image_mypage_barcode = view.findViewById(R.id.image_mypage_barcode);
        edt_mypage_agency_num = view.findViewById(R.id.edt_mypage_agency_num);
        sf = getActivity().getSharedPreferences("barcode",Context.MODE_PRIVATE);
        agencyNum=1;
        String discount_info = "* CU - 천원당 100원 할인(VIP/Gold), 천원당 50원 할인(Silver)";
        discount_info+="\n* 세븐일레븐 - 천원당 100원 할인(VIP/Gold), 천원당 50원 할인(Silver)";
        txt_mypage_discount_info.setText(discount_info);
        image_btn_sk.setBackgroundResource(R.drawable.border);
        image_btn_kt.setBackgroundResource(R.drawable.border_unclick);
        image_btn_lg.setBackgroundResource(R.drawable.border_unclick);
        txt_mypage_agency.setText("SKT");
        String code = sf.getString("skt","");
        edt_mypage_agency_num.setText(code);
        txt_mypage_barcode.setText(code);
        try {

            Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
            image_mypage_barcode.setImageBitmap(bitmap);
        }catch (Exception e){

        }
        image_btn_sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agencyNum=1;
                image_btn_sk.setBackgroundResource(R.drawable.border);
                image_btn_kt.setBackgroundResource(R.drawable.border_unclick);
                image_btn_lg.setBackgroundResource(R.drawable.border_unclick);
                String discount_info = "* CU - 천원당 100원 할인(VIP/Gold), 천원당 50원 할인(Silver)";
                discount_info+="\n* 세븐일레븐 - 천원당 100원 할인(VIP/Gold), 천원당 50원 할인(Silver)";
                txt_mypage_discount_info.setText(discount_info);
                txt_mypage_agency.setText("SKT");
                String code = sf.getString("skt","");
                edt_mypage_agency_num.setText(code);
                txt_mypage_barcode.setText(code);
                try {

                    Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                    image_mypage_barcode.setImageBitmap(bitmap);
                }catch (Exception e){

                }
            }
        });

        image_btn_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agencyNum=2;
                image_btn_kt.setBackgroundResource(R.drawable.border);
                image_btn_sk.setBackgroundResource(R.drawable.border_unclick);
                image_btn_lg.setBackgroundResource(R.drawable.border_unclick);
                String discount_info = "* GS25 - VVIP/VIP/GOLD 10% 차감할인, SILVER/WHITE/일반 - 5% 차감할인";
                discount_info+="\n* CU - 도시락/샐러드 1,000원당 200원 차감할인, 결제금액 5천원까지 할인가능";
                discount_info+="\n* 세븐일레븐 - 천원당 100원 할인(VIP/Gold), 천원당 50원 할인(Silver)";
                txt_mypage_discount_info.setText(discount_info);
                txt_mypage_agency.setText("KT");
                String code = sf.getString("kt","");
                edt_mypage_agency_num.setText(code);
                txt_mypage_barcode.setText(code);

                try {

                    Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                    image_mypage_barcode.setImageBitmap(bitmap);
                }catch (Exception e){

                }
            }
        });

        image_btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agencyNum=3;
                image_btn_lg.setBackgroundResource(R.drawable.border);
                image_btn_kt.setBackgroundResource(R.drawable.border_unclick);
                image_btn_sk.setBackgroundResource(R.drawable.border_unclick);
                txt_mypage_agency.setText("LG U+");
                String discount_info = "* GS25 - 5% 할인, 1일 1회 일 최대 10,000원까지 할인 가능";
                txt_mypage_discount_info.setText(discount_info);
                String code = sf.getString("lg","");
                edt_mypage_agency_num.setText(code);
                txt_mypage_barcode.setText(code);
                try {

                    Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                    image_mypage_barcode.setImageBitmap(bitmap);
                }catch (Exception e){

                }
            }
        });
        btn_mypage_add_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if(agencyNum==1){
                        Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                        SharedPreferences.Editor editor = sf.edit();
                        String code = edt_mypage_agency_num.getText().toString();
                        editor.putString("skt",code);
                        editor.commit();
                        image_mypage_barcode.setImageBitmap(bitmap);
                        txt_mypage_barcode.setText(code);
                    }else if(agencyNum==2){
                        Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                        SharedPreferences.Editor editor = sf.edit();
                        String code = edt_mypage_agency_num.getText().toString();
                        editor.putString("kt",code);
                        editor.commit();
                        image_mypage_barcode.setImageBitmap(bitmap);
                        txt_mypage_barcode.setText(code);

                    }else if(agencyNum==3){
                        Bitmap bitmap = encodeAsBitmap(edt_mypage_agency_num.getText().toString(),BarcodeFormat.CODE_128,600,300);
                        SharedPreferences.Editor editor = sf.edit();
                        String code = edt_mypage_agency_num.getText().toString();
                        editor.putString("lg",code);
                        editor.commit();
                        image_mypage_barcode.setImageBitmap(bitmap);
                        txt_mypage_barcode.setText(code);
                    }
                }catch (WriterException e){

                }
            }
        });


    }

    public interface ClickListener{
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private GestureDetector gestureDetector;
        private FragmentMypage.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final FragmentMypage.ClickListener clickListener){
            this.clickListener=clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null&&clickListener!=null){
                        clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null&&clickListener!=null&&gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE){
            if(resultCode==getActivity().RESULT_OK){

                try {
                    InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    imageList.add(img);

                }catch (Exception e){

                }

            }
            else if(resultCode==getActivity().RESULT_CANCELED){
                Toast.makeText(getContext(),"사진 선택 취소",Toast.LENGTH_LONG).show();
            }
        }
        mypageGifticonAdapter = new MypageGifticonAdapter(imageList,getContext());
        recyclerView.setAdapter(mypageGifticonAdapter);
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
