package com.example.finalproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LifecycleActivity extends AppCompatActivity{
    static int stopCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        createNotificationChannel();
    }

    @Override
    protected void onStop(){
        stopCounter++;
        makeNotification();
        super.onStop();
    }

    NotificationManager notifyManager;
    private void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel("channel_ID", getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(getLocalClassName());
        notifyManager = getSystemService(NotificationManager.class);
        notifyManager.createNotificationChannel(channel);
    }

    protected void makeNotification(){
        int notificationId = 1;

        Notification notify = new Notification.Builder(this, "channel_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Stops")
                .setContentText("Total stop count: " + stopCounter)
                .build();

        // Update existing notification
        notifyManager.notify(notificationId, notify);
    }
}