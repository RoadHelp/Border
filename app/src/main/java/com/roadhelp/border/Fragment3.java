package com.roadhelp.border;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {

    public static Fragment3 newInstance(String id, String recipe) {
        Bundle args = new Bundle();
        args.putString("ID", id);
        args.putString("RECIPE", recipe);

        Fragment3 fragment3 = new Fragment3();
        fragment3.setArguments(args);
        return fragment3;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_punkt, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String id = getArguments().getString("ID");
        String recipe = getArguments().getString("RECIPE");
        TextView title = view.findViewById(R.id.title_textView);
        TextView recipeTextView = view.findViewById(R.id.recipe_textView);
        title.setText(id);
        recipeTextView.setText(recipe);
    }
}
