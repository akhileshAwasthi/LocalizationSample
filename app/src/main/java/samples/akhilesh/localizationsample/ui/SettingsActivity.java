package samples.akhilesh.localizationsample.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import samples.akhilesh.localizationsample.base.BaseActivity;
import samples.akhilesh.localizationsample.utils.LanguageConstants;
import samples.akhilesh.localizationsample.utils.LocaleHelper;
import samples.akhilesh.localizationsample.utils.PreferenceHelper;
import samples.akhilesh.localizationsample.R;

public class SettingsActivity extends BaseActivity {

    private LocaleHelper localeHelper;
    Button saveLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actuvity_pref);
        saveLang = findViewById( R.id.btn_save_lang);
        saveLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
        getFragmentManager().beginTransaction().replace(R.id.container, new SamplePreferenceFragment()).commit();

    }

    private void openMainActivity() {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            if (key.equals(PreferenceHelper.KEY_LOCALE_SELECTED)) {

                String langCode = sharedPreferences.getString(key, LanguageConstants.LANG_CODE_EN);
                PreferenceHelper.setLocaleSelected(SettingsActivity.this, langCode);
                LocaleHelper.setLocale(SettingsActivity.this, langCode);
                onConfigurationChanged(getResources().getConfiguration());
                restartActivity();
            }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        openMainActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPreferences(PreferenceHelper.PREF_FILE_NAME, MODE_PRIVATE).registerOnSharedPreferenceChangeListener(listener);
        onConfigurationChanged(getResources().getConfiguration());
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSharedPreferences(PreferenceHelper.PREF_FILE_NAME, MODE_PRIVATE).unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    protected void attachBaseContext(Context base) {
        localeHelper = new LocaleHelper(base);
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void restartActivity() {
        Intent intent = getIntent();
        ActivityCompat.finishAfterTransition(this);
        startActivity(intent);
    }
}
