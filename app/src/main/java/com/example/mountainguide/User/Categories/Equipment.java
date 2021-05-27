package com.example.mountainguide.User.Categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mountainguide.User.Adapter.EquipmentAdapter;
import com.example.mountainguide.User.Model.EquipmentModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.mountainguide.*;

import java.util.ArrayList;
import java.util.List;

public class Equipment extends AppCompatActivity {

    RecyclerView recview;
    ArrayList<EquipmentModel> dataList;
    FirebaseFirestore db;
    EquipmentAdapter adapter;
    ImageView btnbck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_equipment);

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new EquipmentAdapter(dataList);
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
        db.collection("equipment").orderBy("title").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            EquipmentModel obj = d.toObject(EquipmentModel.class);
                            dataList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}