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

    boolean busy = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String cmd = intent.getStringExtra("command");
        Log.v("tag ", action + " / " + cmd);
        String artist = intent.getStringExtra("artist");
        String album = intent.getStringExtra("album");
        String track = intent.getStringExtra("track");
        //Log.d("intent URI", intent.toUri(0));

        /*Bundle bundle = intent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                Log.d("values", String.format("%s %s (%s)", key,
                        value.toString(), value.getClass().getName()));
            }
        }*/

        String spotifyUri = intent.getStringExtra("id");
        Log.v("tag", artist + ":" + album + ":" + track);
        Toast.makeText(context, spotifyUri, Toast.LENGTH_SHORT).show();

        /*if(busy) return;

        busy = true;
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground( Void... voids ) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                busy = false;
                return null;
            }
        }.execute();*/

        //String key = FirebaseDatabase.getInstance().getReference().child("posts").push().getKey();
        final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final Post newPost = new Post(key, FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), track, artist, spotifyUri);

        /*FirebaseDatabase.getInstance().getReference().child("posts").child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });*/

        FirebaseDatabase.getInstance().getReference().child("posts").child(key).setValue(newPost).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });



    }
}
