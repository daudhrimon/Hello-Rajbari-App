package com.friendsit.hellorajbari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SearchView homeSv;
    public static DatabaseReference databaseReference;
    private List<HelloModel> list;
    private LinearLayout btnLay;
    private RecyclerView homeRecycler;
    private HelloAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initial();

        getAllData();

        homeSv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homeSearchMethod(newText);
                return true;
            }
        });
    }

    private void homeSearchMethod(String newText) {
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
            adapter = new HelloAdapter(MainActivity.this, searchList);
            btnLay.setVisibility(View.GONE);
            homeRecycler.setVisibility(View.VISIBLE);
            homeRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            homeRecycler.setVisibility(View.GONE);
            btnLay.setVisibility(View.VISIBLE);
        }
    }

    private void getAllData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot eachSnap : dataSnapshot.getChildren()) {
                                if (eachSnap.exists()) {
                                    HelloModel helloModel = eachSnap.getValue(HelloModel.class);
                                    list.add(helloModel);
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void govtOfficeClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "GovtOffice");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void policeClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Police");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void hospitalClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Hospital");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void fireServiceClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "FireService");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void ambulanceClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Ambulance");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void doctorsClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Doctors");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void bloodDonorsClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "BloodDonors");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void touristSpotClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "TouristSpots");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void hotelsClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Hotels");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void rentACarClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "RentACar");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void electricityClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("Cat", "Electricity");
        startActivity(intent);
        homeSv.clearFocus();
    }

    public void aboutUsClick(View view) {
        Intent intent = new Intent(MainActivity.this, TaskActivity.class);
        intent.putExtra("State", "About");
        startActivity(intent);
        homeSv.clearFocus();
    }

    private void initial() {
        homeSv = findViewById(R.id.homeSv);
        databaseReference = FirebaseDatabase.getInstance().getReference("AllData");
        databaseReference.keepSynced(true);
        list = new ArrayList<>();
        homeRecycler = findViewById(R.id.homeRecycler);
        btnLay = findViewById(R.id.btnLay);
        homeRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}