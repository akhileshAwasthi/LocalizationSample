package samples.akhilesh.localizationsample.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import samples.akhilesh.localizationsample.base.BaseActivity;
import samples.akhilesh.localizationsample.R;


public class MainActivity extends BaseActivity {

    Button newScreen;
    Button btnChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newScreen = findViewById(R.id.btn_new_screen);
        btnChangeLanguage = findViewById(R.id.btn_change_language);

        btnChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        newScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        onConfigurationChanged(getResources().getConfiguration());
        super.onResume();
    }

    @Override
     public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
