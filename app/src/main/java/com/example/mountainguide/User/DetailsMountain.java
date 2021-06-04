package com.example.mountainguide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mountainguide.*;
import com.example.mountainguide.User.Categories.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsMountain extends AppCompatActivity {

    String t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, textId;
    TextView text1, text2, text3, text4, text5, text6, text7, text8, text9;
    FloatingActionButton btn;
    Button gotobc;
    ImageView img_detail;
    FirebaseFirestore db;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsdesignmountain);

        bundle = getIntent().getExtras();

        t1 = bundle.getString("uname");
        t2 = bundle.getString("ustatus");
        t3 = bundle.getString("uheight");
        t4 = bundle.getString("unote");
        t5 = bundle.getString("udesc");
        t6 = bundle.getString("ulocation");
        t7 = bundle.getString("ugeometry");
        t8 = bundle.getString("uweather");
        t9 = bundle.getString("uterrain");
        t10 = bundle.getString("uimgurl");
        textId = bundle.getString("uid");

        text1 = findViewById(R.id.nameholder);
        text2 = findViewById(R.id.statusholder);
        text3 = findViewById(R.id.heightholder);
        text4 = findViewById(R.id.noteholder);
        text5 = findViewById(R.id.descholder);
        text6 = findViewById(R.id.locationholder);
        text7 = findViewById(R.id.geometryholder);
        text8 = findViewById(R.id.weatherholder);
        text9 = findViewById(R.id.terrainholder);
        img_detail=findViewById(R.id.img_detail);
        if (t1 != null) {
            getIntentMountain();
        } else if (t1 == null) {
            getIntentLocation();
        }

        btn =findViewById(R.id.btnback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        gotobc =findViewById(R.id.goto_bc);
        gotobc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindBasecamp.class);
                intent.putExtra("uid", textId);
                startActivity(intent);
                finish();
            }
        });
    }

    public void getIntentMountain() {

        text1.setText(t1);
        text2.setText(t2);
        text3.setText(t3);
        text4.setText(t4);
        text5.setText(t5);
        text6.setText(t6);
        text7.setText(t7);
        text8.setText(t8);
        text9.setText(t9);
        Glide.with(this)
                .load(t10)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .into(img_detail);
    }

    public void getIntentLocation() {
        db = FirebaseFirestore.getInstance();
        final String id = textId;
        Task<QuerySnapshot> mountain = db.collection("mountain").whereEqualTo("id", textId).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                            if (d.exists()) {
                                t1 = d.getString("name");
                                t2 = d.getString("status");
                                t3 = d.getString("height");
                                t4 = d.getString("note");
                                t5 = d.getString("desc");
                                t6 = d.getString("location");
                                t7 = d.getString("geometry");
                                t8 = d.getString("weather");
                                t9 = d.getString("terrain");
                                t10 = d.get("imgurl").toString();
                            }
                        }
                        text1.setText(t1);
                        text2.setText(t2);
                        text3.setText(t3);
                        text4.setText(t4);
                        text5.setText(t5);
                        text6.setText(t6);
                        text7.setText(t7);
                        text8.setText(t8);
                        text9.setText(t9);
                        Glide.with(DetailsMountain.this)
                                .load(t10)
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                                .centerCrop()
                                .into(img_detail);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Error", String.valueOf(e));
                    }

                });

    }
}