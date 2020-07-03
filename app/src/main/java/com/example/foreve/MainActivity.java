package com.example.foreve;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView unsafeCardView,sirenCardView,callCardView,videoCardView,policeCardView,hospitalCardView,ambulanceCardView,firstAidCardVIew,selfDefenseCardView,aboutCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unsafeCardView=(CardView)findViewById(R.id.unsafeCardViewId);
        sirenCardView=(CardView)findViewById(R.id.sirenCardViewId);
        callCardView=(CardView)findViewById(R.id.callCardViewId);
        videoCardView=(CardView)findViewById(R.id.videoTapingCardViewId);
        policeCardView=(CardView)findViewById(R.id.policeStationCardViewId);
        hospitalCardView=(CardView)findViewById(R.id.hospitalCardViewId);
        ambulanceCardView=(CardView)findViewById(R.id.callAmbulanceCardViewId);
        firstAidCardVIew=(CardView)findViewById(R.id.firstAidCardViewId);
        selfDefenseCardView=(CardView)findViewById(R.id.selfDefenceCardViewId);
        aboutCardView=(CardView)findViewById(R.id.aboutUsCardViewId);

        //unsafeCardView.setOnClickListener(this);
        sirenCardView.setOnClickListener(this);
        callCardView.setOnClickListener(this);
        //videoCardView.setOnClickListener(this);
        //policeCardView.setOnClickListener(this);
        hospitalCardView.setOnClickListener(this);
        ambulanceCardView.setOnClickListener(this);
        //firstAidCardVIew.setOnClickListener(this);
        selfDefenseCardView.setOnClickListener(this);
        aboutCardView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.selfDefenceCardViewId) {
            Intent intent = new Intent(MainActivity.this, SelfDefenseActivity.class);
            startActivity(intent);

        }
        if(v.getId()==R.id.sirenCardViewId){
            Intent intent = new Intent(MainActivity.this, SirenActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.callCardViewId) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:999"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            startActivity(intent);
        }
        else if(v.getId()==R.id.callAmbulanceCardViewId)
        {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:01759821556"));
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            startActivity(intent);
        }

        else if(v.getId()==R.id.aboutUsCardViewId)
        {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
//        else if(v.getId()==R.id.hospitalCardViewId)
//        {
//            Intent intent = new Intent(MainActivity.this, NearbyHospitalActivity.class);
//            startActivity(intent);
//        }


    }
}
