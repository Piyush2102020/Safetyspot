package com.example.safetyspot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.net.InternetDomainName;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sheild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sheild extends Fragment {
    DatabaseReference databaseReference;

    EditText phonenumber1,phonenumber3,phonenumber4,phonenumber5,phonenumber6,phonenumber7;
    EditText phonenumber2,phonenumber8,phonenumber9,phonenumber10;

    private TextView mPhoneTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sheild, container, false);



        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", getActivity().MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phone", "");




        // Get references to the EditText fields for phone number 1 and phone number 2
        phonenumber1 = view.findViewById(R.id.phoneEditText1);
        phonenumber2 = view.findViewById(R.id.phoneEditText2);
        phonenumber3 = view.findViewById(R.id.phoneEditText3);
        phonenumber4 = view.findViewById(R.id.phoneEditText4);
        phonenumber5 = view.findViewById(R.id.phoneEditText5);
        phonenumber6 = view.findViewById(R.id.phoneEditText6);
        phonenumber7 = view.findViewById(R.id.phoneEditText7);
        phonenumber8 = view.findViewById(R.id.phoneEditText8);
        phonenumber9 = view.findViewById(R.id.phoneEditText9);
        phonenumber10 = view.findViewById(R.id.phoneEditText10);

        // Get a reference to the Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the phone numbers from the EditText fields when a button is clicked
        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber1 = phonenumber1.getText().toString().trim();
                String phoneNumber2 = phonenumber2.getText().toString().trim();
                String phoneNumber3 = phonenumber3.getText().toString().trim();
                String phoneNumber4 = phonenumber4.getText().toString().trim();
                String phoneNumber5 = phonenumber5.getText().toString().trim();
                String phoneNumber6 = phonenumber6.getText().toString().trim();
                String phoneNumber7 = phonenumber7.getText().toString().trim();
                String phoneNumber8 = phonenumber8.getText().toString().trim();
                String phoneNumber9 = phonenumber9.getText().toString().trim();
                String phoneNumber10 = phonenumber10.getText().toString().trim();

                DatabaseReference phoneNumberRef = databaseReference.child("user").child(phoneNumber).child("phoneNumbers");
                phoneNumberRef.child("phone1").setValue(phoneNumber1);
                phoneNumberRef.child("phone2").setValue(phoneNumber2);
                phoneNumberRef.child("phone3").setValue(phoneNumber3);
                phoneNumberRef.child("phone4").setValue(phoneNumber4);
                phoneNumberRef.child("phone5").setValue(phoneNumber5);
                phoneNumberRef.child("phone6").setValue(phoneNumber6);
                phoneNumberRef.child("phone7").setValue(phoneNumber7);
                phoneNumberRef.child("phone8").setValue(phoneNumber8);
                phoneNumberRef.child("phone9").setValue(phoneNumber9);
                phoneNumberRef.child("phone10").setValue(phoneNumber10);

                Toast.makeText(getActivity(), "Phone numbers saved in database", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sheild() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sheild.
     */
    // TODO: Rename and change types and number of parameters
    public static sheild newInstance(String param1, String param2) {
        sheild fragment = new sheild();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    }
