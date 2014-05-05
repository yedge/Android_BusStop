package com.formaze.busstop;

import java.util.ArrayList;

public class TestPdata {
	 //통신장애시 개발 테스트용도
	
	 public ConnectPdataIO runGetRouteNoList(String busNum, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
				ArrayList<BusInfoListBean> busInfoList = new ArrayList<BusInfoListBean>();
				BusInfoListBean infoBean = new BusInfoListBean();
				infoBean = new BusInfoListBean();
				infoBean.setRouteNo("5");
				infoBean.setStartNodeNm("유성고");
				infoBean.setEndNodeNm("구득초");
				infoBean.setRouteId("DJB30300004ND");
				infoBean.setCityCode("25");
				busInfoList.add(infoBean);
				
				infoBean = new BusInfoListBean();
				infoBean.setRouteNo("5_1");
				infoBean.setStartNodeNm("유성고_1");
				infoBean.setEndNodeNm("구득초_1");
				infoBean.setRouteId("DJB30300004ND_1");
				infoBean.setCityCode("25_1");
				busInfoList.add(infoBean);
				
				outIo.setResultCode("00");
				outIo.setBusInfoList(busInfoList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }
	 
	 public ConnectPdataIO runGetRouteAcctoBusLcList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
				ArrayList<BusRtListBean> busRtList = new ArrayList<BusRtListBean>();
				BusRtListBean rtBean = new BusRtListBean();
				rtBean = new BusRtListBean();
				rtBean.setNodeId("DJB8005621ND");
				rtBean.setNodeNm("대전역광장종점");
				rtBean.setRouteTp("간선");
				rtBean.setNodeOrd("1");
				rtBean.setGpsLati("36.333445");
				rtBean.setGpsLong("127.438859");
				busRtList.add(rtBean);
				
				rtBean = new BusRtListBean();
				rtBean.setNodeId("DJB8005621ND_2");
				rtBean.setNodeNm("대전역광장종점_2");
				rtBean.setRouteTp("간선_2");
				rtBean.setNodeOrd("1_2");
				rtBean.setGpsLati("36.333445_2");
				rtBean.setGpsLong("127.438859_2");
				busRtList.add(rtBean);
				
				outIo.setResultCode("00");
				outIo.setBusRtList(busRtList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }
 
	 public ConnectPdataIO runGetRouteAcctoThrghSttnList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
				ArrayList<BusSttnListBean> busSttnList = new ArrayList<BusSttnListBean>();
				BusSttnListBean sttnBean = new BusSttnListBean();
				sttnBean = new BusSttnListBean();
				sttnBean.setNodeOrd("1");
				sttnBean.setNodeNm("유성고후문");
				sttnBean.setNodeId("DJB1860090500ND");
				sttnBean.setRouteId("DJB30300004ND");
				busSttnList.add(sttnBean);
				
				sttnBean = new BusSttnListBean();
				sttnBean.setNodeOrd("1_1");
				sttnBean.setNodeNm("유성고후문_1");
				sttnBean.setNodeId("DJB1860090500ND_1");
				sttnBean.setRouteId("DJB30300004ND_1");
				busSttnList.add(sttnBean);

				sttnBean = new BusSttnListBean();
				sttnBean.setNodeOrd("1_2");
				sttnBean.setNodeNm("유성고후문_2");
				sttnBean.setNodeId("DJB1860090500ND_2");
				sttnBean.setRouteId("DJB30300004ND_2");
				busSttnList.add(sttnBean);

				outIo.setResultCode("00");
				outIo.setBusSttnList(busSttnList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }

}
