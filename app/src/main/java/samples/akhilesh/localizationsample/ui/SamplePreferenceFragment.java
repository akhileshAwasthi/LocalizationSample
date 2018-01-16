package samples.akhilesh.localizationsample.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import samples.akhilesh.localizationsample.R;
import samples.akhilesh.localizationsample.utils.PreferenceHelper;

public class SamplePreferenceFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName(PreferenceHelper.PREF_FILE_NAME);
        addPreferencesFromResource(R.xml.preferences);

    }

    @Override
 public boolean onPreferenceTreeClick( PreferenceScreen preferenceScreen,Preference preference) {

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }


}