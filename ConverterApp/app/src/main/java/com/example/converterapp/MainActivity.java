package com.example.converterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        ListView listView = findViewById(R.id.list_view);

        UserAccount donald = new UserAccount("Donald","guest", false);
        UserAccount donald1 = new UserAccount("Donald","guest", false);
        UserAccount donald2 = new UserAccount("Donald","guest", false);
        UserAccount donald3 = new UserAccount("Donald","guest", false);

        ArrayList<UserAccount> users = new ArrayList<>();
        users.add(donald);
        users.add(donald1);
        users.add(donald2);
        users.add(donald3);

        ArrayAdapter<UserAccount> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(arrayAdapter);
    }
}