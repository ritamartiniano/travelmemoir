package com.example.ritamartiniano.travelmemoir;


import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Note
//For writing less lines of code when opening a new activity on setonclicklitener for buttons
//in the xml file in the button this can be set ex:android:onClick:"name of method"
public class MainActivity extends AppCompatActivity {
   // public String userID;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView btmNavigation = findViewById(R.id.bottomNavigation);
        btmNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;
                switch(item.getItemId())
                {
                    case R.id.feed:
                        selectedFragment = FeedFragment.newInstance();
                        break;
                    case R.id.profile:
                        selectedFragment =  ProfileFragment.newInstance();
                        break;
                    case R.id.search:
                        selectedFragment = SearchFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment,selectedFragment);
                transaction.commit();
                return true;

            }

        });
        FragmentTransaction replace = getSupportFragmentManager().beginTransaction();
        replace.replace(R.id.fragment, ProfileFragment.newInstance());
        replace.commit();
        //btmNavigation.setSelectedItemId(R.id.profile);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newJournal:
                break;
            case R.id.newPost:
                break;
            case R.id.take_photo:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
