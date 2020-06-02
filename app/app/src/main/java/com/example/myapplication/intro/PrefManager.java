package com.example.myapplication.intro;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

//    shared pref mode
    int PRIVATE_MODE = 0;

//    shared preferences file name
    private static final String PREF_NAME = "pyunyBbong-welcome";

    private static final String IS_FIRST_TIME_LUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LUNCH,isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LUNCH,true);
    }
}
