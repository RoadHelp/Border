package com.roadhelp.border;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PunktActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punkt);

        TextView title = findViewById(R.id.title_textView);
        TextView recipe = findViewById(R.id.recipe_textView);

        Intent intent = getIntent();
        if (intent != null){
            title.setText(intent.getStringExtra("title"));
            recipe.setText(intent.getStringExtra("recipe"));
        }
    }
}