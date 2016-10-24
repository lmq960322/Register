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
		registerBtn.setOnClickListener(new View.OnClickListener(){ //”注册“按钮的事件处理
			public void onClick(View v){
				String checkResult=checkInfo();//验证用户输入信息的结果
				if(checkResult!=null){  //如果结果不为空，则用对话框提示
					
					Builder builder=new AlertDialog.Builder(MainActivity.this);
					builder.setTitle("出错提示");//设置对话框标题
					builder.setMessage(checkResult);  //设置对话框内容信息
					builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
						//为对话框添加按钮及事件处理
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							// TODO Auto-generated method stub
							psd.setText("");//将密码框设置为空
							psd2.setText("");
							
						}
					});
					builder.create().show();
				}else{   //注册信息符合要求，讲话素具放入Intent，进行传递
					Intent intent=new Intent(MainActivity.this,ResultActivity.class);
					intent.putExtra("naem",name.getText().toString());
					intent.putExtra("psa", psd.getText().toString());
					String gender=male.isChecked()?"男":"女";
					intent.putExtra("gender",gender);
					intent.putExtra("city",city.getText().toString());
					startActivity(intent);     //启动一个新的Activity
                   }
				
			}
         });
		
		cityBtn.setOnClickListener(new OnClickListener(){
		public void onClick(View v){
			Intent intent=new Intent(MainActivity.this,ChooseCityActivity.class);
			//创建需要启动的Activity的Intent
			//启动指定的Activity并等待返回的结果，其中0是请求吗，用于标识该请求
			startActivityForResult(intent,0);  //请求码0，前后一致
		}
			
		});
	}
	public String checkInfo(){
		 if(name.getText().toString()==null||name.getText().toString().equals("")){
			 return "用户名不能为空";   //对用户名进行验证
		 }
		 if (psd.getText().toString().trim().length()<6||psd.getText().toString().trim().length()>15){
			 return"密码位数应该在6~15之间";  //对密码进行验证
		 }
		if(!psd.getText().toString().equals(psd2.getText().toString())){
			return "两次输入的密码不一致";  //对确认密码进行验证
		}
		 return null;
	}

	public void onActivityResult(int requestCode,int resultCode,Intent intent){
		 if(requestCode==0&&resultCode==0){
			 //当requestCode\resultCode同时为=，也即是处理特定的结果
			 Bundle data=intent.getExtras();//取出Intent里的Extras数据
			 String resultCity=data.getString("city");//修改city文本框的内容
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
