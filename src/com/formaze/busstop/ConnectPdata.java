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
		/*** ȣ�� URL ���� callUrl
		 * URL : ���� apiȣ�� url(����,�����) + ����api����ȣ���Լ�Lv1 + ����api����ȣ���Լ�Lv2
		 * ��� : ����Ű(����,�����) + ���񽺾��̵�
		 * ������ : �����ڵ� busId + �����ڵ� cityCd
		 ***/
		 String callAddr = Constant.openUrl_Dev + "/" + Constant.functionLv1_BusRt + "/" + Constant.functionLv2_BusRtPosition;
		 String callHeader = "?ServiceKey=" + Constant.serviceKey_BusRt_Dev + "&id=" + Constant.serviceId_BusRt_Dev;
		 String callData = "&cityCode=" + cityCd + "&routeId=" + busId;
			System.out.println("callAddr :: " + callAddr);
			System.out.println("callHeader :: " + callHeader);
			System.out.println("callData :: " + callData);
		 String callUrl = callAddr + callHeader + callData;
		/*** �Լ���´�� outIo
		 * ��� : ����ڵ�
		 * List : �뼱��ȣ, �ʸ�ĪX��ǥ, �ʸ�ĪY��ǥ, ������ ����, �����Ҹ�, ������ID, �뼱����
		 ***/
		 runGetRouteAcctoBusLcListIO outIo = new runGetRouteAcctoBusLcListIO();
		 //����ڵ�
		 String resultCode = "";
		 //��°�ü(����Ʈ�� ���ο��� �ε����� ������� ����)
		 List<String> routeNmList = new ArrayList<String>();
		 List<String> gpsLatiList = new ArrayList<String>();
		 List<String> gpsLongList = new ArrayList<String>();
		 List<String> nodeOrdList = new ArrayList<String>();
		 List<String> nodeNmList = new ArrayList<String>();
		 List<String> nodeIdList = new ArrayList<String>();
		 List<String> routeTpList = new ArrayList<String>();
		 //
	    
		    try {
		
				System.out.println("�������� :: " + callUrl);
				
				URL url = new URL(callUrl);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				in.close();

				System.out.println("��õ��� :: "+bos.getOut().toString());

				//------------------------------------------------------------//

				
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();
				
				Map<String, Object> result = new HashMap<String, Object>();
				System.out.println("�ļ�����");
				GoDataSAXHandler saxHandler = new GoDataSAXHandler(Constant.busRtPosition_outColNm, result , "response");
				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				DataInputStream dis= new DataInputStream(bos.getInputStream());
				System.out.println("�ļ������մϴ�");
				parser.parse(dis, saxHandler);
				

				// ���� XML�� Parser�� ����Ͽ� �Ľ�
				
				
				//------------------------------------------------------------//

				
				
//				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
//				XmlPullParser parser = parserCreator.newPullParser();
//				
//				parser.setInput(bos.getInputStream(), null);
//				
//				int parserEvent = parser.getEventType();
//				
//				System.out.println(" �ļ��̺�Ʈ :: "+parserEvent);
//				boolean itemFlag = false;		
//				while (parserEvent != XmlPullParser.END_DOCUMENT) {
//					switch(parserEvent) {
//						case XmlPullParser.START_TAG:
//							String tag = parser.getName();
//							
//							//����Ľ�
//							if(tag.compareTo("resultCode") == 0) {
//									resultCode = parser.nextText();
//									System.out.println(" ###resultCode### :: " + resultCode);
//									if (!"00".equals(resultCode)) {
//										System.out.println(" ###�������������###");
//										return outIo;
//									}
//							}
//							
//							//�������Ľ�
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
//				System.out.println("Ǯ�ļ����� :: "+e.getMessage().toString());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("�����޼���"+e.getMessage().toString());
		    } finally {
			}
		    return outIo;
    }
}
