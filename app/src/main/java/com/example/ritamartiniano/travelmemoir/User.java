package com.example.ritamartiniano.travelmemoir;



import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User  {

    public String username;
    public String name;
    public String userID;
    private FirebaseAuth auth;
    private DatabaseReference ref;

    public User()
    {
        auth = FirebaseAuth.getInstance();
        userID = auth.getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String user_name = dataSnapshot.child("username").getValue().toString();
                    String name_ = dataSnapshot.child("name").getValue().toString();
                    setUsername(user_name);
                    setName(name_);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setName(String name)
    {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
