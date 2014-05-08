package com.formaze.busstop;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MakeDataList  extends Activity {

	/**
	 * 도시인덱스로 도시코드를 출력한다
	 **/
	public String getCityCode (int cityIdx) {
		String out = "";
		String[] cityCd = Constant.cityCode;
		out = cityCd[cityIdx];
		return out;
	}

	/**
	 * 버스기본정보 전문결과를 버스정보에 매핑한다.
	 **/
	public void runMappingRouteInfo(LayoutInflater mInf, ArrayList<RouteInfoListBean> routeInfoList) {
		
		System.out.println("들어옴?");
		
		try {
		View v = mInf.inflate(R.layout.activity_buscplist, null);
				
		if (routeInfoList.size() == 0) {
			//매핑할 정보없음
			return;
		}
		System.out.println("매애핑객체드을~");
		
		final TextView routeNo = (TextView) v.findViewById(R.id.routeNo_cp);
		final TextView routeTp = (TextView) v.findViewById(R.id.routeTp_cp);
		final TextView endNodeNm = (TextView) v.findViewById(R.id.endNodeNm_cp);
		final TextView startNodeNm = (TextView) v.findViewById(R.id.startNodeNm_cp);
		final TextView endVehicleTime = (TextView) v.findViewById(R.id.endVehicleTime_cp);
		final TextView startVehicleTime = (TextView) v.findViewById(R.id.startVehicleTime_cp);
		final TextView intervalTime = (TextView) v.findViewById(R.id.intervalTime_cp);
		final TextView intervalSatTime = (TextView) v.findViewById(R.id.intervalSatTime_cp);
		final TextView intervalSunTime = (TextView) v.findViewById(R.id.intervalSunTime_cp);
		
		RouteInfoListBean routeInfo = routeInfoList.get(0);

		System.out.println("매애핑시이자악~ : "+routeInfo.getRouteNo());
		System.out.println("대상 : "+routeNo);
		
		routeNo.setText(routeInfo.getRouteNo());
		routeTp.setText(routeInfo.getRouteTp());
		endNodeNm.setText(routeInfo.getEndNodeNm());
		startNodeNm.setText(routeInfo.getStartNodeNm());
		endVehicleTime.setText(routeInfo.getEndVehicleTime());
		startVehicleTime.setText(routeInfo.getStartVehicleTime());
		intervalTime.setText(routeInfo.getIntervalTime());
		intervalSatTime.setText(routeInfo.getIntervalSatTime());
		intervalSunTime.setText(routeInfo.getIntervalSunTime());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 버스정류소, 버스실시간정보로 버스현위치정보를 출력한다
	 **/
	 public ArrayList<BusCpListBean> runGetCurrentPositionBusList(ArrayList<BusRtListBean> busRtList, ArrayList<BusSttnListBean> busSttnList) {
		 
		 ArrayList<BusCpListBean> outList = new ArrayList<BusCpListBean>();
		 BusCpListBean cpBean = new BusCpListBean();
		 
		 try {

			 for (BusSttnListBean sttnBean : busSttnList) {
				 //정류소정보->버스현재위치기본정보
				 cpBean = new BusCpListBean();
				 cpBean.setNodeOrd(sttnBean.getNodeOrd());
				 cpBean.setNodeId(sttnBean.getNodeId());
				 cpBean.setNodeNm(sttnBean.getNodeNm());
				 
				 Integer busCnt = 0;
				 //실시간정보->버스현재위치 부가정보(위치표시)
				 for (BusRtListBean rtBean : busRtList) {
					 if (rtBean.getNodeOrd().equals(sttnBean.getNodeOrd())) {
						 cpBean.setSetPostion(true);
						 busCnt = cpBean.getBusCnt();
						 busCnt++;
						 cpBean.setBusCnt(busCnt);
					 }
				 }
				 outList.add(cpBean);
			 }

		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		 } finally {
			 
		 }
		 return outList;
    }
}
