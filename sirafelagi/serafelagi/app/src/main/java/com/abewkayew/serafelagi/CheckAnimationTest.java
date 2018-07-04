package com.abewkayew.serafelagi;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckAnimationTest extends AppCompatActivity {
    RelativeLayout mLayout;
    private ImageView job_image;
    private TextView job_title;
    private TextView job_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_animation_test);
        mLayout = findViewById(R.id.job_animation_layout);
        job_image = findViewById(R.id.job_image_animation);
        job_title = findViewById(R.id.job_title_animation);
        job_desc = findViewById(R.id.job_desc_animation);

//        onclick listener implementations...
        mLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckAnimationTest.this, CheckSharedAnimation.class);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(job_image, "imageTransition");
                pairs[1] = new Pair<View, String>(job_title, "titleTransition");
                pairs[2] = new Pair<View, String>(job_desc, "descriptionTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(CheckAnimationTest.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }
}
