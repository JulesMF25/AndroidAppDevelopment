package com.example.time;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Alarm extends AppCompatActivity {

    TextView plainText;
    TextView textView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Asigning Variables
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottombar);
        //Clock as default viewing value
        bottomNavigationView.setSelectedItemId(R.id.alarm);
        //Item Selected Listner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //Alarm Selected
                    case R.id.alarm:
                        return true;
                    //Clock Default
                    case R.id.clock:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    //Stopwatch Selected
                    case R.id.stopwatch:
                        startActivity(new Intent(getApplicationContext(),Stopwatch.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


    public void select(View view){
        plainText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        mediaPlayer = MediaPlayer.create(this,R.raw.alarm);
        try{
            int time = Integer.parseInt(plainText.getText().toString());

            //if you want to start application from fixed time then add to 90,100 milli-second
            final int milliSecond = (time * 1000) + 100;
            //value is needed in milli-second. so first we convert value/time into milli-second
            new CountDownTimer(milliSecond,1000){//1000 is equal to 1 second

                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("00.0" +String.valueOf(millisUntilFinished/1000));
                }

                @Override
                public void onFinish() {
                    textView.setText("Alarm");
                    mediaPlayer.start();
                }
            }.start();
        }catch (NumberFormatException e){
            Toast.makeText(this,"Enter Value in integer Only",Toast.LENGTH_LONG).show();
        }

    }
    public void stop(View view){
        mediaPlayer.stop();
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Designed By Dawa Tamang (L3C8)", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
