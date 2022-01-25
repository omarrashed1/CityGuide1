package com.developerdepository.scout.User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.developerdepository.scout.R;

public class ShopsActivity extends AppCompatActivity {

    ConstraintLayout s1, s2,s3,s4,s5,s6,s7;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);

        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        s1 = findViewById(R.id.most_viewed_location_itemm);
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D8%B3%D9%88%D9%82+%D8%A7%D9%84%D9%82%D9%8A%D8%B5%D8%B1%D9%8A%D8%A9+%D8%A7%D9%84%D8%A5%D8%AD%D8%B3%D8%A7%D8%A1%E2%80%AD/@25.3759045,49.5912692,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3796c081d545a3:0xbd614d55f5b94595!8m2!3d25.3759045!4d49.5890805");
            }
        });

        s2 = findViewById(R.id. most_viewed_location_item2);
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D8%B3%D9%88%D9%82+%D8%A7%D9%84%D9%82%D8%B1%D9%8A%D8%A9+%D8%A7%D9%84%D8%B4%D8%B9%D8%A8%D9%8A%D8%A9%D8%8C+%D8%AE%D8%A7%D9%84%D8%AF+%D8%A8%D9%86+%D8%A7%D9%84%D9%88%D9%84%D9%8A%D8%AF%D8%8C+%D8%B9%D9%8A%D9%86+%D9%86%D8%AC%D9%85%D8%8C+%D8%A7%D9%84%D9%85%D8%A8%D8%B1%D8%B2+36421%E2%80%AD/@25.3883394,49.5499192,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3797a02deeef71:0x43566ed3eabaab2f!8m2!3d25.3883346!4d49.5477305");
            }
        });

        s3 = findViewById(R.id.  most_viewed_location_item3);
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D8%B3%D9%88%D9%82+%D8%A7%D9%84%D8%B3%D9%88%D9%8A%D9%82%E2%80%AD/@25.3724895,49.5886411,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3796c73e13020d:0xb924efbb9907894b!8m2!3d25.3724847!4d49.5864524");
            }
        });

        s4 = findViewById(R.id.  most_viewed_location_item4);
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/AL+AHSA+MALL+by+Arabian+Centres+%7C+%D8%A7%D9%84%D8%A3%D8%AD%D8%B3%D8%A7%D8%A1+%D9%85%D9%88%D9%84+%D9%85%D9%86+%D8%A7%D9%84%D9%85%D8%B1%D8%A7%D9%83%D8%B2+%D8%A7%D9%84%D8%B9%D8%B1%D8%A8%D9%8A%D8%A9%E2%80%AD/@25.3289014,49.5517911,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3790c042c2fe9f:0x8cc10a23607e7fcb!8m2!3d25.3288966!4d49.5496024");
            }
        });

        s5 = findViewById(R.id.  most_viewed_location_item5);
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D9%81%D9%88%D8%A7%D8%B1%D8%B3+%D9%85%D9%88%D9%84%E2%80%AD/@25.3734747,49.5925158,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3796b8953faa4b:0xd01466c6118be5ed!8m2!3d25.3734699!4d49.5903271");
            }
        });

        s6 = findViewById(R.id.  most_viewed_location_item6);
        s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D8%A3%D8%B3%D9%88%D8%A7%D9%82+%D8%A7%D9%84%D9%85%D8%B2%D8%B1%D8%B9%D8%A9%E2%80%AD/@25.3735173,49.6078367,14z/data=!4m9!1m2!2m1!1z2KfYs9mI2KfZgiDYp9mE2YXYstix2LnYqQ!3m5!1s0x3e3797b6fed1d213:0xcd3579e599e7c8d3!8m2!3d25.4068343!4d49.5614153!15sChnYp9iz2YjYp9mCINin2YTZhdiy2LHYudipIgOIAQFaGyIZ2KfYs9mI2KfZgiDYp9mE2YXYstix2LnYqZIBC3N1cGVybWFya2V0");
            }

        });


        s7 = findViewById(R.id.  most_viewed_location_item7);
        s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/%D8%A7%D9%84%D8%B9%D8%AB%D9%8A%D9%85+%D9%85%D9%88%D9%84%E2%80%AD/@25.3735928,49.6078367,14z/data=!4m9!1m2!2m1!1z2KfZhNi52KvZitmFINmF2YjZhA!3m5!1s0x3e3796574b3afe87:0x4d005e16fc9a5367!8m2!3d25.400436!4d49.577898!15sChPYp9mE2LnYq9mK2YUg2YXZiNmEIgOIAQFaFSIT2KfZhNi52KvZitmFINmF2YjZhJIBD3Nob3BwaW5nX2NlbnRlcpoBI0NoWkRTVWhOTUc5blMwVkpRMEZuU1VOM2N6UkhVMFZSRUFF");
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}
