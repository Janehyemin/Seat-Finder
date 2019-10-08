package com.example.lunashiel.seat_finder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class CafeStatusActivity extends AppCompatActivity {
    ImageButton  but1,but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12;
    TextView CafeName;
    TextView TableStatus;
    int TableNum, TableAvailNum; String TableStat;
    String receivedMsg;

    FirebaseDatabase database;
    DatabaseReference myRef;
    public void ButtonSet4(ImageButton but, boolean isEmpty){
        if( isEmpty == true ){
            but.setImageResource(R.drawable.table_4_occupied);
        }
        else{
            but.setImageResource(R.drawable.table_4_vacant);
        }
    }
    public void ButtonSet2(ImageButton but, boolean isEmpty){
        if( isEmpty == true ){
            but.setImageResource(R.drawable.table_2_occupied);
        }
        else{
            but.setImageResource(R.drawable.table_2_vacant);
        }
    }
    public void ButtonSet(){
        database = FirebaseDatabase.getInstance();

        Intent received = getIntent();
        receivedMsg = received.getStringExtra("code");
        CafeName.setText(receivedMsg);
        myRef = database.getReference().child("TableDB").child(receivedMsg);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int tableNum;
                tableNum = dataSnapshot.child("TableNum").getValue(int.class);
                TableNum = tableNum;
                TableAvailNum = dataSnapshot.child("TableAvailNum").getValue(int.class);
                TableStat = TableAvailNum + "/" + TableNum;
                TableStatus.setText(TableStat);
                List<String> L1 = new ArrayList<String>();
                for(int i=1; i<=tableNum; i++){
                    L1.add( dataSnapshot.child("Table" + Integer.toString(i) ).getValue(String.class) );
                    Log.d("but", L1.get(i-1));
                }
                if( L1.get(0).equals("occupy") ){
                    ButtonSet4(but1, true);
                    but1.setTag("40");
                } else{
                    ButtonSet4(but1, false);
                    but1.setTag("41");
                }
                if( L1.get(1).equals("occupy") ){
                    ButtonSet4(but2, true);
                    but2.setTag("40");
                } else{
                    ButtonSet4(but2, false);
                    but2.setTag("41");
                }
                if( L1.get(2).equals("occupy") ){
                    ButtonSet4(but3, true);
                    but3.setTag("40");
                } else{
                    ButtonSet4(but3, false);
                    but3.setTag("41");
                }
                if( L1.get(3).equals("occupy")){
                    ButtonSet4(but4, true);
                    but4.setTag("40");
                } else{
                    ButtonSet4(but4, false);
                    but4.setTag("41");
                }
                if( L1.get(4).equals("occupy") ){
                    ButtonSet4(but5, true);
                    but5.setTag("40");
                } else{
                    ButtonSet4(but5, false);
                    but5.setTag("41");
                }
                if( L1.get(5).equals("occupy") ){
                    ButtonSet4(but6, true);
                    but6.setTag("40");
                } else{
                    ButtonSet4(but6, false);
                    but6.setTag("41");
                }
                if( L1.get(6).equals("occupy") ){
                    ButtonSet2(but7, true);
                    but7.setTag("20");
                } else{
                    ButtonSet2(but7, false);
                    but7.setTag("21");
                }
                if( L1.get(7).equals("occupy") ){
                    ButtonSet2(but8, true);
                    but8.setTag("20");
                } else{
                    ButtonSet2(but8, false);
                    but8.setTag("21");
                }
                if( L1.get(8).equals("occupy") ){
                    ButtonSet4(but9, true);
                    but9.setTag("40");
                } else{
                    ButtonSet4(but9, false);
                    but9.setTag("41");
                }
                if( L1.get(9).equals("occupy") ){
                    ButtonSet4(but10, true);
                    but10.setTag("40");
                } else{
                    ButtonSet4(but10, false);
                    but10.setTag("40");
                }
                if( L1.get(10).equals("occupy") ){
                    ButtonSet2(but11, true);
                    but11.setTag("20");
                } else{
                    ButtonSet2(but11, false);
                    but11.setTag("21");
                }
                if( L1.get(11).equals("occupy") ){
                    ButtonSet2(but12, true);
                    but12.setTag("20");
                } else{
                    ButtonSet2(but12, false);
                    but12.setTag("21");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void OtherButton(final String ButName){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("TableDB").child(receivedMsg);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String v = dataSnapshot.child(ButName).getValue(String.class);
                int w = dataSnapshot.child("TableAvailNum").getValue(int.class);
                if( v.equals("occupy") ){
                    myRef.child(ButName).setValue("vacant");
                    myRef.child("TableAvailNum").setValue(w+1);
                }else{
                    myRef.child(ButName).setValue("occupy");
                    myRef.child("TableAvailNum").setValue(w-1);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe_status);

        but1 = (ImageButton)findViewById(R.id.table1_4);
        but2 = (ImageButton)findViewById(R.id.table2_4);
        but3 = (ImageButton)findViewById(R.id.table3_4);
        but4 = (ImageButton)findViewById(R.id.table4_4);
        but5 = (ImageButton)findViewById(R.id.table5_4);
        but6 = (ImageButton)findViewById(R.id.table6_4);
        but7 = (ImageButton)findViewById(R.id.table7_2);
        but8 = (ImageButton)findViewById(R.id.table8_2);
        but9 = (ImageButton)findViewById(R.id.table9_4);
        but10 = (ImageButton)findViewById(R.id.table10_4);
        but11 = (ImageButton)findViewById(R.id.table11_2);
        but12 = (ImageButton)findViewById(R.id.table12_2);
        //////////////////////////////////////////////////////////
        but1.setImageResource(R.drawable.table_4_occupied);
        but1.setImageResource(R.drawable.table_4_occupied);
        but2.setImageResource(R.drawable.table_4_occupied);
        but3.setImageResource(R.drawable.table_4_occupied);
        but4.setImageResource(R.drawable.table_4_occupied);
        but5.setImageResource(R.drawable.table_4_occupied);
        but6.setImageResource(R.drawable.table_4_occupied);

        but7.setImageResource(R.drawable.table_2_occupied);
        but8.setImageResource(R.drawable.table_2_occupied);

        but9.setImageResource(R.drawable.table_4_occupied);
        but10.setImageResource(R.drawable.table_4_occupied);
        but11.setImageResource(R.drawable.table_2_occupied);
        but12.setImageResource(R.drawable.table_2_occupied);

        CafeName = (TextView) findViewById(R.id.StoreName);
        TableStatus = (TextView) findViewById(R.id.RemainingNumTable);

        // button setting completed
        ButtonSet();

        final Handler handler = new Handler();

        Toast.makeText(CafeStatusActivity.this, receivedMsg, Toast.LENGTH_LONG).show();
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table1");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table2");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table3");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table4");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table5");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table6");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table7");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table8");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table9");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table10");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table11");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                OtherButton("Table12");
                handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });

    }
}
