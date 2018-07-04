package com.abewkayew.serafelagi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.ArcProgress;

import java.util.Collections;
import java.util.List;
public class StartExam extends AppCompatActivity {

//    declare all the views right here...
    private TextView viewQuestions, viewScore, viewCountDownTimer,
                     questionLeft, exam_finished_textView;
    private TextView viewQuestionsCount;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button confirmNext, next_question;
    private TextView text_view_question;
    private ColorStateList textColorDefaultRb;
    private ProgressBar progressBar;

    private List<QuestionsModel> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuestionsModel currentQuestion;

    private int score;
    private boolean answered;

    private ArcProgress arcProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_exam);
        viewQuestions = findViewById(R.id.text_view_question);
        viewScore = findViewById(R.id.view_score);
        arcProgress = findViewById(R.id.arc_progress);
        viewQuestionsCount = findViewById(R.id.question_count);
        viewCountDownTimer = findViewById(R.id.count_down_timer);
        radioGroup  = findViewById(R.id.radio_group);
        radioButton1 = findViewById(R.id.radio_button_question_1);
        radioButton2 = findViewById(R.id.radio_button_question_2);
        radioButton3 = findViewById(R.id.radio_button_question_3);
        radioButton4 = findViewById(R.id.radio_button_question_4);
        confirmNext = findViewById(R.id.confirm_next_btn);
        next_question = findViewById(R.id.next_question);
        questionLeft = findViewById(R.id.questionLeft);
        exam_finished_textView = findViewById(R.id.exam_finished_textView);
        textColorDefaultRb = radioButton1.getTextColors();
        text_view_question = findViewById(R.id.text_view_question);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(100);
        progressBar.setMinimumHeight(100);
//        progressBar.setProgress(10, true);
//      start the countdown timer for the EXAM...
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int)millisUntilFinished / 1000;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;

                viewCountDownTimer.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
                progressBar.setProgress(minutes*60 + seconds);
                arcProgress.setBottomText("Time Left");
                arcProgress.setProgress(minutes*60 + seconds);
//                checkAnswer();
//                viewCountDownTimer.setTextColor(getResources().getColor(R.color.black));
            }

            public void onFinish() {
                String message = "20 seconds are added";
                Toast.makeText(StartExam.this, message, Toast.LENGTH_SHORT).show();
                finishActivityWithMessage();

            }
        }.start();

//        initialize the DB helper database...
        ExamDbHelper examDbHelper = new ExamDbHelper(this);
        questionList = examDbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
        confirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!answered){
                    if(radioButton1.isChecked() || radioButton2.isChecked() ||
                            radioButton3.isChecked() || radioButton4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(StartExam.this, "First, select an answer!!", Toast.LENGTH_SHORT).show();
                        Intent cameraIntent = new
                                Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(cameraIntent);
                    }
                }else{
                   showNextQuestion();
                    Intent cameraIntent = new
                            Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                }

            }
        });

//        display the SCORE result for the first activity that invoked this activity....

        int score_test = 25;
        Intent intent = new Intent();
        intent.putExtra("SCORE",score_test);
        setResult(2,intent);

    }
    public int getTotalScore(){
        return score;
    }
    private void finishActivityWithMessage() {
        final String message = "The time is over.";
        viewCountDownTimer.setTextColor(Color.RED);
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int)millisUntilFinished / 1000;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                int total_time = minutes * 60 + seconds;
                if(total_time >= 10){
                    viewCountDownTimer.setText(String.format("%02d", minutes)
                            + ":" + String.format("%02d", seconds));
                }else{
                    viewCountDownTimer.setText(String.format("%01d", minutes *60 + seconds));
                }

                checkAnswer();
                arcProgress.setProgress(minutes*60 + seconds);
