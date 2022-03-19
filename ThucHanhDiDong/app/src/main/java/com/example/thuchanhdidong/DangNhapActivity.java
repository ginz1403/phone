package com.example.thuchanhdidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class DangNhapActivity extends AppCompatActivity {
    SignInButton google_signIn;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        tv1=findViewById(R.id.dangki);
        google_signIn = findViewById(R.id.google_signIn);


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
}