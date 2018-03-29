package com.sumith.car.sumith_reg;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private Button regist;
    private EditText field_email;
    private EditText field_pass;
    private TextView reg_login;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        regist=findViewById(R.id.button_reg);
        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        field_email=findViewById(R.id.email_field);
        field_pass=findViewById(R.id.password_field);
        reg_login=findViewById(R.id.login_reg);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registation_block();
            }
        });
    }
    private void registation_block()
    {
        String email=field_email.getText().toString().trim();
        String password=field_pass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter a Valid Email Address...!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please Enter a Valid Password...!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("New Registration in process....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public void onclick1(View view )
    {
        Intent intent1=new  Intent(MainActivity.this,Registation.class);
        startActivity(intent1);
    }
}