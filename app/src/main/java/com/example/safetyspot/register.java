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

public class register extends AppCompatActivity{

    FirebaseAuth auth;





    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState){



        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://safetyspot-2126b-default-rtdb.firebaseio.com");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText fname=findViewById(R.id.fullname);
        final EditText femail=findViewById(R.id.email);
        final Button registerbtn=findViewById(R.id.registerbtn);
        final TextView login=findViewById(R.id.login);
        final EditText phone=findViewById(R.id.phone);
        final EditText password=findViewById(R.id.password);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnmeTXT=fname.getText().toString();
                final String emailTXT=femail.getText().toString();
                final String phoneTXT=phone.getText().toString();
                final String passwordTXT=password.getText().toString();


                if(fullnmeTXT.isEmpty() || emailTXT.isEmpty()|| phoneTXT.isEmpty() || passwordTXT.isEmpty()){


                    Toast.makeText(register.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }

                else {

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTXT)){

                                Toast.makeText(register.this,"Phone is already used",Toast.LENGTH_SHORT).show();
                            }

                            else{


                                databaseReference.child("user").child(phoneTXT).child("name").setValue(fullnmeTXT);

                                databaseReference.child("user").child(phoneTXT).child("Email").setValue(emailTXT);
                                databaseReference.child("user").child(phoneTXT).child("password").setValue(passwordTXT);

                                Toast.makeText(register.this,"User Created",Toast.LENGTH_SHORT).show();

                                SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor editor= pref.edit();
                                editor.putBoolean("flag", Boolean.parseBoolean("true"));
                                editor.apply();

                                startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                SharedPreferences pre = getSharedPreferences("user", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = pre.edit();
                                editor1.putString("phone", phoneTXT);
                                editor1.apply();
                            }
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
            }

        }



