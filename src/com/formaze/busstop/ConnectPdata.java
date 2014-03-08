package com.formaze.busstop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ConnectPdata {
	
	 public String runCtyCodeList() {
	    
		String out = "";
	    
		    try {
			   	String defualtAddr = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getCtyCodeList?ServiceKey=nQMV963b4xCHjoSLPmlm8%2Ba97NsKB3am7tv%2FitB4WVbAMAjnhtQCfIWimDj0rhO4FzPOpWbYg2NzorGtcWlSiQ%3D%3D&id=SC-OA-26-14";
		
				System.out.println("최종전송 :: " + defualtAddr);
				
				URL url = new URL(defualtAddr);
				
				InputStream in = url.openStream();
				

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				
				in.close();
				
				out = bos.getOut().toString();
				System.out.println("원천결과 :: "+out);

				//------------------------------------------------------------//
				
				String cityCodeName = new String();
				
				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();
				
				parser.setInput(bos.getInputStream(), null);
				
				int parserEvent = parser.getEventType();
				
				System.out.println(" 파서이벤트 :: "+parserEvent);
						
				while (parserEvent != XmlPullParser.END_DOCUMENT) {
					switch(parserEvent) {
						case XmlPullParser.START_TAG:
							String tag = parser.getName();
							if(tag.compareTo("resultCode") == 0) {
									String resultCode = parser.nextText();
									System.out.println(" ###resultCode### :: " + resultCode);
							}
							
							if(tag.compareTo("citycode") == 0) {
								String resultCode1 = parser.nextText();
								System.out.println(" ###citycode### :: " + resultCode1);
							}
							if(tag.compareTo("cityname") == 0) {
								String cityname = parser.nextText();
								System.out.println(" ###cityname### :: " + cityname);
							}							
							
							
						break;
					}
				
					parserEvent = parser.next();
				}
					
			
						/*case XmlPullParser.START_TAG:
						String tag = parser.getName();

						if (tag.compareTo("resultCode") == 0){

							String resultCode = parser.getAttributeValue(null, "tx");
							System.out.println(" ###resultCode### :: " + resultCode);
						}
						break;
					}
					parserEvent = parser.next();
				}*/
				//-----------------------------------------------------------//

				bos.close();
		    } catch (XmlPullParserException e) {
				e.printStackTrace();
				System.out.println("풀파서에러 :: "+e.getMessage().toString());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		    }
		    return out;
    }
}
