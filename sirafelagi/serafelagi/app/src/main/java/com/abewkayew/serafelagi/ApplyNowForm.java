package com.abewkayew.serafelagi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class ApplyNowForm extends AppCompatActivity {
    RecyclerView recyclerView;
    MaterialEditText university,file,gpa;
    Button applyNowButton;
    ApplyNow_RecyclerAdapter adapter;
    TextView appliedTime;
    ArrayList<Apply_Now_data> arrayList = new ArrayList<>();

    RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_now_form);
//        initialize the views...
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_apply);
        university = (MaterialEditText)findViewById(R.id.applier_name);
        gpa = findViewById(R.id.applier_grade);
        file  = findViewById(R.id.tempo_file);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ApplyNow_RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);

//        Call the readFromLocalstorage method...
        readApplyFormFromLocal();
        setTitle("Job application form");
        int res = checkSize();
//        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();

    }
    public static String getCurrentTime(int hours, int minutes, int seconds){
        String currentTime = hours + " : " + minutes + " : " + seconds;
        return currentTime;
    }
    public int checkSize(){
        adapter  =  new ApplyNow_RecyclerAdapter(arrayList);
        return adapter.getItemCount()*1;

    }

public void submitApplyForm(View view)
    {
        String name = university.getText().toString();
        String files = file.getText().toString();
        String gpas = gpa.getText().toString();
        saveApplyFormToLiveServer(name,files,gpas);
        university.setText("");
        file.setText("");
        gpa.setText("");
    }
    public boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void readApplyFormFromLocal()
    {
        arrayList.clear();
        Sera_Form_DbHelper sera_form_dbHelper = new Sera_Form_DbHelper(this);
        SQLiteDatabase databases = sera_form_dbHelper.getReadableDatabase();
        Cursor cursor = sera_form_dbHelper.readApplyDataFromLocal(databases);
//        get the data recursively...
        while(cursor.moveToNext())
        {
            String name = cursor.getString(cursor.getColumnIndex(ConstantValues.UNIVERSITY_NAME));
            String gpa = cursor.getString(cursor.getColumnIndex(ConstantValues.GPA));
            String file = cursor.getString(cursor.getColumnIndex(ConstantValues.TEMPO_FILE));
            int sync_status = cursor.getInt(cursor.getColumnIndex(ConstantValues.SYNC_STATUS));
            arrayList.add(new Apply_Now_data(name,gpa,file, sync_status));

            myLog(name);
        }
//        refresh the adapter...
        adapter.notifyDataSetChanged();
        cursor.close();
        sera_form_dbHelper.close();
    }

    //        log the data from the internal storage....
    public void myLog(String name){
            Log.d("myLog", name);
    }
//    A method to add data to the local sqlite database...
    public void saveApplyFormToLiveServer(final String name,final String files,final String gpas)
    {
        if(checkInternetConnection()){
//          save the data both in the local storage and in the server database...
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ConstantValues.URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String Response = jsonObject.getString("response");
                                if(Response.equals("OK")){
                                    saveApplyFormToLocal(name,files,gpas,ConstantValues.SYNC_STATUS_OK);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Response from server is not OK", Toast.LENGTH_SHORT).show();
                                    saveApplyFormToLocal(name,files,gpas, ConstantValues.SYNC_STATUS_FAILED);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
                    saveApplyFormToLocal(name, files,gpas,ConstantValues.SYNC_STATUS_FAILED);
                }
            })
            {
                @Override
                public Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<>();
                    params.put("university", name);
                    params.put("gpa",gpas);
                    params.put("tempo_file",files);
                    return super.getParams();
                }

            };
            VolleySingletonApplyForm.getInstance(ApplyNowForm.this).addToRequestQue(stringRequest);
        }else{
//           save the data only in the sqlite database...
            saveApplyFormToLocal(name,files,gpas, ConstantValues.SYNC_STATUS_FAILED);
        }
    }
    private void saveApplyFormToLocal(String name, String file,String gpas,int sync){
        Sera_Form_DbHelper sera_form_dbHelper = new Sera_Form_DbHelper(this);
        SQLiteDatabase database = sera_form_dbHelper.getWritableDatabase();
        sera_form_dbHelper.saveApplyFormToLocal(name,file,gpas,sync, database);
        readApplyFormFromLocal();
        sera_form_dbHelper.close();

    }

}
