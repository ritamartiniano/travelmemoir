package com.example.ritamartiniano.travelmemoir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    public TextView SignUpLink;
    public EditText EMAIL,PASSWORD;
    public Button btnSignIn;
    private FirebaseAuth mAuth;
    public User newUser;
    public String UserID;
    public static final String TAG = "SignIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        EMAIL = (EditText)findViewById(R.id.emailSignIn);
        PASSWORD = (EditText)findViewById(R.id.passwordSignIn);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        SignUpLink = (TextView)findViewById(R.id.editSignUpLink);
        mAuth = FirebaseAuth.getInstance();
        SignUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, Register.class);
                SignIn.this.startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EMAIL.getText().toString();
                String password = PASSWORD.getText().toString();
                signInUser(email,password);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    public void signInUser(String Email, String Password)
    {
     if(validateSignIn(Email,Password))
     {
         mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful())
                 {
                     Toast.makeText(SignIn.this,"Authenticaton Success", Toast.LENGTH_SHORT).show();
                     Intent mainPage = new Intent(SignIn.this, MainActivity.class);
                     SignIn.this.startActivity(mainPage);
                 }
                 else
                 {
                     Log.w(TAG, "SignInWithEmail: failure", task.getException());
                     Toast.makeText(SignIn.this,"Authentication Failed",Toast.LENGTH_SHORT).show();

                 }
             }
         });
     }
    }

    public boolean validateSignIn(String Email, String Password)
    {   boolean validated = true;
        if(TextUtils.isEmpty(Email))
        {
            EMAIL.setError(Email);
            validated = false;
        }
        else if(TextUtils.isEmpty(Password))
        {
            PASSWORD.setError(Password);
            validated = false;
        }
        return validated;
    }
}
