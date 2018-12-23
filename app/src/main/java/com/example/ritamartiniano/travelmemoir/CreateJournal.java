package com.example.ritamartiniano.travelmemoir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateJournal extends AppCompatActivity {
    TextView journal_Name, location, dateOfTravel, Description;
    public Journal newJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_journal);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.journal_creation, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.destroy:
                Intent intent = new Intent(CreateJournal.this, MainActivity.class);
                CreateJournal.this.startActivity(intent);
                finish();
                break;
            case R.id.create:
                createJournal();
                Intent mainActivity = new Intent(CreateJournal.this,MainActivity.class);
                CreateJournal.this.startActivity(mainActivity);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void createJournal()
    {
        journal_Name = findViewById(R.id.journalName);
        location = findViewById(R.id.location);
        dateOfTravel = findViewById(R.id.date);
        Description = findViewById(R.id.description);
        newJournal = new Journal(journal_Name.getText().toString(),
                                 dateOfTravel.getText().toString(),
                                 location.getText().toString(),
                                 Description.getText().toString());
        saveJournal();

    }
    public void saveJournal()
    {
        Map<String,String> journals = new HashMap<>();
        journals.put("name",journal_Name.getText().toString());
        journals.put("dateOfTravel", dateOfTravel.getText().toString());
        journals.put("location",location.getText().toString());
        journals.put("description",Description.getText().toString());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Journals");
        String journalID = ref.push().getKey();
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = current_user.getUid();
        ref.child(userID).child(journalID).setValue(journals);

    }
}
