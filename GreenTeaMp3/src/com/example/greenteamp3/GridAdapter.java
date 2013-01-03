package com.example.greenteamp3;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	ArrayList<Item> mData = new ArrayList<Item>();
	int mColor = Color.BLACK;
	int mSize = 18;
	int mPadding = 1;
	private Context mContext;
	
	
	public GridAdapter(Context context, ArrayList<Item> data) {
		mContext = context; 
		this.mData=data;
		this.notifyDataSetChanged();
	}

	
	public int getCount() {
		if (mData == null)
			return  0;
		return mData.size();
	}

	public Item getItem(int position) {
		return mData.get(position);		
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
    	if (convertView == null) {
    		LayoutInflater li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = li.inflate(R.layout.adapter_item, null); 

    		holder = new ViewHolder();
    		holder.icon = (ImageView) convertView.findViewById(R.id.imageView1);
    		holder.show1 = (TextView) convertView.findViewById(R.id.textView1);
    		holder.show2 = (TextView) convertView.findViewById(R.id.textView2);
    		
    		convertView.setTag(holder);
    	} 
    	else {
    		holder = (ViewHolder) convertView.getTag();
    	}

		holder.icon.setBackgroundResource(getItem(position).icon);
		holder.show1.setText(getItem(position).show1);
		holder.show1.setTextSize(mSize);
		holder.show1.setTextColor(mColor);
		holder.show1.setPadding(mPadding, mPadding, mPadding, mPadding);
		
		holder.show2.setText(getItem(position).show2);
		holder.show2.setTextColor(mColor);
		holder.show2.setVisibility(View.VISIBLE);
		
		return convertView;
	}
	
	public void setData(ArrayList<Item> data) {
		this.mData = data;
		this.notifyDataSetChanged();
	}
	
	public void setFontColor(String color) {
		mColor =  Color.parseColor(color);
	}

	public void setFontSize(int size) {
		mSize = size;
	}
	
	public void setPadding(int padding) {
		mPadding = padding;
	}	
	
	// This is a static class to hold the UI elements, that can be reused
	public static class ViewHolder {
		ImageView icon;
		TextView show1;
		TextView show2;
	}
}
