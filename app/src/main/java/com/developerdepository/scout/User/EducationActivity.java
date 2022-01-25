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

public class EducationActivity extends AppCompatActivity {

    ConstraintLayout c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // KFU
        c1 = findViewById(R.id.category1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/SW5XTG35de323jGF8");
            }
        });

        // Imam mohammad
        c2 = findViewById(R.id.category2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/Cccfvy2iGTzWKX9P7");
            }
        });

        // ksa
        c3 = findViewById(R.id.category3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/tavahMMwRKn7NLK39");
            }
        });

        //Arab Open
        c4 = findViewById(R.id.category4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/pau44zqGjccypgCYA");
            }
        });

        //niti
        c5 = findViewById(R.id.category5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://g.page/niti_ksa?share");
            }
        });

        // technical and training
        c6 = findViewById(R.id.category6);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/BCUoFQLJaV1pVsac9");
            }
        });

        //Alkhaleej
        c7 = findViewById(R.id.category7);
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/YqTcwnV4AaHxs8BG9");
            }
        });

        // wall street
        c8 = findViewById(R.id.category8);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/nLuehgKCXN9GJqN86");
            }
        });

        // al-njal
        c9 = findViewById(R.id.category9);
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://g.page/Anjal_School?share");
            }
        });

        // Future vision
        c10 = findViewById(R.id.category10);
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://g.page/Future-Vision-Private-Schools?share");
            }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}