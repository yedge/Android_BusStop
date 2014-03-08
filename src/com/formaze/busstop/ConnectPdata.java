package com.formaze.busstop;

import java.io.InputStream;
import java.net.URL;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ConnectPdata {
	 public String run() {
	    String out = "";
	    	        
		    try {
			   	String defualtAddr = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getCtyCodeList?ServiceKey=nQMV963b4xCHjoSLPmlm8%2Ba97NsKB3am7tv%2FitB4WVbAMAjnhtQCfIWimDj0rhO4FzPOpWbYg2NzorGtcWlSiQ%3D%3D&id=SC-OA-26-14";
		
				System.out.println("최종전송 :: " + defualtAddr);
				
				URL url = new URL(defualtAddr);
				
				InputStream in = url.openStream();
				
				//------------------------------------------------------------//
				
				XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();
				
				parser.setInput(in, null);
				//int parserEvent = 
				// init test
				System.out.println("There is no Value\n");
						
				
				//-----------------------------------------------------------//

				CachedOutputStream bos = new CachedOutputStream();
				
				IOUtils.copy(in, bos);
				
				
				in.close();
				bos.close();
				out = bos.getOut().toString();
				System.out.println("원천결과 :: ");
		    
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("에러메세지"+e.getMessage().toString());
		    }
		    return out;
    }
}
