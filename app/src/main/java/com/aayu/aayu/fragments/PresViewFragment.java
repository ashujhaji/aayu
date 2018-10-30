package com.aayu.aayu.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aayu.aayu.Model.Prescriptions;
import com.aayu.aayu.R;
import com.bumptech.glide.Glide;

public class PresViewFragment extends Fragment {
    private ImageView pres_img;
    private TextView status;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_prescription, container, false);

        pres_img = view.findViewById(R.id.pres_img);
        status = view.findViewById(R.id.status);

        Prescriptions prescriptions = (Prescriptions) getArguments().getSerializable("list");

        Glide.with(getActivity())
                .load(prescriptions.getUrl())
                .into(pres_img);

        status.setText(prescriptions.getStatus());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static PresViewFragment getInstance(Prescriptions prescriptions) {
        PresViewFragment myFragment = new PresViewFragment();
        Bundle args = new Bundle();
        args.putSerializable("list", prescriptions);
        myFragment.setArguments(args);
        return myFragment;
    }
}
