package com.roadhelp.border;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class Fragment2 extends Fragment {

    private String mImageAddress =
            "https://cams2.rugrad.eu/cams/big/pl/gronovo/in.jpg";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment2, container, false);
        ImageView imageView = view.findViewById(R.id.cameraImageView);

        Glide
                .with(this)
                .load(mImageAddress)
                .into(imageView);

        return view;

    }
}
