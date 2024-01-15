package com.example.finalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class SecondActivity extends LifecycleActivity{

    ListView listView;
    SharedPreferences prefs;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView = findViewById(R.id.listView);
        prefs = getSharedPreferences("Preferences", MODE_PRIVATE);
        dataList = new ArrayList<>();

        displayData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // Check if the selected position exists
                if (dataList != null && position >= 0 && position < dataList.size()){
                    String entry = dataList.get(position);

                    // Split the entry
                    String[] parts = entry.split("\n");

                    String name = parts[0]; // name
                    String sum = parts[1]; // sum

                    Toast.makeText(SecondActivity.this, name + " " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void displayData(){
        // Select entries from SharedPreferences
        Map<String, ?> allEntries = prefs.getAll();

        dataList.clear();

        // Add entries to dataList
        for (Map.Entry<String, ?> entry : allEntries.entrySet()){
            dataList.add(entry.getValue().toString());
        }

        // Create ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, R.layout.list_item_layout, R.id.textViewName, dataList){
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                String entry = dataList.get(position);
                String[] parts = entry.split("\n");
                String name = parts[0];
                String sum = parts[1];

                TextView textViewName = view.findViewById(R.id.textViewName);
                TextView textViewSum = view.findViewById(R.id.textViewSum);

                textViewName.setText(name);
                textViewSum.setText(sum);

                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
    }
}
