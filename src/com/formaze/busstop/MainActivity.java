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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") public class MainActivity extends Activity {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //구글 api 상위버전 쓰레드 에러에 대응하기위한 코드, @TargetApi포함
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        //
        
        final Button srchButton = (Button) findViewById(R.id.btn_busSrch);
        final EditText busNumInput = (EditText) findViewById(R.id.busNum);
        final Spinner cityNm = (Spinner)findViewById(R.id.cityNm);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Constant.cityNm);
        cityNm.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //cityNm.setSelection(2);
        
        srchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			String resultMsg = "";
			String getMsg = "";

			//#1. 화면인입변수를 받는다
			//(1) 버스번호
			String busCd = busNumInput.getText().toString();
			
			//(2) 지역명(getCityCode로 인엑스값을 코드로 맵핑)
			int cityIdx = cityNm.getSelectedItemPosition();
			System.out.println("## cityIdx ##"+cityIdx);
			SpinnerMapCode spinnerMapCode = new SpinnerMapCode();
			String cityCd = "";
			cityCd = spinnerMapCode.getCityCode(cityIdx);
			System.out.println("## cityCd ##"+cityCd);

			//#2. 화면인입변수로 공공데이터 호출
			ConnectPdata connectPdata = new ConnectPdata();
				
			try {
				connectPdata.runGetRouteAcctoBusLcList(busCd, cityCd);
			} catch (Exception e) {
				System.out.println("## 에러에러 ##");
			}

			
//			Toast.makeText(MainActivity.this, resultMsg, Toast.LENGTH_SHORT).show();
				
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
