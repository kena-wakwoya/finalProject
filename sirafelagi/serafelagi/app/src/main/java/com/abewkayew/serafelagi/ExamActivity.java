package com.abewkayew.serafelagi;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import me.itangqi.waveloadingview.WaveLoadingView;

//import me.itangqi.waveloadingview.WaveLoadingView;

public class ExamActivity extends AppCompatActivity {
    Button startExam;
    TextView count_down_timer_exam_start;
    WaveLoadingView waveLoadingView;
    public static final int TOTAL_TIME = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
//        initialize all the views...
//        startExam = findViewById(R.id.start_exam_btn);
        count_down_timer_exam_start = findViewById(R.id.count_down_timer_exam_start);
        waveLoadingView = findViewById(R.id.waveLoadingView);
        waveLoadingView.setProgressValue(0);
//      set the onclick listener for the loadingView library...
        new CountDownTimer(TOTAL_TIME, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int)millisUntilFinished / 1000;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
//                count_down_timer_exam_start.setText(String.format("%02d", minutes)
//                        + " : " + String.format("%02d", seconds));
                count_down_timer_exam_start.setText(String.format("%01d", minutes * 60 + seconds)
                 + " seconds");
//                Toast.makeText(ExamActivity.this, "Total Seconds " + seconds, Toast.LENGTH_LONG).show();
                waveLoadingView.resumeAnimation();
                if(seconds == 14){
//                Toast.makeText(ExamActivity.this, "The first one is: " + seconds, Toast.LENGTH_SHORT).show();
                    waveLoadingView.setProgressValue(95);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 95));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 12){
//                    Toast.makeText(ExamActivity.this, "The second one is: " + seconds, Toast.LENGTH_SHORT).show();
                    waveLoadingView.setProgressValue(80);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 80));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 10){
                    waveLoadingView.setProgressValue(65);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 65));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 8){
                    waveLoadingView.setProgressValue(50);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 50));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 6){
                    waveLoadingView.setProgressValue(35);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 35));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 4){
                    waveLoadingView.setProgressValue(20);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 20));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 2){
                    waveLoadingView.setProgressValue(10);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 10));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }else if(seconds == 1){
                    waveLoadingView.setProgressValue(2);
//                    waveLoadingView.setBottomTitle(String.format("%d%%", 2));
                    waveLoadingView.setCenterTitle(String.format(""));
                    waveLoadingView.setTopTitle(String.format(""));
                }
            }

            public void onFinish() {
//              startExam();
                Intent intent = new Intent(ExamActivity.this, StartExam.class);
                // Activity is started with requestCode 2
                startActivityForResult(intent, 2);
                waveLoadingView.endAnimation();
            }
        }.start();
//        ksdofinasd faksdfonas dfasodfnaskdf asodifn
//        Intent intent = getIntent();
//        count_down_timer_exam_start.setText(getIntent().getIntExtra("score", 0));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){
            int score_test = data.getIntExtra("SCORE", 0);
            count_down_timer_exam_start.setText(score_test);

        }
    }
}
