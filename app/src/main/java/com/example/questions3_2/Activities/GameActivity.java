package com.example.questions3_2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.questions3_2.R;
import com.example.questions3_2.Ultilities.GenerateQuesListTask;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences infoPref;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Toolbar tb;
    private TextView tvQuesNum, tvQues;
    private Button btnAnsA, btnAnsB, btnAnsC, btnAnsD;

    private ArrayList<Integer> quesArr;
    private ArrayList<String> ansArr;
    private boolean activeButton = false;
    private int nowQues = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tb = findViewById(R.id.tb_game);
        tvQuesNum = findViewById(R.id.tv_ques_num);
        tvQues = findViewById(R.id.tv_ques);
        btnAnsA = findViewById(R.id.btn_ans_a);
        btnAnsB = findViewById(R.id.btn_ans_b);
        btnAnsC = findViewById(R.id.btn_ans_c);
        btnAnsD = findViewById(R.id.btn_ans_d);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultWin();
                GameActivity.super.onBackPressed();
            }
        });
        btnAnsA.setOnClickListener(this);
        btnAnsB.setOnClickListener(this);
        btnAnsC.setOnClickListener(this);
        btnAnsD.setOnClickListener(this);

        crQuesArr();
        crAnsArr();
        onPlay(nowQues);
    }

    private void crQuesArr() {
        try {
            quesArr = new GenerateQuesListTask(this, this.getIntent()).execute().get();
            Log.e("Hàng về ", quesArr.toString());
        } catch (ExecutionException | InterruptedException e) {
            Log.e("ERROR", "Lỗi khi nhận hàng từ GenerateQuesListTask về!");
        }
    }

    private void crAnsArr() {
        ansArr = new ArrayList<>();
        ansArr.add("ansTrue");
        ansArr.add("ansFalse1");
        ansArr.add("ansFalse2");
        ansArr.add("ansFalse3");
    }

    private void onPlay(int i) {
        Collections.shuffle(ansArr);
        tvQuesNum.setText("Câu số " + (i + 1));
        btnAnsA.setBackgroundResource(android.R.drawable.btn_default);
        btnAnsB.setBackgroundResource(android.R.drawable.btn_default);
        btnAnsC.setBackgroundResource(android.R.drawable.btn_default);
        btnAnsD.setBackgroundResource(android.R.drawable.btn_default);
        db.document("main/questions/level" + (int)(i / 5 + 1) + "/" + quesArr.get(i))
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        tvQues.setText(document.getString("ques"));
                        btnAnsA.setText(document.getString(ansArr.get(0)));
                        btnAnsB.setText(document.getString(ansArr.get(1)));
                        btnAnsC.setText(document.getString(ansArr.get(2)));
                        btnAnsD.setText(document.getString(ansArr.get(3)));
                        activeButton = true;
                    } else {
                        Log.e("ERROR", "No such question!");
                    }
                } else {
                    Log.e("ERROR", task.getException().getMessage());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_ans_a: {
                if (activeButton) {
                    if (ansArr.get(0) == "ansTrue") {
                        btnAnsA.setBackgroundColor(getResources().getColor(R.color.colorTrue));
                        activeButton = false;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (nowQues < 14) {
                            onPlay(++nowQues);
                        } else {
                            ++nowQues;
                            resultWin();
                            super.onBackPressed();
                        }
                    } else {
                        btnAnsA.setBackgroundColor(getResources().getColor(R.color.colorFalse));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resultLose();
                        super.onBackPressed();
                    }
                }
                break;
            }
            case R.id.btn_ans_b: {
                if (activeButton) {
                    if (ansArr.get(1) == "ansTrue") {
                        btnAnsB.setBackgroundColor(getResources().getColor(R.color.colorTrue));
                        activeButton = false;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (nowQues < 14) {
                            onPlay(++nowQues);
                        } else {
                            ++nowQues;
                            resultWin();
                            super.onBackPressed();
                        }
                    } else {
                        btnAnsB.setBackgroundColor(getResources().getColor(R.color.colorFalse));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resultLose();
                        super.onBackPressed();
                    }
                }
                break;
            }
            case R.id.btn_ans_c: {
                if (activeButton) {
                    if (ansArr.get(2) == "ansTrue") {
                        btnAnsC.setBackgroundColor(getResources().getColor(R.color.colorTrue));
                        activeButton = false;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (nowQues < 14) {
                            onPlay(++nowQues);
                        } else {
                            ++nowQues;
                            resultWin();
                            super.onBackPressed();
                        }
                    } else {
                        btnAnsC.setBackgroundColor(getResources().getColor(R.color.colorFalse));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resultLose();
                        super.onBackPressed();
                    }
                }
                break;
            }
            case R.id.btn_ans_d: {
                if (activeButton) {
                    if (ansArr.get(3) == "ansTrue") {
                        btnAnsD.setBackgroundColor(getResources().getColor(R.color.colorTrue));
                        activeButton = false;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (nowQues < 14) {
                            onPlay(++nowQues);
                        } else {
                            ++nowQues;
                            resultWin();
                            super.onBackPressed();
                        }
                    } else {
                        btnAnsD.setBackgroundColor(getResources().getColor(R.color.colorFalse));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        resultLose();
                        super.onBackPressed();
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        resultWin();
        super.onBackPressed();
    }

    private void resultWin() {
        infoPref = getSharedPreferences("info", MODE_PRIVATE);
        int[] coinLv = getResources().getIntArray(R.array.coinLv);
        int coin = infoPref.getInt("coin", 0);
        if (nowQues >= 1) {
            coin += coinLv[nowQues - 1];
        }
        infoPref.edit().putInt("coin", coin).apply();
    }

    private void resultLose() {
        infoPref = getSharedPreferences("info", MODE_PRIVATE);
        int[] coinLv = getResources().getIntArray(R.array.coinLv);
        int coin = infoPref.getInt("coin", 0);
        if (nowQues >= 10) {
            coin += coinLv[9];
        } else if (nowQues >= 5) {
            coin += coinLv[4];
        }
        infoPref.edit().putInt("coin", coin).apply();
    }
}