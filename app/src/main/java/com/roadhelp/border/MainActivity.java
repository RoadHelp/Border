package com.roadhelp.border;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<com.roadhelp.border.BorderPunktItem> borderPunktItems = new ArrayList<BorderPunktItem>();
        borderPunktItems.add(new BorderPunktItem(R.drawable.bagrat, Utils.TITLE_1, Utils.DESCR_1, Utils.CAMERA_URL_1));
        borderPunktItems.add(new BorderPunktItem(R.drawable.branevo, Utils.TITLE_2, Utils.DESCR_2, Utils.CAMERA_URL_2));
        borderPunktItems.add(new BorderPunktItem(R.drawable.goldap, Utils.TITLE_3, Utils.DESCR_3, Utils.CAMERA_URL_3));
        borderPunktItems.add(new BorderPunktItem(R.drawable.gzhehotki, Utils.TITLE_4, Utils.DESCR_4, Utils.CAMERA_URL_4));

        borderPunktItems.add(new BorderPunktItem(R.drawable.sovetsk, Utils.TITLE_5, Utils.DESCR_5, Utils.CAMERA_URL_5));
        borderPunktItems.add(new BorderPunktItem(R.drawable.morskoe, Utils.TITLE_6, Utils.DESCR_6, Utils.CAMERA_URL_6));
        borderPunktItems.add(new BorderPunktItem(R.drawable.pogranich, Utils.TITLE_7, Utils.DESCR_7, Utils.CAMERA_URL_7));
        borderPunktItems.add(new BorderPunktItem(R.drawable.nesterov, Utils.TITLE_8, Utils.DESCR_8, Utils.CAMERA_URL_8));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new com.roadhelp.border.BorderPunktAdapter(borderPunktItems, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }
}