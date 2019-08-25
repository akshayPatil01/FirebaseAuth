package com.akshaypatil.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnsignup;
    TextView tvsignin;
    EditText etmail,etpassword;
    FirebaseAuth mFirebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnsignup = findViewById(R.id.btnsignup);
        tvsignin = findViewById(R.id.tvsignin);
        etmail = findViewById(R.id.etemail);
        etpassword = findViewById(R.id.etpassword);

        btnsignup.setOnClickListener(this);
        tvsignin.setOnClickListener(this);
    }

    private void registerUser(){
        String emailid = etmail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if(TextUtils.isEmpty(emailid) && TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this,"Please enter your credentials!",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(password))
        {
            etpassword.setError("Please enter your password");
            etpassword.requestFocus();
            return;
        }
        else if(TextUtils.isEmpty(emailid))
        {
            etmail.setError("Please enter your email-id");
            etmail.requestFocus();
            return;
        }

            progressDialog.setMessage("Registering user ...");
            progressDialog.show();

            mFirebaseAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Registred successfull !",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Registration Unsuccessfull, Please try again!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    @Override
    public void onClick(View view) {
        if(view == btnsignup)
        {
            registerUser();
        }

        if(view == tvsignin)
        {

        }
    }
}
