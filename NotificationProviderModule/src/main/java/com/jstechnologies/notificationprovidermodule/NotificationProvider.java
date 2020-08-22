package com.jstechnologies.notificationprovidermodule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class NotificationProvider {

    String channelName="Notification_provider";
    String channelID="Notification_provider";
    int notificationIcon=R.drawable.ic_notification;
    Context context;
    String title,body;
    Intent intent;
    boolean autocancel=false;
    List<NotificationCompat.Action>actions=new ArrayList<>();

    public NotificationProvider(Context context) {
        this.context = context;

    }

    public NotificationProvider setChannelName(String channelName) {
        this.channelName = channelName;
        return this;
    }

    public NotificationProvider setChannelID(String channelID) {
        this.channelID = channelID;
        return this;
    }

    public NotificationProvider setAutocancel(boolean autocancel) {
        this.autocancel = autocancel;
        return this;
    }
    public NotificationProvider addActionButton(NotificationCompat.Action action) {
        this.actions.add(action);
        return this;
    }
    public NotificationProvider setNotificationIcon(int notificationIcon) {
        this.notificationIcon = notificationIcon;
        return this;
    }

    public NotificationProvider setTitle(String title) {
        this.title = title;
        return this;
    }

    public NotificationProvider setBody(String body) {
        this.body = body;
        return this;
    }

    public NotificationProvider setIntent(Intent intent) {
        this.intent = intent;
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
                .setAutoCancel(autocancel);
        if(actions!=null && actions.size()>0)
            for(NotificationCompat.Action action:actions)
                mBuilder.addAction(action);
        if(intent!=null)
        {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addNextIntent(intent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
            mBuilder.setContentIntent(resultPendingIntent);
        }


        notificationManager.notify(NotificationIDManager.getnotificationID(), mBuilder.build());
    }
}
