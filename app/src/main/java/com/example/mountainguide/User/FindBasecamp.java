package com.example.mountainguide.User;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import com.example.mountainguide.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mountainguide.User.Adapter.BasecampAdapter;
import com.example.mountainguide.User.Adapter.MountainAdapter;
import com.example.mountainguide.User.Model.BasecampModel;

import com.example.mountainguide.User.Model.MountainModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindBasecamp extends AppCompatActivity {

    String textId;
    RecyclerView recview;
    ImageView btnbck;
    ArrayList<BasecampModel> dataList;
    FirebaseFirestore db;
    BasecampAdapter adapter;

    Bundle bundle= new Bundle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_basecamp);

        bundle = getIntent().getExtras();

        recview = (RecyclerView) findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new BasecampAdapter(dataList);
        recview.setAdapter(adapter);
        btnbck = (ImageView)findViewById(R.id.btnback);
        textId = bundle.getString("uid");

        btnbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
                finish();
            }
        });

        db = FirebaseFirestore.getInstance();
        Task<QuerySnapshot> basecamp = db.collection("basecamp").whereEqualTo("id_gunung", textId).get()
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
