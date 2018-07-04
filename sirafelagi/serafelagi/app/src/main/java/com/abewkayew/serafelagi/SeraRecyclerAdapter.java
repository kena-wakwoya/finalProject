package com.abewkayew.serafelagi;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.abewkayew.serafelagi.MainActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.checkSelfPermission;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 *
 */

public class
SeraRecyclerAdapter extends RecyclerView.Adapter<SeraRecyclerAdapter.RecyclerViewHolder>

{
    private  ArrayList<sera_datas> data = new ArrayList<>();
    Context context;

    public SeraRecyclerAdapter(Context context, ArrayList<sera_datas> data)
    {
        this.context = context;
        this.data = data;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sera_layout, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, context, data);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        //assign the resources to the view component
        //create an object of data
        sera_datas sera_list = data.get(position); //get each object from the datalist.
//        holder.image_resource.setImageResource(sera_list.getImage_resource());
        holder.company_name.setText(sera_list.getCompany_name());
        holder.job_title.setText(sera_list.getJob_title());
        holder.job_desc.setText(sera_list.getJob_desc());
        holder.job_date.setText(sera_list.getJob_date());
//        Glide.with(context).load();
    }
    public void setFilter(ArrayList<sera_datas> seraItem)
    {
        data = new ArrayList<>();
        data.addAll(seraItem);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount()
    {
        int medicine_count = data.size();
        return medicine_count;
    }

    public static class RecyclerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image_resource;
        TextView company_name, job_title, job_date, job_desc;
        RelativeLayout relativeLayout;
        ArrayList<sera_datas> datas = new ArrayList<>();
        Context context;
        public RecyclerViewHolder(View view, Context context, ArrayList<sera_datas> datas) {
            super(view);
            this.datas = datas;
            this.context = context;
            view.setOnClickListener(this);
            image_resource = (ImageView) view.findViewById(R.id.image_one);
            company_name = (TextView) view.findViewById(R.id.company_name);
            job_title = (TextView) view.findViewById(R.id.job_title);
            job_desc = (TextView)view.findViewById(R.id.job_desc);
            job_date = (TextView)view.findViewById(R.id.job_date);

            relativeLayout = view.findViewById(R.id.relative_layout_one);
//            relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(context, "works", Toast.LENGTH_SHORT).show();
//                }
//            });
            //            relativeLayout.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            //get the position...
            int position = getAdapterPosition();
            //get particular object from the arrayList...
            sera_datas sera_data = this.datas.get(position);
            //dialog start
            final Dialog dialog_medicine = new Dialog(context);
            dialog_medicine.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog_medicine.setContentView(R.layout.job_action);
            dialog_medicine.show();
            TextView view_more = (TextView)dialog_medicine.findViewById(R.id.more);
            view_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCall();
                    //dialog_medicine.dismiss();
                }
            });
            TextView add_to_favorite  = (TextView)dialog_medicine.findViewById(R.id.favorite);
            add_to_favorite.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){
                    Medicine comp_name = new Medicine();
                    String[] add_fav = comp_name.company_name;
                    String sample  = "kena";
                    savedFavorite(sample);
                    Toast.makeText(context, "it works",Toast.LENGTH_LONG).show();

                }
            });





            //dialog  end





        }
        private void savedFavorite(String job_applied) {

            SharedPreferences sharedPreferences = context.getSharedPreferences("favorites", context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("job_name", job_applied);

            editor.commit();
        }
        private void onCall(){
            int position = getAdapterPosition();
            sera_datas sera_data = this.datas.get(position);
            Intent intent = new Intent(this.context, Job_details.class);
            intent.putExtra("image_id", sera_data.getImage_resource());
            intent.putExtra("company_name", sera_data.getCompany_name());
            intent.putExtra("job_title", sera_data.getJob_title());
            intent.putExtra("job_desc", sera_data.getJob_desc());
            intent.putExtra("job_date", sera_data.getJob_date());
            intent.putExtra("job_specs", sera_data.getJob_specs());
            intent.putExtra("job_degree_level", sera_data.getJob_degree_level());
            //After getting this much data, now, start the activity.
            this.context.startActivity(intent);
            Log.d("Error", "It doesn't work");

        }
    }
}
