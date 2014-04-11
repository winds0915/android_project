package com.example.simpleuisend;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
//	public void send(View view) {
//		
//		Log.d("debug", "click");
//	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private Button sendButton;
		private EditText messageText;
		private CheckBox encryptCheckBox;
		
		/**
		 * 送出文字的動作
		 */
		private void send() {
			
			String inputText = messageText.getText().toString(); //取得message text之字串
			Log.d("debug",inputText); //在LogCat上顯示輸入的字串
			
			if(encryptCheckBox.isChecked()){
				inputText = "******";
			}
			
			Toast.makeText(getActivity(), inputText, Toast.LENGTH_LONG).show(); //顯示小面板(activity,要顯示的字串,顯示時間長短) 
			
			messageText.getText().clear(); //清除text上的文字
		}
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false); //將fragment_main實體化
			
			encryptCheckBox = (CheckBox) rootView.findViewById(R.id.encryptCheckBox); //找到id:encryptCheckBox
			sendButton = (Button) rootView.findViewById(R.id.sendButton); //找到id:sendbutton
			messageText = (EditText) rootView.findViewById(R.id.messageText); //找到 id:messageText
			
			/**
			 * 在輸入文字後按下Enter可以傳送文字出去
			 */
			messageText.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
					// TODO Auto-generated method stub
					
					Log.d("debug", "key Code:" + keyCode + "," + "key Event:" + keyEvent);
					
					//判斷兩件事情 1.動作為"按下" 2.按下的鍵為Enter
					if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
						
						send(); //將文字傳出
						return true; //將事件消化不再回傳
					}
					
					return false;
				}
			});
			
			sendButton.setText("send");
			
			/**
			 * send button的監聽(點擊後觸發的動作) 
			 */
			sendButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					send(); //將數入文字送出
				}
			});
			
			return rootView;
		}
	}

}
