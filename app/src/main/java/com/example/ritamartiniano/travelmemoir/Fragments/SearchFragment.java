package com.example.ritamartiniano.travelmemoir.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ritamartiniano.travelmemoir.R;


public class SearchFragment extends Fragment {

    public static SearchFragment newInstance()
    {
        SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
    {
        return inflater.inflate(R.layout.fragment_search,container, false);
    }
}
