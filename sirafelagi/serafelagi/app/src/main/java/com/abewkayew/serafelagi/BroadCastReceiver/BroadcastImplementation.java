package com.abewkayew.serafelagi.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Abebe Kayimo on 5/20/2018.
 */

public class BroadcastImplementation extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "The phone started waked up from boot", Toast.LENGTH_LONG).show();
    }
}
