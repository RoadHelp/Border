package com.roadhelp.border;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

import com.roadhelp.border.ui.main.SectionsPagerAdapter;

public class TabbedPunktActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_punkt);

        TextView title = findViewById(R.id.title); //получаем из интента title, который пришёл из BorderPunktAdapter
        Intent intent = getIntent();
        if (intent != null){
            title.setText(intent.getStringExtra("title")); // устанавливаем в заголовок TabbedPunktActivity
        }


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);

        // прмиеняем самописную функцию addFragment в PagerAdapter.
        // вызывая метод newInstance который находиться в классе фрагмента
        sectionsPagerAdapter.addFragment(Fragment3PunktInfo.newInstance(intent.getStringExtra("title"), intent.getStringExtra("descr")));
        sectionsPagerAdapter.addFragment(Fragment1Chat.newInstance(intent.getStringExtra("title")));
        sectionsPagerAdapter.addFragment(Fragment2.newInstance(intent.getStringExtra("cameraUrl")));
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
}