package com.formaze.busstop;

import java.io.*;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        
        final Button srchButton = (Button) findViewById(R.id.btn_busSrch);
        final EditText busNumInput = (EditText) findViewById(R.id.busNum);
        
        
        srchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			String answer = "4000";
			String resultMsg = "";
			String a = busNumInput.getText().toString();
			String getMsg = "";
			
			if (a.equals(answer)) {
				resultMsg = "버스번호가 4000번이네요\n";
				
				
				ConnectPdata temp = new ConnectPdata();
				
				try {
					getMsg = temp.runCtyCodeList();
					resultMsg += getMsg; 
				} catch (Exception e) {
					System.out.println("## 에러에러 ##");
				}

				
			} else {
				resultMsg = "버스번호가 4000번이 아니네요";
			}
			
			Toast.makeText(MainActivity.this, resultMsg, Toast.LENGTH_SHORT).show();
				
			}
		});
 
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
   
    
}
