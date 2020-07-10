package com.example.foreve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UnsafeActivity extends AppCompatActivity implements LocationListener {

    private Button unsafeButton;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String username, usermail, usermob, fr1mob, fr1mail, fr2mob, fr2mail, fr3mob, fr3mail;
    private static final String USER = "UserInfo";
    private String email;
    private TextView textView;
    private LocationManager locationManager;
    double latitude;
    double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsafe);

        //ActivityCompat.requestPermissions(UnsafeActivity.this,new String[] {Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS},PackageManager.PERMISSION_GRANTED);

//        Intent i=getIntent();
//        email=i.getStringExtra("mail");
        email = DemoClass.msg;

        unsafeButton = (Button) findViewById(R.id.unsafeButtonId);
        textView = (TextView) findViewById(R.id.textViewId);
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


        unsafeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message="Danger Alert from foreve safety app: "+'\n'+"Your friend "+username+" is in danger at http://maps.google.com?q="+latitude+","+longitude;
                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(fr1mob,null,message,null,null);
                //SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(fr2mob,null,message,null,null);
//                SmsManager sms3Manager=SmsManager.getDefault();
                smsManager.sendTextMessage(fr3mob,null,message,null,null);
                String[] recipents={fr1mail,fr2mail,fr3mail};
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,recipents);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Your friend in danger,HELP HER");
                intent.putExtra(Intent.EXTRA_TEXT,message);
                intent.setType("message/rfc822");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        textView.setText("Lat: "+latitude+'\n'+"Long: "+longitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}