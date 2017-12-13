package com.example.mars.zonedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
        {
            //Toast.makeText(this, "AUTH" + auth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,
                    MainActivity.class));
            finish();
        }
        else {
            startActivityForResult(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .setLogo(R.mipmap.ic_launcher)
                            .setTheme(R.style.AppTheme)
                            .build(),
                    RC_SIGN_IN);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                //user logged in
                //Toast.makeText(this, "AUTH" + auth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,
                        MainActivity.class));
                finish();
            }
            else{
                //user not authenticated
                //Toast.makeText(this, "USER NOT AUTHENTICATED", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }
}

