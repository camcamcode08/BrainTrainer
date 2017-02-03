package com.camcamcode.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnGo;
    TextView resultTextView;
    TextView pointsTextView;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    Integer locationOfCorrectAnswer;
    Integer score = 0;
    Integer numberOfQuestions = 0;

    TextView timertextview;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView sumTextView;
    GridLayout answerGridLayout;
    RelativeLayout startRelativeLayout;
    public void go(View view){
        btnGo.setVisibility(view.INVISIBLE);
        startRelativeLayout.setVisibility(startRelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    public  void  chooseAnswer(View view){
        resultTextView.setVisibility(resultTextView.VISIBLE);
        if ( view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            Log.i("Answer", "Correct");
            resultTextView.setText("Correct!");
            score++;
        }
        else {
            resultTextView.setText("Wrong!");
            Log.i("Answer", "Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions) );
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo = (Button)findViewById(R.id.btnGo);
        resultTextView = (TextView) findViewById(R.id.resulttextview);
        pointsTextView = (TextView) findViewById(R.id.pointstextview);
        timertextview = (TextView) findViewById(R.id.timertextview);
        resultTextView.setVisibility(resultTextView.INVISIBLE);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        sumTextView = (TextView) findViewById(R.id.sumtextview);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        answerGridLayout = (GridLayout) findViewById(R.id.answerGridLayout);
        startRelativeLayout = (RelativeLayout)findViewById(R.id.startRelativeLayout) ;

        startRelativeLayout.setVisibility(startRelativeLayout.INVISIBLE);
        btnGo.setVisibility(btnGo.VISIBLE);
        playAgainButton.setVisibility(playAgainButton.INVISIBLE);



    }
    public void generateQuestion(){
        Random  rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        manipulateGridLayout(true);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answer.clear();
        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;
        for (int i=0;i<4;i++){
            if (locationOfCorrectAnswer == i) {
                answer.add(a + b);
            }
            else{
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answer.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        resultTextView.setText("");
        pointsTextView.setText("0/0");
        playAgainButton.setVisibility(playAgainButton.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timertextview.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish(){
                timertextview.setText("0s");
                resultTextView.setText("Your score is " +  Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(playAgainButton.VISIBLE);
                manipulateGridLayout(false);
            }
        }.start();
        generateQuestion();
    }

    public  void manipulateGridLayout(boolean ans){
        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            View child = answerGridLayout.getChildAt(i);
            child.setEnabled(ans);
        }

    }
}
