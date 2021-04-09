package com.example.questions3_2.Ultilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

public class GenerateQuesListTask extends AsyncTask<Void, Void, ArrayList<Integer>> {

    private Context context;
    private Intent intent;
    private ProgressDialog progressDialog;
    private long total, lv1, lv2, lv3;

    public GenerateQuesListTask(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        total = intent.getLongExtra("TOTAL", 0l);
        lv1 = intent.getLongExtra("LV1", 0l);
        lv2 = intent.getLongExtra("LV2", 0l);
        lv3 = intent.getLongExtra("LV3", 0l);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Questions");
        progressDialog.setMessage("Đang tạo màn chơi mới...");
        progressDialog.show();
    }

    @Override
    protected ArrayList<Integer> doInBackground(Void... voids) {
        ArrayList<Integer> quesArr = new ArrayList<>();
        quesArr.addAll(StupidFunc.rss(5, (int) lv1));
        quesArr.addAll(StupidFunc.rss(5, (int) lv2));
        quesArr.addAll(StupidFunc.rss(5, (int) lv3));
        return quesArr;
    }

    @Override
    protected void onPostExecute(ArrayList<Integer> integers) {
        super.onPostExecute(integers);

        progressDialog.dismiss();
    }
}
