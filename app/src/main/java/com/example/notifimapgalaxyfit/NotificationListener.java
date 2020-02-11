package com.example.notifimapgalaxyfit;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.RemoteViews;

public class NotificationListener extends NotificationListenerService {

    /*
        These are the package names of the apps. for which we want to
        listen the notifications
     */
    public static final String TAG = "NotificationListener";
    private static final class ApplicationPackageNames {
        public static final String FACEBOOK_PACK_NAME = "com.facebook.katana";
        public static final String FACEBOOK_MESSENGER_PACK_NAME = "com.facebook.orca";
        public static final String WHATSAPP_PACK_NAME = "com.whatsapp";
        public static final String INSTAGRAM_PACK_NAME = "com.instagram.android";
        public static final String GMAPS = "com.google.android.apps.maps";
    }

    /*
        These are the return codes we use in the method which intercepts
        the notifications, to decide whether we should do something or not
     */
    public static final class InterceptedNotificationCode {
        public static final int FACEBOOK_CODE = 1;
        public static final int WHATSAPP_CODE = 2;
        public static final int MAPS = 3;
        public static final int OTHER_NOTIFICATIONS_CODE = 4; // We ignore all notification with code == 4
    }

    private String title = "";

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        int notificationCode = matchNotificationCode(sbn);


        if(notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE){
            Intent intent = new  Intent("com.github.chagall.notificationlistenerexample");
            intent.putExtra("NotificationUtil Code", notificationCode);
            sendBroadcast(intent);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){
        int notificationCode = matchNotificationCode(sbn);

        if(notificationCode != InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE) {

            StatusBarNotification[] activeNotifications = this.getActiveNotifications();

            if(activeNotifications != null && activeNotifications.length > 0) {
                for (int i = 0; i < activeNotifications.length; i++) {
                    if (notificationCode == matchNotificationCode(activeNotifications[i])) {
                        Intent intent = new  Intent("com.github.chagall.notificationlistenerexample");
                        intent.putExtra("NotificationUtil Code", notificationCode);

                        sendBroadcast(intent);
                        break;
                    }
                }
            }
        }
    }

    private int matchNotificationCode(StatusBarNotification sbn) {
        String packageName = sbn.getPackageName();

        if(packageName.equals(ApplicationPackageNames.FACEBOOK_PACK_NAME)
                || packageName.equals(ApplicationPackageNames.FACEBOOK_MESSENGER_PACK_NAME)){
            return(InterceptedNotificationCode.FACEBOOK_CODE);
        }
        else if(packageName.equals(ApplicationPackageNames.GMAPS)){

            getContent(sbn);

            return(InterceptedNotificationCode.MAPS);
        }

        else{
            return(InterceptedNotificationCode.OTHER_NOTIFICATIONS_CODE);
        }
    }

    private void getContent(StatusBarNotification sbn){

        Log. e ( TAG , "********** onNotificationPosted" ) ;
        Log. e ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification(). tickerText + " \t " + sbn.getPackageName()) ;

        Bitmap bmp;
        Bundle extras;
        byte[] byteArrayS;

        extras = sbn.getNotification().extras;

        NotifiObj notifiObj =  new NotifiObj();
        try {
            notifiObj = notifiObj.setupDataFromBundle(extras);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!title.equals(notifiObj.getTitle())){
            NotificationUtil.showNotification(this, notifiObj.getTitle(),notifiObj.getText());
            title = notifiObj.getTitle();
        }
    }





}