//                viewCountDownTimer.setTextColor(getResources().getColor(R.color.black));
            }

            public void onFinish() {
                //      handle the notification implementation here...

                Intent intent = new Intent(StartExam.this, Medicine.class);
//                int totalScore = getIntent().getIntExtra("totalScore", 0);
                PendingIntent pIntent = PendingIntent.getActivity(StartExam.this, 0, intent, 0);
                NotificationCompat.Builder notification = new NotificationCompat.Builder(StartExam.this);
                        notification.setTicker("sera-Felagi");
                        notification.setAutoCancel(true);
                        notification.setDefaults(NotificationCompat.DEFAULT_ALL);
                        notification.setContentInfo("Congratulation");
                        notification.setContentTitle("SeraFelagi");
                        notification.setWhen(20000);
                        notification.setContentText("You have scored " + checkAnswer() + " point");
                        notification.addAction(R.drawable.ic_apply, "Visit their office", pIntent);
                        notification.setSmallIcon(R.drawable.ic_notifications);
                        notification.setVibrate(new long[] { 1000, 1000});
                        notification.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                        notification.setColor(100);
                        notification.setContentIntent(pIntent).getNotification();
//                notification.flags = Notification.DEFAULT_VIBRATE;
//                notification.setColor() = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;

                NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//        nManager.wait();
                nManager.notify(1, notification.build());





                AlertDialog alertDialog = new AlertDialog.Builder(StartExam.this).create();
                alertDialog.setTitle("confirmation message");
                alertDialog.setMessage("We will notify ASAP if u pass the exam\nYour score is: " + checkAnswer());
                alertDialog.setIcon(R.drawable.ic_apply);
                alertDialog.setCancelable(false);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(StartExam.this, Medicine.class);
                        intent.putExtra("totalScore", checkAnswer());
                        startActivity(intent);
                    }
                });
                alertDialog.show();

            }
        }.start();
    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(StartExam.this, "Do u really want to exit?", Toast.LENGTH_SHORT).show();
        final AlertDialog alertDialog = new AlertDialog.Builder(StartExam.this).create();
        alertDialog.setTitle("Warning Message");
        alertDialog.setMessage("You can't just leave the exam once you get inside.");
        alertDialog.setIcon(R.drawable.ic_apply);

        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(StartExam.this, "Be quite and finish ur exam questions",
//                        Toast.LENGTH_SHORT).show();
                alertDialog.cancel();
            }
        });

        alertDialog.show();
    }

    private int checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNumber = radioGroup.indexOfChild(rbSelected) + 1;
        if(answerNumber == currentQuestion.getAnswerNumber()){
            score++;
//            notifyScore(score);
        }
//        Toast.makeText(StartExam.this, "Score : " + score, Toast.LENGTH_LONG).show();
        showSolution();
        return score;
    }

    private void notifyScore( final int score) {

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                int totalSeconds = (int)millisUntilFinished / 1000;
                int minutes = totalSeconds / 60;
                int seconds = totalSeconds % 60;
                text_view_question.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
                text_view_question.setTextColor(getResources().getColor(R.color.black));
            }

            public void onFinish() {
//                Toast.makeText(StartExam.this, "My score is: " + score, Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(StartExam.this).create();
                alertDialog.setTitle("Confirmation message");
                alertDialog.setMessage("We will notify u ASAP if u pass the exam");
                alertDialog.setIcon(R.drawable.ic_apply);

                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(StartExam.this, Medicine.class);
                        startActivity(intent);
                    }
                });

                alertDialog.show();
            }

        }.start();

    }

    private void showSolution() {
//        radioButton1.setTextColor(Color.RED);
//        radioButton2.setTextColor(Color.RED);
//        radioButton3.setTextColor(Color.RED);
//        radioButton4.setTextColor(Color.RED);
//        switch (currentQuestion.getAnswerNumber()){
//            case 1:
//                radioButton1.setTextColor(Color.GREEN);
//                break;
//            case 2:
//                radioButton2.setTextColor(Color.GREEN);
//                break;
//            case 3:
//                radioButton3.setTextColor(Color.GREEN);
//                break;
//            case 4:
//                radioButton4.setTextColor(Color.GREEN);
//
//        }

        if(questionCounter <= questionCountTotal){
//            confirmNext.setText("Next");
            confirmNext.setVisibility(View.INVISIBLE);
            next_question.setVisibility(View.VISIBLE);
            next_question.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!answered){
                        if(radioButton1.isChecked() || radioButton2.isChecked() ||
                                radioButton3.isChecked() || radioButton4.isChecked()){
//                            checkAnswer();
                            showNextQuestion();
                        }else{
                            Toast.makeText(StartExam.this, "First, select an answer!!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        showNextQuestion();
                    }
//                    showNextQuestion();
                }
            });
        }else{
            next_question.setText("Finish");
            checkAnswer();
        }
    }

    private void showNextQuestion() {
        radioButton1.setTextColor(textColorDefaultRb);
        radioButton2.setTextColor(textColorDefaultRb);
        radioButton3.setTextColor(textColorDefaultRb);
        radioButton4.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();
//        check for the existence of the next question....
        if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);
            viewQuestions.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getOption1());
            radioButton2.setText(currentQuestion.getOption2());
            radioButton3.setText(currentQuestion.getOption3());
            radioButton4.setText(currentQuestion.getOption4());

//            increment the question counter...
            questionCounter++;
            viewQuestionsCount.setText(""+questionCounter+". ");
            answered = false;
            confirmNext.setText("CONFIRM");
            questionLeft.setText("Questions Left: "  + (questionCountTotal - questionCounter));
            questionLeft.setTextSize(30);
        }else{
            finishExam();
        }
    }

    private void finishExam() {
        finish();
    }
}
