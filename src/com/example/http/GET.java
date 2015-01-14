package com.example.http;

import java.io.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.os.*;

public class GET extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

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
        .build());    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_get, menu);
        return true;
    }
    
    public String HTTPGET(String url)
    {
    	String result = "";
    	try{
    		HttpGet httpget=new HttpGet(url);
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpResponse hResponse;
    		hResponse = httpclient.execute(httpget);
    		if(hResponse.getStatusLine().getStatusCode()==200)
    			{
    				result = EntityUtils.toString(hResponse.getEntity());
    		    	result = new String(result.getBytes("ISO_8859_1"),"gbk");
    			}

    	}catch (ClientProtocolException e){
    		e.printStackTrace();
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    	return(result);
    }
    
    Runnable get = new Runnable(){
		@Override
    	public void run(){
    		final EditText editText1 =(EditText)findViewById(R.id.geteditText1);
	    	final TextView textView2 =(TextView)findViewById(R.id.gettextView2);
	    	final String url = editText1.getText().toString();
	    	final String result = HTTPGET(url);
	    	textView2.post(new Runnable() {
                public void run() {
                	
                	textView2.setText(result);
                }
            });
    	}
    };

    public void get(View v)
    {
    	Thread thread = new Thread(get);
    	thread.start();
    }
}
