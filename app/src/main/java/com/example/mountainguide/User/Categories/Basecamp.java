package com.example.mountainguide.User.Categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mountainguide.*;
import com.example.mountainguide.User.Adapter.MountainAdapter;
import com.example.mountainguide.User.Model.MountainModel;
import com.example.mountainguide.User.UserDashboard;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.mountainguide.User.Model.BasecampModel;
import com.example.mountainguide.User.Adapter.BasecampAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Basecamp extends AppCompatActivity {

    RecyclerView recview;
    ImageView btnbck;
    ArrayList<BasecampModel> dataList;
    FirebaseFirestore db;
    BasecampAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_basecamp);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new BasecampAdapter(dataList);
        recview.setAdapter(adapter);
        btnbck = (ImageView)findViewById(R.id.btnback);

        btnbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

        db = FirebaseFirestore.getInstance();
        db.collection("basecamp").orderBy("name").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot value) {
                        List<DocumentSnapshot> list = value.getDocuments();
                        for (DocumentSnapshot d : list) {
                            BasecampModel obj = d.toObject(BasecampModel.class);
                            dataList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}