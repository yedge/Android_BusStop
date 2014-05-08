package com.formaze.busstop;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class busInfoListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<BusInfoListBean> items;

	LayoutInflater layoutInflater;
	int layout;

	public busInfoListAdapter(Context context, int layout, ArrayList<BusInfoListBean> items){
		
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
		TextView routeNo = (TextView)convertView.findViewById(R.id.routeNo);
		TextView startNodeNm = (TextView)convertView.findViewById(R.id.startNodeNm);
		TextView endNodeNm = (TextView)convertView.findViewById(R.id.endNodeNm);
		
		routeNo.setText(items.get(position).getRouteNo());
		startNodeNm.setText(items.get(position).getStartNodeNm());
		endNodeNm.setText(items.get(position).getEndNodeNm());
		
		convertView.setTag(position);
		convertView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int position = (Integer) v.getTag();
				BusInfoListBean item = (BusInfoListBean)getItem(position);
				Bundle extras = new Bundle();
				extras.putString("routeId", item.getRouteId());
				extras.putString("cityCode", item.getCityCode());
				Intent intent = new Intent(context, BusRtActivity.class);
				intent.putExtras(extras);
				context.startActivity(intent);
			}
		});
		
		return convertView;
	}

}
