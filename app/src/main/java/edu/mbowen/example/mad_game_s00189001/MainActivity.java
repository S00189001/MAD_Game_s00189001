package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





    public void StartGame(View _view)
    {
        // Create an Activity
        Intent A2_SequenceI = new Intent(this, A2_Sequence.class);
        // Start Activity
        startActivity(A2_SequenceI);
    }

    public void HighScores(View _view)
    {

    }

    public void QuitGame(View _view)
    {
        System.exit(0);
    }

}