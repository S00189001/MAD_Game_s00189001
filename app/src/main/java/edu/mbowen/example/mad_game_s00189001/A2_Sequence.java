package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class A2_Sequence extends AppCompatActivity {

    enum GameState
    {
        First,
        Wait,
        CountDown,
        Sequence,
        End
    }

    // States
    GameState currenState, afterWaitState;

    Button PlayBtn;
    TextView ReadyTimerDisp;
    CountDownTimer Timer;

    int ReadyTimerWait, SecondCounter, WaitCounter, WaitTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__sequence);

        ReadyTimerWait = 4;
        SecondCounter = 3;
        WaitCounter = 0;
        WaitTime = 4;

        // References
        ReadyTimerDisp = (TextView) findViewById(R.id.UI_a2_ReadyTimeDisp);
        PlayBtn = findViewById(R.id.UI_a2_PlayBtn);

        // Set up Timer
        Timer = new CountDownTimer(30000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                switch (currenState)
                {
                    case First:
                        break;
                    case Wait:
                        WaitCounter++;

                        if (WaitCounter >= WaitTime)
                            currenState = afterWaitState;
                        break;
                    case CountDown:

                        ReadyTimerDisp.setText(""+ SecondCounter);
                        SecondCounter--;

                        if (SecondCounter <= -1)
                        {
                            ReadyTimerDisp.setText("Memorize");
                            currenState = GameState.Sequence;
                        }
                        break;
                    case Sequence:

                        //

                        break;
                    case End:
                        break;
                }



            }

            @Override
            public void onFinish()
            {
                ReadyTimerDisp.setText("Memorize");
            }
        };

        // Initialise
        ReadyTimerDisp.setEnabled(false);

    }


    public void PlayButton(View _view)
    {
        PlayBtn.setEnabled(false);
        PlayBtn.setVisibility(View.INVISIBLE);
        ReadyTimerDisp.setEnabled(true);
        // Start Game Loop
        Timer.start();
        afterWaitState = GameState.CountDown;
        WaitTime = 2;
        WaitCounter = 0;
        currenState = GameState.Wait;
    }


}