package com.formaze.busstop;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BusCpListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<BusCpListBean> items;

	LayoutInflater layoutInflater;
	int layout;

	public BusCpListAdapter(Context context, int layout, ArrayList<BusCpListBean> items){
		this.context = context;
		this.items = items;
		layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		this.layout = layout;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}
	
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			convertView = layoutInflater.inflate(layout, parent, false);
		}
		TextView nodeOrd = (TextView)convertView.findViewById(R.id.nodeOrd);
		TextView nodeNm = (TextView)convertView.findViewById(R.id.nodeNm);
		TextView positionSet = (TextView)convertView.findViewById(R.id.positionSet);
		
		nodeOrd.setText(items.get(position).getNodeOrd());
		nodeNm.setText(items.get(position).getNodeNm());

		String pSet = "";
		Boolean setYn = items.get(position).getSetPostion();
		if (setYn) {
			pSet = Constant.msg_busCp_ok;
		}
		positionSet.setText(pSet);
		
		return convertView;
	}
}
