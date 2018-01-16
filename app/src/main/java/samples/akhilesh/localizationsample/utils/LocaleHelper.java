package samples.akhilesh.localizationsample.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import java.util.Locale;
import static samples.akhilesh.localizationsample.utils.PreferenceHelper.PREF_FILE_NAME;


public class LocaleHelper {

    private static final String DEFAULT_COUNTRY = "IN";

    private Context context;
    private SharedPreferences mPref;

    public LocaleHelper(Context context) {
        this.context = context;
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    //private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public  static Context onAttach(Context context) {

        //Timber.d("context is %s", context == null);
        String locale = getLocaleSelected(context);
        return setLocale(context, locale);
    }

    public Context onAttach(Context context, String defaultLanguage) {
        String locale = getLocaleSelected(context);
        return setLocale(context, locale);
    }

    public static Context setLocale(Context context, String locale) {

        setLocaleSelected(context, locale);
        //persist(context, locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, locale);
        }

        return updateResourcesLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String localeCode) {
        //Log.d(LocaleHelper.class.getSimpleName(), "localeCode:  updateResources: " + localeCode);

        Locale locale = new Locale(localeCode, DEFAULT_COUNTRY);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String localeCode) {

        //Log.d(LocaleHelper.class.getSimpleName(), "localeCode:  updateResourcesLegacy: " + localeCode);
        Locale locale = new Locale(localeCode, DEFAULT_COUNTRY);
        //Log.d(LocaleHelper.class.getSimpleName(), "Locale:  updateResourcesLegacy: " + locale);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }

    /**
     * Duplicate methods from PreferenceHelper
     *
     * @return
     */
    private static String getLocaleSelected(Context context) {
        return  PreferenceHelper.getLocaleSelected(context);
    }

    /**
     * Duplicate methods from PreferenceHelper
     */
    private static void setLocaleSelected(Context context, String value) {
        PreferenceHelper.setLocaleSelected(context,value);
    }


}