package com.formaze.busstop;

public interface Constant {
public static final String cityNm[] = {"����","�λ�","�뱸","��õ","����","���","���","����","����","��õ/ȫõ","����","û��",
								  	   "õ��","�ƻ�","����","����","����","��õ","����","����","����","���","â��","����","�뿵",
								  	   "����","�о�","����","���"};
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
//���ڵ� : hRUxCAsRrgYuNTi2DmMCM%2FOADts6RhuxyK6ZqS%2FEX9G4%2BYyhMONaFyfGrSWbn%2F3p%2FR6tnNmp4VS9V3RkYTtjZA%3D%3D
public static final String serviceId_BusInfo_Dev = "SC-OA-26-12";
public static final String serviceId_BusRt_Dev = "SC-OA-26-14";

public static final String msg_busCd_empty = "������ȣ�� �Է��� �ֽʽÿ�.";
public static final String msg_busListRslt_empty = "���� ��ȸ����� �������� �ʽ��ϴ�.";
public static final String msg_infoListRslt_empty = "�����⺻���� ��ȸ����� �������� �ʽ��ϴ�.";
public static final String msg_sttnListRslt_empty = "���������� ��ȸ����� �������� �ʽ��ϴ�.";
public static final String msg_rtListRslt_empty = "�����ǽð����� ��ȸ����� �������� �ʽ��ϴ�.";
public static final String msg_busCp_ok = "������ �̰��� �������� �ֽ��ϴ�.";
}
