package com.example.mountainguide.User.Categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mountainguide.*;
import com.example.mountainguide.User.Adapter.FirstAidAdapter;
import com.example.mountainguide.User.Model.FirstAidModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FirstAid extends AppCompatActivity {
    RecyclerView recview;
    ArrayList<FirstAidModel> dataList;
    FirebaseFirestore db;
    FirstAidAdapter adapter;
    ImageView btnbck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_firstaid);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new FirstAidAdapter(dataList);
        recview.setAdapter(adapter);
        btnbck = (ImageView) findViewById(R.id.btnback);
        btnbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                finish();
            }
        });


        db = FirebaseFirestore.getInstance();
        db.collection("firstAid").orderBy("title").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            FirstAidModel obj = d.toObject(FirstAidModel.class);
                            dataList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}