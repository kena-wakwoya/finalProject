package com.abewkayew.serafelagi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button mNextButton, mPrevButton;
    private int mCurrentPage;
    Boolean isSplashed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sear_boarding_activity);

//        splash screen implementation...

        SharedPreferences sp = this.getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
        String username = sp.getString("username", "");

        if(!username.isEmpty()){
            Intent intent = new Intent(MainActivity.this, Medicine.class);
            startActivity(intent);
        }
//                Instantiate the classes...

            //        view initialization
            mSlideViewPager = findViewById(R.id.slideViewPage);
            mDotLayout = findViewById(R.id.dotsLayout);
            mPrevButton = findViewById(R.id.prevButton);
            mNextButton = findViewById(R.id.nextButton);

            sliderAdapter = new SliderAdapter(this);
            mSlideViewPager.setAdapter(sliderAdapter);
            addDotsIndicator(0);

            mSlideViewPager.addOnPageChangeListener(viewListener);
//      set onclick listener for the preButton and nextButton;
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mCurrentPage == 2){
//                    Toast.makeText(sera_boarding_activity.this, "It works", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }else{
                        mSlideViewPager.setCurrentItem(mCurrentPage + 1);

                    }
                }
            });

            mPrevButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSlideViewPager.setCurrentItem(mCurrentPage - 1);
                }
            });

        }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        for(int i = 0; i < mDots.length; i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(50);
            mDots[i].setTextColor(getResources().getColor(R.color.background));

            mDotLayout.addView(mDots[i]);

        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.dark_gray));
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

//            addDotsIndicator(i);
            mCurrentPage = i;
            if(i == 0){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(false);
                mPrevButton.setVisibility(View.INVISIBLE);
                mDots[0].setTextColor(getResources().getColor(R.color.dark_gray));
                mDots[1].setTextColor(getResources().getColor(R.color.colorWhite));
                mDots[2].setTextColor(getResources().getColor(R.color.colorWhite));

                mNextButton.setText("Next");
                mPrevButton.setText("");

            }else if(i == mDots.length-1){
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);
                mDots[0].setTextColor(getResources().getColor(R.color.colorWhite));
                mDots[1].setTextColor(getResources().getColor(R.color.colorWhite));
                mDots[2].setTextColor(getResources().getColor(R.color.dark_gray));

                mNextButton.setText("Finish");
                mPrevButton.setText("Back");

            }else{
                mNextButton.setEnabled(true);
                mPrevButton.setEnabled(true);
                mPrevButton.setVisibility(View.VISIBLE);
                mDots[0].setTextColor(getResources().getColor(R.color.colorWhite));
                mDots[1].setTextColor(getResources().getColor(R.color.dark_gray));
                mDots[2].setTextColor(getResources().getColor(R.color.colorWhite));

                mNextButton.setText("Next");
                mPrevButton.setText("Back");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
//    A simple Fragment holder class in the main activity...
}
