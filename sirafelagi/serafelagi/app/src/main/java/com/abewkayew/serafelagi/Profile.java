package com.abewkayew.serafelagi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    CircleImageView profile_image;
    FloatingActionButton button_edit_profile;
    Toolbar toolbar;
    MaterialEditText phone, email, usernameEditTextView;
    TextView username;
    Button updateProfileButton;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phone = findViewById(R.id.userPhone);
        email = findViewById(R.id.userEmail);
        usernameEditTextView = findViewById(R.id.userAccountName);

        username = findViewById(R.id.sera_usernameProfile);
        updateProfileButton = findViewById(R.id.updateProfileButton);
//        shared preference implementation...
        SharedPreferences sp = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        String sera_userPhone = sp.getString("phoneNumber", "");
        final String sera_username = sp.getString("username", "");
        final String sera_userEmailAddress = sp.getString("userEmail", "");
//      set the profile_image with the image from the sharedpreferences...


//        set the sharedpreference data into the profile field areas...
        if(sera_userPhone.isEmpty()){
            phone.setText("+251946556085");
        }else{
            phone.setText(sera_userPhone);
        }
        email.setText(sera_userEmailAddress);
        username.setText(sera_username);
        usernameEditTextView.setText(sera_username);
//        onclick event for the update button...
        updateProfileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginDetails", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", usernameEditTextView.getText().toString());
                editor.putString("userEmail", email.getText().toString());
                editor.putString("phone", phone.getText().toString());
                editor.commit();
                Intent intent = new Intent(Profile.this, Medicine.class);
                startActivity(intent);

                Toast.makeText(Profile.this, "Profile updated", Toast.LENGTH_SHORT).show();
            }
        });

//        button_edit_profile = (FloatingActionButton)findViewById(R.id.button_edit_profile);
//        button_edit_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(Profile.this, "It is working", Toast.LENGTH_SHORT).show();
//            }
//        });
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        profile_image = findViewById(R.id.profile_image);
//      get the profileImage from the stored data in the sharedpreferences...
        getProfileImage();

//        //add zoom implementation for the image...
//        image_profile.setOnTouchListener(new ImageMatrixTouchHandler(getApplicationContext()));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            setActionBar(toolbar);
//        }
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

    }

    public void storeImageInSharedPreferences(){
        //                save the data inside the sharedpreferences...
//                change the image to the bitmap...

    }
    public void getProfileImage(){
        SharedPreferences preferences = getSharedPreferences("LoginDetails",MODE_PRIVATE);
        String img_string = preferences.getString("profileImage", "");
        if (!img_string.equals("")){
            //decode string to image
            String bases = img_string;
            byte[] imageAsByte = Base64.decode(bases.getBytes(), Base64.DEFAULT);
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsByte, 0,
                    imageAsByte.length) );
        }else{
            Toast.makeText(Profile.this, "Image store is not working!!!", Toast.LENGTH_LONG).show();
        }
    }

    private void openGallery() {
        Intent getImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(getImage, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            profile_image.setImageURI(imageUri);
//            storeImageInSharedPreferences();

//            begining of the storing of the image inside the sharedpreferences....
            profile_image.buildDrawingCache();
            Bitmap bitmap = profile_image.getDrawingCache();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] image = stream.toByteArray();

            String img_str = Base64.encodeToString(image, 0);
            //decode string to image
            String base = img_str;
            byte[] imageAsBytes = Base64.decode(base.getBytes(), Base64.DEFAULT);
            profile_image.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes,0, imageAsBytes.length) );

            SharedPreferences preferences = getSharedPreferences("LoginDetails",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("profileImage", img_str);
            editor.commit();


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
