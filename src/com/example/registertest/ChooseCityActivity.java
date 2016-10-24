package com.example.registertest;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseCityActivity extends ExpandableListActivity
	{
	private String[ ]provinces=new String[ ]{"江西","江苏","浙江"};
	private String[ ][ ]cities=new String[ ][ ]{{"南昌","九江","赣州","吉安"},{"南京","苏州","无锡","扬州"},
			{"杭州","温州","台州","金华"}};
	public void onCreate(Bundle savadInstanceState){
		super.onCreate(savadInstanceState);
		ExpandableListAdapter adapter=new BaseExpandableListAdapter(){
			public Object getChild(int groupPosition,int childPosition){
				     //获取指定组位置的指定子列表项数据
			 return cities [groupPosition][childPosition];
			}
			public long getChildId(int groupPosition,int childPosition){
				return childPosition;
			}
			
			private TextView getTextView(){
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,64);
				TextView textView=new TextView(ChooseCityActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
				textView.setPadding(36,0,0,0);
				textView.setTextSize(20);
				return textView;
             }
			public View getChildView(int groupPositon,int childPosition,boolean isLastChild,View convertView,ViewGroup parent){
				//该方法决定每个子选项的外观
				TextView textView=getTextView();
				textView.setText(getChild (groupPositon,childPosition).toString());
				return textView;
			}
			public Object getGroup(int groupPosition){
				//获取指定位置处的组数据
				return provinces[groupPosition];
				
			}
			public int getGroupCount(){
				//获取该扩展表的组数，级省份数
				return provinces.length;
			}
			public long getGroupId(int groupPosition){
				//获取组的ID号，即省份的ID号
				return groupPosition;
			}
			public View getGroupView(int groupPosition,boolean isExpanded,View convertView,ViewGroup parent){
				LinearLayout ll=new LinearLayout(ChooseCityActivity.this);
				ll.setOrientation(LinearLayout.VERTICAL);
				ImageView logo=new ImageView(ChooseCityActivity.this);
				ll.addView(logo);
				TextView textView=getTextView();
				textView.setText(getGroup(groupPosition).toString().toString());
				ll.addView(textView);
				return ll;
				}
			public boolean isChildSelectable (int groupPosition,int childPosition){
				return true;
			}
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return cities[groupPosition].length;
			}
			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return true;
			}
		};
		setListAdapter(adapter);
		//设置该窗口显示列表
		getExpandableListView().setOnChildClickListener(new OnChildClickListener(){
			public boolean onChildClick (ExpandableListView parent,View source,int groupPosition,int childPosition,long id){
				Intent intent=getIntent();//获取启动该Activity之前的activity对应的Intent
				Bundle data=new Bundle();
				data.putString("city", cities[groupPosition][childPosition]);
				intent.putExtras(data);
				ChooseCityActivity.this.setResult(0,intent);
				//设置结果码和退回的Activity
				ChooseCityActivity.this.finish();
				//结束SelectCityActivity
				return false;
			}
			
		});
		
	}

	}
