package com.abewkayew.serafelagi;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Job_first_entry extends AppCompatActivity {
    TextView medicine_first;
    CardView card_view_medicine, card_view_sociology;
    ImageView more_vertical_medicine;
    boolean twice;
    String usernameFromSharedPreference;
    TextView username_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_first_entry);
        medicine_first = (TextView) findViewById(R.id.medicine_first);
        more_vertical_medicine = (ImageView)findViewById(R.id.more_vertical_medicine);
        card_view_medicine = findViewById(R.id.card_view_medicine);
        card_view_sociology = findViewById(R.id.card_view_sociology);
        username_check = findViewById(R.id.username_check);
//      get data from sharedpreferences from earlier storage.
        SharedPreferences sp = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        String sera_username = sp.getString("username", "");

        username_check.setText(sera_username);
        Intent intent = getIntent();
//        Medicine username....
        usernameFromSharedPreference = intent.getStringExtra("username");

//        instantiate views...

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        set onclick listener for the view...
        card_view_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Job_first_entry.this, Medicine.class);
                intent.putExtra("username", usernameFromSharedPreference);
                startActivity(intent);

            }
        });
        card_view_sociology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Job_first_entry.this, ApplyNowForm.class);
                intent.putExtra("username", usernameFromSharedPreference);
                startActivity(intent);

            }
        });
        more_vertical_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog_medicine = new Dialog(Job_first_entry.this);
                dialog_medicine.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog_medicine.setContentView(R.layout.medicine_dialog);
                dialog_medicine.show();
                TextView view_more = (TextView)dialog_medicine.findViewById(R.id.view_more_medicine_details);
                view_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Job_first_entry.this, Medicine.class);
                        startActivity(intent);
                        dialog_medicine.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        if(twice = true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        Toast.makeText(Job_first_entry.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                twice = false;
            }
        }, 3000);
        twice = true;
    }
}
