package com.mathiasluo.statusprogress;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mathiasluo.library.StatusProgress;
import com.mathiasluo.library.StatusProgressUtil;


public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StatusProgress statusProgress = StatusProgressUtil.setStatusProgress(this);

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 100);
                Log.e("-------->>", "123");
                statusProgress.incrementProgressBy(1);
            }
        };

        statusProgress.setStatusProgressBarListener(new StatusProgress.StatusProgressBarListener() {
            @Override
            public void onProgressChange(StatusProgress progressBar, int current, int max) {
                if (current == max) {
                    progressBar.closeProgressBar();
                    handler.removeCallbacks(runnable);
                }
            }
        });

        handler.post(runnable);


    }
}
