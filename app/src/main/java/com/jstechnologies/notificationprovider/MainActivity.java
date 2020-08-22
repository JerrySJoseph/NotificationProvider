package com.jstechnologies.notificationprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.jstechnologies.notificationprovidermodule.ExpandedNotificationProvider;
import com.jstechnologies.notificationprovidermodule.NotificationProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void show(View view) {

        Bitmap img=BitmapFactory.decodeResource(getResources(),R.drawable.user5);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(getIntent());
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        new ExpandedNotificationProvider(this, ExpandedNotificationProvider.ExpandedNotificationType.INBOX).
                setTitle("This is a notification").
                setNotificationIcon(R.mipmap.ic_launcher).
                setIntent(this.getIntent()).
                setAutocancel(false).
                addMessages(new NotificationCompat.MessagingStyle.Message("Hello",System.currentTimeMillis(),"Jerin")).
                addMessages(new NotificationCompat.MessagingStyle.Message("Hi there",System.currentTimeMillis(),"Sundar")).
                addMessages(new NotificationCompat.MessagingStyle.Message("How are you?",System.currentTimeMillis(),"Jerin")).
                addMessages(new NotificationCompat.MessagingStyle.Message("I am fine bro. wbu?",System.currentTimeMillis(),"Sundar")).
                addMessages(new NotificationCompat.MessagingStyle.Message("U know me.. I am fine too",System.currentTimeMillis(),"Jerin")).
                setBigText("Apply NotificationCompat.InboxStyle to a notification if you want to add multiple short summary lines, " +
                        "such as snippets from incoming emails. This allows you to add multiple pieces of content text that are " +
                        "each truncated to one line, instead of one continuous line of text provided by NotificationCompat.BigTextStyle.").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addLines("This is a line").
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                addActionButton(new NotificationCompat.Action(R.drawable.ic_reply,"Reply",resultPendingIntent)).
                setBody("Test").
                setLargeIcon(img).
                setImage(img).
                show();
    }
}