package com.iamdeveloper.firebaseemaillogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText viewEmail, viewPassword;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        viewEmail = (EditText) findViewById(R.id.et_email);
        viewPassword = (EditText) findViewById(R.id.et_password);

        findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSingUp();
            }
        });
    }

    private void onSingUp() {
        String strEmail = viewEmail.getText().toString();
        String strPassword = viewPassword.getText().toString();

        if (!strEmail.isEmpty() && !strPassword.isEmpty()) {
            mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPassword)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this,"Register OK"+authResult.getUser().getEmail(),Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toast.makeText(MainActivity.this,"Register Fail",Toast.LENGTH_SHORT).show();
                        }
            });
        }


    }
}
