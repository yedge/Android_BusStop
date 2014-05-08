package com.formaze.busstop;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;

public class ConnectPdata {
	
	 /**
	  * 도시코드와 버스번호로 버스정보를 조회한다
	  **/
	 public ConnectPdataIO runGetRouteNoList(String busNum, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** 호출 URL 구성 callUrl
		 * URL : 공공 api호출 url(개발,운영구분) + 공공api서비스호출함수Lv1 + 공공api서비스호출함수Lv2
		 * 헤더 : 서비스키(개발,운영구분) + 서비스아이디
		 * 데이터 : 버스번호 busNum + 지역코드 cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_BusNoList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusInfo_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeNo=" + busNum;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** 함수출력대상 outIo
		 * 헤더 : 결과코드
		 * List : 노선ID, 노선번호, 노선유형, 종점, 기점, 막차시간, 첫차시간
		 ***/
				System.out.println("최종전송 :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				//------------------------------------------------------------//
				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				ArrayList list = new ArrayList();
				String[] outColNm = {"item"};
				
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(outColNm, result , "response");

				// 전문 XML을 Parser를 사용하여 파싱
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//결과값 셋팅
				String resultCode = (String) result.get("resultCode");

//				<tr>
//					<td>라우트타입</td>
//					<td>라티</td>
//				</tr>
//<%
				ArrayList<BusInfoListBean> busInfoList = new ArrayList<BusInfoListBean>();
				BusInfoListBean infoBean = new BusInfoListBean();
				list = (ArrayList) result.get("item");
				for(int i=0; i<list.size(); i++) {					
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap = (Map) list.get(i);
					infoBean = new BusInfoListBean();
					infoBean.setRouteNo(itemMap.get("routeno").toString());
					infoBean.setRouteId(itemMap.get("routeid").toString());
					infoBean.setStartNodeNm(itemMap.get("startnodenm").toString());
					infoBean.setEndNodeNm(itemMap.get("endnodenm").toString());
					infoBean.setCityCode(cityCd);
					busInfoList.add(infoBean);
//%>
//					<tr>
//						<td><%=itemMap.get("routeType")%></td>
//						<td><%=itemMap.get("gpslati")%></td>
//					</tr>
//<%
				}
//%>
				outIo.setResultCode(resultCode);
				outIo.setBusInfoList(busInfoList);
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }

    /**
	 * 도시코드와 버스아이디로 버스실시간정보를 조회한다
	 **/
	public ConnectPdataIO runGetRouteAcctoBusLcList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** 호출 URL 구성 callUrl
		 * URL : 공공 api호출 url(개발,운영구분) + 공공api서비스호출함수Lv1 + 공공api서비스호출함수Lv2
		 * 헤더 : 서비스키(개발,운영구분) + 서비스아이디
		 * 데이터 : 버스코드 busId + 지역코드 cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusRt + "/" + Constant.functionLv2_BusRtPosition;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** 함수출력대상 outIo
		 * 헤더 : 결과코드
		 * List : 노선번호, 맵매칭X좌표, 맵매칭Y좌표, 정류소 순서, 정류소명, 정류소ID, 노선유형
		 ***/
				System.out.println("최종전송 :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				//------------------------------------------------------------//
				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				ArrayList list = new ArrayList();
				String[] outColNm = {"item"};
				
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(outColNm, result , "response");

				// 전문 XML을 Parser를 사용하여 파싱
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//결과값 셋팅
				String resultCode = (String) result.get("resultCode");

				ArrayList<BusRtListBean> busRtList = new ArrayList<BusRtListBean>();
				BusRtListBean rtBean = new BusRtListBean();
				list = (ArrayList) result.get("item");
				for(int i=0; i<list.size(); i++) {					
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap = (Map) list.get(i);
					rtBean = new BusRtListBean();
					rtBean.setNodeOrd(itemMap.get("nodeord").toString());
					rtBean.setNodeNm(itemMap.get("nodenm").toString());
					rtBean.setRouteTp(itemMap.get("routetp").toString());
					rtBean.setNodeId(itemMap.get("nodeid").toString());
					rtBean.setGpsLati(itemMap.get("gpslati").toString());
					rtBean.setGpsLong(itemMap.get("gpslong").toString());
					busRtList.add(rtBean);
				}
				outIo.setResultCode(resultCode);
				outIo.setBusRtList(busRtList);
				
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		    } finally {
			}
		    return outIo;
    }
	
