package com.example.amitnew.ui.fragments.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amitnew.R;
import com.example.amitnew.ui.activity.LoginScreen;
import com.example.amitnew.ui.activity.SignUpScreen;


public class ProfileFragment extends Fragment {

    Button log_out;

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), SignUpScreen.class);
                startActivity(in);

            }
        });
    }

    protected void initview(View v){
        log_out = v.findViewById(R.id.logout_btn);
    }




}