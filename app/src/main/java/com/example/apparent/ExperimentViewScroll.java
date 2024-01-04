package com.example.apparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class  ExperimentViewScroll extends AppCompatActivity implements MyAdapter.RecyclerviewClickListener {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<ApartmentUnit> list;

    private MyAdapter.RecyclerviewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_view_scroll);

        recyclerView = findViewById(R.id.listViewApartment);
        database = FirebaseDatabase.getInstance().getReference("ApartmentUnit");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list, listener);
        recyclerView.setAdapter(myAdapter);
        setOnClickListner();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ApartmentUnit apartmentUnit = dataSnapshot.getValue(ApartmentUnit.class);
                    list.add(apartmentUnit);


                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private void setOnClickListner() {
        listener = new MyAdapter.RecyclerviewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(),
                        MainPageInformationDisplay.class);
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("desc", list.get(position).getDescription());
                intent.putExtra("address", list.get(position).getAddress());
                intent.putExtra("rate", list.get(position).getRatings());
                intent.putExtra("photo", list.get(position).getImagePath());
                startActivity(intent);


            }
        };
    }


    /**
     * @param v
     * @param position
     */
    @Override
    public void onClick(View v, int position) {
        Intent intent = new Intent(getApplicationContext(),
                MainPageInformationDisplay.class);
        intent.putExtra("name", list.get(position).getName());
        intent.putExtra("desc", list.get(position).getDescription());
        intent.putExtra("address", list.get(position).getAddress());
        intent.putExtra("rate", list.get(position).getRatings());
        intent.putExtra("photo", list.get(position).getImagePath());
        startActivity(intent);
    }
}



