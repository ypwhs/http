package com.example.http;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class http extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskWrites()
        .detectDiskReads()
        .detectNetwork()
        .penaltyLog()
        .build());
        
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
        .penaltyDeath()
        .build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_http, menu);
        return true;
    }
    
    public void jumpToGet(View v){
    	Intent intent = new Intent();
    	intent.setClass(this, GET.class);
    	startActivity(intent);
    }
    public void jumpToPost(View v){
    	Intent intent = new Intent();
    	intent.setClass(this, POST.class);
    	startActivity(intent);
    }    
}
