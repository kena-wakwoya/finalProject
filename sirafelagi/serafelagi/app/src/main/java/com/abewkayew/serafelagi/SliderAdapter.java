package com.abewkayew.serafelagi;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Abebe on 4/3/2018.
 */

public class SliderAdapter extends PagerAdapter{
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }
    public int[] slide_image = {
            R.drawable.online_exam,
            R.drawable.apply_now,
            R.drawable.notification
    };
    public String[] slide_headings = {
            "Take Online Exam",
            "Apply for Job",
            "Get Notified for Job"
    };
    public String[] slide_descs = {
        "The exam is the main concern of this application and you all can take online examination",
        "You can apply for job online with not more than 2 second time by using ur application CV",
        "Notification is our main concern. All can be notified for the right time of the exam comes and this is really important."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        CircleImageView slideImageView = (CircleImageView)view.findViewById(R.id.slide_image_one);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_desc);

        //attach the file with along with the views...
        slideImageView.setImageResource(slide_image[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);

    }


}

