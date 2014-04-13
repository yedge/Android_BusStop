package com.formaze.busstop;

import java.util.ArrayList;
import java.util.List;

public class runGetRouteAcctoBusLcListIO {
	String resultCode;
	List<String> routeNmList = new ArrayList<String>();

	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public List<String> getRouteNmList() {
		return routeNmList;
	}

	public void setRouteNmList(List<String> routeNmList) {
		this.routeNmList = routeNmList;
	}
	
}
