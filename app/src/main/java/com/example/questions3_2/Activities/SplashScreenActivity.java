package com.example.questions3_2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    private SharedPreferences infoPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        infoPref = getSharedPreferences("info", MODE_PRIVATE);
        int coin = infoPref.getInt("coin", -1);
        if (coin == -1) {
            infoPref.edit().putInt("coin", 5).apply();
        }

        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}