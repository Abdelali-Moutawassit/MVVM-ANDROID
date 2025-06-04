package com.example.mvvmconcept;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvvmconcept.project.user.UsersFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button bt1 = findViewById(R.id.button3);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsersFragment fragment = new UsersFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction ft = manager.beginTransaction();
                ft.replace(R.id.videFF,fragment);
                ft.commit();
            }
        });

    }

}