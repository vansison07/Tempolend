package com.example.tempolend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pl.droidsonroids.gif.GifImageView;


public class Signin extends AppCompatActivity {

    //declaring Variables
    EditText Loginusername, Loginpassword;
    Button LoginButton;
    TextView SignupNow;
    GifImageView loadinganim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Initialize all variables
        Loginusername = findViewById(R.id.login_username);
        Loginpassword = findViewById(R.id.login_password);
        LoginButton = findViewById(R.id.login_button);
        SignupNow = findViewById(R.id.toSignup);
        loadinganim = findViewById(R.id.loading);


        //this will open signupactivity
        SignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signin.this, Signup.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


        //this will check username || password
        LoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!validateUsername()) {

                } else {
                    checkUser();

                    if (validatePassword()) {

                    } else {
                        checkUser();

                    }

                }

                loadinganim.setVisibility(View.VISIBLE);

            }


            //validating username
            public Boolean validateUsername() {
                String val = Loginusername.getText().toString();

                if (val.isEmpty()) {
                    Loginusername.setError("Username cannot be empty");
                    return false;
                } else {
                    Loginusername.setError(null);
                    return true;
                }
            }

            //validating password
            public Boolean validatePassword() {
                String val = Loginpassword.getText().toString();

                if (val.isEmpty()) {
                    Loginpassword.setError("Password cannot be empty");
                    return false;
                } else {
                    Loginpassword.setError(null);
                    return true;
                }
            }


            //check user if present in the database or not
            public void checkUser() {
                String userUsername = Loginusername.getText().toString().trim();
                String userPassword = Loginpassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);


                checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Loginusername.setError(null);
                            String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                            if (passwordFromDB.equals(userPassword)) {
                                Loginpassword.setError(null);
                                Toast.makeText(Signin.this, "Welcome", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Signin.this, MainActivity.class);

                                startActivity(intent);

                                //pass the data using intent
                                //String nameFromDB = snapshot.child(userUsername).child("fullname").getValue(String.class);
                                //String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                                //String addressFromDB = snapshot.child(userUsername).child("address").getValue(String.class);
                                //String contactnoFromDB =snapshot.child(userUsername).child("contactno").getValue(String.class);
                                // String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                                //intent1.putExtra("name", nameFromDB);
                                //intent1.putExtra("email", emailFromDB);
                                //intent1.putExtra("address", addressFromDB);
                                //intent1.putExtra("contactno", contactnoFromDB);
                                //intent1.putExtra("username", usernameFromDB);
                                //intent1.putExtra("password", passwordFromDB);

                                //startActivity(intent);


                            } else {
                                Loginpassword.setError("Incorrect password");
                                Loginpassword.requestFocus();

                            }
                        } else {
                            Loginusername.setError("User not exist");
                            Loginusername.requestFocus();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }

                });

            }
        });

    }

}