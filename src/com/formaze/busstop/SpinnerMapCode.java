package com.formaze.busstop;

public class SpinnerMapCode {

	public String getCityCode (int cityIdx) {
		String out = "";
		String[] cityCd = Constant.cityCode;
		out = cityCd[cityIdx];
		return out;
	}
}
