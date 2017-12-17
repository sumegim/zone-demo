package com.example.mars.zonedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MusicBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String cmd = intent.getStringExtra("command");
        Log.v("tag ", action + " / " + cmd);
        String artist = intent.getStringExtra("artist");
        String album = intent.getStringExtra("album");
        String track = intent.getStringExtra("track");
        Log.v("tag", artist + ":" + album + ":" + track);
        Toast.makeText(context, track, Toast.LENGTH_SHORT).show();

        //String key = FirebaseDatabase.getInstance().getReference().child("posts").push().getKey();
        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Post newPost = new Post(key, FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), track, artist);

        FirebaseDatabase.getInstance().getReference().child("posts").child(key).setValue(newPost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }
}
