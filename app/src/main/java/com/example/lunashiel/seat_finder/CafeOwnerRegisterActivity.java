package com.example.lunashiel.seat_finder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CafeOwnerRegisterActivity extends AppCompatActivity {
    EditText CafeName;
    EditText CafeAddress;
    EditText OwnerName;
    EditText CaffeSize; // inputtye = String why?
    EditText UserPhoneNumber;  // inputtype = phonenum
    ImageButton Submit;

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeowner_register);

        CafeName = (EditText) findViewById(R.id.editText_owner_cafename);
        CafeAddress = (EditText) findViewById(R.id.editText_owner_cafeaddress);
        OwnerName = (EditText) findViewById(R.id.editText_owner_name);
        CaffeSize = (EditText) findViewById(R.id.editText_owner_sizeofthecafe);
        UserPhoneNumber = (EditText) findViewById(R.id.editText_owner_phonenum);
        Submit = (ImageButton) findViewById(R.id.imgbut_register_cafe);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Applications").child("AppedDB");
        Submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String name = CafeName.getText().toString();
                final String address = CafeAddress.getText().toString();
                final String ownername = OwnerName.getText().toString();
                final String size = CaffeSize.getText().toString();
                final String phone = UserPhoneNumber.getText().toString();

                // There are no special conditions.
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String Child = "Caffe";
                        int i;
                        for(i=1;;i++){
                            if( dataSnapshot.hasChild(Child+Integer.toString(i)) ){
                                continue;
                            }
                            else{
                                break;
                            }
                        }
                        Child = Child+Integer.toString(i);
                        myRef.child(Child).child("Address").setValue(address);
                        myRef.child(Child).child("CaffeName").setValue(name);
                        myRef.child(Child).child("CaffeSize").setValue(size);
                        myRef.child(Child).child("OwnerName").setValue(ownername);
                        myRef.child(Child).child("PhoneNum").setValue(phone);
                        Toast.makeText(CafeOwnerRegisterActivity.this, "기재된 주소로 곧 연락드리겠습니다!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
