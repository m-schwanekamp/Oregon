package com.example.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    location rmv = new location(0, "start");
    player play = new player ("Hattie Campbell");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.button);
        final TextView OregonTrail = findViewById(R.id.OregonTrail);
        final TextView MilesTraveled = findViewById(R.id.MilesTraveled);
        final TextView TextInventory = findViewById(R.id.TextInventory);
        final EditText editPlayerInput = findViewById(R.id.editPlayerInput);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String input = editPlayerInput.getText().toString();
                play.input(rmv,input);
                MilesTraveled.setText(Integer.toString(rmv.getDistance()) + "\n" + rmv.getName() + "\n" + play.getEventLog());
                TextInventory.setText(play.getInvText());
            }
        });
