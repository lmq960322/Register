package com.example.registertest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends Activity {
    Button registerBtn,cityBtn;
    EditText psd,psd2,name,city;
    RadioButton male,female;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 cityBtn=(Button)findViewById(R.id.cityBtn);
	        registerBtn=(Button)findViewById(R.id.registerBtn);
	        name=(EditText)findViewById(R.id.name);
	        psd=(EditText)findViewById(R.id.psd);
	        psd2=(EditText)findViewById(R.id.psd2);
	        city=(EditText)findViewById(R.id.city);
	        male=(RadioButton)findViewById(R.id.male);
		registerBtn.setOnClickListener(new View.OnClickListener(){ //��ע�ᡰ��ť���¼�����
			public void onClick(View v){
				String checkResult=checkInfo();//��֤�û�������Ϣ�Ľ��
				if(checkResult!=null){  //��������Ϊ�գ����öԻ�����ʾ
					
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("������ʾ");//���öԻ������
					builder.setMessage(checkResult);  //���öԻ���������Ϣ
					builder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
						//Ϊ�Ի�����Ӱ�ť���¼�����
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							// TODO Auto-generated method stub
							psd.setText("");//�����������Ϊ��
							psd2.setText("");
							
						}
					});
					builder.create().show();
				}else{   //ע����Ϣ����Ҫ�󣬽����ؾ߷���Intent�����д���
					Intent intent=new Intent(MainActivity.this,ResultActivity.class);
					intent.putExtra("naem",name.getText().toString());
					intent.putExtra("psa", psd.getText().toString());
					String gender=male.isChecked()?"��":"Ů";
					intent.putExtra("gender",gender);
					intent.putExtra("city",city.getText().toString());
					startActivity(intent);     //����һ���µ�Activity
                   }
				
			}
         });
		
		cityBtn.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			Intent intent=new Intent(MainActivity.this,ChooseCityActivity.class);
			//������Ҫ������Activity��Intent
			//����ָ����Activity���ȴ����صĽ��������0�����������ڱ�ʶ������
			startActivityForResult(intent,0);  //������0��ǰ��һ��
		}
			
		});
	}
	public String checkInfo(){
		 if(name.getText().toString()==null||name.getText().toString().equals("")){
			 return "�û�������Ϊ��";   //���û���������֤
		 }
		 if (psd.getText().toString().trim().length()<6||psd.getText().toString().trim().length()>15){
			 return"����λ��Ӧ����6~15֮��";  //�����������֤
		 }
		if(!psd.getText().toString().equals(psd2.getText().toString())){
			return "������������벻һ��";  //��ȷ�����������֤
		}
		 return null;
	}

	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		 if(requestCode==0&&resultCode==0){
			 //��requestCode\resultCodeͬʱΪ=��Ҳ���Ǵ����ض��Ľ��
			 Bundle data=intent.getExtras();//ȡ��Intent���Extras����
			 String resultCity=data.getString("city");//�޸�city�ı��������
			 city.setText(resultCity);
		 }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
