package com.example.ritamartiniano.travelmemoir;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class create_post extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    ImageView photo;
    ImageButton buttonPhoto;
    TextView post_name, post_description,post_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        photo = findViewById(R.id.post_Photo);
        post_name = findViewById(R.id.postName);
        post_date = findViewById(R.id.postDate);
        post_description = findViewById(R.id.postDescription);
        buttonPhoto = findViewById(R.id.photo_button);
        showPopup(buttonPhoto);
    }
   public void showPopup(View v)
   {
       PopupMenu popup = new PopupMenu(this,v);
       popup.setOnMenuItemClickListener(this);
       popup.inflate(R.menu.photo_popup);
       popup.show();
   }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.usecam:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,0);
                return true;
            case R.id.useLibrary:
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bm = (Bitmap)data.getExtras().get("data");
        photo.setImageBitmap(bm);
    }
}
