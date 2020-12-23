package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Activity_a3_GamePlay extends AppCompatActivity {

    public int[] Sequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3__game_play);


        GetPassedVariables();
        Toast.makeText(this, String.valueOf(Sequence.length), Toast.LENGTH_LONG).show();

    }

    private void GetPassedVariables()
    {
        Sequence = getIntent().getIntArrayExtra("SEQUENCE_ARRAY");
    }

}