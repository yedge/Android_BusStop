package com.formaze.busstop;

import java.util.ArrayList;

public class MakeDataList {
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
