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
	  * �����ڵ�� ������ȣ�� ���������� ��ȸ�Ѵ�
	  **/
	 public ConnectPdataIO runGetRouteNoList(String busNum, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** ȣ�� URL ���� callUrl
		 * URL : ���� apiȣ�� url(����,�����) + ����api����ȣ���Լ�Lv1 + ����api����ȣ���Լ�Lv2
		 * ��� : ����Ű(����,�����) + ���񽺾��̵�
		 * ������ : ������ȣ busNum + �����ڵ� cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_BusNoList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusInfo_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeNo=" + busNum;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** �Լ���´�� outIo
		 * ��� : ����ڵ�
		 * List : �뼱ID, �뼱��ȣ, �뼱����, ����, ����, �����ð�, ù���ð�
		 ***/
				System.out.println("�������� :: " + callUrl);
				
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

				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//����� ����
				String resultCode = (String) result.get("resultCode");

//				<tr>
//					<td>���ƮŸ��</td>
//					<td>��Ƽ</td>
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
		    	System.out.println("�����޼���"+e.getMessage().toString());
		 } finally {
			 
		 }
		    return outIo;
    }

    /**
	 * �����ڵ�� �������̵�� �����ǽð������� ��ȸ�Ѵ�
	 **/
	public ConnectPdataIO runGetRouteAcctoBusLcList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** ȣ�� URL ���� callUrl
		 * URL : ���� apiȣ�� url(����,�����) + ����api����ȣ���Լ�Lv1 + ����api����ȣ���Լ�Lv2
		 * ��� : ����Ű(����,�����) + ���񽺾��̵�
		 * ������ : �����ڵ� busId + �����ڵ� cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusRt + "/" + Constant.functionLv2_BusRtPosition;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** �Լ���´�� outIo
		 * ��� : ����ڵ�
		 * List : �뼱��ȣ, �ʸ�ĪX��ǥ, �ʸ�ĪY��ǥ, ������ ����, �����Ҹ�, ������ID, �뼱����
		 ***/
				System.out.println("�������� :: " + callUrl);
				
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

				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//����� ����
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
		    	System.out.println("�����޼���"+e.getMessage().toString());
		    } finally {
			}
		    return outIo;
    }
	
    /**
	 * �����ڵ�� �������̵�� ���������������� ��ȸ�Ѵ�
	 **/
	public ConnectPdataIO runGetRouteAcctoThrghSttnList(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** ȣ�� URL ���� callUrl
		 * URL : ���� apiȣ�� url(����,�����) + ����api����ȣ���Լ�Lv1 + ����api����ȣ���Լ�Lv2
		 * ��� : ����Ű(����,�����) + ���񽺾��̵�
		 * ������ : �����ڵ� busId + �����ڵ� cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_BusSttnList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** �Լ���´�� outIo
		 * ��� : ����ڵ�
		 * List : �뼱ID, ������ID, �����Ҹ�, ������ ����
		 ***/
				System.out.println("�������� :: " + callUrl);
				
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

				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//����� ����
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
		    	System.out.println("�����޼���"+e.getMessage().toString());
	    } finally {
		}
	    return outIo;
    }
	
    /**
	 * �����ڵ�� �������̵�� �����⺻������ ��ȸ�Ѵ�
	 **/
	public ConnectPdataIO runGetRouteInfoIem(String busId, String cityCd) {

		 ConnectPdataIO outIo = new ConnectPdataIO();

		 try {
		/*** ȣ�� URL ���� callUrl
		 * URL : ���� apiȣ�� url(����,�����) + ����api����ȣ���Լ�Lv1 + ����api����ȣ���Լ�Lv2
		 * ��� : ����Ű(����,�����) + ���񽺾��̵�
		 * ������ : �����ڵ� busId + �����ڵ� cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusInfo + "/" + Constant.functionLv2_RouteInfoList;
		 String callHeader = "?ServiceKey=" + URLEncoder.encode(Constant.serviceKey_Bus_Dev, "UTF-8") + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** �Լ���´�� outIo
		 * ��� : ����ڵ�
		 * List : �뼱ID, �뼱��ȣ, �뼱����, ����, ����, �����ð�, ù���ð�, ��������(����), ��������(�����), ��������(�Ͽ���)
		 ***/
				System.out.println("�������� :: " + callUrl);
				
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

				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				parser.parse(dis, saxHandler);
				//------------------------------------------------------------//
				bos.close();

				//����� ����
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
		    	System.out.println("�����޼���"+e.getMessage().toString());
	    } finally {
		}
	    return outIo;
    }
}
