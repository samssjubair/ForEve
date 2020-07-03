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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signupEmailEditText,signupPasswordEditText;
    private Button signupButton;
    private TextView signupLoginTextview;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setTitle("Sign Up ");


        signupEmailEditText=(EditText)findViewById(R.id.emailSignupEditTextId);
        signupPasswordEditText=(EditText) findViewById(R.id.passwordSignupEditTextId);
        signupButton=(Button)findViewById(R.id.signupButtonId);
        signupLoginTextview=(TextView)findViewById(R.id.signupLoginTextViewId);
        progressBar=(ProgressBar)findViewById(R.id.progressbarId);

        mAuth=FirebaseAuth.getInstance();

        signupButton.setOnClickListener(this);
        signupLoginTextview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.signupButtonId:
                userRegister();
                break;


            case R.id.signupLoginTextViewId:
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                break;

        }
    }
    private void userRegister(){
        String email=signupEmailEditText.getText().toString().trim();
        String password=signupPasswordEditText.getText().toString().trim();
        if(email.isEmpty())
        {
            signupEmailEditText.setError("Enter an email address");
            signupEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signupEmailEditText.setError("Enter a valid email address");
            signupEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            signupPasswordEditText.setError("Enter a password");
            signupPasswordEditText.requestFocus();
            return;
        }

        if(password.length()<6){
            signupPasswordEditText.setError("Password length should be minimum 6 characters");
            signupPasswordEditText.requestFocus();
            return;
        }
       progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);


                        } else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}