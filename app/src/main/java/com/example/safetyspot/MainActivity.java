package com.example.safetyspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bnview = findViewById(R.id.bnview);
        loadfrag(new navhome());

        bnview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId= item.getItemId();

                if (itemId == R.id.navhome) {
                    loadfrag(new navhome());
                } else if (itemId == R.id.search) {
                    loadfrag(new search());
                } else if (itemId == R.id.sheild) {
                    // Load the sheild fragment and pass the argument
                    loadfrag(new sheild());
                } else if (itemId==R.id.user) {
                    loadfrag(new user());
                } else {
                    loadfrag(new user());
                }

                return true;
            }
        });


    }
    public void loadfrag(Fragment fragment){


            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.main, fragment); // Use replace() instead of add()
            ft.commit();

    }
}
