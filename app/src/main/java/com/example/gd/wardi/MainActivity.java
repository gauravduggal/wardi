package com.example.gd.wardi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView NewText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main);
        NewText = (TextView)findViewById(R.id.textView2);
        // Get an instance of the SensorManager
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    private double gravity[] = new double[] {0, 0, 0};
    private double linear_acceleration[] = new double[] {0, 0, 0};
    private double magnitude = 0;
    private double roll = 0;

    public void onSensorChanged(SensorEvent event){

        final double alpha = 0.8;

        // Isolate the force of gravity with the low-pass filter.
        this.gravity[0] = alpha * this.gravity[0] + (1 - alpha) * event.values[0];
        this.gravity[1] = alpha * this.gravity[1] + (1 - alpha) * event.values[1];
        this.gravity[2] = alpha * this.gravity[2] + (1 - alpha) * event.values[2];
        this.magnitude = Math.sqrt(this.gravity[0]*this.gravity[0]+this.gravity[1]*this.gravity[1]+this.gravity[2]*this.gravity[2]);

        this.roll = Math.atan2(this.gravity[1],this.magnitude);
        this.roll = this.roll*180/3.1415;
        Log.d("check","roll"+roll);
        if(this.roll>15)
        {
            NewText.setText(" Don't you get bored of me?");

        }
        else if(this.roll<-15)
        {
            NewText.setText(" See I told you");
        }
        else
            NewText.setText(" This text will change");
        setContentView(R.layout.activity_main);

    }


}
