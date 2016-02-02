package com.pushnotificationtest.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.pushnotificationtest.R;

/**
 * Created by smskin on 02.02.16.
 *
 new InternalNotification()
     .setCallbackIntent(MainActivity.class)
     .setContext(getApplicationContext())
     .setTitle("title")
     .setMessage("message")
     .setIcon(R.mipmap.ic_launcher)
     .submit();
 */
public class InternalNotification {

    protected Class _callbackIntent;
    protected Context _context;
    protected String _title;
    protected String _message;
    protected int _icon = R.mipmap.ic_launcher;
    protected boolean _autoCancel = true;

    public InternalNotification setCallbackIntent(Class callbackIntent){
        _callbackIntent = callbackIntent;
        return this;
    }

    public InternalNotification setContext(Context context){
        _context = context;
        return this;
    }

    public InternalNotification setTitle(String title){
        _title = title;
        return this;
    }

    public InternalNotification setMessage(String message){
        _message = message;
        return this;
    }

    public InternalNotification setIcon(int icon){
        _icon = icon;
        return this;
    }

    public InternalNotification setAutoCancel(boolean bool){
        _autoCancel = bool;
        return this;
    }

    public void submit(){
        Intent notificationIntent = new Intent(_context, _callbackIntent);
        PendingIntent contentIntent = PendingIntent.getActivity(_context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        android.app.Notification.Builder notificationBuilder = new android.app.Notification.Builder(_context)
                .setContentTitle(_title)
                .setContentText(_message)
                .setDefaults(android.app.Notification.DEFAULT_SOUND)
                .setSmallIcon(_icon)
                .setAutoCancel(_autoCancel)
                .setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager)
                _context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT < 16) {
            notificationManager.notify(0, notificationBuilder.getNotification());
        } else {
            notificationManager.notify(0, notificationBuilder.build());
        }
    }
}
