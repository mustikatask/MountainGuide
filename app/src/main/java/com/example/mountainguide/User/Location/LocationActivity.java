package com.example.mountainguide.User.Location;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.example.mountainguide.User.Categories.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.example.mountainguide.*;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.mountainguide.User.*;

import static android.content.ContentValues.TAG;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseFirestore db;
    public LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        db = FirebaseFirestore.getInstance();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final CollectionReference mountain = db.collection("mountain");
        mountain.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        GeoPoint geoPoint = document.getGeoPoint("geoPoint");
                        String id = document.getString("id");
                        LatLng latLng = new LatLng(document.getGeoPoint("geoPoint").getLatitude(), document.getGeoPoint("geoPoint").getLongitude());
                        if (geoPoint != null) {
                            mMap.addMarker(new MarkerOptions().position(new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude())).title(id));

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        mMap.getUiSettings().setZoomControlsEnabled(true);
                        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {
                                Intent i = new Intent(LocationActivity.this, DetailsMountain.class);
                                i.putExtra("uid", id);
                                startActivity(i);
                                finish();
                                return false;
                            }
                        });

                    }
                }
            }
        });
    }
}