package com.sumith.car.sumith_reg;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registation extends AppCompatActivity {
    private Button loginto;
    private EditText f_email;
    private EditText f_pass;
    private TextView login_reg;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        loginto=findViewById(R.id.button_log);
        firebaseAuth =FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            ///start the profile Activity.....:)
        }
        progressDialog=new ProgressDialog(this);
        f_email=findViewById(R.id.email_field2);
        f_pass=findViewById(R.id.password_field2);
        login_reg=findViewById(R.id.log_log);
        loginto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_login();
            }
        });
    }
    private void  new_login(){
        String email2=f_email.getText().toString().trim();
        String password2=f_pass.getText().toString().trim();
        if(TextUtils.isEmpty(email2)){
            Toast.makeText(this, "Please Enter a Valid Email Address...!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this, "Please Enter a Valid Password...!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Login is on process....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email2,password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(Registation.this, " Welcome New User...", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Registation.this, "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

