package com.abewkayew.serafelagi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Abebe Kayimo on 4/3/2018.
 */

public class Sera_detailsRecyclerAdapter extends RecyclerView.Adapter<Sera_detailsRecyclerAdapter.RecyclerViewHolder>
{
    private ArrayList<sera_datas> sera_datas = new ArrayList<>();
    Context context;
    public Sera_detailsRecyclerAdapter(ArrayList<sera_datas> sera_datas, Context context)
    {
        this.sera_datas = sera_datas;
        this.context = context;
    }


    @Override
    public Sera_detailsRecyclerAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_details_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, sera_datas);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(Sera_detailsRecyclerAdapter.RecyclerViewHolder holder, final int position) {

        //assign the resources to the view component
        //create an object of data
        sera_datas sera_list = sera_datas.get(position);//get each object from the datalist.
        holder.company_name.setText(sera_list.getCompany_name());
        holder.job_title.setText(sera_list.getJob_title());
        holder.job_desc.setText(sera_list.getJob_desc());
        holder.job_date.setText(sera_list.getJob_date());

        Glide.with(context)
              .load(sera_list.getImage_resource())
              .into(holder.image_resource);

        //set the onclick listener for recyclerview
//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent intent = new Intent(view.getContext(), Login.class);
////                startActivity(view.getContext(), intent, Bundle.EMPTY);
////                startActivity(view.getContext(), ActivityOne.class);
//                Toast.makeText(view.getContext(), "clicked " + position, Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public int getItemCount()
    {
        return sera_datas.size();
    }

    public static class RecyclerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image_resource;
        TextView company_name, job_title, job_date, job_desc;
        RelativeLayout relativeLayout;
        ArrayList<sera_datas> sera_datas = new ArrayList<sera_datas>();
        Context context;
        public RecyclerViewHolder(View view, Context context, ArrayList<sera_datas> sera_datas) {
            super(view);
            this.sera_datas = sera_datas;
            this.context = context;
            view.setOnClickListener(this);
            image_resource = (ImageView) view.findViewById(R.id.image_one);
            company_name = (TextView) view.findViewById(R.id.company_name);
            job_title = (TextView) view.findViewById(R.id.job_title);
            job_desc = (TextView)view.findViewById(R.id.job_desc);
            job_date = (TextView)view.findViewById(R.id.job_date);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_one);

        }

        @Override
        public void onClick(View view) {
            //get the position...
            int position = getAdapterPosition();
            //get particular object from the arrayList...
            sera_datas sera_datas = this.sera_datas.get(position);
            Intent intent = new Intent(context, Job_details.class);
            intent.putExtra("image_id", sera_datas.getImage_resource());
            intent.putExtra("company_name", sera_datas.getCompany_name());
            intent.putExtra("job_title", sera_datas.getJob_title());
            intent.putExtra("job_desc", sera_datas.getJob_desc());
            intent.putExtra("job_date", sera_datas.getJob_date());
            //After getting this much data, now, start the activity.
            view.getContext().startActivity(intent);

        }
    }
}
