package com.formaze.busstop;

public class BusRtListBean {
	String nodeOrd;
	String nodeNm;
	String routeTp;
	String nodeId;
	String gpsLati;
	String gpsLong;

	public BusRtListBean() {
		
	}

	public BusRtListBean(String nodeOrd, String nodeNm, String routeTp) {
		this.nodeOrd = nodeOrd;
		this.nodeNm = nodeNm;
		this.routeTp = routeTp;
	}

	public String getNodeOrd() {
		return nodeOrd;
	}

	public void setNodeOrd(String nodeOrd) {
		this.nodeOrd = nodeOrd;
	}

	public String getNodeNm() {
		return nodeNm;
	}

	public void setNodeNm(String nodeNm) {
		this.nodeNm = nodeNm;
	}

	public String getRouteTp() {
		return routeTp;
	}

	public void setRouteTp(String routeTp) {
		this.routeTp = routeTp;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getGpsLati() {
		return gpsLati;
	}

	public void setGpsLati(String gpsLati) {
		this.gpsLati = gpsLati;
	}

	public String getGpsLong() {
		return gpsLong;
	}

	public void setGpsLong(String gpsLong) {
		this.gpsLong = gpsLong;
	}

	
}
