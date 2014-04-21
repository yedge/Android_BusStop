package com.formaze.busstop;

public interface Constant {
public static final String cityNm[] = {"서울","부산","대구","인천","대전","울산","경기","제주","광주","춘천/홍천","원주","청주",
								  	   "천안","아산","전주","군산","여수","순천","광양","포항","구미","경산","창원","진주","통영",
								  	   "김해","밀양","거제","양산"};
public static final String cityCode[] = {"11","21","22","23","25","26","31","39","31250","32010","32020","33010","34010","34040",
										 "35010","35020","36020","36030","36060","37010","37050","37100","38010","38030","38050",
										 "38070","38080","38090","38100"};
public static final String openUrl_Dev = "http://openapi.tago.go.kr/openapi/service";
public static final String functionLv1_BusRt = "BusLcInfoInqireService";
public static final String functionLv2_BusRtPosition = "getRouteAcctoBusLcList";
public static final String serviceKey_BusRt_Dev = "hRUxCAsRrgYuNTi2DmMCM/OADts6RhuxyK6ZqS/EX9G4+YyhMONaFyfGrSWbn/3p/R6tnNmp4VS9V3RkYTtjZA==";
public static final String serviceId_BusRt_Dev = "SC-OA-26-14";

public static final String busRtPosition_outColNm[] = {"routeNm", "gpsLati", "gpsLong", "nodeOrd", "nodeNm", "nodeId", "routeTp"};
}
