package net.sepiroth887.brokeit.notificationmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationManagerActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, NotificationManager.class);
        startService(intent);
    }

}
