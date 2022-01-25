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

public class HotelsActivity extends AppCompatActivity {

    ConstraintLayout h1,h2,h3,h4,h5,h6,h7,h8,h9,h10;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        h1 = findViewById(R.id.category1);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/ea3J2DudJ4AKFaQUA");
            }
        });

        h2 = findViewById(R.id.category2);
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/1L8PLCAWrUofUfDPA");
            }
        });

        h3 = findViewById(R.id.category3);
        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/9jTC1tjWVg7kMUkE6");
            }
        });

        h4 = findViewById(R.id.category4);
        h4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/JTzXRjviQ6Ve2ikw5");
            }
        });

        h5 = findViewById(R.id.category5);
        h5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/EYok6CUkPYsQNvbCA");
            }
        });

        h6 = findViewById(R.id.category6);
        h6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/HFcC61MP972z5wxD9");
            }
        });

        h7 = findViewById(R.id.category7);
        h7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://g.page/Taleentaleen?share");
            }
        });

        h8 = findViewById(R.id.category8);
        h8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://g.page/garden_plaza_sa?share");
            }
        });

        h9 = findViewById(R.id.category9);
        h9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/o5185sNTcUdbgFFA7");
            }
        });

        h10 = findViewById(R.id.category10);
        h10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://goo.gl/maps/hgSDEEfJc8VxAFzw9");
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
