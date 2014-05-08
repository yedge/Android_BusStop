package com.formaze.busstop;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") 
public class BusRtActivity extends Activity {
    @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscplist);
        
        //LayoutInflater mInf;
        
        //구글 api 상위버전 쓰레드 에러에 대응하기위한 코드, @TargetApi포함  
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);
        }
        //
        String routeId = getIntent().getStringExtra("routeId");
        String cityCode = getIntent().getStringExtra("cityCode");
        final ListView busCpListView = (ListView)findViewById(R.id.list_busCp);
        final ImageView busCpListHeader = new ImageView(this);
        final ImageView busCpListFoot = new ImageView(this);
        busCpListHeader.setImageResource(R.drawable.head_rt);
        busCpListFoot.setImageResource(R.drawable.foot_rt);
        busCpListView.addHeaderView(busCpListHeader);
        busCpListView.addFooterView(busCpListFoot);

		final TextView routeNo = (TextView) findViewById(R.id.routeNo_cp);
		final TextView routeTp = (TextView) findViewById(R.id.routeTp_cp);
		final TextView endNodeNm = (TextView) findViewById(R.id.endNodeNm_cp);
		final TextView startNodeNm = (TextView) findViewById(R.id.startNodeNm_cp);
		final TextView endVehicleTime = (TextView) findViewById(R.id.endVehicleTime_cp);
		final TextView startVehicleTime = (TextView) findViewById(R.id.startVehicleTime_cp);
		final TextView intervalTime = (TextView) findViewById(R.id.intervalTime_cp);
		final TextView intervalSatTime = (TextView) findViewById(R.id.intervalSatTime_cp);
		final TextView intervalSunTime = (TextView) findViewById(R.id.intervalSunTime_cp);

		//#2. 화면인입변수로 공공데이터 호출
		ConnectPdata connectPdata = new ConnectPdata();
		TestPdata testPdata = new TestPdata(); //장애시 테스트용
		MakeDataList makeData = new MakeDataList();
		ConnectPdataIO outInfoListIo = new ConnectPdataIO();
		ConnectPdataIO outRtListIo = new ConnectPdataIO();
		ConnectPdataIO outSttnListIo = new ConnectPdataIO();
		
		try {
			//(1)버스기본정보조회
			outInfoListIo = connectPdata.runGetRouteInfoIem(routeId, cityCode);

			if(!"00".equals(outInfoListIo.getResultCode()) || outInfoListIo.getRouteInfoList() == null) {
				Toast.makeText(BusRtActivity.this, Constant.msg_infoListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}

			//mInf = getLayoutInflater();
			//makeData.runMappingRouteInfo(mInf, outInfoListIo.getRouteInfoList());

			
			RouteInfoListBean routeInfo = outInfoListIo.getRouteInfoList().get(0);

			String startVehicleTimeStr = routeInfo.getStartVehicleTime().substring(0,2) + ":" + routeInfo.getStartVehicleTime().substring(2,4);
			String endVehicleTimeStr = routeInfo.getEndVehicleTime().substring(0,2) + ":" + routeInfo.getEndVehicleTime().substring(2,4);
			
			routeNo.setText(routeInfo.getRouteNo());
			routeTp.setText(routeInfo.getRouteTp());
			endNodeNm.setText(routeInfo.getEndNodeNm());
			startNodeNm.setText(routeInfo.getStartNodeNm());
			endVehicleTime.setText(endVehicleTimeStr);
			startVehicleTime.setText(startVehicleTimeStr);
			intervalTime.setText(routeInfo.getIntervalTime());
			intervalSatTime.setText(routeInfo.getIntervalSatTime());
			intervalSunTime.setText(routeInfo.getIntervalSunTime());

			//(2)버스정류소조회
			outSttnListIo = connectPdata.runGetRouteAcctoThrghSttnList(routeId, cityCode);

			if(!"00".equals(outSttnListIo.getResultCode()) || outSttnListIo.getBusSttnList() == null) {
				Toast.makeText(BusRtActivity.this, Constant.msg_sttnListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(3)실시간버스정보조회
			outRtListIo = connectPdata.runGetRouteAcctoBusLcList(routeId, cityCode);

			if(!"00".equals(outRtListIo.getResultCode()) || outRtListIo.getBusRtList() == null) {
				Toast.makeText(BusRtActivity.this, Constant.msg_rtListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(4)버스현재위치리스트
			ArrayList<BusCpListBean> cpList = new ArrayList<BusCpListBean>();
			cpList = makeData.runGetCurrentPositionBusList(outRtListIo.getBusRtList(), outSttnListIo.getBusSttnList());

			BusCpListAdapter busCpAdapter = new BusCpListAdapter(BusRtActivity.this, R.layout.buscp_list, cpList);

			busCpListView.setAdapter(busCpAdapter);
			busCpAdapter.notifyDataSetChanged();

		} catch (Exception e) {
			System.out.println("## 에러에러 ##");
		}
    }
}
