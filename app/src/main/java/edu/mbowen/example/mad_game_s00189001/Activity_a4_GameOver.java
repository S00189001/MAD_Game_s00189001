package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class Activity_a4_GameOver extends AppCompatActivity {

    TextView rScore;

    Button MainMenuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        // References
        rScore = findViewById(R.id.UI_a4_RoundScore);


        rScore.setText("You Scored " + String.valueOf(A2_Sequence.GLOBAL_CurrentHighScore) + " !");
        MainMenuBtn = findViewById(R.id.UI_a4_MainMenuBtn);

        MainMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                A2_Sequence.GLOBAL_CurrentHighScore = 0;
                finish();
            }});

    }

    public void MainMenu(View view)
    {
        finish();
    }
}