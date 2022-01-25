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

public class RestaurantsActivity extends AppCompatActivity {

    ConstraintLayout image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        image1= findViewById(R.id.category1);
        image2= findViewById(R.id.category2);
        image3= findViewById(R.id.category3);
        image4= findViewById(R.id.category4);
        image5= findViewById(R.id.category5);
        image6= findViewById(R.id.category6);
        image7= findViewById(R.id.category7);
        image8= findViewById(R.id.category8);
        image9= findViewById(R.id.category9);
        image10= findViewById(R.id.category10);

        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D8%AE%D9%8A%D8%A8%D8%B1&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=mRDIYc6cK5WDjLsP-6C9iAI&oq=%D9%85%D8%B7%D8%B9%D9%85+%D8%AE%D9%8A%D8%A8%D8%B1&gs_l=psy-ab.3..0i512k1l10.22138.22897.0.23276.4.3.0.1.1.0.163.428.0j3.3.0....0...1c.1.64.psy-ab..0.4.434...0i512i433k1j0i512i457k1j0i402k1.0.fgdFngHMTps#rlfi=hd:;si:14527542529829487933,l,ChHZhdi32LnZhSDYrtmK2KjYsUicv5KFiquAgAhaGxAAEAEYABgBIhHZhdi32LnZhSDYrtmK2KjYsZIBCnJlc3RhdXJhbnQ,y,MrqCNsYyYIc;mv:[[26.3297211,50.622726799999995],[25.298709900000002,49.5177386]]");
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D8%AA%D9%88%D8%A8%D8%A7%D8%B2&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=hhDIYcv4BLWFjLsPtvSpkAI&oq=%D9%85%D8%B7%D8%B9%D9%85+%D8%AA%D9%88%D8%A8%D8%A7%D8%B2&gs_l=psy-ab.3..0i512k1l10.15221.18227.0.18764.11.11.0.0.0.0.234.1189.0j7j1.8.0....0...1c.1.64.psy-ab..4.7.1049...0i67k1j0i512i433k1j0i512i433i131k1j0i512i433i457k1j0i402k1j0i512i457i10k1j0i512i10k1.0.snyBx6w383Q");
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D9%87%D9%88%D8%B1%D9%86+%D8%A7%D9%84%D8%A7%D8%AD%D8%B3%D8%A7%D8%A1&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=-g_IYfWpFb-cjLsPpaaN-Ag&oq=%D9%85%D8%B7%D8%B9%D9%85+%D9%87%D9%88%D8%B1%D9%86+%D8%A7%D9%84%D8%A7%D8%AD%D8%B3&gs_l=psy-ab.1.0.0i512k1j0i22i30k1l2.131441.137852.0.138917.15.15.0.0.0.0.232.1578.0j9j1.10.0....0...1c.1.64.psy-ab..5.10.1575...0i67k1j0i512i457k1j0i402k1j0i512i433k1j0i512i433i131k1j0i512i433i457k1j0i512i10k1.0.wIXJGIfxUxs");
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D8%A8%D9%87%D8%A7%D8%B1%D8%A7%D8%AA+%D8%A7%D9%84%D8%A7%D8%AD%D8%B3%D8%A7%D8%A1&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=Dw_IYb_aHNLFgwf3h4vYCg&oq=%D9%85%D8%B7%D8%B9%D9%85+%D8%A8%D9%87%D8%A7%D8%B1%D8%A7%D8%AA+&gs_l=psy-ab.3.0.0i512k1l10.201465.206827.0.210733.4.3.0.1.1.0.192.543.0j3.3.0....0...1c.1.64.psy-ab..0.4.553...0i67k1j0i512i457k1j0i402k1j0i512i433k1.0.5g7NWKgtv2o");
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D8%AF%D8%A7%D9%86%D9%83%D9%86&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=4w_IYZjdBIi1Uu3rhqgC&oq=%D9%85%D8%B7%D8%B9%D9%85+%D8%AF%D8%A7%D9%86%D9%83%D9%85&gs_l=psy-ab.1.0.0i13k1l3j0i13i30k1j0i13i10i30k1j0i13i30k1l5.17442.20584.0.22412.19.14.0.0.0.0.297.2115.0j4j6.10.0....0...1c.1.64.psy-ab..12.7.1335...0i67k1j0i22i30k1j0i512k1j0i512i457k1j0i402k1j0i512i433k1j0i512i433i131k1j0i512i433i457k1.0.FK_04-cJUv4#rlfi=hd:;si:17514715879789229857;mv:[[25.434141879987916,49.628460527099875],[25.289417902659114,49.53576338354519],null,[25.361801551562603,49.58211195532253],13]");
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85+%D9%84%D8%A7%D9%81%D8%A7+%D8%A7%D9%84%D9%87%D9%81%D9%88%D9%81&rlz=1C1GCEA_enSA969SA969&tbm=lcl&ei=eQzIYb7wLOaGjLsP0paz0Ac&oq=%D9%85%D8%B7%D8%B9%D9%85+%D9%84%D8%A7%D9%81%D8%A7&gs_l=psy-ab.3.2.0i512k1l3j0i10k1j0i512k1l4j0i10k1j0i512k1.654973.657801.0.660523.8.8.0.0.0.0.343.1088.0j5j0j1.6.0....0...1c.1.64.psy-ab..2.6.1085...0i22i30k1j0i22i10i30k1j0i512i433k1j0i512i433i131k1j0i512i433i457k1j0i402k1j0i512i10k1.0.UxkD90zLfA8");
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/search?q=%D9%85%D8%B7%D8%B9%D9%85%20%D9%83%D8%A7%D8%B2%D8%A7%20%D8%A8%D8%A7%D8%B3%D8%AA%D8%A7&rlz=1C1GCEA_enSA969SA969&ei=Gv7HYa7pM9LMaKnKtsgH&oq=%D9%85%D8%B7%D8%B9%D9%85+%D9%83%D8%A7%D8%B2%D8%A7&gs_lcp=Cgdnd3Mtd2l6EAEYATIFCAAQgAQyCAgAEIAEEMkDMgUIABCSAzIFCAAQkgMyCwguEIAEEMcBEK8BMgsILhCABBDHARCvATIFCAAQgAQyBQgAEIAEMgsILhCABBDHARCvATILCC4QgAQQxwEQrwE6BwgAEEcQsAM6CggAEEcQsAMQyQM6CAgAEJIDELADOhAILhDHARCvARDIAxCwAxBDOgoILhDHARCvARBDOgQIABBDOgYIABAKEEM6CAgAEIAEELEDOg4ILhCABBCxAxDHARCjAjoGCAAQFhAeOgUIABDNAkoECEEYAEoECEYYAFDzCljAQmClTmgFcAJ4AIABvQGIAZYMkgEDMC45mAEAoAEByAELwAEB&sclient=gws-wiz&tbs=lf:1,lf_ui:9&tbm=lcl&rflfq=1&num=10&rldimm=15137660111764660343&lqi=ChzZhdi32LnZhSDZg9in2LLYpyDYqNin2LPYqtinSPbNgsGZqoCACFo4EAAQARACGAAYARgCIhzZhdi32LnZhSDZg9in2LLYpyDYqNin2LPYqtinKgQIAxAAKgYIAhABEAKSARJpdGFsaWFuX3Jlc3RhdXJhbnQ&phdesc=Y3tLGzETiLA&ved=2ahUKEwjsjueA7ID1AhVOzRoKHUJHAaUQvS56BAgDEDY&rlst=f#rlfi=hd:;si:15137660111764660343,l,ChzZhdi32LnZhSDZg9in2LLYpyDYqNin2LPYqtinSPbNgsGZqoCACFo4EAAQARACGAAYARgCIhzZhdi32LnZhSDZg9in2LLYpyDYqNin2LPYqtinKgQIAxAAKgYIAhABEAKSARJpdGFsaWFuX3Jlc3RhdXJhbnQ,y,Y3tLGzETiLA;mv:[[26.4712374,50.206644999999995],[26.2951615,50.0207413]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!1m4!1u1!2m2!1m1!1e1!1m4!1u1!2m2!1m1!1e2!2m1!1e2!2m1!1e1!2m1!1e3!3sIAE,lf:1,lf_ui:9");
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/maps?q=%D9%85%D8%B7%D8%B9%D9%85+%D8%A7%D9%84%D9%83%D9%8A%D8%AA&rlz=1C1GCEA_enSA969SA969&um=1&ie=UTF-8&sa=X&sqi=2&ved=2ahUKEwiu3tSo3oD1AhVSJhoKHSmlDXkQ_AUoAXoECAEQAw");
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.google.com/maps/place/%D8%AD%D8%B3%D8%A7+%D8%B3%D8%AA%D9%8A%D9%83+%D8%A7%D9%86%D8%AF+%D9%82%D8%B1%D9%8A%D9%84%E2%80%AD/@25.3686363,49.5690765,17z/data=!3m1!4b1!4m5!3m4!1s0x3e3797c1c64d898b:0xc373d577e7e914b2!8m2!3d25.3686401!4d49.5669094");
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://g.page/tenderloinandco?share");
            }
        });

    }
    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}