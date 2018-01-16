package samples.akhilesh.localizationsample.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class PreferenceHelper {

    public static final String PREF_FILE_NAME = "agrahyah_pref_file";
    public static final String KEY_LOCALE_SELECTED = "KEY_LOCALE_SELECTED";
    private static final String KEY_FIRST_LAUNCH = "KEY_FIRST_LAUNCH";

   public PreferenceHelper() {}

    static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static String getLocaleSelected(Context context) {
        return getPreferences(context).getString(KEY_LOCALE_SELECTED, Locale.getDefault().getLanguage());
    }

    public static void setLocaleSelected(Context context, String value) {
        getPreferences(context).edit().putString(KEY_LOCALE_SELECTED, value).commit();
    }

    public static int isFirstLaunch(Context context) {
        return getPreferences(context).getInt(KEY_FIRST_LAUNCH, 0);
    }

    public static void setFirstLaunch(Context context, int value) {
        getPreferences(context).edit().putInt(KEY_FIRST_LAUNCH, value).apply();
    }

}
