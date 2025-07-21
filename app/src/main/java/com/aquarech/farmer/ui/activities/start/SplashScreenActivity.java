package com.aquarech.farmer.ui.activities.start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import com.aquarech.farmer.R;
import com.aquarech.farmer.utils.Config;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, IntroScreenActivity.class));
            finish();
        }, Config.SPLASH_DELAY);
    }
}