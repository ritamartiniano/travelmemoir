package com.example.ritamartiniano.travelmemoir;



import android.support.v7.app.AppCompatActivity;

public class User extends AppCompatActivity {

    public String username;
    public String name;
    public String UID;

    public User()
    {

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
