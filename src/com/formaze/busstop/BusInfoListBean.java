package com.formaze.busstop;

public class BusInfoListBean {
String routeNo;
String startNodeNm;
String endNodeNm;
String routeId;
String cityCode;

public BusInfoListBean() {
	
}

public BusInfoListBean(String routeNo, String startNodeNm, String endNodeNm) {
	this.routeNo = routeNo;
	this.startNodeNm = startNodeNm;
	this.endNodeNm = endNodeNm;
}

public String getRouteNo() {
	return routeNo;
}

public void setRouteNo(String routeNo) {
	this.routeNo = routeNo;
}

public String getStartNodeNm() {
	return startNodeNm;
}

public void setStartNodeNm(String startNodeNm) {
	this.startNodeNm = startNodeNm;
}

public String getEndNodeNm() {
	return endNodeNm;
}

public void setEndNodeNm(String endNodeNm) {
	this.endNodeNm = endNodeNm;
}

public String getRouteId() {
	return routeId;
}

public void setRouteId(String routeId) {
	this.routeId = routeId;
}

public String getCityCode() {
	return cityCode;
}

public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
}

}
