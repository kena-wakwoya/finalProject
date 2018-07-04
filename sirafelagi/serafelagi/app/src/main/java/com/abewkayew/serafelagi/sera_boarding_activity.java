package com.abewkayew.serafelagi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

public class sera_boarding_activity extends AppCompatActivity {

    BottomBar bottomBar;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout relativeLayout;
    String[] company_name, job_title, job_desc, job_date, job_specs, job_degree_level;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;

    Dialog dialog_developers;

    //integer array for representing the image resource.
//
//    String [] Img_res = {R.drawable.accounting_one, R.drawable.business_one, R.drawable.chemical_engineering_one,
//            R.drawable.civil_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
//            R.drawable.medicine_one, R.drawable.accounting_one, R.drawable.business_one, R.drawable.chemical_engineering_one,
//            R.drawable.civil_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
//            R.drawable.medicine_one, R.drawable.medicine_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
//            R.drawable.medicine_one, R.drawable.medicine_one,};

    ArrayList<sera_datas> arrayList = new ArrayList<sera_datas>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutManager = new LinearLayoutManager(this);

//        Intent intent = new Intent(MainActivity.this, sera_boarding_activity.class);
//        startActivity(intent);

        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_one);
        company_name = getResources().getStringArray(R.array.company_names);
        job_title = getResources().getStringArray(R.array.job_title);
        job_desc = getResources().getStringArray(R.array.job_desc);
        job_date = getResources().getStringArray(R.array.job_date);
        job_specs = getResources().getStringArray(R.array.job_specs);
        job_degree_level = getResources().getStringArray(R.array.job_degree_level);

//        adapter = new SeraRecyclerAdapter(arrayList, getApplicationContext());

//        int i = 0;
//        for (String name : company_name) {
//            sera_datas sera_list = new sera_datas(Img_res[i], name, job_title[i], job_desc[i],
//                    job_date[i], job_specs[i],job_degree_level[i]);
//            //add each of the objects to the arrayList
//            arrayList.add(sera_list);
////            adapter.notifyDataSetChanged();
//            i++;
//        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
//                scrollOutItems = layoutManager.findViewByPosition(dy);


            }
        });

    }


    //check for INTERNET connections
    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = connectivityManager.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public AlertDialog.Builder builderDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("No internet connection");
        builder.setMessage("You need to have mobile or wifi connection to apply for job");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            View view;

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getApplicationContext(), "Good Luck", Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(MainActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
        return builder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBackPressed()
    {
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        dialog_developers = new Dialog(sera_boarding_activity.this);

        int res_id = item.getItemId();
        if (res_id == R.id.action_login) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
        if (res_id == R.id.action_settings) {
            dialog_developers.setTitle("Application Developers");
            dialog_developers.setContentView(R.layout.app_workers_layout);
            dialog_developers.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
            dialog_developers.show();

        }
        if (res_id == R.id.action_search) {
//            Toast.makeText(this, "Search works", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, sera_boarding_activity.class);
            startActivity(intent);
        }

        return true;
    }
}