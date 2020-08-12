package com.example.foreve;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.foreve.App.CHANNEL_ID;

public class ExampleService extends Service {
    private SensorManager sensorManager;
    private float acelVal;
    private float acelLast;
    private float shake;
    private MediaPlayer mediaPlayer;


    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String username, usermail, usermob, fr1mob, fr1mail, fr2mob, fr2mail, fr3mob, fr3mail;
    private static final String USER = "UserInfo";
    private String email;
    private LocationManager locationManager;
    double latitude;
    double longitude;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



    public ExampleService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        email = user.getEmail();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        onLocationChanged(location);


        database=FirebaseDatabase.getInstance();
        userRef=database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("useremail").getValue().equals(email)){
//                        textView.setText(ds.child("fr3Email").getValue(String.class));
                        username=ds.child("user").getValue(String.class);
                        usermail=ds.child("useremail").getValue(String.class);
                        usermob=ds.child("usermobile").getValue(String.class);
                        fr1mob=ds.child("fr1Mobile").getValue(String.class);
                        fr1mail=ds.child("fr1Email").getValue(String.class);
                        fr2mob =ds.child("fr2Mobile").getValue(String.class);
                        fr2mail=ds.child("fr2Email").getValue(String.class);
                        fr3mob=ds.child("fr3Mobile").getValue(String.class);
                        fr3mail=ds.child("fr3Email").getValue(String.class);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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


                String message="Danger Alert from foreve safety app: "+'\n'+"Your friend "+username+" is in danger at http://maps.google.com?q="+latitude+","+longitude;
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(fr1mob,null,message,null,null);
                //SmsManager smsManager=SmsManager.getDefault();
//                smsManager.sendTextMessage(fr2mob,null,message,null,null);
//                SmsManager sms3Manager=SmsManager.getDefault();
//                smsManager.sendTextMessage(fr3mob,null,message,null,null);
                String[] recipents={fr1mail,fr2mail,fr3mail};
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,recipents);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Your friend in danger,HELP HER");
                intent.putExtra(Intent.EXTRA_TEXT,message);
                intent.setType("message/rfc822");
                startActivity(intent);


            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    public void onLocationChanged(@NonNull Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        //textView.setText("Lat: "+latitude+'\n'+"Long: "+longitude);

    }

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
