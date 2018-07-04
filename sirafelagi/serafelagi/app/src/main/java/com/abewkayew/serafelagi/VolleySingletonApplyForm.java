package com.abewkayew.serafelagi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Abebe Kayimo on 5/2/2018.
 */

public class VolleySingletonApplyForm {
    private static VolleySingletonApplyForm myInstance;
    private static RequestQueue requestQueue;
    private static Context mCtx;

    private VolleySingletonApplyForm(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();

    }
    private  RequestQueue getRequestQueue(){
        if(requestQueue == null){
           requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized VolleySingletonApplyForm getInstance(Context context){
        if(myInstance == null){
            myInstance = new VolleySingletonApplyForm(context);

        }
        return myInstance;
    }
    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }
}
