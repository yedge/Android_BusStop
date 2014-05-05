package com.formaze.busstop;

public class BusSttnListBean {
	String nodeOrd;
	String nodeNm;
	String routeId;
	String nodeId;

	public BusSttnListBean() {
		
	}

	public BusSttnListBean(String nodeOrd, String nodeNm) {
		this.nodeOrd = nodeOrd;
		this.nodeNm = nodeNm;
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

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
}
