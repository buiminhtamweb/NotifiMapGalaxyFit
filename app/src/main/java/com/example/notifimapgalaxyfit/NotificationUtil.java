package com.example.notifimapgalaxyfit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.app.NotificationCompat.WearableExtender;

import java.util.Date;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {

    private static final int NOTIFICATION_ID = 12345;
    private static final String CHANNEL_ID = "APP_NOTIFICAITON";
    private static final String nameChanel = "nameChanel";
    private static final String descriptionChanel = "descriptionChanel";
//    private static final String CHANNEL_ID = "APP_NOTIFICAITON";


    public static void showNotification(Service context, String title, String content) {

        createNotificationChannel(context);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                0, notificationIntent, 0);

        RemoteInput remoteInput = new RemoteInput.Builder("KEY_TEXT_REPLY")
                .setLabel("Reply")
                .build();
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(
                        R.drawable.ic_arrow_forward_black_24dp,
                        "Reply",
                        pendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();


        Person user = new Person.Builder().setIcon(null).setName(title).build();
        NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle(user)
                .addMessage(new NotificationCompat.MessagingStyle.Message(content, new Date().getTime(), user));

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_arrow_forward_black_24dp)
                .setContentIntent(pendingIntent)
                .addAction(action)
                .setStyle(style)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();


        NotificationCompat.Action.Builder  actionBuilder = new NotificationCompat.Action.Builder(action);
        NotificationCompat.Action.WearableExtender actionExtender =
                new NotificationCompat.Action.WearableExtender()
                        .setHintLaunchesActivity(true)
                        .setHintDisplayActionInline(true);
        NotificationCompat.WearableExtender wearableExtender = new WearableExtender();
        wearableExtender.addAction(actionBuilder.extend(actionExtender).build());

        context.startForeground(1, notification);
    }

    private static void createNotificationChannel(Service service) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = service.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }



//    private NotificationCompat.Builder recreateBuilderWithMessagingStyle( Service service, String title,String content ) {
//
//        // Main steps for building a MESSAGING_STYLE notification:
//        //      0. Get your data
//        //      1. Create Notification Channel for O and beyond devices (26+)
//        //      2. Build the MESSAGING_STYLE
//        //      3. Set up main Intent for notification
//        //      4. Set up RemoteInput (users can input directly from notification)
//        //      5. Build and issue the notification
//
//        // 0. Get your data (everything unique per Notification).
//
//        // 1. Retrieve Notification Channel for O and beyond devices (26+). We don't need to create
//        //    the NotificationChannel, since it was created the first time this Notification was
//        //    created.
//        String notificationChannelId = CHANNEL_ID;
//
//        // 2. Build the Notification.Style (MESSAGING_STYLE).
//
//
//        Person user = new Person.Builder().setIcon(null).setName(title).build();
//        NotificationCompat.MessagingStyle messagingStyle =
//                new NotificationCompat.MessagingStyle(user)
//                        /*
//                         * <p>This API's behavior was changed in SDK version
//                         * {@link Build.VERSION_CODES#P}. If your application's target version is
//                         * less than {@link Build.VERSION_CODES#P}, setting a conversation title to
//                         * a non-null value will make {@link #isGroupConversation()} return
//                         * {@code true} and passing {@code null} will make it return {@code false}.
//                         * This behavior can be overridden by calling
//                         * {@link #setGroupConversation(boolean)} regardless of SDK version.
//                         * In {@code P} and above, this method does not affect group conversation
//                         * settings.
//                         *
//                         * In our case, we use the same title.
//                         */
//                        .setConversationTitle(title);
//
//        // Adds all Messages.
//        // Note: Messages include the text, timestamp, and sender.
//        for (NotificationCompat.MessagingStyle.Message message : messagingStyleCommsAppData.getMessages()) {
//            messagingStyle.addMessage(message);
//        }
//
//        messagingStyle.setGroupConversation(false);
//
//        // 3. Set up main Intent for notification.
//        Intent notifyIntent = new Intent(this, MessagingMainActivity.class);
//
//        PendingIntent mainPendingIntent =
//                PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        // 4. Set up a RemoteInput Action, so users can input (keyboard, drawing, voice) directly
//        // from the notification without entering the app.
//
//        // Create the RemoteInput specifying this key.
//        String replyLabel = getString(R.string.reply_label);
//        RemoteInput remoteInput =
//                new RemoteInput.Builder(MessagingIntentService.EXTRA_REPLY)
//                        .setLabel(replyLabel)
//                        // Use machine learning to create responses based on previous messages.
//                        .setChoices(messagingStyleCommsAppData.getReplyChoicesBasedOnLastMessage())
//                        .build();
//
//        // Create PendingIntent for service that handles input.
//        Intent replyIntent = new Intent(this, MessagingIntentService.class);
//        replyIntent.setAction(MessagingIntentService.ACTION_REPLY);
//        PendingIntent replyActionPendingIntent = PendingIntent.getService(this, 0, replyIntent, 0);
//
//        // Enable action to appear inline on Wear 2.0 (24+). This means it will appear over the
//        // lower portion of the Notification for easy action (only possible for one action).
//        final NotificationCompat.Action.WearableExtender inlineActionForWear2 =
//                new NotificationCompat.Action.WearableExtender()
//                        .setHintDisplayActionInline(true)
//                        .setHintLaunchesActivity(false);
//
//        NotificationCompat.Action replyAction =
//                new NotificationCompat.Action.Builder(
//                        R.drawable.ic_arrow_forward_black_24dp,
//                        replyLabel,
//                        replyActionPendingIntent)
//                        .addRemoteInput(remoteInput)
//                        // Informs system we aren't bringing up our own custom UI for a reply
//                        // action.
//                        .setShowsUserInterface(false)
//                        // Allows system to generate replies by context of conversation.
//                        .setAllowGeneratedReplies(true)
//                        // Add WearableExtender to enable inline actions.
//                        .setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_REPLY)
//                        .extend(inlineActionForWear2)
//                        .build();
//
//        // 5. Build and issue the notification.
//
//        // Notification Channel Id is ignored for Android pre O (26).
//        NotificationCompat.Builder notificationCompatBuilder =
//                new NotificationCompat.Builder(getApplicationContext(), notificationChannelId);
//
//        GlobalNotificationBuilder.setNotificationCompatBuilderInstance(notificationCompatBuilder);
//
//        notificationCompatBuilder
//                // MESSAGING_STYLE sets title and content for Wear 1.+ and Wear 2.0 devices.
//                .setStyle(messagingStyle)
//                .setContentTitle(contentTitle)
//                .setContentText(messagingStyleCommsAppData.getContentText())
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setLargeIcon(
//                        BitmapFactory.decodeResource(
//                                getResources(), R.drawable.ic_person_black_48dp))
//                .setContentIntent(mainPendingIntent)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                // Set primary color (important for Wear 2.0 Notifications).
//                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
//
//                // Number of new notifications for API <24 (Wear 1.+) devices.
//                .setSubText(Integer.toString(messagingStyleCommsAppData.getNumberOfNewMessages()))
//                .addAction(replyAction)
//                .setCategory(Notification.CATEGORY_MESSAGE)
//
//                // Sets priority for 25 and below. For 26 and above, 'priority' is deprecated for
//                // 'importance' which is set in the NotificationChannel. The integers representing
//                // 'priority' are different from 'importance', so make sure you don't mix them.
//                .setPriority(messagingStyleCommsAppData.getPriority())
//
//                // Sets lock-screen visibility for 25 and below. For 26 and above, lock screen
//                // visibility is set in the NotificationChannel.
//                .setVisibility(messagingStyleCommsAppData.getChannelLockscreenVisibility());
//
//        // If the phone is in "Do not disturb" mode, the user may still be notified if the
//        // sender(s) are in a group allowed through "Do not disturb" by the user.
//        for (Person person : messagingStyleCommsAppData.getParticipants()) {
//            notificationCompatBuilder.addPerson(person.getUri());
//        }
//        return notificationCompatBuilder;
//    }


}
