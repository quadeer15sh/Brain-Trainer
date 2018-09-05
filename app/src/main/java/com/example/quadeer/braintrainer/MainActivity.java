package com.example.quadeer.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();
    Random rand = new Random();
    ConstraintLayout mainLay;
    Button goButton;
    int correctAnsLoc;
    TextView result;
    TextView question;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreView;
    TextView timeCount;
    int a,b;

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        playAgain.setVisibility(View.INVISIBLE);
        scoreView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        createQuestions();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeCount.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                float ans = (((float)score/(float)numberOfQuestions)*100);
                result.setText(String.valueOf(Math.round(ans))+"%");
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }

    public void createQuestions(){
        a = rand.nextInt(21);
        b = rand.nextInt(21);

        question.setText(Integer.toString(a)+" + "+Integer.toString(b));
        answers.clear();
        correctAnsLoc = rand.nextInt(4);

        for (int i = 0; i<4; i++){
            if (i == correctAnsLoc){
                answers.add(a+b);
            }
            else {
                int wrongAns = rand.nextInt(41);
                while (wrongAns == a+b)
                    wrongAns = rand.nextInt(41);
                answers.add(wrongAns);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        mainLay.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.scoreView));
    }

    public void correctAnswer(View view){
        if (Integer.toString(correctAnsLoc).equals(view.getTag().toString())){
            result.setText("Correct!");
            score++;
        }
        else{
            result.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        createQuestions();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.buttonGo);

        question = findViewById(R.id.question);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        result = findViewById(R.id.result);
        scoreView = findViewById(R.id.scoreView);
        timeCount = findViewById(R.id.timeCount);
        mainLay = findViewById(R.id.mainLay);
        playAgain = findViewById(R.id.playAgain);

        goButton.setVisibility(View.VISIBLE);
        mainLay.setVisibility(View.INVISIBLE);

    }
}
