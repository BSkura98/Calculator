package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpleCalculatorBtn = (Button)findViewById(R.id.simpleCalculatorBtn);
        simpleCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SimpleCalculatorActivity.class);
                startActivity(startIntent);
            }
        });

        Button advancedCalculatorBtn = (Button)findViewById(R.id.advancedCalculatorBtn);
        advancedCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), AdvancedCalculatorActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
