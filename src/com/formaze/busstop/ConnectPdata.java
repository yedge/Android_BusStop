package com.formaze.busstop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ConnectPdata {
	
	 public runGetRouteAcctoBusLcListIO runGetRouteAcctoBusLcList(String busId, String cityCd) {
		//List list = new ArrayList();
	    //HashMap outMap = new HashMap();
	    
	    //for(int i = 0; i<items.length(); i++) {
	    //	outMap.get("gpsati", items.get(i).get("gpsati"));	    	
	    	
	    //	list.add(outMap);
	    //}
	    
	    //Map aaa = (Map) list.get(0);
		 cityCd = "25";
		 busId = "DJB30300052ND";
		/*** 호출 URL 구성 callUrl
		 * URL : 공공 api호출 url(개발,운영구분) + 공공api서비스호출함수Lv1 + 공공api서비스호출함수Lv2
		 * 헤더 : 서비스키(개발,운영구분) + 서비스아이디
		 * 데이터 : 버스코드 busId + 지역코드 cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusRt + "/" + Constant.functionLv2_BusRtPosition;
		 String callHeader = "?ServiceKey=" + Constant.serviceKey_BusRt_Dev + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** 함수출력대상 outIo
		 * 헤더 : 결과코드
		 * List : 노선번호, 맵매칭X좌표, 맵매칭Y좌표, 정류소 순서, 정류소명, 정류소ID, 노선유형
		 ***/
		 runGetRouteAcctoBusLcListIO outIo = new runGetRouteAcctoBusLcListIO();
		 //결과코드
		 String resultCode = "";
		 //출력객체(리스트가 따로여도 인덱스를 순서대로 넣음)
		 List<String> routeNmList = new ArrayList<String>();
		 List<String> gpsLatiList = new ArrayList<String>();
		 List<String> gpsLongList = new ArrayList<String>();
		 List<String> nodeOrdList = new ArrayList<String>();
		 List<String> nodeNmList = new ArrayList<String>();
		 List<String> nodeIdList = new ArrayList<String>();
		 List<String> routeTpList = new ArrayList<String>();
		 //
	    
		    try {
		
				System.out.println("최종전송 :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				System.out.println("원천결과 :: "+bos.getOut().toString());

				//------------------------------------------------------------//

				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				System.out.println("파서선언");
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(Constant.busRtPosition_outColNm, result , "response");
				// 전문 XML을 Parser를 사용하여 파싱
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				System.out.println("파서진입합니당");
				parser.parse(dis, saxHandler);
				

				// 전문 XML을 Parser를 사용하여 파싱
				
				
				//------------------------------------------------------------//

				
				
//				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
//				XmlPullParser parser = parserCreator.newPullParser();
//				
//				parser.setInput(bos.getInputStream(), null);
//				
//				int parserEvent = parser.getEventType();
//				
//				System.out.println(" 파서이벤트 :: "+parserEvent);
//				boolean itemFlag = false;		
//				while (parserEvent != XmlPullParser.END_DOCUMENT) {
//					switch(parserEvent) {
//						case XmlPullParser.START_TAG:
//							String tag = parser.getName();
//							
//							//헤더파싱
//							if(tag.compareTo("resultCode") == 0) {
//									resultCode = parser.nextText();
//									System.out.println(" ###resultCode### :: " + resultCode);
//									if (!"00".equals(resultCode)) {
//										System.out.println(" ###전문결과비정상###");
//										return outIo;
//									}
//							}
//							
//							//데이터파싱
//							if (tag.compareTo("items") == 0) {
//								itemFlag = true;
//							}
//							
//							String[] outDataColList = Constant.busRtPosition_outColNm; 
//							for (int i=0; i < outDataColList.length; i++) {
//								if(tag.compareTo(outDataColList[i]) == 0) {
//									String resultCode1 = parser.nextText();
//									System.out.println(" ###citycode### :: " + resultCode1);
//								}
//							}
//
//						break;
//					}
//				
//					parserEvent = parser.next();
//				}
					

				bos.close();
//		    } catch (XmlPullParserException e) {
//				e.printStackTrace();
//				System.out.println("풀파서에러 :: "+e.getMessage().toString());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		    } finally {
			}
		    return outIo;
    }
}
