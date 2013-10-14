package net.sepiroth887.brokeit.notificationmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OnBootServiceStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("NotificationManager", "starting service");
        Intent serviceIntent = new Intent(context, NotificationManager.class);
        context.stopService(serviceIntent);
        context.startService(serviceIntent);
    }
}
