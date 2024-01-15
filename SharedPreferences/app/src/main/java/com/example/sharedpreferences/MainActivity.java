package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView counterView;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        counterView = findViewById(R.id.counterView);

        prefs = getPreferences(MODE_PRIVATE);
        updateInterface();
    }

    private void updateInterface() {
        List<String> list = getPrefs();
        setListData(list);
        counterView.setText(String.valueOf(list.size()));
    }

    private List<String> getPrefs() {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, ?> entry : prefs.getAll().entrySet())
            list.add(entry.getValue().toString());
        return list;
    }

    private void setListData(List<String> data) {
        listView.setAdapter(new ArrayAdapter<>(
        this, android.R.layout.simple_list_item_1, data));
    }

    public void addData(View view) {
        prefs.edit().putString(counterView.getText().toString(), getDateTime()).apply();
        updateInterface();
    }

    public void delData(View view) {
        prefs.edit().clear().apply();
        updateInterface();
    }

    private String getDateTime() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        return String.format(Locale.ENGLISH, "%04d-%02d-%02d %02d:%02d:%02d:%03d",
                year, month, day, hours, minutes, seconds,currentTimeMillis%1000);
    }
}