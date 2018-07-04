package com.abewkayew.serafelagi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abewkayew.serafelagi.Apply_Now_data;
import com.abewkayew.serafelagi.ConstantValues;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 4/30/2018.
 */

public class ApplyNow_RecyclerAdapter extends RecyclerView.Adapter<ApplyNow_RecyclerAdapter.MyViewHolder> {
    private ArrayList<Apply_Now_data> arrayList  =new ArrayList<>();

    public ApplyNow_RecyclerAdapter(ArrayList<Apply_Now_data> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applynow_layout, parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.university.setText(arrayList.get(position).getName_of_university());
        holder.gpa.setText(arrayList.get(position).getGpa());
        holder.file.setText(arrayList.get(position).getTempoFile());
        int sync_status = arrayList.get(position).getSync_status();
        if(sync_status == ConstantValues.SYNC_STATUS_OK){
            holder.sync_status.setImageResource(R.drawable.ic_ok);
        }else{
            holder.sync_status.setImageResource(R.drawable.ic_sync);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView university, appliedTime,gpa,file;
        ImageView sync_status;
        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);

            Date dt = new Date();
            int hours = dt.getHours();
            int minutes = dt.getMinutes();
            int seconds = dt.getSeconds();
            String currentTime = ApplyNowForm.getCurrentTime(hours, minutes, seconds);

// textView is the TextView view that should display it
            appliedTime = itemView.findViewById(R.id.timeApplied);
            appliedTime.setText(currentTime);
            university = (TextView)itemView.findViewById(R.id.name_applier);
            gpa = itemView.findViewById(R.id.gpa_result);
            file = itemView.findViewById(R.id.file_result);

            sync_status = (ImageView)itemView.findViewById(R.id.sync_status_image);

        }
    }
}
