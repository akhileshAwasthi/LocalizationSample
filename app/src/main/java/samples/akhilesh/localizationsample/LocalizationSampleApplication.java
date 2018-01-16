package samples.akhilesh.localizationsample;

import android.app.Application;
import android.content.Context;
import samples.akhilesh.localizationsample.utils.LocaleHelper;

public abstract class LocalizationSampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

}

