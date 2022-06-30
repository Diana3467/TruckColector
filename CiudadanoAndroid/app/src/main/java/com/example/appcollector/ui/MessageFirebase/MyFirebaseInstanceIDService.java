package com.example.appcollector.ui.MessageFirebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.appcollector.MainActivity;
import com.example.appcollector.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.StorageException;


import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService implements OnFailureListener {


    @Override
    public void onNewToken(@NonNull @NotNull String token) {
        super.onNewToken(token);
        Log.d("TOKEN", "Mi token es: " + token);
    }

    @Override
    public void onMessageReceived( RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null){
            String titulo = remoteMessage.getNotification().getTitle();
            String cuerpo = remoteMessage.getNotification().getBody();
            mayorqueoreo(titulo, cuerpo);
        }

    }


    private void mayorqueoreo(String titulo, String cuerpo){
        String id = "mensaje";

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel nc =  new NotificationChannel(id, "nuevo", NotificationManager.IMPORTANCE_HIGH);
            nc.setShowBadge(true);
            assert nm != null;
            nm.createNotificationChannel(nc);
        }
        builder.setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(clicknotifiacion())
                .setContentText(cuerpo)
                .setContentInfo("nuevo");

        Random random = new Random();
        int idNotify = random.nextInt(8000);

        assert nm != null;
        nm.notify(idNotify, builder.build());

    }

    public PendingIntent clicknotifiacion(){
        Intent nf = new Intent(getApplicationContext(), MainActivity.class);
        nf.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, nf, 0);
    }

    @Override
    public void onFailure(@NonNull Exception exception) {
        int errorCode = ((StorageException) exception).getErrorCode();
        String errorMessage = exception.getMessage();

        Log.e("TAG","Codigo_Error: " + errorCode);
        Log.e("TAG","Error_mensaje: " + errorMessage);
    }


}
