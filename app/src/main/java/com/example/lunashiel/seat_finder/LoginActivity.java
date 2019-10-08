package com.example.lunashiel.seat_finder;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    static int userType;
    static String uid;
    int cafeOrder;
    ImageButton loginbutton;
    String PhoneNumber;
    String Password;
    EditText PhoneText;
    EditText PasswordText;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneText = (EditText) findViewById(R.id.editText_login_phonenum);
        PasswordText = (EditText) findViewById(R.id.editText2);
        loginbutton = (ImageButton) findViewById(R.id.imageButton2);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        // child("UserDB").child("RegistrationUsers")
        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PhoneNumber =  PhoneText.getText().toString();
                uid = PhoneNumber;
                Password = PasswordText.getText().toString();

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String UsrPass;
                        String UsrPhone;
                        String P1;
                        P1 = dataSnapshot.child("UserDB").child("RegistratedUsers").child(PhoneNumber).child("Password").getValue(String.class);
                        UsrPass = P1;
                        if( UsrPass.equals(Password) && UsrPass != ""){
                            String UsrType = dataSnapshot.child("UserDB").child("RegistratedUsers").child(PhoneNumber).child("Type").getValue(String.class);
                            if( UsrType.equals("Owner") == true ){
                                // Hamsoon reciept 2-1
                                moveToOwneRegesiterActivity();
                                // move to owner activity
                            }
                            else if( UsrType.equals("User") == true){
                                // Hamsoon reciept 2-2
                                userType = 1;
                                moveToSearchActivity();
                            }
                            else if( UsrType.equals("Worker") == true){
                                userType = 2;
                                String cafeName = dataSnapshot.child("UserDB").child("RegistratedUsers").child(PhoneNumber).child("Job").getValue(String.class);
                                cafeOrder = dataSnapshot.child("TableDB").child(cafeName).child("zActNum").getValue(int.class);
                                moveToStatusLayout(cafeName);
                            }
                            else {
                                Log.d("LoginActivity", "What Type?");
                            }
                        }
                        // Reciet 1-1 (HamSoon Reciept)
                        else{
                            Toast.makeText(LoginActivity.this, "Please check your password!",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    public void moveToStatusLayout(String _name){
        if(cafeOrder == 1) {
            Intent intent = new Intent(this, CafeStatusActivity.class);
            intent.putExtra("code", _name);
            startActivity(intent);
        }
        else if(cafeOrder == 2){
            Intent intent = new Intent(this, CafeStatusActivity2.class);
            intent.putExtra("code", _name);
            startActivity(intent);
        }
    }
    public void moveToOwneRegesiterActivity(){
        Intent intent = new Intent(this, CafeOwnerRegisterActivity.class);
        startActivity(intent);
    }
    public void moveToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}