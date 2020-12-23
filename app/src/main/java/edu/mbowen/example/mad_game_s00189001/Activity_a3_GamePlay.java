package edu.mbowen.example.mad_game_s00189001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class Activity_a3_GamePlay extends AppCompatActivity implements SensorEventListener {


    // Sensors
    private SensorManager mSensorManager;
    Sensor mSensor;
    // Buttons
    Button PlayBtn, LeftBtnImage, TopBtnImage, RightBtnImage, BottomBtnImage;

    int currentIndex, userInput;

    public int[] Sequence;
    public Button[] AllBtns;

    public boolean isFlat = false, takeUserInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3__game_play);


        // References
        LeftBtnImage = findViewById(R.id.UI_a3_LeftBtn);
        RightBtnImage = findViewById(R.id.UI_a3_RightBtn);
        TopBtnImage = findViewById(R.id.UI_a3_TopBtn);
        BottomBtnImage = findViewById(R.id.UI_a3_BottomBtn);

        // Filling the Array for Buttons
        AllBtns = new Button[] {LeftBtnImage, TopBtnImage, RightBtnImage, BottomBtnImage };

        GetPassedVariables();

        // Sensor Service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);

        Log.i("WIN", String.valueOf(Sequence.length));
        Log.i("WIN", String.valueOf(currentIndex));



    }

    private void GetPassedVariables()
    {
        Sequence = getIntent().getIntArrayExtra("SEQUENCE_ARRAY");
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];




        if (takeUserInput)
        {
            // Top
            if (x <= -2 && y < 1 && y > -1)
            {
                userInput = 1;
                ResetInput(1);
            }// Bot
            else if (x >= 2 && y < 1 && y > -1)
            {
                userInput = 3;
                ResetInput(3);
            }// Left
            else if(y <= -2 && x < 1 && x > -1)
            {
                userInput = 0;
                ResetInput(0);
            }// Right
            else if(y >= 2 && x < 1 && x > -1)
            {
                userInput = 2;
                ResetInput(2);
            }





        }// Flat
        else if (!isFlat)
        {
            if (x < 1 && x > -1 && y < 1 && y > -1)
            {
                isFlat = true;

                Log.i("IsFlat", String.valueOf(isFlat));
                AllBtns[userInput].setEnabled(false);

//                for (int i = 0; i >= AllBtns.length; i++)
//                {
//                    AllBtns[i].setEnabled(false);
//                    Log.i("IsFlat", String.valueOf(isFlat));
//
//                }

                if (userInput == Sequence[currentIndex])
                {
                    currentIndex++;
                    takeUserInput = true;
                }
                else
                {
                    Log.i("WIN", "You .. Lose");
                    mSensorManager.unregisterListener(this, mSensor);
                    GameOver();
                    finish();
                }
            }
        }

        // Full Sequence Finished
        if (currentIndex >= Sequence.length)
        {
            // win
            A2_Sequence.GLOBAL_CurrentHighScore += 4;
            Log.i("WIN", "You Win");
            mSensorManager.unregisterListener(this, mSensor);
            finish();
        }
    }

    void ResetInput(int _index)
    {
        takeUserInput = false;
        AllBtns[_index].setEnabled(true);
        isFlat = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void GameOver()
    {
        Intent GameOver = new Intent(this, Activity_a4_GameOver.class);
        startActivity(GameOver);
    }

}