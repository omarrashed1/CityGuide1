package com.developerdepository.scout.Common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.developerdepository.scout.R;
import com.developerdepository.scout.User.UserDashboardActivity;
import maes.tech.intentanim.CustomIntent;

public class SplashScreenActivity extends AppCompatActivity {

    //View Variables
    private ImageView appLogo;
    private TextView appSlogan, poweredBy1, poweredBy2;

    //Other Variables
    private Animation topAnimation, bottomAnimation;
    private SharedPreferences onBoardingPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


        initViews();
        initAnimation();
    }

    private void initViews() {
        //Initialize Views
        appLogo = findViewById(R.id.app_logo);
        appSlogan = findViewById(R.id.app_slogan);
        poweredBy1 = findViewById(R.id.app_powered_by1);
        poweredBy2 = findViewById(R.id.app_powered_by2);
    }

    private void initAnimation() {
        //Initialize Animations
        topAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.splash_bottom_animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int SPLASH_TIMER = 4000;

        //Set Preferences
        onBoardingPreference = getSharedPreferences("onBoardingPreference", MODE_PRIVATE);
        final boolean isFirstTime = onBoardingPreference.getBoolean("firstTime", true);

        //Set Animation To Views
        appLogo.setAnimation(topAnimation);
        appSlogan.setAnimation(bottomAnimation);
        poweredBy1.setAnimation(bottomAnimation);
        poweredBy2.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isFirstTime) {
                    @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = onBoardingPreference.edit();
                    editor.putBoolean("firstTime", false);
                    editor.apply();

                    Intent intent = new Intent(SplashScreenActivity.this, OnBoardingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    CustomIntent.customType(SplashScreenActivity.this, "fadein-to-fadeout");
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, UserDashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    CustomIntent.customType(SplashScreenActivity.this, "fadein-to-fadeout");
                    finish();
                }
            }
        }, SPLASH_TIMER);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}