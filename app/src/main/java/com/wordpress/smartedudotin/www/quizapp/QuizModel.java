package com.wordpress.smartedudotin.www.quizapp;

public class QuizModel {

    private int mQuestion;
    private boolean mAnswer;

    public QuizModel(int question, boolean answer){

        mQuestion = question;
        mAnswer = answer;
    }

    public int getmQuestion() {
        return mQuestion;
    }

    public void setmQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
