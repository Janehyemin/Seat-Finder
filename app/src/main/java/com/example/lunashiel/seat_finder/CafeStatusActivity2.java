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

public class CafeStatusActivity2 extends AppCompatActivity {
    ImageButton  but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12, but13, but14, but15, but16, but17, but18, but19, but20, but21, but22, but23, but24, but25;
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
                    ButtonSet4(but7, true);
                    but7.setTag("40");
                } else{
                    ButtonSet4(but7, false);
                    but7.setTag("41");
                }
                if( L1.get(7).equals("occupy") ){
                    ButtonSet4(but8, true);
                    but8.setTag("40");
                } else{
                    ButtonSet4(but8, false);
                    but8.setTag("41");
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
                    but10.setTag("41");
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
                if( L1.get(12).equals("occupy") ){
                    ButtonSet2(but13, true);
                    but13.setTag("20");
                } else{
                    ButtonSet2(but13, false);
                    but13.setTag("21");
                }
                if( L1.get(13).equals("occupy") ){
                    ButtonSet4(but14, true);
                    but14.setTag("40");
                } else {
                    ButtonSet4(but14, false);
                    but14.setTag("41");
                }
                if( L1.get(14).equals("occupy") ){
                    ButtonSet2(but15, true);
                    but15.setTag("20");
                } else {
                    ButtonSet2(but15, false);
                    but15.setTag("21");
                }
                if( L1.get(15).equals("occupy") ){
                    ButtonSet2(but16, true);
                    but16.setTag("20");
                } else {
                    ButtonSet2(but16, false);
                    but16.setTag("21");
                }
                if( L1.get(16).equals("occupy") ){
                    ButtonSet2(but17, true);
                    but17.setTag("20");
                } else {
                    ButtonSet2(but17, false);
                    but17.setTag("21");
                }
                if( L1.get(17).equals("occupy") ){
                    ButtonSet4(but18, true);
                    but18.setTag("40");
                } else {
                    ButtonSet4(but18, false);
                    but18.setTag("41");
                }
                if( L1.get(18).equals("occupy") ){
                    ButtonSet2(but19, true);
                    but19.setTag("20");
                } else {
                    ButtonSet2(but19, false);
                    but19.setTag("21");
                }
                if( L1.get(19).equals("occupy") ){
                    ButtonSet2(but20, true);
                    but20.setTag("20");
                } else {
                    ButtonSet2(but20, false);
                    but20.setTag("21");
                }
                if( L1.get(20).equals("occupy") ){
                    ButtonSet2(but21, true);
                    but21.setTag("20");
                } else {
                    ButtonSet2(but21, false);
                    but21.setTag("21");
                }
                if( L1.get(21).equals("occupy") ){
                    ButtonSet4(but22, true);
                    but22.setTag("40");
                } else {
                    ButtonSet4(but22, false);
                    but22.setTag("41");
                }
                if( L1.get(22).equals("occupy") ){
                    ButtonSet2(but23, true);
                    but23.setTag("20");
                } else {
                    ButtonSet2(but23, false);
                    but23.setTag("21");
                }
                if( L1.get(23).equals("occupy") ){
                    ButtonSet2(but24, true);
                    but24.setTag("20");
                } else {
                    ButtonSet2(but24, false);
                    but24.setTag("21");
                }
                if( L1.get(24).equals("occupy") ){
                    ButtonSet2(but25, true);
                    but25.setTag("20");
                } else {
                    ButtonSet2(but25, false);
                    but25.setTag("21");
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
        setContentView(R.layout.activity_cafe_status2);

        but1 = (ImageButton)findViewById(R.id.table1_4);
        but2 = (ImageButton)findViewById(R.id.table2_4);
        but3 = (ImageButton)findViewById(R.id.table3_4);
        but4 = (ImageButton)findViewById(R.id.table4_4);
        but5 = (ImageButton)findViewById(R.id.table5_4);
        but6 = (ImageButton)findViewById(R.id.table6_4);
        but7 = (ImageButton)findViewById(R.id.table7_4);
        but8 = (ImageButton)findViewById(R.id.table8_4);
        but9 = (ImageButton)findViewById(R.id.table9_4);
        but10 = (ImageButton)findViewById(R.id.table10_4);
        but11 = (ImageButton)findViewById(R.id.table11_2);
        but12 = (ImageButton)findViewById(R.id.table12_2);
        but13 = (ImageButton)findViewById(R.id.table13_2);
        but14 = (ImageButton)findViewById(R.id.table14_4);
        but15 = (ImageButton)findViewById(R.id.table15_2);
        but16 = (ImageButton)findViewById(R.id.table16_2);
        but17 = (ImageButton)findViewById(R.id.table17_2);
        but18 = (ImageButton)findViewById(R.id.table18_4);
        but19 = (ImageButton)findViewById(R.id.table19_2);
        but20 = (ImageButton)findViewById(R.id.table20_2);
        but21 = (ImageButton)findViewById(R.id.table21_2);
        but22 = (ImageButton)findViewById(R.id.table22_4);
        but23 = (ImageButton)findViewById(R.id.table23_2);
        but24 = (ImageButton)findViewById(R.id.table24_2);
        but25 = (ImageButton)findViewById(R.id.table25_2);

        //////////////////////////////////////////////////////////
        but1.setImageResource(R.drawable.table_4_occupied);
        but2.setImageResource(R.drawable.table_4_occupied);
        but3.setImageResource(R.drawable.table_4_occupied);
        but4.setImageResource(R.drawable.table_4_occupied);
        but5.setImageResource(R.drawable.table_4_occupied);
        but6.setImageResource(R.drawable.table_4_occupied);
        but7.setImageResource(R.drawable.table_4_occupied);
        but8.setImageResource(R.drawable.table_4_occupied);
        but9.setImageResource(R.drawable.table_4_occupied);
        but10.setImageResource(R.drawable.table_4_occupied);
        but11.setImageResource(R.drawable.table_2_occupied);
        but12.setImageResource(R.drawable.table_2_occupied);
        but13.setImageResource(R.drawable.table_2_occupied);
        but14.setImageResource(R.drawable.table_4_occupied);
        but15.setImageResource(R.drawable.table_2_occupied);
        but16.setImageResource(R.drawable.table_2_occupied);
        but17.setImageResource(R.drawable.table_2_occupied);
        but18.setImageResource(R.drawable.table_4_occupied);
        but19.setImageResource(R.drawable.table_2_occupied);
        but20.setImageResource(R.drawable.table_2_occupied);
        but21.setImageResource(R.drawable.table_2_occupied);
        but22.setImageResource(R.drawable.table_4_occupied);
        but23.setImageResource(R.drawable.table_2_occupied);
        but24.setImageResource(R.drawable.table_2_occupied);
        but25.setImageResource(R.drawable.table_2_occupied);


        CafeName = (TextView) findViewById(R.id.StoreName);
        TableStatus = (TextView) findViewById(R.id.RemainingNumTable);

        // button setting completed
        ButtonSet();

        final Handler handler = new Handler();

        Toast.makeText(CafeStatusActivity2.this, receivedMsg, Toast.LENGTH_LONG).show();
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
        but13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table13");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table14");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table15");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table16");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table17");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table18");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table19");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table20");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table21");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table22");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table23");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table24");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
        but25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userType == 2){
                    OtherButton("Table25");
                    handler.postDelayed(new Runnable(){@Override public void run(){ButtonSet();}},250);}
            }
        });
    }
}
