package com.friendsit.hellorajbari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskActivity extends AppCompatActivity {
    private SearchView taskSv;
    private String State;
    private LinearLayout adminLay, msgLay;
    private String Cat;
    private List<HelloModel> list;
    private RecyclerView recycler;
    private HelloAdapter adapter;
    private TextView devTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        initial();

        taskSv.clearFocus();

        stateCheck();

        if (Cat != null && !Cat.isEmpty()) {
            retriveAndShow();
        }

        taskSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchMethod(newText);
                return true;
            }
        });
    }


    public void daudPhnClick(View view) {
        phoneClickAction("+8801779808900");
    }

    public void daudEmailClick(View view) {
        emailClickAction("daudhrimon@gmail.com");
    }

    public void polokPhnClick(View view) {
        phoneClickAction("+8801911866613");
    }

    public void polokEmailClick(View view) {
        emailClickAction("hello@jrpolok.com");
    }

    private void searchMethod(String newText) {
        List<HelloModel> searchList = new ArrayList<>();
        if (!newText.isEmpty()) {
            searchList.clear();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getNam().toLowerCase(Locale.ROOT).contains(newText) ||
                        list.get(i).getTit().toLowerCase(Locale.ROOT).contains(newText) ||
                        list.get(i).getPho().contains(newText)) {
                    searchList.add(list.get(i));
                }
            }
            adapter = new HelloAdapter(TaskActivity.this, searchList);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            adapter = new HelloAdapter(TaskActivity.this, list);
            recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void retriveAndShow() {
        DatabaseReference dataRef = MainActivity.databaseReference.child(Cat);
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    msgLay.setVisibility(View.GONE);
                    recycler.setVisibility(View.VISIBLE);
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (dataSnapshot.exists()) {
                            HelloModel helloModel = dataSnapshot.getValue(HelloModel.class);
                            list.add(helloModel);
                        }
                    }
                    adapter = new HelloAdapter(TaskActivity.this, list);
                    recycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    recycler.setVisibility(View.GONE);
                    msgLay.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void stateCheck() {
        Intent intent = getIntent();
        this.State = intent.getStringExtra("State");
        if (State != null && !State.isEmpty()) {
            if (State.equals("About")) {
                taskSv.setVisibility(View.GONE);
                msgLay.setVisibility(View.GONE);
                adminLay.setVisibility(View.VISIBLE);
                devTv.setVisibility(View.VISIBLE);
            }
        } else {
            this.Cat = intent.getStringExtra("Cat");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void phoneClickAction(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    private void emailClickAction(String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void initial() {
        taskSv = findViewById(R.id.taskSv);
        adminLay = findViewById(R.id.adminLay);
        list = new ArrayList<>();
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(TaskActivity.this));
        msgLay = findViewById(R.id.msgLay);
        devTv = findViewById(R.id.devTv);
    }
}