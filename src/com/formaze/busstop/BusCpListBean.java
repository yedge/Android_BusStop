package com.formaze.busstop;

public class BusCpListBean {
	String nodeOrd;
	String nodeNm;
	String nodeId;
	Boolean setPostion = false;
	Integer busCnt = 0;
	
	public BusCpListBean() {
		
	}

	public BusCpListBean(String nodeOrd, String nodeNm, Boolean setPostion, Integer busCnt) {
		this.nodeOrd = nodeOrd;
		this.nodeNm = nodeNm;
		this.setPostion = setPostion;
		this.busCnt = busCnt;
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public Boolean getSetPostion() {
		return setPostion;
	}

	public void setSetPostion(Boolean setPostion) {
		this.setPostion = setPostion;
	}

	public Integer getBusCnt() {
		return busCnt;
	}

	public void setBusCnt(Integer busCnt) {
		this.busCnt = busCnt;
	}

}
