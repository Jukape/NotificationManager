package net.sepiroth887.brokeit.notificationmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class NotificationManager extends NotificationListenerService{

    private String mIconString;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mIconString = loadIcon();
        Intent gearIntent = new Intent("com.samsung.accessory.intent.action.ALERT_NOTIFICATION_ITEM");
        gearIntent.putExtra("NOTIFICATION_PACKAGE_NAME", "net.sepiroth887.brokeit.notifications.AllTheNotificationsService");
        gearIntent.putExtra("NOTIFICATION_ID", 1);
        gearIntent.putExtra("NOTIFICATION_TIME", System.currentTimeMillis());
        gearIntent.putExtra("NOTIFICATION_MAIN_TEXT", "NotificationServiceStarted");
        gearIntent.putExtra("NOTIFICATION_APP_ICON", mIconString);
        gearIntent.putExtra("NOTIFICATION_LAUNCH_INTENT", "net.sepiroth887.brokeit.notifications.AllTheNotificationsService");
        gearIntent.putExtra("NOTIFICATION_CUSTOM_FIELD1", "provided by ATN");
        this.sendBroadcast(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        if(mIconString == null) {
            mIconString = loadIcon();
        }
        Log.e("NotificationManager", "Notification received "+statusBarNotification.toString());
        Log.e("NotificationManager", statusBarNotification.getNotification().toString());
        final Intent intent = new Intent("com.samsung.accessory.intent.action.ALERT_NOTIFICATION_ITEM");
        intent.putExtra("NOTIFICATION_PACKAGE_NAME", "net.sepiroth887.brokeit.notifications.AllTheNotificationsService");
        intent.putExtra("NOTIFICATION_ID", 0);
        intent.putExtra("NOTIFICATION_TIME", statusBarNotification.getPostTime());
        intent.putExtra("NOTIFICATION_MAIN_TEXT", "Received a notification");
        intent.putExtra("NOTIFICATION_APP_ICON", mIconString);
        intent.putExtra("NOTIFICATION_LAUNCH_INTENT", "com.android.mail");
        intent.putExtra("NOTIFICATION_CUSTOM_FIELD1", "provided by ATN");
        this.sendBroadcast(intent);
    }

    private String loadIcon() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 50, bytearrayoutputstream);
        return Base64.encodeToString(bytearrayoutputstream.toByteArray(), 2);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        Log.e("NotificationManager", "Notification removed "+statusBarNotification.toString());
        final Intent intent = new Intent("com.samsung.accessory.intent.action.CHECK_NOTIFICATION_ITEM");
        intent.putExtra("NOTIFICATION_PACKAGE_NAME", "net.sepiroth887.brokeit.notifications.AllTheNotificationsService");
        intent.putExtra("NOTIFICATION_ID", statusBarNotification.getId());
        intent.putExtra("NOTIFICATION_TIME", statusBarNotification.getPostTime());
        intent.putExtra("NOTIFICATION_MAIN_TEXT", statusBarNotification.getNotification().tickerText);
        intent.putExtra("NOTIFICATION_APP_ICON", statusBarNotification.getNotification().icon);
        intent.putExtra("NOTIFICATION_LAUNCH_INTENT", statusBarNotification.getNotification().fullScreenIntent.getCreatorPackage());
        intent.putExtra("NOTIFICATION_CUSTOM_FIELD1", "provided by ATN");
        this.sendBroadcast(intent);
    }
}
