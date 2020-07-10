package com.example.foreve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        unsafeCardView.setOnClickListener(this);
        sirenCardView.setOnClickListener(this);
        callCardView.setOnClickListener(this);
        videoCardView.setOnClickListener(this);
        policeCardView.setOnClickListener(this);
        hospitalCardView.setOnClickListener(this);
        ambulanceCardView.setOnClickListener(this);
        firstAidCardVIew.setOnClickListener(this);
        selfDefenseCardView.setOnClickListener(this);
        aboutCardView.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.profileId){
            Intent intent=new Intent(MainActivity.this,UserInfoActivity.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.contactUsId){
            Intent intent=new Intent(MainActivity.this,AboutActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.selfDefenceCardViewId) {
            Intent intent = new Intent(MainActivity.this, SelfDefenseActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.unsafeCardViewId){
            Intent intent= new Intent(MainActivity.this,UnsafeActivity.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.sirenCardViewId){
            Intent intent = new Intent(MainActivity.this, SirenActivity.class);
            startActivity(intent);

        }
        else if(v.getId()==R.id.hospitalCardViewId){
            Intent intent=new Intent(MainActivity.this,NearbyHospital.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.firstAidCardViewId){
            Intent intent=new Intent(MainActivity.this,FirstAidActivity.class);
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
        else if(v.getId()==R.id.policeStationCardViewId)
        {
            Intent intent = new Intent(MainActivity.this, NearbyHospital.class);
            startActivity(intent);
        }
        else if(v.getId()==R.id.videoTapingCardViewId)
        {
            Intent intent = new Intent(MainActivity.this, HiddenCamActivity.class);
            startActivity(intent);


        }


    }
}
