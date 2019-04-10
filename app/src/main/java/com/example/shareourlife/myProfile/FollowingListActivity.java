package com.example.shareourlife.myProfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.shareourlife.R;

import io.reactivex.functions.Action;

//页面内分组与展示。就像Bilibili的展示方式
public class FollowingListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FollowingList_RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followinglist);


        recyclerView = findViewById(R.id.FollowingList_recyclerView);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new FollowingList_RecyclerViewAdapter(FollowingListActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnClickListener(new FollowingList_RecyclerViewAdapter.OnClickListener() {
            @Override
            public void OnSettingClick(int position, FollowingList_RecyclerView_Item item) {
                Toast.makeText(FollowingListActivity.this, "OnSettingClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFollowButtonClick(int position) {
                Toast.makeText(FollowingListActivity.this, "OnFollowButtonClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
