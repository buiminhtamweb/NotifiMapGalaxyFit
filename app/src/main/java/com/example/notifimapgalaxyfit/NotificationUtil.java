package com.example.notifimapgalaxyfit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notifimapgalaxyfit.Data.SettingData;

public class NotificationUtil {

    public static final int TURN_LEFT = 1;
    public static final int TURN_RIGH = 2;
    public static final int TURN_CONTINUE = 3;

    public static final int STATUS_WARNING = 4;
    public static final int STATUS_SILENT = 5;

    private static final int NOTIFICATION_ID = 12345;
    private static final int NOTIFICATION_ID_SERVICE = 123;
    private static final String CHANNEL_ID = "APP_NOTIFICAITON";
    private static final String CHANNEL_ID_SERVICE = "APP_NOTIFICAITON_SERVICE";
    private static final String nameChanel = "nameChanel";
    private static final String descriptionChanel = "descriptionChanel";
//    private static final String CHANNEL_ID = "APP_NOTIFICAITON";


    public static void showNotification(Context context, String title, String content) {

        createNotificationChannel(context, CHANNEL_ID);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        notifiBuilder.setSmallIcon(R.drawable.ic_stat_name);
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_stat_name);
        notifiBuilder.setLargeIcon(bm);

        if (SettingData.getVibrate(context)) {
            notifiBuilder.setVibrate(new long[500]);
        } else {
            notifiBuilder.setVibrate(null);
        }

        Notification notification = notifiBuilder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID_SERVICE, notification);

    }

    public static void showNotificationService(Service service, String title, String content) {

        createNotificationChannel(service, CHANNEL_ID_SERVICE);
        Intent notificationIntent = new Intent(service, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(service,
                0, notificationIntent, 0);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(service, CHANNEL_ID_SERVICE)
                .setContentTitle(title)
                .setContentText(content)
                .setContentIntent(pendingIntent)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notifiBuilder.setSmallIcon(R.drawable.ic_arrow_forward_black_24dp);
        Bitmap bm = BitmapFactory.decodeResource(service.getResources(), R.drawable.ic_arrow_forward_black_24dp);
        notifiBuilder.setLargeIcon(bm);

        if (SettingData.getVibrate(service)) {
            notifiBuilder.setVibrate(new long[500]);
        } else {
            notifiBuilder.setVibrate(null);
        }

        Notification notification = notifiBuilder.build();

        service.startForeground(NOTIFICATION_ID, notification);

    }

    public static void cancelAllNotification(Service service) {
        NotificationManager notifManager = (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancelAll();
    }

    private static void createNotificationChannel(Context context, String channel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = 0;
            if (SettingData.getVibrate(context)) {
                importance = NotificationManager.IMPORTANCE_DEFAULT;
            } else {
                importance = NotificationManager.IMPORTANCE_LOW;

            }

            NotificationChannel serviceChannel = new NotificationChannel(
                    channel,
                    "Foreground Service Channel", importance
            );

            if (SettingData.getVibrate(context)) {
                serviceChannel.enableVibration(true);
                serviceChannel.setVibrationPattern(new long[500]);
            } else {
                serviceChannel.enableVibration(false);
                serviceChannel.setVibrationPattern(null);
            }


            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


}
