package com.example.ritamartiniano.travelmemoir.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ritamartiniano.travelmemoir.Adapter.JournalAdapter;
import com.example.ritamartiniano.travelmemoir.Journal;
import com.example.ritamartiniano.travelmemoir.R;
import com.example.ritamartiniano.travelmemoir.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    public TextView userName;
    public String userID;
    private FirebaseAuth auth;
    private DatabaseReference ref;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter journaladapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Journal> journals;
    private DatabaseReference getjournals;

    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View v = inflater.inflate(R.layout.fragment_profile2, container, false);
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();
        userName = v.findViewById(R.id.username);
        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String user_name = dataSnapshot.child("username").getValue().toString();
                    userName.setText(user_name);
                    Log.d("Profile Fragment ", "Username " + user_name);
                }
                else {

                Log.d("Profile Fragment:" , "Username request failed");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        //reference to the recyclerview
        mRecyclerView = v.findViewById(R.id.recycler_view);

        //using a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        journals = new ArrayList<Journal>();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getjournals = FirebaseDatabase.getInstance().getReference().child("Journals").child(userId);

        getjournals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {

                    Journal j = ds.getValue(Journal.class);
                    journals.add(j);
                    journaladapter = new JournalAdapter(getActivity(),journals);
                    mRecyclerView.setAdapter(journaladapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
