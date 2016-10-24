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
	private String[ ]provinces=new String[ ]{"����","����","�㽭"};
	private String[ ][ ]cities=new String[ ][ ]{{"�ϲ�","�Ž�","����","����"},{"�Ͼ�","����","����","����"},
			{"����","����","̨��","��"}};
	public void onCreate(Bundle savadInstanceState){
		super.onCreate(savadInstanceState);
		ExpandableListAdapter adapter=new BaseExpandableListAdapter(){
			public Object getChild(int groupPosition,int childPosition){
				     //��ȡָ����λ�õ�ָ�����б�������
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
				//�÷�������ÿ����ѡ������
				TextView textView=getTextView();
				textView.setText(getChild (groupPositon,childPosition).toString());
				return textView;
			}
			public Object getGroup(int groupPosition){
				//��ȡָ��λ�ô���������
				return provinces[groupPosition];
				
			}
			public int getGroupCount(){
				//��ȡ����չ�����������ʡ����
				return provinces.length;
			}
			public long getGroupId(int groupPosition){
				//��ȡ���ID�ţ���ʡ�ݵ�ID��
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
		//���øô�����ʾ�б�
		getExpandableListView().setOnChildClickListener(new OnChildClickListener(){
			public boolean onChildClick (ExpandableListView parent,View source,int groupPosition,int childPosition,long id){
				Intent intent=getIntent();//��ȡ������Activity֮ǰ��activity��Ӧ��Intent
				Bundle data=new Bundle();
				data.putString("city", cities[groupPosition][childPosition]);
				intent.putExtras(data);
				ChooseCityActivity.this.setResult(0,intent);
				//���ý������˻ص�Activity
				ChooseCityActivity.this.finish();
				//����SelectCityActivity
				return false;
			}
			
		});
		
	}

	}
