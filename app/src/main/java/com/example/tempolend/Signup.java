package com.example.tempolend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //declaring Variables
    EditText username, email, password,  confirm, address, contactno;
    TextView LoginNow;
    Button SignupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialize all variables
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        address = findViewById(R.id.address);
        contactno = findViewById(R.id.contactno);
        LoginNow = findViewById(R.id.LoginNow);
        SignupButton = findViewById(R.id.SignupButton);

        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String user_name = username.getText().toString();
                String Email = email.getText().toString();
                String signup_pass = password.getText().toString();
                String confirm_pass = confirm.getText().toString();
                String Address = address.getText().toString();
                String contact_no = contactno.getText().toString();

                if (user_name.isEmpty() || Email.isEmpty() || signup_pass.isEmpty() || confirm_pass.isEmpty() ||Address.isEmpty() || contact_no.isEmpty()) {
                    username.setError("This Section cannot be empty");
                    email.setError("This Section cannot be empty");
                    password.setError("This Section cannot be empty");
                    confirm.setError("This Section cannot be empty");
                    address.setError("This Section cannot be empty");
                    contactno.setError("This Section cannot be empty");
                    return;

                } else {
                    username.setError(null);
                    email.setError(null);
                    password.setError(null);
                    confirm.setError(null);
                    address.setError(null);
                    contactno.setError(null);
                }

                HelperClass helperClass = new HelperClass(user_name, Email, signup_pass, confirm_pass, Address, contact_no);
                reference.child(user_name).setValue(helperClass);

                Toast.makeText(Signup.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Signup.this, Signin.class);
                startActivity(intent);
            }
        });

        LoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Signin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
}