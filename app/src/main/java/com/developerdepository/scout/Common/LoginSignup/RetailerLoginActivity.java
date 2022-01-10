package com.developerdepository.scout.Common.LoginSignup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.developerdepository.scout.Databases.SessionManager;
import com.developerdepository.scout.LocationOwner.RetailerDashboardActivity;
import com.developerdepository.scout.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.tapadoo.alerter.Alerter;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.util.HashMap;

import dmax.dialog.SpotsDialog;
import maes.tech.intentanim.CustomIntent;

public class RetailerLoginActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private CountryCodePicker loginCCP;
    private TextInputLayout mobileInputLayout, passwordInputLayout;
    private CheckBox rememberMe;
    private Button loginBtn;
    private TextView forgotPassword, createAccountBtn;


    AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_login);

        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);

        //Transparent StatusBar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        progressDialog = new SpotsDialog.Builder().setContext(RetailerLoginActivity.this)
                .setMessage("Logging you in...")
                .setCancelable(false)
                .setTheme(R.style.SpotsDialog)
                .build();

        initViews();
        setActionOnViews();

        //Check whether mobile and password are already saved in shared preferences or not
        SessionManager rememberMeSessionManager = new SessionManager(RetailerLoginActivity.this, SessionManager.SESSION_REMEMBERME);
        if(rememberMeSessionManager.checkRememberMe()) {
            HashMap<String, String> rememberMeDetails = rememberMeSessionManager.getRememberMeDetailsFromSession();
            mobileInputLayout.getEditText().setText(rememberMeDetails.get(SessionManager.KEY_SESSIONMOBILE));
            passwordInputLayout.getEditText().setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));
        }

        KeyboardVisibilityEvent.setEventListener(RetailerLoginActivity.this, new KeyboardVisibilityEventListener(){
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if(!isOpen)
                {
                    mobileInputLayout.clearFocus();
                    passwordInputLayout.clearFocus();
                }
            }
        });
    }

    private void initViews() {
        backBtn = findViewById(R.id.back_arrow_btn);
        loginCCP = findViewById(R.id.retailer_login_ccp);
        mobileInputLayout = findViewById(R.id.retailer_login_mobile_field);
        passwordInputLayout = findViewById(R.id.retailer_login_password_field);
        rememberMe = findViewById(R.id.retailer_login_remember_me_checkbox);
        forgotPassword = findViewById(R.id.retailer_login_forgot_password);
        loginBtn = findViewById(R.id.retailer_login_login_btn);
        createAccountBtn = findViewById(R.id.retailer_login_create_account_btn);
    }

    private void setActionOnViews() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetailerLoginActivity.this, ForgotPasswordActivity.class));
                CustomIntent.customType(RetailerLoginActivity.this, "left-to-right");
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.hideKeyboard(RetailerLoginActivity.this);

                if(!validateMobile() | !validatePassword()) {
                    return;
                } else {
                    if(!isConnectedToInternet(RetailerLoginActivity.this)) {
                        showConnectToInternetDialog();
                        return;
                    } else {
                        progressDialog.show();

                        final String mobile = "+" + loginCCP.getSelectedCountryCode() + mobileInputLayout.getEditText().getText().toString().trim();
                        final String password = passwordInputLayout.getEditText().getText().toString().trim();

                        if(rememberMe.isChecked()) {
                            SessionManager rememberMeSessionManager = new SessionManager(RetailerLoginActivity.this, SessionManager.SESSION_REMEMBERME);
                            rememberMeSessionManager.createRememberMeSession(mobileInputLayout.getEditText().getText().toString().trim(), passwordInputLayout.getEditText().getText().toString().trim());
                        }

                        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("mobile").equalTo(mobile);
                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()) {
                                    mobileInputLayout.setError(null);
                                    mobileInputLayout.setErrorEnabled(false);

                                    String systemPassword = dataSnapshot.child(mobile).child("password").getValue(String.class);
                                    if(systemPassword.equals(password)) {
                                        progressDialog.dismiss();
                                        passwordInputLayout.setError(null);
                                        passwordInputLayout.setErrorEnabled(false);

                                        String _name = dataSnapshot.child(mobile).child("name").getValue(String.class);
                                        String _username = dataSnapshot.child(mobile).child("username").getValue(String.class);
                                        String _email = dataSnapshot.child(mobile).child("email").getValue(String.class);
                                        String _password = dataSnapshot.child(mobile).child("password").getValue(String.class);
                                        String _gender = dataSnapshot.child(mobile).child("gender").getValue(String.class);
                                        String _dateOfBirth = dataSnapshot.child(mobile).child("dateOfBirth").getValue(String.class);
                                        String _mobile = dataSnapshot.child(mobile).child("mobile").getValue(String.class);

                                        SessionManager userSessionManager = new SessionManager(RetailerLoginActivity.this, SessionManager.SESSION_USERSESSION);
                                        userSessionManager.createLoginSession(_name, _username, _email, _password, _gender, _dateOfBirth, _mobile);

                                        startActivity(new Intent(RetailerLoginActivity.this, RetailerDashboardActivity.class));
                                        CustomIntent.customType(RetailerLoginActivity.this, "left-to-right");

                                        Toast.makeText(RetailerLoginActivity.this, "Logged In Successfully!", Toast.LENGTH_LONG).show();
                                    } else {
                                        progressDialog.dismiss();
                                        Alerter.create(RetailerLoginActivity.this)
                                                .setText("Whoa! Invalid Credentials. Try Again!")
                                                .setTextAppearance(R.style.ErrorAlert)
                                                .setBackgroundColorRes(R.color.errorColor)
                                                .setIcon(R.drawable.ic_error)
                                                .setDuration(3000)
                                                .enableSwipeToDismiss()
                                                .enableIconPulse(true)
                                                .enableVibration(true)
                                                .disableOutsideTouch()
                                                .enableProgress(true)
                                                .setProgressColorInt(getResources().getColor(android.R.color.white))
                                                .show();
                                    }
                                } else {
                                    progressDialog.dismiss();
                                    Alerter.create(RetailerLoginActivity.this)
                                            .setText("Whoa! There's no account with that mobile!")
                                            .setTextAppearance(R.style.ErrorAlert)
                                            .setBackgroundColorRes(R.color.errorColor)
                                            .setIcon(R.drawable.ic_error)
                                            .setDuration(3000)
                                            .enableSwipeToDismiss()
                                            .enableIconPulse(true)
                                            .enableVibration(true)
                                            .disableOutsideTouch()
                                            .enableProgress(true)
                                            .setProgressColorInt(getResources().getColor(android.R.color.white))
                                            .show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                progressDialog.dismiss();
                                Alerter.create(RetailerLoginActivity.this)
                                        .setText("Whoa! There was some error!")
                                        .setTextAppearance(R.style.ErrorAlert)
                                        .setBackgroundColorRes(R.color.errorColor)
                                        .setIcon(R.drawable.ic_error)
                                        .setDuration(3000)
                                        .enableSwipeToDismiss()
                                        .enableIconPulse(true)
                                        .enableVibration(true)
                                        .disableOutsideTouch()
                                        .enableProgress(true)
                                        .setProgressColorInt(getResources().getColor(android.R.color.white))
                                        .show();
                            }
                        });
                    }
                }
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RetailerLoginActivity.this, RetailerSignUp1Activity.class));
                CustomIntent.customType(RetailerLoginActivity.this, "fadein-to-fadeout");
                finish();
            }
        });
    }

    private boolean isConnectedToInternet(RetailerLoginActivity retailerLoginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) retailerLoginActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    private void showConnectToInternetDialog() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(RetailerLoginActivity.this)
                .setTitle("No Internet Connection!")
                .setMessage("Please connect to a network first to proceed further!")
                .setCancelable(false)
                .setAnimation(R.raw.no_internet_connection)
                .setPositiveButton("Connect", R.drawable.ic_material_dialog_connect, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", R.drawable.ic_material_dialog_cancel, new MaterialDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                }).build();
        materialDialog.show();
    }

    private boolean validateMobile() {
        String mobile = mobileInputLayout.getEditText().getText().toString().trim();

        if(mobile.isEmpty()) {
            mobileInputLayout.setError("Field can't be empty.");
            return false;
        } else if(mobile.length() != 10) {
            mobileInputLayout.setError("Invalid Mobile. Try Again!");
            return false;
        } else {
            mobileInputLayout.setError(null);
            mobileInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = passwordInputLayout.getEditText().getText().toString().trim();

        if(password.isEmpty()) {
            passwordInputLayout.setError("Field can't be empty.");
            return false;
        } else {
            passwordInputLayout.setError(null);
            passwordInputLayout.setErrorEnabled(false);
            return true;
        }
    }

    public static void setWindowFlag(RetailerLoginActivity retailerLoginActivity, final int bits, boolean on) {
        Window window = retailerLoginActivity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();

        if(on){
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}