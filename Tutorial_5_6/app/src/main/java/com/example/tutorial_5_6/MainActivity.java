package com.example.tutorial_5_6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.tutorial_5_6.adapters.FriendAdapter;
import com.example.tutorial_5_6.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int FRIEND_ADDED = 1;
    private List<Friend> friends;
    private FriendAdapter friendAdapter;

    private RecyclerView rvFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFriends = findViewById(R.id.rvFriends);

        friends = new ArrayList<>();
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));
        friends.add(new Friend("Tavanhiep", "tavanhiep12@gmail.com", "0124578979"));

        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);

        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        ImageButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, FRIEND_ADDED);
            }
        });}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == FRIEND_ADDED){
            Friend friend = (Friend) data.getSerializableExtra("FRIEND");

            friends.add(0, friend);

            //notify the adapter recycle view
            friendAdapter.notifyItemInserted(0);
        }
    }
}