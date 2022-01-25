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
import androidx.appcompat.app.AppCompatActivity;
import com.developerdepository.scout.HelperClasses.CURRNCYHELP.Retrofit.RetrofitBuilder;
import com.developerdepository.scout.HelperClasses.CURRNCYHELP.Retrofit.RetrofitInterface;
import com.developerdepository.scout.R;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {
    Button button;
    EditText currencyToBeConverted;
    TextView currencyConverted;
    Spinner convertToDropdown;
    Spinner convertFromDropdown;
    ImageButton backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        //Initialization
        currencyConverted = (TextView) findViewById(R.id.result1);
        currencyToBeConverted = (EditText) findViewById(R.id.txtamt);
        convertToDropdown = (Spinner) findViewById(R.id.spto);
        convertFromDropdown = (Spinner) findViewById(R.id.spfrom);
        button = (Button) findViewById(R.id.btn1);

        backbtn = findViewById(R.id.back_arrow_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        //Adding Functionality
        String[] dropDownList = {"USD", "INR","EUR","EGP","SAR","PHP","QAR","BHD","KWD","AED","OMR","YER"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, dropDownList);
        convertToDropdown.setAdapter(adapter);
        convertFromDropdown.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (currencyToBeConverted.length() == 0){
                currencyToBeConverted.setError(getString(R.string.error));

            }else {
                RetrofitInterface retrofitInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitInterface.class);
                Call<JsonObject> call = retrofitInterface.getExchangeCurrency(convertFromDropdown.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();
                        JsonObject rates = res.getAsJsonObject("conversion_rates");
                        double currency = Double.valueOf(currencyToBeConverted.getText().toString());
                        double multiplier = Double.valueOf(rates.get(convertToDropdown.getSelectedItem().toString()).toString());
                        double result = currency * multiplier;
                        currencyConverted.setText(String.valueOf(result));
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }

        }
    });

    }
}