package com.developerdepository.scout.Common;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.developerdepository.scout.HelperClasses.SliderAdapter;
import com.developerdepository.scout.R;
import com.developerdepository.scout.User.UserDashboardActivity;
import maes.tech.intentanim.CustomIntent;

public class OnBoardingActivity extends AppCompatActivity {

    //View Variables
    private ViewPager sliderPager;
    private LinearLayout dashLayout;
    private Button getStartedBtn;
    private ImageButton nextBtn;
    private TextView skipBtn;

    //Other Variables
    private SliderAdapter sliderAdapter;
    private TextView[] dash;
    private Animation btnAnimation;
    int CURRENT_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //StatusBar Color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        initViews();
        setActionOnViews();

        //Call Adapter
        sliderAdapter = new SliderAdapter(OnBoardingActivity.this);
        sliderPager.setAdapter(sliderAdapter);
        sliderPager.addOnPageChangeListener(onPageChangeListener);

        createDashLayout(0);
    }

    private void initViews() {
        //Initialize Views
        sliderPager = findViewById(R.id.slider_pager);
        dashLayout = findViewById(R.id.dash_layout);
        getStartedBtn = findViewById(R.id.get_started_btn);
        nextBtn = findViewById(R.id.next_btn);
        skipBtn = findViewById(R.id.skip_btn);
    }

    private void setActionOnViews() {
        //Set Action On Views

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, UserDashboardActivity.class));
                CustomIntent.customType(OnBoardingActivity.this, "left-to-right");
                finish();
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, UserDashboardActivity.class));
                CustomIntent.customType(OnBoardingActivity.this, "left-to-right");
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderPager.setCurrentItem(CURRENT_POSITION + 1);
            }
        });
    }

    private void createDashLayout(int position) {
        //Set Dashes
        dash = new TextView[4];
        dashLayout.removeAllViews();

        for(int i = 0; i < dash.length; i++) {
            dash[i] = new TextView(OnBoardingActivity.this);
            dash[i].setText(Html.fromHtml("&#183;"));
            dash[i].setTextSize(35);
            dash[i].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            dash[i].setAlpha(0.40f);
            dash[i].setTextColor(getResources().getColor(R.color.lightWhite));

            dashLayout.addView(dash[i]);
        }

        if(dash.length > 0) {
            dash[position].setText(Html.fromHtml("&#8211;"));
            dash[position].setTextSize(35);
            dash[position].setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            dash[position].setAlpha(1.0f);
            dash[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            createDashLayout(position);
            CURRENT_POSITION = position;
            if(position == 0) {
                getStartedBtn.setVisibility(View.INVISIBLE);
                getStartedBtn.setEnabled(false);
                nextBtn.setVisibility(View.VISIBLE);
                nextBtn.setEnabled(true);
                skipBtn.setVisibility(View.VISIBLE);
                skipBtn.setEnabled(true);
            } else if(position == 1) {
                getStartedBtn.setVisibility(View.INVISIBLE);
                getStartedBtn.setEnabled(false);
                nextBtn.setVisibility(View.VISIBLE);
                nextBtn.setEnabled(true);
                skipBtn.setVisibility(View.VISIBLE);
                skipBtn.setEnabled(true);
            } else if(position == 2) {
                getStartedBtn.setVisibility(View.INVISIBLE);
                getStartedBtn.setEnabled(false);
                nextBtn.setVisibility(View.VISIBLE);
                nextBtn.setEnabled(true);
                skipBtn.setVisibility(View.VISIBLE);
                skipBtn.setEnabled(true);
            } else if(position == 3) {
                getStartedBtn.setVisibility(View.VISIBLE);
                getStartedBtn.setEnabled(true);
                btnAnimation = AnimationUtils.loadAnimation(OnBoardingActivity.this, R.anim.onboarding_button_animation);
                getStartedBtn.setAnimation(btnAnimation);
                nextBtn.setVisibility(View.INVISIBLE);
                nextBtn.setEnabled(false);
                skipBtn.setVisibility(View.INVISIBLE);
                skipBtn.setEnabled(false);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

        public static void setWindowFlag(OnBoardingActivity onBoardingActivity, final int bits, boolean on) {
        Window window = onBoardingActivity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }
}