package com.example.lunashiel.seat_finder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    Button but1, but2, but3;
    TextView tvCafeName1, tvCafeName2, tvCafeName3;
    TextView tvCafeAddress1, tvCafeAddress2, tvCafeAddress3;
    TextView tvCafeNumber1, tvCafeNumber2, tvCafeNumber3;
    TextView tvRemainingNumTable1, tvRemainingNumTable2, tvRemainingNumTable3;
    int cafesplit;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        but1 = (Button) findViewById(R.id.button1);
        but2 = (Button) findViewById(R.id.button2);
        but3 = (Button) findViewById(R.id.button3);
        tvCafeName1 = (TextView) findViewById(R.id.CafeName1);
        tvCafeName2 = (TextView) findViewById(R.id.CafeName2);
        tvCafeName3 = (TextView) findViewById(R.id.CafeName3);
        tvCafeAddress1 = (TextView) findViewById(R.id.CafeAddress1);
        tvCafeAddress2 = (TextView) findViewById(R.id.CafeAddress2);
        tvCafeAddress3 = (TextView) findViewById(R.id.CafeAddress3);
        tvCafeNumber1 = (TextView) findViewById(R.id.CafeNumber1);
        tvCafeNumber2 = (TextView) findViewById(R.id.CafeNumber2);
        tvCafeNumber3 = (TextView) findViewById(R.id.CafeNumber3);
        tvRemainingNumTable1 = (TextView) findViewById(R.id.RemainingNumTable1);
        tvRemainingNumTable2 = (TextView) findViewById(R.id.RemainingNumTable2);
        tvRemainingNumTable3 = (TextView) findViewById(R.id.RemainingNumTable3);

        Intent recieved = getIntent();
        final String receivedMsg = recieved.getStringExtra("Cafe");
        Toast.makeText(SearchResultActivity.this,receivedMsg, Toast.LENGTH_LONG).show();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int n = dataSnapshot.child("CafeDB").child(receivedMsg).child("CafeNum").getValue(int.class);
                String _CafeCode = receivedMsg;
                String _CafeAddress;
                String _CafeName;
                String _CafeNumber;
                String _TableStatus;
                int _CafeOrder;
                int TableNum, TableAvailNum;
                List<String> CafeNameList = new ArrayList<String>();
                List<String> CafeAddressList = new ArrayList<String>();
                List<String> CafeNumberList = new ArrayList<String>();
                List<String> TableStatusList = new ArrayList<String>();
                List<Integer> CafeOrderList = new ArrayList<Integer>();
                for(int i=1; i<=n; i++){
                    _CafeCode = receivedMsg + Integer.toString(i);
                    _CafeName = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeName").getValue(String.class);
                    _CafeAddress = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeAddress").getValue(String.class);
                    _CafeNumber = dataSnapshot.child("CafeDB").child(receivedMsg).child(_CafeCode).child("CafeNumber").getValue(String.class);
                    _CafeOrder = dataSnapshot.child("TableDB").child(_CafeName).child("zActNum").getValue(int.class);
                    CafeNameList.add(_CafeName);
                    CafeAddressList.add(_CafeAddress);
                    CafeNumberList.add(_CafeNumber);
                    CafeOrderList.add(_CafeOrder);
                    TableNum = dataSnapshot.child("TableDB").child(_CafeName).child("TableNum").getValue(int.class);
                    TableAvailNum = dataSnapshot.child("TableDB").child(_CafeName).child("TableAvailNum").getValue(int.class);
                    _TableStatus = TableAvailNum + "/" + TableNum;
                    TableStatusList.add(_TableStatus);
                }
                tvCafeName1.setText(CafeNameList.get(0));
                tvCafeName2.setText(CafeNameList.get(1));
                tvCafeName3.setText(CafeNameList.get(2));
                tvCafeAddress1.setText(CafeAddressList.get(0));
                tvCafeAddress2.setText(CafeAddressList.get(1));
                tvCafeAddress3.setText(CafeAddressList.get(2));
                tvCafeNumber1.setText(CafeNumberList.get(0));
                tvCafeNumber2.setText(CafeNumberList.get(1));
                tvCafeNumber3.setText(CafeNumberList.get(2));
                tvRemainingNumTable1.setText(TableStatusList.get(0));
                tvRemainingNumTable2.setText(TableStatusList.get(1));
                tvRemainingNumTable3.setText(TableStatusList.get(2));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        but1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cafesplit = 1;
                moveToCafeStatusActivity(tvCafeName1.getText().toString());}
        });
        but2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cafesplit = 1;
                moveToCafeStatusActivity(tvCafeName2.getText().toString());}
        });
        but3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cafesplit = 2;
                moveToCafeStatusActivity(tvCafeName3.getText().toString());}
        });
    }

    public void moveToCafeStatusActivity(String _name) {
        if (cafesplit == 1) {
            Intent intent = new Intent(this, CafeStatusActivity.class);
            intent.putExtra("code", _name);
            startActivity(intent);
        }
        else if(cafesplit == 2){
            Intent intent = new Intent(this, CafeStatusActivity2.class);
            intent.putExtra("code", _name);
            startActivity(intent);
        }
    }
}
