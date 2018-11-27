package com.example.ritamartiniano.travelmemoir;

import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ritamartiniano.travelmemoir.Fragments.Profile;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btmNavigation;
    public User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmNavigation = findViewById(R.id.bottomNavigation);
        btmNavigation.setOnNavigationItemSelectedListener(selectedItem);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener selectedItem = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override

        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fg = null;
            switch (item.getItemId())
            {
                case R.id.ProfileIcon:
                    fg = new Profile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fg).commit();
            return true;}
        };


}
