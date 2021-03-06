package com.example.thuchanhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DangNhapActivity extends AppCompatActivity {
    TextView tv1,google_signIn;
    EditText input_email,input_password;
    Button btnSignIn;
    FirebaseAuth fAuth;
    FirebaseDatabase fDatabase;
    String emailPattern="[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        tv1=findViewById(R.id.dangki);
        google_signIn = findViewById(R.id.google_signIn);

        fAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseDatabase.getInstance();

        input_email = findViewById(R.id.editText);
        input_password=findViewById(R.id.editText2);
        btnSignIn = findViewById(R.id.dangnhap);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              perforLogin();
            }
        });


        google_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DangNhapActivity.this,GoogleSignInActivity.class);
                startActivity(intent);

            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DangNhapActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void perforLogin(){
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();

        if(email.isEmpty()){
            if(password.isEmpty()){
                input_email.setError("Vui l??ng nh???p t??i kho???n");
                input_password.setError("Vui l??ng nh???p m???t kh???u");
            }else {
                input_email.setError("Vui l??ng nh???p t??i kho???n");
            }
        }else if(!email.matches(emailPattern))
        {
            input_email.setError("T??i kho???n kh??ng h???p l???");
        }else if(password.length()<6){
            input_password.setError("????? d??i ph???i t??? 6 k?? t??? tr??? l??n");
        }else {
            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DangNhapActivity.this, "????ng nh???p th??nh c??ng", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhapActivity.this,SignInActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(DangNhapActivity.this, "????ng nh???p th???t b???i", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}