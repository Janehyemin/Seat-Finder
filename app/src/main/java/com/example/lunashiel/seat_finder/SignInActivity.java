package com.example.lunashiel.seat_finder;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {
    EditText nameView;
    EditText PhonenumView;
    EditText passwordView;
    EditText password_doubleView;
    EditText worker_cafeaddressView;

    RadioButton isWorker;
    RadioButton isUser;
    RadioButton isOwner;

    ImageButton ButSignin;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        nameView = (EditText)findViewById(R.id.editText_name);
        PhonenumView = (EditText) findViewById(R.id.editText_phonenum);
        passwordView = (EditText) findViewById(R.id.editText_password);
        password_doubleView = (EditText) findViewById(R.id.editText_password_double);
        worker_cafeaddressView = (EditText) findViewById(R.id.editText_worker_cafeaddress);

        isWorker = (RadioButton) findViewById(R.id.RadioButton_worker);
        isUser = (RadioButton) findViewById(R.id.RadioButton_cafeuser);
        isOwner = (RadioButton) findViewById(R.id.RadioButton_cafeowner);

        ButSignin = (ImageButton) findViewById(R.id.imageButton);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("UserDB").child("RegistratedUsers");
        ButSignin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String name = nameView.getText().toString();
                        String phonenum = PhonenumView.getText().toString();
                        String pass1 = passwordView.getText().toString();
                        String pass2 = password_doubleView.getText().toString();
                        String cafe_address = worker_cafeaddressView.getText().toString();
                        if( pass1.equals( pass2 ) != true){ // hamsoon 1-1 is already done
                            Toast.makeText(SignInActivity.this,"비밀번호와 비밀번호 확인이 다릅니다.", Toast.LENGTH_LONG).show();
                        }
                        // recipet Hamsoon 1-2
                        else if ( pass1.length() < 8 ){
                            Toast.makeText(SignInActivity.this, "비밀번호는 8자이상으로 설정해야 합니다.", Toast.LENGTH_LONG).show();
                        }

                        else{
                            int type = 0;
                            if( isWorker.isChecked() ) type =1;
                            if( isUser.isChecked() ) type = 2;
                            if( isOwner.isChecked() ) type=3;
                            myRef.child(phonenum).child("Password").setValue(pass1);
                            myRef.child(phonenum).child("Name").setValue(name);
                            switch (type){
                                case 1:
                                    myRef.child(phonenum).child("Type").setValue("Worker");
                                    myRef.child(phonenum).child("Job").setValue(cafe_address);
                                    break;
                                case 2:
                                    myRef.child(phonenum).child("Type").setValue("User");
                                    myRef.child(phonenum).child("Fav1").setValue(" ");
                                    myRef.child(phonenum).child("Fav2").setValue(" ");
                                    break;
                                case 3:
                                    myRef.child(phonenum).child("Type").setValue("Owner");
                                    break;
                            }
                            Toast.makeText(SignInActivity.this, "회원가입이 완료되었습니다!\n이제 로그인해주세요!", Toast.LENGTH_SHORT).show();
                            finish(); // 1-+@ Added
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    public void moveToSearchActivity(View view) {


    }
}
