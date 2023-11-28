package com.example.julytimerreworked;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * Klasse zur Verwaltung von Benachrichtigungen und zum Senden von Nachrichten.
 */
public class messageHandler {

    /**
     * Sendet eine Benachrichtigung mit den angegebenen Zeiten und dem Kontext.
     *
     * @param times   Das xyzSet-Objekt, das die Zeiten enthält.
     * @param context Der Kontext, in dem die Benachrichtigung gesendet wird.
     */
    public static void sendMessage(xyzSet times, Context context) {
        if(times.getPercent() > 0) {
            sendMessageBiggerZero(times, context);
        } else {
            sendMessageZero(times, context);
        }
    }

    private static void sendMessageZero(xyzSet times, Context context) {
        JulyTimersave save = saveExec.load(context);
        String title = StringCompiler.getTimeString(save.getShow(), -times.getElapsed(), context);
        String content = StringCompiler.getTillGoString(save.getShow(), context);
        content = StringCompiler.replacelast(content, '!');

        NotificationCompat.Builder builder;
        NotificationManager notificationManager;
        NotificationChannel channel;
        notificationManager = context.getSystemService(NotificationManager.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("35", "channel", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(context, "35");
        builder.setContentText(content)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.herz)
                .setStyle(new NotificationCompat.BigTextStyle());
        notificationManager.notify(1, builder.build());
    }

    private static void sendMessageBiggerZero(xyzSet times, Context context) {
        JulyTimersave save = saveExec.load(context);
        String title = StringCompiler.getTimeString(save.getShow(), times.getRemaining(), context);
        String content = StringCompiler.getTillSeenString(save.getShow(), context);
        content = StringCompiler.replacelast(content, '!');
        content += " " + StringCompiler.getPercentString(times.getPercent());
        content += " " + context.getString(R.string.percentage_done);
        content = StringCompiler.replacelast(content, '!');

        NotificationCompat.Builder builder;
        NotificationManager notificationManager;
        NotificationChannel channel;
        notificationManager = context.getSystemService(NotificationManager.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("35", "channel", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);
        }
        builder = new NotificationCompat.Builder(context, "35");
        builder.setContentText(content)
                .setContentTitle(title)
                .setSmallIcon(R.drawable.herz)
                .setStyle(new NotificationCompat.BigTextStyle())
                .setProgress(10000,(int) times.getPercent()*100, false);
        notificationManager.notify(1, builder.build());
    }

    /**
     * Entfernt die angezeigte Benachrichtigung.
     *
     * @param context Der Kontext, in dem die Benachrichtigung entfernt wird.
     */
    static void removeMessage(Context context) {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("35", "channel", NotificationManager.IMPORTANCE_LOW);
            channel.setDescription("getString(R.string.notification_name_1)");
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.cancel(1);
    }
}
