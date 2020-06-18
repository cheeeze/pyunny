package ssafy.d102.pyunny.customView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.d102.pyunny.R;

public class CustomActionBar {

    private Activity activity;
    private ActionBar actionBar;
    private ImageButton button_barcode;

    private SharedPreferences sf;

    public CustomActionBar(Activity activity, ActionBar actionBar) {
        this.activity = activity;
        this.actionBar = actionBar;
    }

    public void setActionBar(){
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        final View mCustomView = LayoutInflater.from(activity).inflate(R.layout.custom_actionbar, null);
        sf = mCustomView.getContext().getSharedPreferences("barcode",Context.MODE_PRIVATE);
        button_barcode = mCustomView.findViewById(R.id.button_barcode);
        button_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCustomView.getContext(),"편의점을 선택해주세요.",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sf.edit();
                editor.putBoolean("isDismiss",false);
                editor.commit();
                Log.d("isDismiss",sf.getBoolean("isDismiss",true)+"");
                CustomBarcodeDialog customBarcodeDialog = new CustomBarcodeDialog(mCustomView.getContext());
                customBarcodeDialog.callFunction(mCustomView.getContext());
            }
        });
        actionBar.setCustomView(mCustomView);
    }
}
