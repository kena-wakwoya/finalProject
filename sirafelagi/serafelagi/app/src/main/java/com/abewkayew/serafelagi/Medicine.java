package com.abewkayew.serafelagi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.roughike.bottombar.BottomBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class Medicine extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    BottomBar bottomBar;
    SearchView searchView;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout relativeLayout;
    String[] company_name, job_title, job_desc, job_date, job_specs, job_degree_level;
    SeraRecyclerAdapter adapter;
    Dialog dialog_developers;
    String job_url = "http://192.168.55.116/sera_felagi/Job_lists/job_lists.php";
    TextView number_of_medicine_jobs_txtView;
    ImageView sera_profile_pic;
    RelativeLayout progressbarContainerLayout;
    ScrollView scrollViewRecyclerViewContainer;
    LinearLayout profile_header_layout;
//integer array for representing the image resource.
    int[] Img_res = {R.drawable.accounting_one, R.drawable.business_one, R.drawable.chemical_engineering_one,
            R.drawable.civil_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
            R.drawable.medicine_one, R.drawable.accounting_one, R.drawable.business_one, R.drawable.chemical_engineering_one,
            R.drawable.civil_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
            R.drawable.medicine_one, R.drawable.medicine_one, R.drawable.electrical_engineering_one, R.drawable.software_engineering_two, R.drawable.mechanical_engineering_one,
            R.drawable.medicine_one, R.drawable.medicine_one,};

    ArrayList<sera_datas> arrayList;
    String usernameFromSharedPreference;
    TextView medicineTextView, sera_username, sera_userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        number_of_medicine_jobs_txtView = (TextView)findViewById(R.id.number_of_medicine_jobs_txtView);
        arrayList = new ArrayList<>();
        SharedPreferences sp = this.getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
        String username =  sp.getString("username", "");
        String userEmail = sp.getString("userEmail", "");
        View header = navigationView.getHeaderView(0);
        profile_header_layout = header.findViewById(R.id.profile_header_layout);
        sera_profile_pic = header.findViewById(R.id.profile_image);
        sera_username = header.findViewById(R.id.sera_username);
        sera_userEmail = header.findViewById(R.id.sera_userEmail);
        sera_username.setText(username);
        sera_userEmail.setText(userEmail);
//      set the imageview with the one from the sharedpreference...
        getProfileImage();
//        onclick listener for the profile picture...
        profile_header_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Medicine.this, Profile.class);
                startActivity(intent);
            }
        });
//        StartExam startExam = new StartExam();
        Profile profile = new Profile();
//      handle the notification implementation here...

//                Intent intent = new Intent(Medicine.this, ExamActivity.class);
//                int totalScore = getIntent().getIntExtra("totalScore", 0);
//                PendingIntent pIntent = PendingIntent.getActivity(Medicine.this, 0, intent, 0);
//                Notification notification = new Notification.Builder(Medicine.this)
//                        .setTicker("seraFelagi")
//                        .setAutoCancel(true)
//                        .setContentTitle("SeraFelagi")
//                        .setContentText("You have only 5 minutes to start the exam you have applied")
//                        .addAction(R.drawable.ic_apply, "Take exam now", pIntent)
//                        .setSmallIcon(R.drawable.ic_notifications)
////                        .setVibrate(new long[] { 1000, 1000})
////                        .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
//                        .setContentIntent(pIntent).getNotification();
////                notification.flags = Notification.DEFAULT_VIBRATE;
//                notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
//
//                NotificationManager nManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
////        nManager.wait();
//        nManager.notify(0, notification);




//        set the name
//        LinearLayout sera_usernameLayout = (LinearLayout) LayoutInflater.from((Context) this).inflate(R.layout.employee_nav_drawer, null);
//        sera_usernameLayout.addView(sera_username);
//        sera_username = (TextView)sera_usernameLayout.findViewById(R.id.sera_username);
//        sera_username.setText(username);

//        ViewGroup parent = null;
//        View view = LayoutInflater.from(this).inflate(R.layout.nav_header_employee_drawer, parent, false);
//        sera_username = (TextView)view.findViewById(R.id.sera_username);
        medicineTextView = (TextView)findViewById(R.id.number_of_medicine_jobs_txtView);
        progressbarContainerLayout = findViewById(R.id.progressbarContainerLayout);
        scrollViewRecyclerViewContainer = findViewById(R.id.scrollViewRecyclerViewContainer);
//        sera_username = (TextView)findViewById(R.id.sera_username);
        bottomBar = (BottomBar) findViewById(R.id.bottom_bar);
//
//            Intent intent = getIntent();

        number_of_medicine_jobs_txtView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                scrollViewRecyclerViewContainer.setVisibility(View.VISIBLE);
                progressbarContainerLayout.setVisibility(View.GONE);
            }
        });

        // Medicine username....

//      adapter = new SeraRecyclerAdapter(arrayList, getApplicationContext());
        recyclerView = findViewById(R.id.recycler_view_sera_content);
        relativeLayout = findViewById(R.id.relative_layout_one);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

//      get the data from the server...

        loadRecyclerView();

        company_name = getResources().getStringArray(R.array.company_names);
        job_title = getResources().getStringArray(R.array.job_title);
        job_desc = getResources().getStringArray(R.array.job_desc);
        job_date = getResources().getStringArray(R.array.job_date);
        job_specs = getResources().getStringArray(R.array.job_specs);
        job_degree_level = getResources().getStringArray(R.array.job_degree_level);
        int i = 0;
        for (String name : company_name) {
            sera_datas sera_list = new sera_datas(Img_res[i], name, job_title[i], job_desc[i],
                    job_date[i], job_specs[i], job_degree_level[i]);
            //add each of the objects to the arrayList
            arrayList.add(sera_list);
            i++;
        }
