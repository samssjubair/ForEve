package com.example.foreve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText loginEmailEditText;
    private EditText loginPasswordEditText;
    private Button loginButton;
    private TextView loginSignupTextview;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Login ");


        loginEmailEditText=(EditText)findViewById(R.id.emailLoginEditTextId);
        loginPasswordEditText=(EditText) findViewById(R.id.passwordLoginEditTextId);
        loginButton=(Button)findViewById(R.id.loginButtonId);
        loginSignupTextview=(TextView) findViewById(R.id.loginSignupTextviewId);
        progressBar=(ProgressBar)findViewById(R.id.progressbarId);
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(this);
        loginSignupTextview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.loginButtonId:
                userLogin();
                break;


            case R.id.loginSignupTextviewId:
                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
                break;

        }
    }
    private void userLogin() {
        final String email = loginEmailEditText.getText().toString().trim();
        String password = loginPasswordEditText.getText().toString().trim();
        if (email.isEmpty()) {
            loginEmailEditText.setError("Enter an email address");
            loginEmailEditText.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmailEditText.setError("Enter a valid email address");
            loginEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if (password.isEmpty()) {
            loginPasswordEditText.setError("Enter a password");
            loginPasswordEditText.requestFocus();
            return;
        }

        if (password.length() < 6) {
            loginPasswordEditText.setError("Password length should be minimum 6 characters");
            loginPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

//        Intent intent= new Intent(getApplicationContext(),UnsafeActivity.class);
//        intent.putExtra("mail",email);



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    DemoClass.msg=email;

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
