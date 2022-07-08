package com.example.uts_mpr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    ImageView mycart, home, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mycart = findViewById(R.id.mycart);
        home = findViewById(R.id.home);
        back = findViewById(R.id.back);

        mycart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mycart = new Intent(ProfileActivity.this, ListorderActivity.class);
                startActivity(mycart);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}