package com.developerdepository.scout.User;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developerdepository.scout.Common.LoginSignup.RetailerStartUpScreenActivity;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.CategoriesAdapter;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.CategoriesModel;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.FeaturedLocationsAdapter;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.FeaturedModel;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.MostViewedLocationsAdapter;
import com.developerdepository.scout.HelperClasses.DashboardHelperClasses.MostViewedModel;
import com.developerdepository.scout.R;
import com.google.android.material.navigation.NavigationView;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;

import java.util.ArrayList;
import java.util.Locale;

import maes.tech.intentanim.CustomIntent;

public class UserDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Drawer Menu Variables
    private DrawerLayout dashboardDrawerLayout;
    private NavigationView dashboardNavigationMenu;

    //Dashboard View Variables
    private ConstraintLayout contentView, addPlacesBtn;
    private ImageButton dashboardMenu;
    private RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    private TextView categoriesViewAll;
    private ImageButton imgBtnRe, imgBtnHo, imgBtnEd, imgBtnSh;
    private Button button;
    //Other Variables
    private RecyclerView.Adapter featuredAdapter, mostViewedAdapter, categoriesAdapter;
    private static final float END_SCALE = 0.8f;

    Spinner spinner;
    public static final String[] languages = {"Select Language", "English", "Arabic"};

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
        setCategoriesRecycler();
        setActionOnViews();

        imgBtnRe = findViewById(R.id.imgBtnRe);
        imgBtnHo = findViewById(R.id.imgBtnHo);
        imgBtnEd = findViewById(R.id.imgBtnEd);
        imgBtnSh = findViewById(R.id.imgBtnSh);

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

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedLang = adapterView.getItemAtPosition(i).toString();

                if (selectedLang.equals("English")){
                    setLocal(UserDashboardActivity.this, "en");
                    finish();
                    startActivity(getIntent());
                }else if(selectedLang.equals("Arabic")){
                    setLocal(UserDashboardActivity.this, "ar");
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void initViews() {
        //Initialize Views

        //Drawer Menu
        dashboardDrawerLayout = findViewById(R.id.dashboard_drawer_layout);
        dashboardNavigationMenu = findViewById(R.id.dashboard_navigation_menu);

        //Dashboard Views
        contentView = findViewById(R.id.content_view);
        dashboardMenu = findViewById(R.id.dashboard_menu);
        addPlacesBtn = findViewById(R.id.add_places_btn);
        featuredRecycler = findViewById(R.id.featured_locations_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_locations_recycler);
        categoriesRecycler = findViewById(R.id.categories_recycler);
        categoriesViewAll = findViewById(R.id.categories_view_all);


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
            case R.id.nav_all_categories :
                startActivity(new Intent(UserDashboardActivity.this, AllCategoriesActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "left-to-right");
                break;
//            case R.id.nav_add_missing_place :
//                startActivity(new Intent(UserDashboardActivity.this, RetailerStartUpScreenActivity.class));
//                CustomIntent.customType(UserDashboardActivity.this, "bottom-to-up");
//                break;
            case R.id.nav_currency :
                startActivity(new Intent(UserDashboardActivity.this, CurrencyActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "bottom-to-up");
                break;
//            case R.id.languages :
//                setLocal(UserDashboardActivity.this, "ar");
//                finish();
//                startActivity(getIntent());
//                break;
//            case R.id.languages :
//                setLocal(UserDashboardActivity.this, "en");
//                finish();
//                startActivity(getIntent());
//                break;
        }
        if (item.getItemId() == R.id.languages){
            setLocal(UserDashboardActivity.this, "en");
            finish();
            startActivity(getIntent());
        }else {
            setLocal(UserDashboardActivity.this, "ar");
            finish();
            startActivity(getIntent());
        }
        return false;
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

        mostViewedLocations.add(new MostViewedModel(R.drawable.house, getString(R.string.HouseAllegiance), "The House of Allegiance or House of Allegiance is a historical house located in the Al Kut neighborhood of Al Hofuf Al Ahsa’iya city, Kingdom of Saudi Arabia. It is the house of Abdul Latif bin Abdul Rahman Al-Mulla, the head of the Al-Mulla family, and the house of the one who witnessed the people of Al-Ahsa pledge allegiance to King Abdul Aziz and their accession to the Saudi state in 1913.",  4.1, "https://goo.gl/maps/p5QXwJvbgVx8sT5D9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.ibrahem, getString(R.string.IbrahimPalace), "The Ibrahim Palace was built during the time of the first Saudi state and is considered to be an architectural masterpiece.",  4.2, "https://goo.gl/maps/d46xCcZon2Au4cLW9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.sahoood, getString(R.string.SahoodFort), "Sahood Fort is situated outside the western walls of Al Mubarraz, Saudi Arabia. The fort is used as barracks for the Saudi Arabian Armed Forces.",  4.0, "https://goo.gl/maps/7sE3UNmeVwJRtrh89"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.muqair, getString(R.string.OldAlUqayrSeaport), "Uqair is the site of numerous historic meetings between the founding king and foreign diplomats and the place in which he conducted negotiations with international political forces in the region.",  4.3, "https://goo.gl/maps/BDwqm4NjFLkApL397"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.school, getString(R.string.PrincesSchool), "The Princes School, the House of Culture, or the First Hofuf School is one of the oldest public schools in the Kingdom of Saudi Arabia,Its construction began in 1937 and was officially opened in February 1941.",  4.4, "https://goo.gl/maps/zziZ2NVQ24hA5uFV9"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.mehers, getString(R.string.MuhairisPalace), "Muhairis Palace is a palace built by Imam Saud bin Abdul Aziz on the top of a hill in 1208 AH for military purposes.",  3.5, "https://goo.gl/maps/pAWeGE75s8JaXH5T6"));
        mostViewedLocations.add(new MostViewedModel(R.drawable.khusam, getString(R.string.KhuzamPalace), "Khuzam Palace is located in the east of Al-Raqeqa neighborhood (currently Al-Mazrou’iya) located west of the city of Hofuf. It is the seat of the Bedouin residents who go to Al-Ahsa in the summer seasons for two months to exchange goods available in Al-Ahsa such as dates, sugar, some textiles, guns, ammunition and others for the goods they brought from the desert.",  2.5, "https://goo.gl/maps/8QXrya6cbGwX6oKm8"));

        mostViewedAdapter = new MostViewedLocationsAdapter(mostViewedLocations);

        mostViewedRecycler.setAdapter(mostViewedAdapter);
    }

    private void setCategoriesRecycler() {
        //Setting Categories Recycler
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(UserDashboardActivity.this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CategoriesModel> categories = new ArrayList<>();

        categories.add(new CategoriesModel(R.color.card2, R.drawable.illustration_shopping, R.string.navShops));
        categories.add(new CategoriesModel(R.color.card5, R.drawable.illustration_restaurant, R.string.navRestaurants));
        categories.add(new CategoriesModel(R.color.card4, R.drawable.illustration_hospital, R.string.navHotels));
        categories.add(new CategoriesModel(R.color.card1, R.drawable.illustration_education, R.string.navEducation));
        categories.add(new CategoriesModel(R.color.card3, R.drawable.illustration_travel, R.string.travel));

        categoriesAdapter = new CategoriesAdapter(categories);

        categoriesRecycler.setAdapter(categoriesAdapter);
    }

    private void setActionOnViews() {
        categoriesViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboardActivity.this, AllCategoriesActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "left-to-right");
            }
        });

        addPlacesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashboardActivity.this, RetailerStartUpScreenActivity.class));
                CustomIntent.customType(UserDashboardActivity.this, "bottom-to-up");
            }
        });
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