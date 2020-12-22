package com.roadhelp.border;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Fragment2 extends Fragment {

    public static Fragment2 newInstance(String cameraUrl) {  // метод принимает 2 строки и запихивает их в bundle args и возвращает фрагмент
        Bundle args = new Bundle();
        args.putString("CameraUrl", cameraUrl);


        Fragment2 fragment2 = new Fragment2();
        fragment2.setArguments(args);
        return fragment2;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String cameraUrl = getArguments().getString("CameraUrl");
        ImageView imageView = view.findViewById(R.id.cameraImageView);

        Glide
                .with(this)
                .load(cameraUrl)
                .into(imageView);

    }
}
