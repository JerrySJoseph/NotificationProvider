package com.jstechnologies.notificationprovidermodule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class ExpandedNotificationProvider {

    public enum ExpandedNotificationType{
        BIG_PICTURE,
        BIG_TEXT,
        MESSAGE,
        INBOX
    }
    String channelName="Notification_provider";
    String channelID="Notification_provider";
    int notificationIcon=R.drawable.ic_notification;
    Context context;
    String title,body;
    Intent intent;
    Bitmap image;
    boolean autocancel=false;
    int notificationId=101;
    List<NotificationCompat.Action> actions=new ArrayList<>();
    ExpandedNotificationType type=ExpandedNotificationType.BIG_TEXT;
    String bigText;
    List<NotificationCompat.MessagingStyle.Message>messages= new ArrayList<>();
    List<String>inboxLines= new ArrayList<>();
    Bitmap largeIcon;
    String replyname="user";

    public ExpandedNotificationProvider(Context context, ExpandedNotificationType type) {
        this.context = context;
        this.type = type;
    }

    public ExpandedNotificationProvider setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public ExpandedNotificationProvider setImage(Bitmap image) {
        this.image = image;
        return this;
    }

    public ExpandedNotificationProvider setLargeIcon(Bitmap largeIcon) {
        this.largeIcon = largeIcon;
        return this;
    }

    public ExpandedNotificationProvider setChannelID(String channelID) {
        this.channelID = channelID;
        return this;
    }
    public ExpandedNotificationProvider addActionButton(NotificationCompat.Action action) {
        this.actions.add(action);
        return this;
    }

    public ExpandedNotificationProvider setBigText(String bigText) {
        this.bigText = bigText;
        return this;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public ExpandedNotificationProvider addMessages(NotificationCompat.MessagingStyle.Message msg) {
        this.messages.add(msg);
        return this;
    }
    public ExpandedNotificationProvider addLines(String line) {
        this.inboxLines.add(line);
        return this;
    }

    public ExpandedNotificationProvider setNotificationIcon(int notificationIcon) {
        this.notificationIcon = notificationIcon;
        return this;
    }

    public ExpandedNotificationProvider setTitle(String title) {
        this.title = title;
        return this;
    }

    public ExpandedNotificationProvider setBody(String body) {
        this.body = body;
        return this;
    }

    public ExpandedNotificationProvider setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public ExpandedNotificationProvider setAutocancel(boolean autocancel) {
        this.autocancel = autocancel;
        return this;
    }
    public void show()
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelID, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(notificationIcon)
                .setContentTitle(title)
                .setContentText(body)
                .setLargeIcon(largeIcon);

        switch (type)
        {
            case BIG_PICTURE: mBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(null));break;
            case BIG_TEXT:mBuilder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(bigText));break;
            case INBOX: NotificationCompat.InboxStyle obj=new NotificationCompat.InboxStyle();
                        for(String line:inboxLines)
                        {
                            obj.addLine(line);
                        }
                        mBuilder.setStyle(obj);
                        break;
            case MESSAGE:NotificationCompat.MessagingStyle obj1=new NotificationCompat.MessagingStyle(replyname);
                for(NotificationCompat.MessagingStyle.Message line:messages)
                {
                    obj1.addMessage(line);
                }
                mBuilder.setStyle(obj1);
                break;
        }
        if(actions!=null && actions.size()>0)
            for(NotificationCompat.Action action:actions)
                mBuilder.addAction(action);

        notificationManager.notify(NotificationIDManager.getnotificationID(), mBuilder.build());
    }
}
