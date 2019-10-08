package com.example.lunashiel.seat_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    Button buttonSearchButton;
    ImageButton imgbut1, imgbut2;
    EditText editText_CafeSearch;

    Button but1, but2;
    TextView tvCafeName1, tvCafeName2;
    TextView tvCafeAddress1, tvCafeAddress2;
    TextView tvCafeNumber1, tvCafeNumber2;
    TextView tvRemainingNumTable1, tvRemainingNumTable2;

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(SearchActivity.this, "SearchActivity", Toast.LENGTH_SHORT).show();

        editText_CafeSearch = (EditText) findViewById(R.id.editText_cafesearch);
        buttonSearchButton = (Button) findViewById(R.id.button_cafesearchbutton);

        but1 = (Button) findViewById(R.id.button1);
        but2 = (Button) findViewById(R.id.button2);
        tvCafeName1 = (TextView) findViewById(R.id.CafeName1);
        tvCafeName2 = (TextView) findViewById(R.id.CafeName2);
        tvCafeAddress1 = (TextView) findViewById(R.id.CafeAddress1);
        tvCafeAddress2 = (TextView) findViewById(R.id.CafeAddress2);
        tvCafeNumber1 = (TextView) findViewById(R.id.CafeNumber1);
        tvCafeNumber2 = (TextView) findViewById(R.id.CafeNumber2);
        tvRemainingNumTable1 = (TextView) findViewById(R.id.RemainingNumTable1);
        tvRemainingNumTable2 = (TextView) findViewById(R.id.RemainingNumTable2);

        final String receivedMsg = "스타벅스";

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String _CafeCode;
                String _CafeAddress;
                String _CafeName;
                String _CafeNumber;
                String _TableStatus;
                int TableNum, TableAvailNum;
                List<String> CafeNameList = new ArrayList<String>();
                List<String> CafeAddressList = new ArrayList<String>();
                List<String> CafeNumberList = new ArrayList<String>();
                List<String> TableStatusList = new ArrayList<String>();
                for (int i = 1; i <= 2; i++) {
                    _CafeCode = dataSnapshot.child("UserDB").child("RegistratedUsers").child(LoginActivity.uid).child("Fav" + i).getValue(String.class);
                    if (_CafeCode.length() > 2) {
                        _CafeName = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeName").getValue(String.class);
                        _CafeAddress = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeAddress").getValue(String.class);
                        _CafeNumber = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeNumber").getValue(String.class);
                        TableAvailNum = dataSnapshot.child("TableDB").child(_CafeName).child("TableAvailNum").getValue(int.class);
                        TableNum = dataSnapshot.child("TableDB").child(_CafeName).child("TableNum").getValue(int.class);
                        _TableStatus = TableAvailNum + "/" + TableNum;
                    } else {
                        _CafeName = "";
                        _CafeAddress = "";
                        _CafeNumber = "";
                        _TableStatus = "";
                    }
                    CafeNameList.add(_CafeName);
                    CafeAddressList.add(_CafeAddress);
                    CafeNumberList.add(_CafeNumber);
                    TableStatusList.add(_TableStatus);
                }
                tvCafeName1.setText(CafeNameList.get(0));
                tvCafeName2.setText(CafeNameList.get(1));
                tvCafeAddress1.setText(CafeAddressList.get(0));
                tvCafeAddress2.setText(CafeAddressList.get(1));
                tvCafeNumber1.setText(CafeNumberList.get(0));
                tvCafeNumber2.setText(CafeNumberList.get(1));
                tvRemainingNumTable1.setText(TableStatusList.get(0));
                tvRemainingNumTable2.setText(TableStatusList.get(1));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        buttonSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usr_input;
                usr_input = editText_CafeSearch.getText().toString();

                myRef = database.getReference();
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child("CafeDB").hasChild(usr_input)) {
                            moveToSearchResultActivity(usr_input);
                        } else {
                            Toast.makeText(SearchActivity.this, "Please Check cafe name!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToCafeStatusActivity(tvCafeName1.getText().toString());
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToCafeStatusActivity(tvCafeName2.getText().toString());
            }
        });
        /*imgbut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        myRef.child("UserDB").child("RegistratedUsers").child(LoginActivity.uid).child("Fav1").setValue(" ");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });*/
    }

    public void moveToSearchResultActivity(String usr_input) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("Cafe", usr_input);
        startActivity(intent);
    }

    public void moveToCafeStatusActivity(String _name) {
        Intent intent = new Intent(this, CafeStatusActivity.class);
        intent.putExtra("code", _name);
        startActivity(intent);
    }
}