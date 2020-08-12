package com.example.foreve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserInfoActivity extends AppCompatActivity {
    private Button saveButton;

    private EditText nameET,emailET,mobileET,friend1MobileET,friend1EmailET,friend2MobileET,friend2EmailET,friend3MobileET,friend3EmailET;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        getSupportActionBar().setTitle("Your Info");

        databaseReference=FirebaseDatabase.getInstance().getReference("UserInfo");

        saveButton=(Button)findViewById(R.id.saveDataButtonId);
        nameET=(EditText)findViewById(R.id.userNameId);
        emailET=(EditText)findViewById(R.id.userEmailId);
        mobileET=(EditText)findViewById(R.id.userMobileId);
        friend1EmailET=(EditText)findViewById(R.id.friend1EmailId);
        friend1MobileET=(EditText)findViewById(R.id.friend1MobileId);
        friend2EmailET=(EditText)findViewById(R.id.friend2EmailId);
        friend2MobileET=(EditText)findViewById(R.id.friend2MobileId);
        friend3EmailET=(EditText)findViewById(R.id.friend3EmailId);
        friend3MobileET=(EditText)findViewById(R.id.friend3MobileId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

    }

    public void saveData(){
        String userName=nameET.getText().toString().trim();
        String userMobile=mobileET.getText().toString().trim();
        String userEmail=emailET.getText().toString().trim();
        String friend1Email=friend1EmailET.getText().toString().trim();
        String friend1Mobile=friend1MobileET.getText().toString().trim();
        String friend2Email=friend2EmailET.getText().toString().trim();
        String friend2Mobile=friend2MobileET.getText().toString().trim();
        String friend3Email=friend3EmailET.getText().toString().trim();
        String friend3Mobile=friend3MobileET.getText().toString().trim();

        String key=databaseReference.push().getKey();
        UserData userData= new UserData(userName,userEmail,userMobile,friend1Email,friend1Mobile,friend2Email,friend2Mobile,friend3Email,friend3Mobile);
        databaseReference.child(key).setValue(userData);
        Toast.makeText(getApplicationContext(),"Your information is added now",Toast.LENGTH_LONG).show();

        nameET.setText("");
        emailET.setText("");
        mobileET.setText("");
        friend1MobileET.setText("");
        friend1EmailET.setText("");
        friend2MobileET.setText("");
        friend2EmailET.setText("");
        friend3MobileET.setText("");
        friend3EmailET.setText("");

        Intent intent=new Intent(UserInfoActivity.this,MainActivity.class);
        startActivity(intent);

    }

}