    /**
	 * 도시코드와 버스아이디로 버스정류소정보를 조회한다
	 **/
	public ConnectPdataIO runGetRouteAcctoThrghSttnList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** 호출 URL 구성 callUrl
		 * URL : 공공 api호출 url(개발,운영구분) + 공공api서비스호출함수Lv1 + 공공api서비스호출함수Lv2
		 * 헤더 : 서비스키(개발,운영구분) + 서비스아이디
		 * 데이터 : 버스코드 busId + 지역코드 cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_BusSttnList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** 함수출력대상 outIo
		 * 헤더 : 결과코드
		 * List : 노선ID, 정류소ID, 정류소명, 정류소 순번
		 ***/
				System.out.println("최종전송 :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				//------------------------------------------------------------//
				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				ArrayList list = new ArrayList();
				String[] outColNm = {"item"};
				
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(outColNm, result , "response");

				// 전문 XML을 Parser를 사용하여 파싱
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//결과값 셋팅
				String resultCode = (String) result.get("resultCode");

				ArrayList<BusSttnListBean> busSttnList = new ArrayList<BusSttnListBean>();
				BusSttnListBean sttnBean = new BusSttnListBean();
				list = (ArrayList) result.get("item");
				for(int i=0; i<list.size(); i++) {					
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap = (Map) list.get(i);
					sttnBean = new BusSttnListBean();
					sttnBean.setNodeOrd(itemMap.get("nodeord").toString());
					sttnBean.setNodeNm(itemMap.get("nodenm").toString());
					sttnBean.setNodeId(itemMap.get("nodeid").toString());
					sttnBean.setRouteId(itemMap.get("routeid").toString());
					busSttnList.add(sttnBean);
				}
				outIo.setResultCode(resultCode);
				outIo.setBusSttnList(busSttnList);
				
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
	    } finally {
		}
	    return outIo;
    }
	
    /**
	 * 도시코드와 버스아이디로 버스기본정보를 조회한다
	 **/
	public ConnectPdataIO runGetRouteInfoIem(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** 호출 URL 구성 callUrl
		 * URL : 공공 api호출 url(개발,운영구분) + 공공api서비스호출함수Lv1 + 공공api서비스호출함수Lv2
		 * 헤더 : 서비스키(개발,운영구분) + 서비스아이디
		 * 데이터 : 버스코드 busId + 지역코드 cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_RouteInfoList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** 함수출력대상 outIo
		 * 헤더 : 결과코드
		 * List : 노선ID, 노선번호, 노선유형, 종점, 기점, 막차시간, 첫차시간, 배차간격(평일), 배차간격(토요일), 배차간격(일요일)
		 ***/
				System.out.println("최종전송 :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				//------------------------------------------------------------//
				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				ArrayList list = new ArrayList();
				String[] outColNm = {"item"};
				
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(outColNm, result , "response");

				// 전문 XML을 Parser를 사용하여 파싱
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//결과값 셋팅
				String resultCode = (String) result.get("resultCode");

				ArrayList<RouteInfoListBean> routeInfoList = new ArrayList<RouteInfoListBean>();
				RouteInfoListBean infoBean = new RouteInfoListBean();
				list = (ArrayList) result.get("item");
				for(int i=0; i<list.size(); i++) {					
					Map<String, Object> itemMap = new HashMap<String, Object>();
					itemMap = (Map) list.get(i);
					infoBean = new RouteInfoListBean();
					infoBean.setRouteId(itemMap.get("routeid").toString());
					infoBean.setRouteNo(itemMap.get("routeno").toString());
					infoBean.setRouteTp(itemMap.get("routetp").toString());
					infoBean.setEndNodeNm(itemMap.get("endnodenm").toString());
					infoBean.setStartNodeNm(itemMap.get("startnodenm").toString());
					infoBean.setEndVehicleTime(itemMap.get("endvehicletime").toString());
					infoBean.setStartVehicleTime(itemMap.get("startvehicletime").toString());
					infoBean.setIntervalTime(itemMap.get("intervaltime").toString());
					infoBean.setIntervalSatTime(itemMap.get("intervalsattime").toString());
					infoBean.setIntervalSunTime(itemMap.get("intervalsuntime").toString());
					routeInfoList.add(infoBean);
				}
				outIo.setResultCode(resultCode);
				outIo.setRouteInfoList(routeInfoList);
				
		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
	    } finally {
		}
	    return outIo;
    }
}
