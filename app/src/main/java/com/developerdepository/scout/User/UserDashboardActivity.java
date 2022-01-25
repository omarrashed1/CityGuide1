package com.developerdepository.scout.User;

import static com.developerdepository.scout.R.drawable.house;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.FeaturedLocationsAdapter;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.FeaturedModel;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.MostViewedLocationsAdapter;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.MostViewedModel;
import com.developerdepository.scout.R;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import maes.tech.intentanim.CustomIntent;

public class UserDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Drawer Menu Variables
    private  static final String API_KEY ="bb93b338055f73edcb743d4d348999e2";
    private DrawerLayout dashboardDrawerLayout;
    private NavigationView dashboardNavigationMenu;
    //Dashboard View Variables
    private ConstraintLayout contentView, categories_background;
    private RecyclerView featuredRecycler, mostViewedRecycler;
    private ImageButton imgBtnHo;
    private ImageButton imgBtnEd;
    private ImageButton imgBtnSh;
    private ImageButton dashboardMenu;
    private CardView w1;
    //Other Variables
    private RecyclerView.Adapter featuredAdapter, mostViewedAdapter;
    private static final float END_SCALE = 0.8f;

    TextView tVtemp  ,etCityName, descriptionw1, maxtemp , mintemp;
    private ImageView iconWeather;
    String city = "Hofuf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        //StatusBar Color
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.dashboard_background));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initViews();

        setNavigationMenu();
        setFeaturedRecycler();
        setMostViewedRecycler();


        //    private TextView categoriesViewAll;
        ImageButton imgBtnRe = findViewById(R.id.imgBtnRe);
        imgBtnHo = findViewById(R.id.imgBtnHo);
        imgBtnEd = findViewById(R.id.imgBtnEd);
        imgBtnSh = findViewById(R.id.imgBtnSh);
        w1 = findViewById(R.id.weathercard);
        categories_background =findViewById(R.id.categories_background);

        if (isNetworkConnected()){
            loadWeatherByCityName(city);
        }else {
            Toast.makeText(UserDashboardActivity.this, R.string.errorWIFI,Toast.LENGTH_SHORT).show();
        }
            w1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (isNetworkConnected()){
                        loadWeatherByCityName(city);
                    }else {
                        Toast.makeText(UserDashboardActivity.this, R.string.errorWIFI,Toast.LENGTH_SHORT).show();
                    }
                }
            });

        imgBtnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, RestaurantsActivity.class));
            }
        });

        imgBtnHo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, HotelsActivity.class));

            }
        });

        imgBtnEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, EducationActivity.class));
            }
        });

        imgBtnSh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboardActivity.this, ShopsActivity.class));
            }
        });


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void initViews() {
        //Initialize Views

        //Drawer Menu
        dashboardDrawerLayout = findViewById(R.id.dashboard_drawer_layout);
        dashboardNavigationMenu = findViewById(R.id.dashboard_navigation_menu);

        //Dashboard Views
        contentView = findViewById(R.id.content_view);
        dashboardMenu = findViewById(R.id.dashboard_menu);
        featuredRecycler = findViewById(R.id.featured_locations_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_locations_recycler);
        etCityName = findViewById(R.id.CityName);
        tVtemp = findViewById(R.id.tVtemp);
        iconWeather = findViewById(R.id.iconWeather);
        descriptionw1 = findViewById(R.id.descriptionw1);
        maxtemp = findViewById(R.id.maxtemp);
        mintemp = findViewById(R.id.mintemp);

    }

    private void setNavigationMenu() {
        dashboardNavigationMenu.bringToFront();
        dashboardNavigationMenu.setNavigationItemSelectedListener(UserDashboardActivity.this);
        dashboardNavigationMenu.setCheckedItem(R.id.nav_home);

        dashboardMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dashboardDrawerLayout.isDrawerVisible(GravityCompat.START))
                    dashboardDrawerLayout.closeDrawer(GravityCompat.START);
                else dashboardDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();
    }

    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
                case R.id.nav_home :
                startActivity(new Intent(UserDashboardActivity.this, UserDashboardActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "left-to-right");
                break;
            case R.id.nav_currency :
                startActivity(new Intent(UserDashboardActivity.this, CurrencyActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "bottom-to-up");
                break;
            case R.id.nav_share:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                i.putExtra(Intent.EXTRA_TEXT, "https://bit.ly/3qcOtAB");
                startActivity(Intent.createChooser(i, "Share URL"));
                break;
            case R.id.nav_rate_us:
                gotoUrl("https://bit.ly/3qcOtAB");
                break;
        }
//        change
        if (item.getItemId() == R.id.languages && getString(R.string.lang).equals("English")){
            setLocal(UserDashboardActivity.this, "en");
            finish();
            startActivity(getIntent());
        }else if (item.getItemId() == R.id.languages && getString(R.string.lang).equals("اللغة العربية")){
            setLocal(UserDashboardActivity.this, "ar");
            finish();
            startActivity(getIntent());
        }

        return false;
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    private void animateNavigationDrawer() {
        dashboardDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    private void setFeaturedRecycler() {
        //Setting Featured Locations Recycler
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(UserDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false ));

        ArrayList<FeaturedModel> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedModel(R.drawable.thumbnail_16, R.string.UqairBeach, R.string.aluqairdec,4.1, "https://goo.gl/maps/VsvYoYf4hKAYZKdq6"));
        featuredLocations.add(new FeaturedModel(R.drawable.garah2, R.string.AlQarah, R.string.alqaradec,4.1,"https://goo.gl/maps/sif9HkYrGtzmLopY7"));
        featuredLocations.add(new FeaturedModel(R.drawable.ysea, R.string.AlAsfar, R.string.alasfardec,3.9, "https://goo.gl/maps/HcwayaM5mNTmFkys7"));
        featuredLocations.add(new FeaturedModel(R.drawable.juatha, R.string.JawathaCity, R.string.jawadec,4.4, "https://goo.gl/maps/ChwGniSrBxQ3dH4m6"));
        featuredLocations.add(new FeaturedModel(R.drawable.alarba, R.string.AlArbaa, R.string.alarbadec,4.2, "https://goo.gl/maps/dMR1ipYXiwkMxMz77"));
        featuredLocations.add(new FeaturedModel(R.drawable.alshabah, R.string.AlShu, R.string.alshudec,4.0, "https://goo.gl/maps/ic6gMSsEL8cVNsiu8"));
        featuredLocations.add(new FeaturedModel(R.drawable.park, R.string.KingAbdullahPark, R.string.kingabdudec,3.9, "https://goo.gl/maps/kHq7VxmTwDS5zjox6"));
        featuredLocations.add(new FeaturedModel(R.drawable.zoo, R.string.ZooPark, R.string.zoodec,3.3, "https://goo.gl/maps/MDtYUvrYjx6fT49X8"));

        featuredAdapter = new FeaturedLocationsAdapter(featuredLocations);

        featuredRecycler.setAdapter(featuredAdapter);
    }

    private void setMostViewedRecycler() {
        //Setting Most Viewed Locations Recycler
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(UserDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewedModel> mostViewedLocations = new ArrayList<>();

        mostViewedLocations.add(new MostViewedModel(house, getString(R.string.HouseAllegiance), getString(R.string.housedec),  4.1, "https://goo.gl/maps/p5QXwJvbgVx8sT5D9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.ibrahem, getString(R.string.IbrahimPalace), getString(R.string.ibrahimdec),  4.2, "https://goo.gl/maps/d46xCcZon2Au4cLW9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.sahoood, getString(R.string.SahoodFort), getString(R.string.sahooddec),  4.0, "https://goo.gl/maps/7sE3UNmeVwJRtrh89"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.muqair, getString(R.string.OldAlUqayrSeaport), getString(R.string.oladec),  4.3, "https://goo.gl/maps/BDwqm4NjFLkApL397"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.school, getString(R.string.PrincesSchool), getString(R.string.princedec),  4.4, "https://goo.gl/maps/zziZ2NVQ24hA5uFV9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.mehers, getString(R.string.MuhairisPalace), getString(R.string.Muhdec),  3.5, "https://goo.gl/maps/pAWeGE75s8JaXH5T6"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.khusam, getString(R.string.KhuzamPalace), getString(R.string.khuzdec),  2.5, "https://goo.gl/maps/8QXrya6cbGwX6oKm8"));

        mostViewedAdapter = new MostViewedLocationsAdapter(mostViewedLocations);

        mostViewedRecycler.setAdapter(mostViewedAdapter);
    }
    private void loadWeatherByCityName(String city) {
        Ion.with(this)
                .load("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+ API_KEY )
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        NumberFormat nf =NumberFormat.getInstance(new Locale("en","EN"));

                        // do stuff with the result or error
                        if (e != null){
                            e.printStackTrace();
                            Toast.makeText(UserDashboardActivity.this, "SERVER ERROR", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            JsonObject  main = result.get("main").getAsJsonObject();
                            double temp = main.get("temp").getAsDouble();
                            String s = nf.format(temp - 273.15) + "°C";
                            tVtemp.setText(s);

                            JsonArray weather = result.get("weather").getAsJsonArray();
                            String description = weather.get(0).getAsJsonObject().get("main").getAsString();

                            double tempmax = main.get("temp_max").getAsDouble();
                            String s1 = nf.format( tempmax - 273.15)+ "°C";
                            maxtemp.setText(getString(R.string.m)+ "\n" + s1);

                            double tempmin = main.get("temp_min").getAsDouble();
                            String s2 = nf.format(tempmin - 273.15) +"°C";
                            mintemp.setText(getString(R.string.n)+"\n"+s2);

                            if (getString(R.string.lang).equals("English")){
                                ardscrip(description);
                            }else if (getString(R.string.lang).equals("اللغة العربية")){
                                descriptionw1.setText(StringUtils.capitalize(description));
                            }

                            String icon = weather.get(0).getAsJsonObject().get("icon").getAsString();
                            loadicon(icon);

                        }
                        Log.d("result",result.toString());
                    }
                });
    }

    private void ardscrip(String description) {

        switch (description)
        {
            case "Clouds":
                descriptionw1.setText("غائم");
                break;
                case "Thunderstorm":
                descriptionw1.setText("عاصفة رعدية");
                break;
                case "Drizzle":
                descriptionw1.setText("ندى المطر");
                break;
                case "Rain":
                descriptionw1.setText("مطر");
                break;
                case "Snow":
                descriptionw1.setText("ثلوج");
                break;
                case "Mist":
                descriptionw1.setText("ضباب");
                break;
                case "Smoke":
                descriptionw1.setText("دُُخان");
                break;
                case "Haze":
                descriptionw1.setText("ضباب");
                break;
                case "Dust":
                descriptionw1.setText("غبار");
                break;
                case "Fog":
                descriptionw1.setText("ضباب");
                break;
                case "Sand":
                descriptionw1.setText("تراب");
                break;
                case "Ash":
                descriptionw1.setText("غبار كثيف");
                break;
                case "Squall":
                descriptionw1.setText("عاصفة");
                break;
                case "Tornado":
                descriptionw1.setText("اعصار");
                break;
                case "Clear":
                descriptionw1.setText("صافي");
                break;
                default: descriptionw1.setText("جاري التحميل ..");

        }

    }

    private void loadicon(String icon) {
        switch(icon) {
                case "01d":

                iconWeather.setImageResource(R.drawable.sun);
                categories_background.setBackgroundResource(R.drawable.clear1);

                break;

                case "01n":
                    categories_background.setBackgroundResource(R.drawable.clearnight);
                iconWeather.setImageResource(R.drawable.moon);
                break;

                case "02d":
                 categories_background.setBackgroundResource(R.drawable.scatteredclouds);
                iconWeather.setImageResource( R.drawable.cloudyday);
                break;

                case "02n":
                    categories_background.setBackgroundResource(R.drawable.scatteredcloudsnight);
                iconWeather.setImageResource( R.drawable.night);
                break;

                case "03d":
                    categories_background.setBackgroundResource(R.drawable.fewclouds);
                iconWeather.setImageResource( R.drawable.cloudy);
                break;

                case "03n":
                    categories_background.setBackgroundResource(R.drawable.fewcloudsnight);
                iconWeather.setImageResource( R.drawable.cloudy);
                break;

                case "04d":
                    categories_background.setBackgroundResource(R.drawable.brokenclouds);
                iconWeather.setImageResource( R.drawable.cloudcomputing);
                break;

                case "04n":
                    categories_background.setBackgroundResource(R.drawable.brokencloudsnight);
                iconWeather.setImageResource( R.drawable.cloudcomputing);
                break;

                case "09d":
                    categories_background.setBackgroundResource(R.drawable.showerrain);
                iconWeather.setImageResource( R.drawable.rainy);
                break;

                case "09n":
                    categories_background.setBackgroundResource(R.drawable.showerrainaight);
                iconWeather.setImageResource( R.drawable.rainy);
                break;

                case "10d":
                    categories_background.setBackgroundResource(R.drawable.rain);
                iconWeather.setImageResource( R.drawable.raining);
                break;

                case "10n":
                    categories_background.setBackgroundResource(R.drawable.rainight);
                iconWeather.setImageResource( R.drawable.rainynight);
                break;

                case "11d":
                    categories_background.setBackgroundResource(R.drawable.thunderstorm1);
                iconWeather.setImageResource( R.drawable.thunderstorm);
                break;

                case "11n":
                    categories_background.setBackgroundResource(R.drawable.thunderstormnight);
                 iconWeather.setImageResource( R.drawable.thunderstorm);
                 break;

                 case "13d":
                     categories_background.setBackgroundResource(R.drawable.snow);
                 iconWeather.setImageResource( R.drawable.snowflake);
                 break;

                 case "13n":
                     categories_background.setBackgroundResource(R.drawable.snownight);
                 iconWeather.setImageResource( R.drawable.snowflake);
                 break;

                 case "50d":
                     categories_background.setBackgroundResource(R.drawable.mist1);
                 iconWeather.setImageResource( R.drawable.mist);
                 break;

                 case "50n":
                     categories_background.setBackgroundResource(R.drawable.mistnight);
                 iconWeather.setImageResource( R.drawable.mist);
                 break;

                 default:
                     Glide.with(this).load(R.drawable.loading).into(iconWeather);
                // code block
        }

    }

    @Override
    public void onBackPressed() {
        if(dashboardDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            dashboardDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            MaterialDialog materialDialog = new MaterialDialog.Builder(UserDashboardActivity.this)
                    .setTitle(getResources().getString(R.string.exitDialogTitle))
                    .setMessage(getResources().getString(R.string.exitDialogMessage))
                    .setCancelable(false)
                    .setPositiveButton(getResources().getString(R.string.exitDialogConfirm), R.drawable.ic_material_dialog_confirm, new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();
                            finishAffinity();
                        }
                    })
                    .setNegativeButton(getResources().getString(R.string.exitDialogCancel), R.drawable.ic_material_dialog_cancel, new MaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();
                        }
                    }).build();
            materialDialog.show();
        }
    }


}