package samples.akhilesh.localizationsample.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import samples.akhilesh.localizationsample.base.BaseActivity;
import samples.akhilesh.localizationsample.R;

public class NewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
