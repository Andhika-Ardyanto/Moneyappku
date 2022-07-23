package com.example.moneyappku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String username = "ndhikatok";
    String password = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtUser = findViewById(R.id.txtUser);
        final EditText txtPass = findViewById(R.id.txtPass);
        Button login = findViewById(R.id.login);
        TextView profil = findViewById(R.id.tvprofil);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUser.getText().toString().equalsIgnoreCase(username)&& txtPass.getText().toString().equalsIgnoreCase(password)){
                    startActivity(new Intent(MainActivity.this, FirstPage.class));
                }else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PageProfil.class));
            }
        });
    }
}