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
        
        //���� api �������� ������ ������ �����ϱ����� �ڵ�, @TargetApi����  
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

		//#2. ȭ�����Ժ����� ���������� ȣ��
		ConnectPdata connectPdata = new ConnectPdata();
		TestPdata testPdata = new TestPdata(); //��ֽ� �׽�Ʈ��
		MakeDataList makeData = new MakeDataList();
		ConnectPdataIO outInfoListIo = new ConnectPdataIO();
		ConnectPdataIO outRtListIo = new ConnectPdataIO();
		ConnectPdataIO outSttnListIo = new ConnectPdataIO();
		
		try {
			//(1)�����⺻������ȸ
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

			//(2)������������ȸ
			outSttnListIo = connectPdata.runGetRouteAcctoThrghSttnList(routeId, cityCode);

			if(!"00".equals(outSttnListIo.getResultCode()) || outSttnListIo.getBusSttnList() == null) {
				Toast.makeText(BusRtActivity.this, Constant.msg_sttnListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(3)�ǽð�����������ȸ
			outRtListIo = connectPdata.runGetRouteAcctoBusLcList(routeId, cityCode);

			if(!"00".equals(outRtListIo.getResultCode()) || outRtListIo.getBusRtList() == null) {
				Toast.makeText(BusRtActivity.this, Constant.msg_rtListRslt_empty, Toast.LENGTH_SHORT).show();
				return;
			}
			
			//(4)����������ġ����Ʈ
			ArrayList<BusCpListBean> cpList = new ArrayList<BusCpListBean>();
			cpList = makeData.runGetCurrentPositionBusList(outRtListIo.getBusRtList(), outSttnListIo.getBusSttnList());

			BusCpListAdapter busCpAdapter = new BusCpListAdapter(BusRtActivity.this, R.layout.buscp_list, cpList);

			busCpListView.setAdapter(busCpAdapter);
			busCpAdapter.notifyDataSetChanged();

		} catch (Exception e) {
			System.out.println("## �������� ##");
		}
    }
}
