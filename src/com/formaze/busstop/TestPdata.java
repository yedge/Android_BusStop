package com.formaze.busstop;

import java.util.ArrayList;

public class TestPdata {
	 //�����ֽ� ���� �׽�Ʈ�뵵
	
	 public ConnectPdataIO runGetRouteNoList(String busNum, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
				ArrayList<BusInfoListBean> busInfoList = new ArrayList<BusInfoListBean>();
				BusInfoListBean infoBean = new BusInfoListBean();
				infoBean = new BusInfoListBean();
				infoBean.setRouteNo("5");
				infoBean.setStartNodeNm("������");
				infoBean.setEndNodeNm("������");
				infoBean.setRouteId("DJB30300004ND");
				infoBean.setCityCode("25");
				busInfoList.add(infoBean);
				
				infoBean = new BusInfoListBean();
				infoBean.setRouteNo("5_1");
				infoBean.setStartNodeNm("������_1");
				infoBean.setEndNodeNm("������_1");
				infoBean.setRouteId("DJB30300004ND_1");
				infoBean.setCityCode("25_1");
				busInfoList.add(infoBean);
				
				outIo.setResultCode("00");
				outIo.setBusInfoList(busInfoList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("�����޼���"+e.getMessage().toString());
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
				rtBean.setNodeNm("��������������");
				rtBean.setRouteTp("����");
				rtBean.setNodeOrd("1");
				rtBean.setGpsLati("36.333445");
				rtBean.setGpsLong("127.438859");
				busRtList.add(rtBean);
				
				rtBean = new BusRtListBean();
				rtBean.setNodeId("DJB8005621ND_2");
				rtBean.setNodeNm("��������������_2");
				rtBean.setRouteTp("����_2");
				rtBean.setNodeOrd("1_2");
				rtBean.setGpsLati("36.333445_2");
				rtBean.setGpsLong("127.438859_2");
				busRtList.add(rtBean);
				
				outIo.setResultCode("00");
				outIo.setBusRtList(busRtList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("�����޼���"+e.getMessage().toString());
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
				sttnBean.setNodeNm("�������Ĺ�");
				sttnBean.setNodeId("DJB1860090500ND");
				sttnBean.setRouteId("DJB30300004ND");
				busSttnList.add(sttnBean);
				
				sttnBean = new BusSttnListBean();
				sttnBean.setNodeOrd("1_1");
				sttnBean.setNodeNm("�������Ĺ�_1");
				sttnBean.setNodeId("DJB1860090500ND_1");
				sttnBean.setRouteId("DJB30300004ND_1");
				busSttnList.add(sttnBean);

				sttnBean = new BusSttnListBean();
				sttnBean.setNodeOrd("1_2");
				sttnBean.setNodeNm("�������Ĺ�_2");
				sttnBean.setNodeId("DJB1860090500ND_2");
				sttnBean.setRouteId("DJB30300004ND_2");
				busSttnList.add(sttnBean);

				outIo.setResultCode("00");
				outIo.setBusSttnList(busSttnList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("�����޼���"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }

}