//
//
        adapter = new SeraRecyclerAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        setTitle("Medicine");

//        recyclerView.setAdapter(adapter);
//      get medicne jobs count...
//        int medicine_count = adapter.getItemCount();
//        number_of_medicine_jobs_txtView.setText("More than " + medicine_count +" Medicine Jobs");


//
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "We are working on this feature", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        drawer.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //set the toobar as an actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
    }


    @Override
    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            finish();
            Intent intent = new Intent(Medicine.this, Job_first_entry.class);
            startActivity(intent);
            }
        }

    public void getProfileImage(){
        SharedPreferences preferences = getSharedPreferences("LoginDetails",MODE_PRIVATE);
        String img_string = preferences.getString("profileImage", "");
        if (!img_string.equals("")){
            //decode string to image
            String bases = img_string;
            byte[] imageAsByte = Base64.decode(bases.getBytes(), Base64.DEFAULT);
            sera_profile_pic.setImageBitmap(BitmapFactory.decodeByteArray(imageAsByte, 0,
                    imageAsByte.length) );
        }else{
//          Toast.makeText(Medicine.this, "Image store is not working!!!", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView)myActionMenuItem.getActionView();
        ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.colorWhite));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final ArrayList<sera_datas> filterModeList = filter(arrayList, newText);
                adapter.setFilter(filterModeList);
                return true;
            }
        });

        return true;
    }
    private ArrayList<sera_datas> filter(List<sera_datas> pl, String query)
    {
            query = query.toLowerCase();
            final ArrayList<sera_datas> filteredModeList = new ArrayList<>();
            for(sera_datas model:pl){
                final String text = model.getCompany_name();
                if(text.startsWith(query)){
                    filteredModeList.add(model);
                }
            }
        return filteredModeList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            // Handle the profile action
            Intent i = new Intent(this, Profile.class);
            startActivity(i);
        }  else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_applied_jobs) {
            Intent intent = new Intent(Medicine.this, ExamActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {
            SharedPreferences sp = this.getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
            sp.edit().clear().apply();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_chagePassword) {
            showChangePasswordDialog();
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

// LoadRecyclerView data from the server...
    private void loadRecyclerView(){
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        int progress_checker = 0;
//        progressDialog.setMessage("Wait a second");
//        progressDialog.show();
        progressbarContainerLayout.setVisibility(View.VISIBLE);
        scrollViewRecyclerViewContainer.setVisibility(View.GONE);

//        Use the volley internet library implementations then...
        StringRequest stringRequest = new StringRequest(Request.Method.GET, job_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        Toast.makeText(Medicine.this, "Response is working", Toast.LENGTH_LONG).show();
                        try {
                            JSONArray sera_lists = new JSONArray(response);
                            Toast.makeText(Medicine.this,sera_lists.getString(3), Toast.LENGTH_LONG).show();

                            for (int i = 0; i < sera_lists.length(); i++){
                              JSONObject jsonObject = sera_lists.getJSONObject(i);
                              int id = jsonObject.getInt("id");
                              String job_title = jsonObject.getString("job_title");
                              String job_desc = jsonObject.getString("job_desc");
                              String job_date = jsonObject.getString("job_date");
                              String job_specs = jsonObject.getString("job_specs");
                              String company_name = jsonObject.getString("company_name");
                              String company_image = jsonObject.getString("company_image");
                              String degree_level = jsonObject.getString("job_degree_level");
//
//                              sera_datas datas = new sera_datas(
//                                      company_image,
//                                      company_name,
//                                      job_title,
//                                      job_desc,
//                                      job_date,
//                                      job_specs,
//                                      degree_level
//                              );
//                                    arrayList.add(datas);

//                                arrayList.add(datas);
//                                Toast.makeText(Medicine.this,datas.getJob_date(), Toast.LENGTH_LONG).show();


                            }
                            adapter = new SeraRecyclerAdapter(Medicine.this, arrayList);
                            recyclerView.setAdapter(adapter);

//                            Toast.\makeText(Medicine.this, "Total : "+ arrayList.size(), Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        medicineTextView.setText(adapter.getItemCount());


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //This indicates that the reuest has either time out or there is no connection
                    Toast.makeText(getApplicationContext(), "Timeout or no connection error", Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    // Error indicating that there was an Authentication Failure while performing the request
                    Toast.makeText(getApplicationContext(), "Authentication error", Toast.LENGTH_LONG).show();
                } else if (error instanceof ServerError) {
                    //Indicates that the server responded with a error response
                    Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();
                } else if (error instanceof NetworkError) {
                    //Indicates that there was network error while performing the request
                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_LONG).show();
                } else if (error instanceof ParseError) {
                    // Indicates that the server response could not be parsed
                    Toast.makeText(getApplicationContext(), "Response parse error", Toast.LENGTH_LONG).show();

                }
            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Medicine.this);
        alertDialog.setTitle("change password");
        alertDialog.setInverseBackgroundForced(true);
        //layout inflater
        LayoutInflater inflater = LayoutInflater.from(this);
        View change_password_view = inflater.inflate(R.layout.change_password, null);
        MaterialEditText newPassword = findViewById(R.id.new_password);
        MaterialEditText confirmPassword = findViewById(R.id.confirm_password);
        MaterialEditText oldPassword = findViewById(R.id.old_password);

        //place the password_change view inside the alertDialog...
        alertDialog.setView(change_password_view);
        //put the buttons...
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final AlertDialog waitingDialog = new SpotsDialog(Medicine.this);
                waitingDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            waitingDialog.dismiss();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(Medicine.this, "Password changed", Toast.LENGTH_LONG).show();

            }

        });
        alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();

    }
}
