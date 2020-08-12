package com.example.foreve;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import static com.example.foreve.App.CHANNEL_ID;

public class ExampleService extends Service {
    private SensorManager sensorManager;
    private float acelVal;
    private float acelLast;
    private float shake;
    private MediaPlayer mediaPlayer;

    public ExampleService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        acelVal=SensorManager.GRAVITY_EARTH;
        acelLast=SensorManager.GRAVITY_EARTH;
        shake=0.00f;
        mediaPlayer=MediaPlayer.create(this,R.raw.siren);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent notificationIntent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);

        Notification notification=new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Foreve")
                .setContentText("Shake to turn on foreve")
                .setSmallIcon(R.drawable.foreve_round)
                .setContentIntent(pendingIntent)
                .build();


        startForeground(1,notification);

        return START_NOT_STICKY;
    }

    public final SensorEventListener sensorListener= new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x=sensorEvent.values[0];
            float y=sensorEvent.values[1];
            float z=sensorEvent.values[2];

            acelLast=acelVal;
            acelVal=(float)Math.sqrt((double)(x*x+y*y+z*z));
            float delta=acelVal-acelLast;
            shake=shake*0.9f+delta;


            if(shake>12)
            {
//                Intent intent= new Intent(ExampleService.this,SplashActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);


                mediaPlayer.start();
                mediaPlayer.setLooping(true);


            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//
//    }

//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int i) {
//
//    }
}
