package com.example.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class POST extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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
    
    public String HTTPPOST(String url,String postdata)
    {
    	String result = "";
    	HttpPost hPost=new HttpPost(url);
    	List <NameValuePair> params=new ArrayList<NameValuePair>();
    	String posts[]=postdata.split("&");
    	String posts2[];
    	int i;
		for(i=0;i<posts.length;i++)
		{
			posts2=posts[i].split("=");
			if(posts2.length == 2)
			params.add(new BasicNameValuePair (posts2[0],posts2[1]));
			else params.add(new BasicNameValuePair (posts2[0],""));
		}
    	try{
    		HttpEntity hen=new UrlEncodedFormEntity(params,"gb2312");
    		hPost.setEntity(hen);
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpResponse hResponse;
    		hResponse = httpclient.execute(hPost);
    		if(hResponse.getStatusLine().getStatusCode()==200)
    			{
    				result = EntityUtils.toString(hResponse.getEntity());
    				result = new String(result.getBytes("ISO_8859_1"),"gbk");
    			}

    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	return(result);
    }
    
    Runnable post = new Runnable(){
		@Override
    	public void run(){
    		EditText editText1 =(EditText)findViewById(R.id.peditText1);
    		EditText editText2 =(EditText)findViewById(R.id.peditText2);
	    	final TextView textView2 =(TextView)findViewById(R.id.ptextView2);
	    	String url = editText1.getText().toString();
	    	String postdata = editText2.getText().toString();
	    	final String result = HTTPPOST(url,postdata);
	    	textView2.post(new Runnable() {
                public void run() {
                	textView2.setText(result);
                }
            });
    	}
    };

    public void post(View v)
    {
    	Thread thread = new Thread(post);
    	thread.start();
    }
}
