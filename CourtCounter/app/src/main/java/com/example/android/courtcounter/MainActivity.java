package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreA = 0;
    private int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForATeam(scoreA);
    }

    public void displayForATeam (int score) {
        TextView scoreView = (TextView)findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForBTeam (int score) {
        TextView scoreView = (TextView)findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void increment(View view) {
        switch (view.getId())
        {
            case R.id.increment_3_a:
                scoreA += 3;
                displayForATeam(scoreA);
                break;

            case R.id.increment_2_a:
                scoreA += 2;
                displayForATeam(scoreA);
                break;

            case R.id.increment_1_a:
                scoreA += 1;
                displayForATeam(scoreA);
                break;

            case R.id.increment_3_b:
                scoreB += 3;
                displayForBTeam(scoreB);
                break;

            case R.id.increment_2_b:
                scoreB += 2;
                displayForBTeam(scoreB);
                break;

            case R.id.increment_1_b:
                scoreB += 1;
                displayForBTeam(scoreB);
                break;
        }
    }

    public void reset(View view) {
        scoreA = 0;
        scoreB = 0;
        displayForATeam(scoreA);
        displayForBTeam(scoreB);
    }

}
