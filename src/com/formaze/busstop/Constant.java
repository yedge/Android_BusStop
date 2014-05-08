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
public static final String functionLv1_BusInfo = "BusRouteInfoInqireService";

public static final String functionLv2_BusRtPosition = "getRouteAcctoBusLcList";
public static final String functionLv2_BusNoList = "getRouteNoList";
public static final String functionLv2_BusSttnList = "getRouteAcctoThrghSttnList";
public static final String functionLv2_RouteInfoList = "getRouteInfoIem";

public static final String serviceKey_Bus_Dev = "hRUxCAsRrgYuNTi2DmMCM/OADts6RhuxyK6ZqS/EX9G4+YyhMONaFyfGrSWbn/3p/R6tnNmp4VS9V3RkYTtjZA==";
//인코딩 : hRUxCAsRrgYuNTi2DmMCM%2FOADts6RhuxyK6ZqS%2FEX9G4%2BYyhMONaFyfGrSWbn%2F3p%2FR6tnNmp4VS9V3RkYTtjZA%3D%3D
public static final String serviceId_BusInfo_Dev = "SC-OA-26-12";
public static final String serviceId_BusRt_Dev = "SC-OA-26-14";

public static final String msg_busCd_empty = "버스번호를 입력해 주십시오.";
public static final String msg_busListRslt_empty = "버스 조회결과가 존재하지 않습니다.";
public static final String msg_infoListRslt_empty = "버스기본정보 조회결과가 존재하지 않습니다.";
public static final String msg_sttnListRslt_empty = "버스정류소 조회결과가 존재하지 않습니다.";
public static final String msg_rtListRslt_empty = "버스실시간정보 조회결과가 존재하지 않습니다.";
public static final String msg_busCp_ok = "버스가 이곳을 지나가고 있습니다.";
}
