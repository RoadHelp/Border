package com.roadhelp.border;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment3PunktInfo extends Fragment {

    public static Fragment3PunktInfo newInstance(String title, String descr) {  // метод принимает 2 строки и запихивает их в bundle args и возвращает фрагмент
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        args.putString("DESCR", descr);

        Fragment3PunktInfo fragment3PunktInfo = new Fragment3PunktInfo();
        fragment3PunktInfo.setArguments(args);
        return fragment3PunktInfo;
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

        String id = getArguments().getString("TITLE");
        String recipe = getArguments().getString("DESCR");
        TextView title = view.findViewById(R.id.title_textView);
        TextView recipeTextView = view.findViewById(R.id.recipe_textView);
        title.setText(id);
        recipeTextView.setText(recipe);

    }
}
