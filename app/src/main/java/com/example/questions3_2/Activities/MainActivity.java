package com.example.questions3_2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.questions3_2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SharedPreferences infoPref;
    private TextView tvCoin;
    private Button btnStart, btnUpgrade, btnExit;

    private long total = -1, lv1 = -1, lv2 = -1, lv3 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCoin = findViewById(R.id.tv_coin);
        btnStart = findViewById(R.id.btn_start);
        btnUpgrade = findViewById(R.id.btn_upgrade);
        btnExit = findViewById(R.id.btn_exit);

        btnStart.setOnClickListener(this);
        btnUpgrade.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        db.document("main/questions").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        total = document.getLong("total");
                        lv1 = document.getLong("lv1");
                        lv2 = document.getLong("lv2");
                        lv3 = document.getLong("lv3");
                    } else {
                        Log.e("ERROR", "No such document");
                    }
                } else {
                    Log.e("ERROR", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCoin();
    }

    private void updateCoin() {
        infoPref = getSharedPreferences("info", MODE_PRIVATE);
        tvCoin.setText("$ " + infoPref.getInt("coin", 0));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_start: {
                if (total > 0 && lv1 > 0 && lv2 > 0 && lv3 > 0) {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("TOTAL", total);
                    intent.putExtra("LV1", lv1);
                    intent.putExtra("LV2", lv2);
                    intent.putExtra("LV3", lv3);
                    startActivity(intent);
                }
                break;
            }
            case R.id.btn_upgrade: {
                break;
            }
            case R.id.btn_exit: {
                super.finish();
                break;
            }
        }
    }
}