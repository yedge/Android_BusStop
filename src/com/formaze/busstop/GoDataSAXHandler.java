package com.formaze.busstop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;

//@SuppressWarnings({ "rawtypes", "unchecked" })
public class GoDataSAXHandler extends DefaultHandler{

	private String[] dset = null;								// �����ͼ� ��Ī �迭
	private Map dsetInfo = new HashMap();		// �����ͼ� ������ �����ϴ� Map
	private boolean record = false;							// XML TAG�� ���ڵ����� ����
	private boolean dataset = false;						// XML TAG�� �����ͼ����� ����

	private String column = null;								// �÷���

	private StringBuffer value = new StringBuffer();		// �Ϲ����� �� ����

	private StringBuffer resultCode = new StringBuffer();	// ����/���� ���� [00:����, �׿�]

	private StringBuffer resultMsg = new StringBuffer();	// ����/���� ���� �޼���

	private String tagName = "";							// TAG��Ī

	private String serviceId = "";

	private boolean service = false;						// ���� ����.

	private Map row = null;

	private List ds = null;

	private Map fixRow = null;						// FIX�� ó���� �����ͼ�

	private List fixDs = null;

	/**
	 * XML������ �Ľ��Ͽ� ó���ϴ� XML �ڵ鷯
	 * @param dataSetKey	- �����ͼ��� ��Ī fcSingleSelect�� key���� �ش��ϴ� ����
	 * @param service TODO
	 */

	public GoDataSAXHandler(String[] dataSetKey, Map resultInfo, String service) {
		System.out.println("�����ͻ轺�ڵ鷯");
		//�����
		// ��°������� ��
		dsetInfo = resultInfo;
		// �����ͼ��� ������ ���
		dset = dataSetKey;
		// ����� ���̵� ã��:�������ۺ�
		serviceId = service;

		/** UI���� �Ѿ�� �����ͼ� ������ ��� �κ� **/
		for (int i=0;i<dset.length; i++) {
			dsetInfo.put(dataSetKey[i], new ArrayList());
		}
		System.out.println("�����ͻ轺�ڵ鷯�����ͼ�"+dsetInfo);
	}

	/**
	 * XML Document [����]
	 */
	 public void startDocument(){
			System.out.println("����ó��!!!");
		 value.setLength(0);	// �ʱ�ȭ
	 }

	 /**
	  * XML Document [��]
	  */
	 public void endDocument(){
			System.out.println("������!!!");
		 Iterator it = dsetInfo.keySet().iterator();

		 /** �����ͼ��� �����͸� Ŭ���̾�Ʈ ���� **/
		 while(it.hasNext()) {
			 System.out.println("it.hasNext()???");
			 String key = (String)it.next();
			 if ("fixData".equals(key)) {
				 List fix = (List)dsetInfo.get("fixData");
				 fix.add(fixRow);
			 }
		 }

		 /** ���� �޽��� �� ���� �޽����� ���� ó�� **/
		 String code = resultCode.toString().trim();
		 String msg = resultMsg.toString().trim();
		 
			 dsetInfo.put("resultCode",  code);
			 dsetInfo.put("resultMsg",  msg);

	 }

	 /**
	  * �Ľ��� ������Ʈ [����]
	  */
	 public void startElement(String namespaceURI, String localName, String qName, Attributes attrs){
			System.out.println("####��ŸƮ������Ʈ#### : "+namespaceURI+" : "+localName+" : "+qName+" : "+attrs);
		 value.setLength(0);
		 // ?
		 System.out.println("dataset ::: "+dataset);
		 System.out.println("record ::: "+record);
		 if (dataset && record) {
			 column = qName;
			 tagName = "";
		 } else {
			 tagName = qName;
		 }
		 System.out.println("column ::: " + column);
		 System.out.println("tagName ::: " + tagName);
		 /** XML�� TAG�� �����ͼ��� Key��Ī�� ��ġ�ϴ��� �Ǵ� **/
		 if (dsetInfo.containsKey(qName)) {
			 System.out.println("dsetInfo.containsKey(qName) !!!");
			 //ds�� �ش��÷��� �����ʹ�´� �Ƹ��� addrow
			 ds = (List)dsetInfo.get(qName);
			 dataset = true;
			 record = true;
			 row = new HashMap();
		 //�����
		 } else if (serviceId.equals(qName)) {
			 
			 System.out.println("####��ŸƮ������Ʈ:�������ۺ�####"); 
			 service = true;	// ������ ����.

		 }
	}

