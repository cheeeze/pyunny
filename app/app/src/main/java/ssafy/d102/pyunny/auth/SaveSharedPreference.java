package ssafy.d102.pyunny.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    //nickname을 저장해서 로그인을 판단하자!

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    //계정 정보 저장
    public static void setEmail(Context ctx, String email) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString("nickname", email);
        editor.commit();
    }

    //저장된 정보 가져오기
    public static String getEmail(Context ctx) {
        return getSharedPreferences(ctx).getString("email","");
    }

    //로그아웃 (저장정보지우기)
    public static void deleteEmail(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
