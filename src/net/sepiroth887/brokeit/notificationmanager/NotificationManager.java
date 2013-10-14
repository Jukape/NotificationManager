package net.sepiroth887.brokeit.notificationmanager;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationManager extends NotificationListenerService{

    private String mIconString;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent gearIntent = new Intent("com.samsung.accessory.intent.action.ALERT_NOTIFICATION_ITEM");
        gearIntent.putExtra("NOTIFICATION_PACKAGE_NAME", "net.sepiroth887.brokeit.notifications");
        gearIntent.putExtra("NOTIFICATION_ID", 1);
        gearIntent.putExtra("NOTIFICATION_TIME", System.currentTimeMillis());
        gearIntent.putExtra("NOTIFICATION_MAIN_TEXT", "NotificationServiceStarted");
        gearIntent.putExtra("NOTIFICATION_APP_ICON", R.drawable.ic_launcher);
        gearIntent.putExtra("NOTIFICATION_LAUNCH_INTENT", "net.sepiroth887.brokeit.notifications");
        gearIntent.putExtra("NOTIFICATION_CUSTOM_FIELD1", "provided by ATN");
        this.sendBroadcast(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        Log.e("NotificationManager", "Notification received "+statusBarNotification.toString());
        final Intent intent = new Intent("com.samsung.accessory.intent.action.ALERT_NOTIFICATION_ITEM");
        intent.putExtra("NOTIFICATION_PACKAGE_NAME", "net.sepiroth887.brokeit.notifications");
        intent.putExtra("NOTIFICATION_ID", 0);
        intent.putExtra("NOTIFICATION_TIME", statusBarNotification.getPostTime());
        intent.putExtra("NOTIFICATION_MAIN_TEXT", "new notification from: "+statusBarNotification.getTag());
        intent.putExtra("NOTIFICATION_APP_ICON", R.drawable.ic_launcher);
        intent.putExtra("NOTIFICATION_LAUNCH_INTENT", statusBarNotification.getPackageName());
        intent.putExtra("NOTIFICATION_TO_ACC_INTENT", "net.sepiroth887.brokeit.notifications");
        intent.putExtra("NOTIFICATION_CUSTOM_FIELD1", "provided by ATN");
        Log.d("NotificationManager", "Sending broadcast: "+intent.toString());
        this.sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        // noop
    }
}
