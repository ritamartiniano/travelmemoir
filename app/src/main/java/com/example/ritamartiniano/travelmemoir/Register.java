package com.example.ritamartiniano.travelmemoir;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class Register extends AppCompatActivity {

    public Button btnSignUp;
    public EditText NAME,USERNAME,PASSWORD, EMAIL,PASSWORD2;
    public TextView signInLink;
    private FirebaseAuth mAuth;
    private static final String TAG = "Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EMAIL = (EditText) findViewById(R.id.editEmail);
        PASSWORD = (EditText) findViewById(R.id.editPassword);
        PASSWORD2 = (EditText) findViewById(R.id.editPassword2);
        NAME = (EditText)findViewById(R.id.editName);
        USERNAME = (EditText)findViewById(R.id.editusername);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        signInLink = (TextView) findViewById(R.id.signInLink);
        mAuth = FirebaseAuth.getInstance();
        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, SignIn.class);
                Register.this.startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = EMAIL.getText().toString();
                String password = PASSWORD.getText().toString();
                String password2 = PASSWORD2.getText().toString();

                createAccount(email,password,password2);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void createAccount(String Email, String Password,String Password2) {
             if(validateForm(Email,Password,Password2)) {
                 mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         if (task.isSuccessful()) {
                             Log.d(TAG, "createUserWithEmail : Success");
                             Toast.makeText(Register.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                             String user_id = mAuth.getCurrentUser().getUid();
                             DatabaseReference currentUser = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                             HashMap user_info= new HashMap();
                             String name = NAME.getText().toString();
                             String username = USERNAME.getText().toString();
                             user_info.put("name", name);
                             user_info.put("username",username);
                             currentUser.setValue(user_info);
                         }
                         else
                             {
                             Log.w(TAG, "createUserWithEmail:failure", task.getException());
                             Toast.makeText(Register.this, "Authentication failed.",
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
             }
    }

    public boolean validateForm(String Email, String Password,String password2) {
        boolean validated = true;
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        if (TextUtils.isEmpty(Email)) {
            EMAIL.setError("Required");
            validated = false;
        } else {
            EMAIL.setError(null);
        }

        if (TextUtils.isEmpty(Password)) {
            EMAIL.setError("Required");
            validated = false;
        } else {
            PASSWORD.setError(null);
        }
        if(Password.length()<6)
        {
            Toast.makeText(Register.this, "Password should contain at least 6 characters",Toast.LENGTH_LONG).show();
            validated = false;
        }
        if(Password.equals(password2))
        {
            if(!(Password.contains(upperCaseChars) && Password.contains(lowerCaseChars) && Password.contains(numbers)))
            {
                validated = true;
            }
            else
            {
                validated = false;
                Toast.makeText(Register.this,"Password should contain Upper Case characters and numbers", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Register.this, "Passwords don't match",Toast.LENGTH_SHORT).show();
            validated = false;
        }

        return validated;
    }
}