	 /**
	  * �Ľ��� ������Ʈ [��]
	  * - �����ͼ¿� FirstRow�� ����Ǿ� addDataRow�ϴ� ������ Ư�� ���ڵ� ������ �Է��̵Ǹ� Ŭ���̾�Ʈ�� ���� ����
	  */
	 public void endElement(String namesapceURI, String localName, String qName){
			System.out.println("���忤����Ʈ : "+namesapceURI+" : "+localName+" : "+qName);
		 if (dsetInfo.containsKey(qName)) {
			 System.out.println("##qName�� ����̸��� ����!##"+row);
			 dataset = false;
			 record = false;
			 column = null;
			 /** �÷��� ���̸� ������ ���ڵ带 �����ͼ¿� �߰� **/
			 ds.add(row);
		 } else if (service && !record && tagName.equals(qName)) {
			 System.out.println("???????????????");
			 // �����ο� ���� �߰�.
			 if (fixDs != null) {
				 fixRow.put(qName, value.toString());
			 }
			 value.setLength(0);
	 	 } else {
			 /** ������ ���ڵ�(IXyncDataRow)�� ���� �Է� **/
	 		 if (" ".equals(value.toString()))  {
	 			 value.trimToSize();
	 		 }
			 if(value.length() != 0){
				 if (record && dataset){
					row.put(column, value.toString());
					 value.setLength(0);
					 value.trimToSize();
				 }
			 }
		 }
	 }

	 /**
	  * XML Element���� Text�� �Ľ��Ͽ� ��� �κ�
	  */
	 public void characters(char[] ch, int start, int length){
			System.out.println("ĳ���� : "+ch+" : "+start+" : "+length);
			System.out.println("ĳ���ͽ�Ʈ�� : "+ new String(ch, start, length));
			System.out.println("dataset : "+dataset + " record : " + record);
			//tagName���ϵ����� Ȥ�� ���ⵥ���ͽ��� column���ⵥ����
		 if (dataset && record) {
			 System.out.println("�÷����������� ok");
			 String str = new String(ch, start, length);
			 value.append(str);
	     //���ϴ� ���ϵ�����ó���� �ַ� ���
		//������Ű���ڵ�	 
		 } else if ("resultCode".equals(tagName)) {
			 System.out.println("resultCode ����������!");
			 String str = new String(ch, start, length);
			 resultCode.append(str.trim());
			 //������Ű���޼���
		 } else if ("resultMsg".equals(tagName)) {
			 System.out.println("resultMsg ����������!");
			 String str = new String(ch, start, length);
			 resultMsg.append(str.trim());

		 //���ϴ� ����Ҽ������Ҽ���
//		 } else if ("messageCode".equals(tagName)) {
//			 String str = new String(ch, start, length);
//			 messageCode.append(str);
//		 } else if ("messages".equals(tagName)) {
//			 String str = new String(ch, start, length);
//			 messages.append(str);
//		 } else if ("traceMessage".equals(tagName)) {
//			 String str = new String(ch, start, length);
//			 traceMessage.append(str);
			 
			 //���񽺽��۵̰� �ݺ��ΰ� �ƴ� ������ ������ ����ܰ���
		 } else if (service && !dataset) {
			 System.out.println("service && !dataset ����������!");
			 String str = new String(ch, start, length);
			 value.append(str.trim());
		 }

	 }
}
