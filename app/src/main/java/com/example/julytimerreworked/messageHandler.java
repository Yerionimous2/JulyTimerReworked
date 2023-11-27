package com.example.julytimerreworked;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class messageHandler {
    public static void sendMessage(xyzSet times, Context context) {
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
