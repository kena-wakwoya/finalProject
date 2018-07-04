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

import java.util.ArrayList;

/**
 * Created by Abebe Kayimo on 5/17/2018.
 */

public class Sera_Horizontal_View_Adapter extends RecyclerView.Adapter<Sera_Horizontal_View_Adapter.RecyclerViewHolder> {
    private ArrayList<sera_datas> sera_data = new ArrayList<>();
    Context context;
    public Sera_Horizontal_View_Adapter(Context context, ArrayList<sera_datas> sera_data)
    {
        this.context = context;
        this.sera_data = sera_data;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sera_list_horizontal_layout, parent, false);

        return new RecyclerViewHolder(view);

    }
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        //assign the resources to the view component
        //create an object of data
        sera_datas sera_list = sera_data.get(position); //get each object from the datalist.
        holder.image_resource.setImageResource(sera_list.getImage_resource());
        holder.company_name.setText(sera_list.getCompany_name());
//        Glide.with(context).load();
    }
    public void setFilter(ArrayList<sera_datas> seraItem)
    {
        sera_data = new ArrayList<>();
        sera_data.addAll(seraItem);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount()
    {
        int medicine_count = sera_data.size() - 3;
        return medicine_count;
    }

    public static class RecyclerViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image_resource;
        TextView company_name, job_title, job_date, job_desc;
        RelativeLayout relativeLayout;
        ArrayList<sera_datas> sera_datas = new ArrayList<sera_datas>();
        Context context;
        public RecyclerViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            company_name = (TextView) view.findViewById(R.id.comp_name);
            image_resource = view.findViewById(R.id.image);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_one);
            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //get the position...
            int position = getAdapterPosition();
            //get particular object from the arrayList...
            sera_datas sera_datas = this.sera_datas.get(position);
            Intent intent = new Intent(this.context, Job_details.class);
            intent.putExtra("image_id", sera_datas.getImage_resource());
            intent.putExtra("company_name", sera_datas.getCompany_name());
            intent.putExtra("job_title", sera_datas.getJob_title());
            intent.putExtra("job_desc", sera_datas.getJob_desc());
            intent.putExtra("job_date", sera_datas.getJob_date());
            intent.putExtra("job_specs", sera_datas.getJob_specs());
            intent.putExtra("job_degree_level", sera_datas.getJob_degree_level());
            //After getting this much data, now, start the activity.
//            this.context.startActivity(intent);
            view.getContext().startActivity(intent);

        }
    }
}
