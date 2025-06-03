package com.example.mvvmconcept;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmconcept.ui.user.UserFragment;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Charger le UserFragment au démarrage
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new UserFragment())
                    .commit();
        }
    }
}
