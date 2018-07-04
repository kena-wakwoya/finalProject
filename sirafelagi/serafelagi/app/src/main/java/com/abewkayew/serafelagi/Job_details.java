package com.abewkayew.serafelagi;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.zip.Inflater;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

import static com.abewkayew.serafelagi.R.layout.activity_job_details;

public class Job_details extends AppCompatActivity {
    ImageView imageView;
    TextView company_name, job_title, job_desc, job_date, job_spec, job_degree_levels;
    FloatingTextButton applyJob, get_location;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        //cast all the views to their respective ID's....
        imageView = (ImageView)findViewById(R.id.company_logo_details);
        company_name = (TextView)findViewById(R.id.company_name_details);
        job_title = (TextView)findViewById(R.id.job_title_details);
        job_desc = (TextView)findViewById(R.id.job_desc_details);
        job_date = (TextView)findViewById(R.id.job_date_details);
        job_spec = (TextView)findViewById(R.id.job_specs_details);
        job_degree_levels = (TextView)findViewById(R.id.job_degree_level_details);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbar_collapsing);

//        set properties for the toolbar...
        setSupportActionBar(toolbar);
        getSupportActionBar();
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        View view = View.inflate(this, R.layout.job_details_layout, null);

        applyJob = findViewById(R.id.applyJob);
        get_location = findViewById(R.id.getLocation);
//        set onclick Listeners for the views...
        //setup the recycler view...

//        imageView.setImageResource(getIntent().getIntExtra("image_id", 00));
        company_name.setText(""+getIntent().getStringExtra("company_name"));
        job_title.setText("job title  " + getIntent().getStringExtra("job_title"));
        job_desc.setText("Desctription  " + getIntent().getStringExtra("job_desc"));
        job_date.setText("date  " + getIntent().getStringExtra("job_date"));
        job_spec.setText("Specification:" + getIntent().getStringExtra("job_specs"));

//        applyJob.setText("Apply");
        job_degree_levels.setText("More Specification: " + getIntent().getStringExtra("job_degree_level") );
        setTitle("");
//        applyJob.setTextSize(25);
        collapsingToolbarLayout.setTitleEnabled(true);
//        toolbar.setTitle("Sera details");
//      toolbar implementation
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        applybutton onclick implementation...
        applyJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Job_details.this, ApplyNowForm.class);
                startActivity(intent);

            }
        });

//        implement for toolbar



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.job_details, menu);
        return true;
    }
        //    back actionbar implementation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return true;
    }
}
