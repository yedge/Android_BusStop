package com.formaze.busstop;

import java.util.ArrayList;

public class ConnectPdataIO {

	String resultCode;
	ArrayList<BusInfoListBean> busInfoList = new ArrayList<BusInfoListBean>();
	ArrayList<BusRtListBean> busRtList = new ArrayList<BusRtListBean>();
	ArrayList<BusSttnListBean> busSttnList = new ArrayList<BusSttnListBean>();
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public ArrayList<BusInfoListBean> getBusInfoList() {
		return busInfoList;
	}

	public void setBusInfoList(ArrayList<BusInfoListBean> busInfoList) {
		this.busInfoList = busInfoList;
	}

	public ArrayList<BusRtListBean> getBusRtList() {
		return busRtList;
	}

	public void setBusRtList(ArrayList<BusRtListBean> busRtList) {
		this.busRtList = busRtList;
	}

	public ArrayList<BusSttnListBean> getBusSttnList() {
		return busSttnList;
	}

	public void setBusSttnList(ArrayList<BusSttnListBean> busSttnList) {
		this.busSttnList = busSttnList;
	}

}
