package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends LifecycleActivity{

    EditText nameEditText, number1, number2;
    TextView sumResult, nameResult;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        sumResult = findViewById(R.id.sumResult);
        nameResult = findViewById(R.id.nameResult);

        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
    }

    public void addData(View view){
        String name = nameEditText.getText().toString().trim();
        String num1Str = number1.getText().toString().trim();
        String num2Str = number2.getText().toString().trim();

        if (name.isEmpty() || num1Str.isEmpty() || num2Str.isEmpty()){
            Toast.makeText(this, "Entries can't be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        int sum = num1 + num2;

        String entry = name + "\n" + sum;

        prefs.edit().putString(String.valueOf(System.currentTimeMillis()), entry).apply();

        // Display result
        nameResult.setText(name);
        sumResult.setText(String.valueOf(sum));
    }

    public void delData(View view){
        prefs.edit().clear().apply();
        Toast.makeText(this, "The list has been emptied!", Toast.LENGTH_SHORT).show();
    }

    public void showList(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
