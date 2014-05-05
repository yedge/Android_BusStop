package com.formaze.busstop;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") 
public class BusRtActivity extends Activity {
    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscplist);
        //구글 api 상위버전 쓰레드 에러에 대응하기위한 코드, @TargetApi포함  
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        //
        String routeId = getIntent().getStringExtra("routeId");
        String cityCode = getIntent().getStringExtra("cityCode");
        final ListView busCpListView = (ListView)findViewById(R.id.list_busCp);
 
		//#2. 화면인입변수로 공공데이터 호출
		ConnectPdata connectPdata = new ConnectPdata();
		TestPdata testPdata = new TestPdata(); //장애시 테스트용
		MakeDataList makeData = new MakeDataList();
		ConnectPdataIO outRtListIo = new ConnectPdataIO();
		ConnectPdataIO outSttnListIo = new ConnectPdataIO();
		
		try {
			//(1)버스정류소조회
			outSttnListIo = testPdata.runGetRouteAcctoThrghSttnList(routeId, cityCode);

			if(!"00".equals(outSttnListIo.getResultCode())) {
				Toast.makeText(BusRtActivity.this, Constant.msg_sttnListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(2)실시간버스정보조회
			outRtListIo = testPdata.runGetRouteAcctoBusLcList(routeId, cityCode);

			if(!"00".equals(outRtListIo.getResultCode())) {
				Toast.makeText(BusRtActivity.this, Constant.msg_rtListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(3)버스현재위치리스트
			ArrayList<BusCpListBean> cpList = new ArrayList<BusCpListBean>();
			cpList = makeData.runGetCurrentPositionBusList(outRtListIo.getBusRtList(), outSttnListIo.getBusSttnList());

			System.out.println("## out ##"+cpList);
			BusCpListAdapter busCpAdapter = new BusCpListAdapter(BusRtActivity.this, R.layout.buscp_list, cpList);

			busCpListView.setAdapter(busCpAdapter);


		} catch (Exception e) {
			System.out.println("## 에러에러 ##");
		}
    }
}
