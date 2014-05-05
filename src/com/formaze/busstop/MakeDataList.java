package com.formaze.busstop;

import java.util.ArrayList;

public class MakeDataList {
	/**
	 * ����������, �����ǽð������� ��������ġ������ ����Ѵ�
	 **/
	 public ArrayList<BusCpListBean> runGetCurrentPositionBusList(ArrayList<BusRtListBean> busRtList, ArrayList<BusSttnListBean> busSttnList) {
		 
		 ArrayList<BusCpListBean> outList = new ArrayList<BusCpListBean>();
		 BusCpListBean cpBean = new BusCpListBean();
		 
		 try {
				
			 for (BusSttnListBean sttnBean : busSttnList) {
				 //����������->����������ġ�⺻����
				 cpBean = new BusCpListBean();
				 cpBean.setNodeOrd(sttnBean.getNodeOrd());
				 cpBean.setNodeId(sttnBean.getNodeId());
				 cpBean.setNodeNm(sttnBean.getNodeNm());
				 
				 Integer busCnt = 0;
				 //�ǽð�����->����������ġ �ΰ�����(��ġǥ��)
				 for (BusRtListBean rtBean : busRtList) {
					 if (rtBean.getNodeOrd().equals(sttnBean.getNodeOrd())) {
						 cpBean.setSetPostion(true);
						 busCnt = cpBean.getBusCnt();
						 busCnt++;
						 cpBean.setBusCnt(busCnt);
					 }
				 }
				 outList.add(cpBean);
			 }

		 } catch (Exception e) {
		    	e.printStackTrace();
		    	System.out.println("�����޼���"+e.getMessage().toString());
		 } finally {
			 
		 }
		 return outList;
    }
}
