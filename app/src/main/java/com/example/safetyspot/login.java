package com.example.safetyspot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://safetyspot-2126b-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final Button login = findViewById(R.id.loginbtn);
        final TextView registerpage = findViewById(R.id.registerpage);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String phoneTXT = phone.getText().toString();
                final String passwordTXT = password.getText().toString();

                if (phoneTXT.isEmpty() || passwordTXT.isEmpty()) {
                    Toast.makeText(login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            if (snapshot.hasChild(phoneTXT)) {

                                final String getpassword = snapshot.child(phoneTXT).child("password").getValue(String.class);

                                if (getpassword.equals(passwordTXT)) {
                                    Toast.makeText(login.this, "Login success", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                                    SharedPreferences.Editor editor= pref.edit();
                                    editor.putBoolean("flag", Boolean.parseBoolean("true"));
                                    editor.apply();
                                    SharedPreferences pre = getSharedPreferences("user", MODE_PRIVATE);
                                    SharedPreferences.Editor editor1 = pre.edit();
                                    editor1.putString("phone", phoneTXT);
                                    editor1.apply();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                } else {
                                    Toast.makeText(login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        registerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });
    }
}
