package samples.akhilesh.localizationsample.ui.LanguageSeletcion;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import samples.akhilesh.localizationsample.ui.MainActivity;
import samples.akhilesh.localizationsample.model.Language;
import samples.akhilesh.localizationsample.utils.LanguageConstants;
import samples.akhilesh.localizationsample.utils.LocaleHelper;
import samples.akhilesh.localizationsample.utils.PreferenceHelper;
import samples.akhilesh.localizationsample.R;
import samples.akhilesh.localizationsample.utils.Utils;

public class LanguageSelectionActivity extends AppCompatActivity {

    private RecyclerView rcLanguageList;
    private LanguageAdapter languageAdapter;
    private FloatingActionButton fab;
    private List<Language> langList = new ArrayList<>();

    private String selectedLocale;
    private TextView txt_lang_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_language_selection);

        if (checkFirstRun() == 1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        } else {

            selectedLocale = Locale.getDefault().getLanguage();
            LocaleHelper.setLocale(this, selectedLocale);

            txt_lang_title = findViewById(R.id.text_lang_title);
            fab = findViewById(R.id.fab_set_lang);
            rcLanguageList = findViewById(R.id.langList);

            // fill in language list
            prepareLanguageList();

            Language deviceLanguage = new Language(Utils.localeToLanguage(selectedLocale));
            int langPositionInList = langList.indexOf(deviceLanguage);

            // setup adapter
            languageAdapter = new LanguageAdapter(langPositionInList, langList, itemClickListener);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            rcLanguageList.setHasFixedSize(true);
            rcLanguageList.setLayoutManager(mLayoutManager);
            rcLanguageList.setItemAnimator(new DefaultItemAnimator());

            rcLanguageList.setAdapter(languageAdapter);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    LocaleHelper.setLocale(LanguageSelectionActivity.this, selectedLocale);
                    PreferenceHelper.setFirstLaunch(LanguageSelectionActivity.this, 1);
                    startActivity(new Intent(LanguageSelectionActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    private OnLanguageItemClickListener itemClickListener = new OnLanguageItemClickListener() {
        @Override
        public void onItemClick(Language item) {

            selectedLocale = Utils.languageToLocale(item.getLang());
            LocaleHelper.setLocale(LanguageSelectionActivity.this, selectedLocale);
            onConfigurationChanged(getResources().getConfiguration());
        }
    };

    private void setLanguageDescription() {
        String one = getString(R.string.txt_select_lang_one);
        String two = getString(R.string.txt_select_lang_two);

        final SpannableString spannableString = new SpannableString(one.concat(two));
        spannableString.setSpan(new RelativeSizeSpan(0.7F), one.length(), spannableString.length(), 0);
        txt_lang_title.setText(spannableString);
    }

    private void prepareLanguageList() {

        langList.add(new Language(LanguageConstants.LANG_VAL_ENG));
        langList.add(new Language(LanguageConstants.LANG_VAL_HIN));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public int checkFirstRun() {
        return PreferenceHelper.isFirstLaunch(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLanguageDescription();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onConfigurationChanged(getResources().getConfiguration());
    }
}
