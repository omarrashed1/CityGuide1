package com.developerdepository.scout.User;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.developerdepository.scout.R;

import java.text.DecimalFormat;

public class CurrencyActivity extends AppCompatActivity {
    Spinner sp1,sp2;
    EditText ed1 ;
    Button b1;
    TextView result ;
    ImageButton backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        ed1 = findViewById(R.id.txtamt);
         result= findViewById(R.id.result1);
        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        String[] from = {"SAR","Indian Rupees","USD","EUR"};
        ArrayAdapter ad = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);


        String[] to = {"SAR","Indian Rupees","USD","EUR"};
        ArrayAdapter ad1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);

        b1 = findViewById(R.id.btn1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double tot;

                double amt = Double.parseDouble(ed1.getText().toString());
                DecimalFormat  dU = new  DecimalFormat("0.00");
                if(sp1.getSelectedItem().toString().equals("SAR") && sp2.getSelectedItem().toString().equals("Indian Rupees"))
                {

                    tot = amt *19.943;
                    String d = dU.format(tot);
                    result.setText(   d );
                }
                else if(sp1.getSelectedItem().toString().equals("SAR") && sp2.getSelectedItem().toString().equals("USD"))
                {
                    tot = amt / 3.75;
                    String d = dU.format(tot);
                    result.setText(   d );


                }


                else if(sp1.getSelectedItem().toString().equals("SAR") && sp2.getSelectedItem().toString().equals("EUR"))
                {
                    tot = amt * 0.23552;
                    String d = dU.format(tot);
                    result.setText(  d );
                }



                else if(sp1.getSelectedItem().toString().equals("Indian Rupees") && sp2.getSelectedItem().toString().equals("SAR"))
                {

                    tot = amt /19.943;
                    String d = dU.format(tot);
                    result.setText(   d );
                }
                else if(sp1.getSelectedItem().toString().equals("USD") && sp2.getSelectedItem().toString().equals("SAR"))
                {
                    tot = amt * 3.75;
                    String d = dU.format(tot);
                    result.setText(   d );


                }


                else if(sp1.getSelectedItem().toString().equals("EUR") && sp2.getSelectedItem().toString().equals("SAR"))
                {
                    tot = amt / 0.23552;
                    String d = dU.format(tot);
                    result.setText(  d );
                }
                else if (sp1.getSelectedItem().toString().equals("SAR") && sp2.getSelectedItem().toString().equals("SAR"))
                {
                    Toast.makeText(CurrencyActivity.this, "You can not choose same currency  ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(CurrencyActivity.this, "You have to chose from SAR to other currency or opposite ", Toast.LENGTH_SHORT).show();
                }



            }
        });



    }
}