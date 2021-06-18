package com.example.drysister.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.drysister.R;
import com.example.drysister.bean.entity.Sister;
import com.example.drysister.imgloader.PictureLoader;
import com.example.drysister.network.SisterApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showBtn;
    private Button refreshBtn;
    private ImageView showImg;

    private ArrayList<Sister> data;
    private int curPos = 0;
    private int page = 3;
    private PictureLoader loader;
    private SisterApi sisterApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sisterApi = new SisterApi();
        loader = new PictureLoader();
        initData();
        initUI();
    }

    private void initUI() {
        showBtn = findViewById(R.id.btn_show);
        refreshBtn = findViewById(R.id.btn_refresh);
        showImg = findViewById(R.id.img_show);

        showBtn.setOnClickListener(this);
        refreshBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_show) {
            if (curPos > 9) {
                curPos = 0;
            }
            Log.d("TEST", "data size: " + data.size());
            loader.load(showImg, data.get(curPos).getUrl());
            curPos++;
//            Toast.makeText(MainActivity.this, String.valueOf(curPos), Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.btn_refresh) {
            page++;
            new SisterTask(page).execute();
            curPos = 0;
        }
    }

    private void initData() {
        data = new ArrayList<>();
        new SisterTask(page).execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class SisterTask extends AsyncTask<Void, Void, ArrayList<Sister>> {
        private int page;

        public SisterTask(int page) {
            this.page = page;
        }

        @Override
        protected ArrayList<Sister> doInBackground(Void... voids) {
            return sisterApi.fetchSister(10, page);
        }

        @Override
        protected void onPostExecute(ArrayList<Sister> sisters) {
            super.onPostExecute(sisters);
            data.clear();
            data.addAll(sisters);
        }
    }
}