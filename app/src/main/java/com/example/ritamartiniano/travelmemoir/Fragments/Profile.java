package com.example.ritamartiniano.travelmemoir.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ritamartiniano.travelmemoir.R;
import com.example.ritamartiniano.travelmemoir.User;

public class Profile extends Fragment {
    public User user;
    public TextView profUsername;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
        //profUsername = (TextView)findViewById(R.id.username);
    }


}
