package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

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
    GameState currentState = GameState.First, afterWaitState;

    Button PlayBtn, LeftBtnImage, TopBtnImage, RightBtnImage, BottomBtnImage;
    TextView ReadyTimerDisp;
    CountDownTimer Timer;

    public int ReadyTimerWait, SecondCounter, WaitCounter, WaitTime, CurrentIndex;
    public int SequenceDiff = 3;

    public boolean isHighlighted = false;

    public Button[] AllBtns;
    public int[] Sequence;

    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2__sequence);

        ReadyTimerWait = 4;
        SecondCounter = 1;
        WaitCounter = 0;
        WaitTime = 4;

        // References
        ReadyTimerDisp = findViewById(R.id.UI_a2_ReadyTimeDisp);
        PlayBtn = findViewById(R.id.UI_a2_PlayBtn);
        LeftBtnImage = findViewById(R.id.UI_a2_LeftBtn);
        RightBtnImage = findViewById(R.id.UI_a2_RightBtn);
        TopBtnImage = findViewById(R.id.UI_a2_TopBtn);
        BottomBtnImage = findViewById(R.id.UI_a2_BottomBtn);

        // Filling the Array for Buttons
        AllBtns = new Button[] {LeftBtnImage, TopBtnImage, RightBtnImage, BottomBtnImage };

        // Set up Timer
        Timer = new CountDownTimer(30000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                switch (currentState)
                {
                    // Setup
                    case First:
                        CurrentIndex = 0;
                        PlayBtn.setEnabled(true);
                        PlayBtn.setVisibility(View.VISIBLE);
                        ReadyTimerDisp.setText("Get Ready");
                        isHighlighted =  false;
                        CreateSequence();
                        GoToWait(GameState.CountDown, Integer.MAX_VALUE);
                        break;
                    case Wait:
                        WaitCounter++;

                        if (WaitCounter >= WaitTime)
                            currentState = afterWaitState;
                        break;
                    case CountDown:

                        ReadyTimerDisp.setText(""+ SecondCounter);
                        SecondCounter--;

                        if (SecondCounter <= -1)
                        {
                            ReadyTimerDisp.setText("Memorize");
                            currentState = GameState.Sequence;
                        }
                        break;
                    case Sequence:

                        PlaySequence();
                        break;
                    case End:

                        SequenceDiff += 2;
                        PassToPlayIntent();
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
        // Start Game Loop
        Timer.start();

    }

    public void CreateSequence()
    {
            Sequence = new int[SequenceDiff];

            for (int i = 0; i < Sequence.length; i++)
            {
                Sequence[i] = random.nextInt(4);
            }
        //currenState = GameState.Wait;
    }

    public void PlaySequence()
    {
        if (CurrentIndex >= Sequence.length)
        {
            //Toast.makeText(this, "In Play Sequence", Toast.LENGTH_SHORT).show();
            currentState = GameState.End;
            return;
        }

        // Check if highlighted or not
        if (isHighlighted)
        {
            AllBtns[Sequence[CurrentIndex]].setEnabled(false);
            isHighlighted = false;
            CurrentIndex++;
        }
        else
        {
            AllBtns[Sequence[CurrentIndex]].setEnabled(true);
            GoToWait(GameState.Sequence, 1);
            isHighlighted = true;
        }



    }

    public void PlayButton(View _view)
    {
        PlayBtn.setEnabled(false);
        PlayBtn.setVisibility(View.INVISIBLE);
        ReadyTimerDisp.setEnabled(true);
        GoToWait(GameState.CountDown, 1);
    }

    private void GoToWait(GameState _gameState, int _waitTime)
    {
        afterWaitState = _gameState;
        WaitTime = _waitTime;
        WaitCounter = 0;
        currentState = GameState.Wait;
    }


    private void PassToPlayIntent()
    {
        Intent GamePlay = new Intent(this, Activity_a3_GamePlay.class);
        GamePlay.putExtra("SEQUENCE_ARRAY", Sequence);
        currentState = GameState.First;
        startActivity(GamePlay);
    }

}