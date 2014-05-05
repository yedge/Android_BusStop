package com.formaze.busstop;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class busRtListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<BusRtListBean> items;

	LayoutInflater layoutInflater;
	int layout;

	public busRtListAdapter(Context context, int layout, ArrayList<BusRtListBean> items){
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
		TextView routeTp = (TextView)convertView.findViewById(R.id.routeTp);
		
		nodeOrd.setText(items.get(position).getNodeOrd());
		nodeNm.setText(items.get(position).getNodeNm());
		routeTp.setText(items.get(position).getRouteTp());

		return convertView;
	}
}
