package com.abewkayew.serafelagi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class CheckSharedAnimation extends AppCompatActivity {
    RelativeLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_shared_animation);
        mLayout = findViewById(R.id.relative_layout_shared);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckSharedAnimation.this, CheckAnimationTest.class);
                startActivity(intent);
            }
        });
    }
}
