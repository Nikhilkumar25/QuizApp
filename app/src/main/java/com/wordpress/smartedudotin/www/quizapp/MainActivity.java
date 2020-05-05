package com.wordpress.smartedudotin.www.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.DialerKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";


    private TextView mTxtQuestion;
    private Button btnTrue;
    private Button btnFalse;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizStats;

    private int userScore;

    private QuizModel[] questionCollection = new QuizModel[]{
      new QuizModel (R.string.q1,true),
            new QuizModel (R.string.q2,false),
            new QuizModel (R.string.q3,true),
            new QuizModel (R.string.q4,true),
            new QuizModel (R.string.q5,true),
            new QuizModel (R.string.q6,false),
            new QuizModel (R.string.q7,false),
            new QuizModel (R.string.q8,true),
            new QuizModel (R.string.q9,false),
            new QuizModel (R.string.q10,false),
            new QuizModel (R.string.q11,true),

    };

    final int USER_PROGRESS = (int) Math.ceil (100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        btnTrue = findViewById (R.id.btnTrue);
        btnFalse = findViewById (R.id.btnFalse);
        mProgressBar = findViewById (R.id.progressBar);
        mTxtQuestion = findViewById (R.id.txtQuiz);
        mQuizStats = findViewById (R.id.txtQuizStats);

        if(savedInstanceState != null){
            userScore =savedInstanceState.getInt (SCORE_KEY);
            mQuestionIndex= savedInstanceState.getInt (INDEX_KEY);
            mQuizStats.setText (""+ userScore);

        }else {
            userScore = 0;
            mQuestionIndex = 0;

        }


        QuizModel q1= questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion ();
        mTxtQuestion.setText (mQuizQuestion);




        btnTrue.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                evaluateUserGuess (true);

                changeQuestionOnButtonClick ();
                mQuizStats.setText(""+userScore);
            }
        });

        btnFalse.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                evaluateUserGuess (false);
                changeQuestionOnButtonClick ();
                mQuizStats.setText (""+userScore);
            }
        });


    }

    private void changeQuestionOnButtonClick(){
        mQuestionIndex = (mQuestionIndex+1)%11;

        if(mQuestionIndex==0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder (this);
            quizAlert.setCancelable (false);
            quizAlert.setTitle ("The Quiz is finished ");
            quizAlert.setMessage ("Your score is "+userScore);
            quizAlert.setPositiveButton ("Finish the quiz", new DialogInterface.OnClickListener () {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish ();
                }
            });
            quizAlert.show ();
        }

        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion ();

        mTxtQuestion.setText (mQuizQuestion);

        mProgressBar.incrementProgressBy (USER_PROGRESS);
    }

    private void evaluateUserGuess(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer ();

        if (currentQuestionAnswer==userGuess){
            Toast.makeText (getApplicationContext (),R.string.correct_toast_message, Toast.LENGTH_SHORT).show ();

            userScore = userScore+1;

        }else {
            Toast.makeText (getApplicationContext (),R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show ();
        }



    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState (outState);
        outState.putInt (SCORE_KEY, userScore);
        outState.putInt (INDEX_KEY, mQuestionIndex);
    }
}
