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
		 * �e�X��r���ʧ@
		 */
		private void send() {
			
			String inputText = messageText.getText().toString(); //���omessage text���r��
			Log.d("debug",inputText); //�bLogCat�W��ܿ�J���r��
			
			if(encryptCheckBox.isChecked()){
				inputText = "******";
			}
			
			Toast.makeText(getActivity(), inputText, Toast.LENGTH_LONG).show(); //��ܤp���O(activity,�n��ܪ��r��,��ܮɶ����u) 
			
			messageText.getText().clear(); //�M��text�W����r
		}
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false); //�Nfragment_main�����
			
			encryptCheckBox = (CheckBox) rootView.findViewById(R.id.encryptCheckBox); //���id:encryptCheckBox
			sendButton = (Button) rootView.findViewById(R.id.sendButton); //���id:sendbutton
			messageText = (EditText) rootView.findViewById(R.id.messageText); //��� id:messageText
			
			/**
			 * �b��J��r����UEnter�i�H�ǰe��r�X�h
			 */
			messageText.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
					// TODO Auto-generated method stub
					
					Log.d("debug", "key Code:" + keyCode + "," + "key Event:" + keyEvent);
					
					//�P�_���Ʊ� 1.�ʧ@��"���U" 2.���U���䬰Enter
					if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
						
						send(); //�N��r�ǥX
						return true; //�N�ƥ���Ƥ��A�^��
					}
					
					return false;
				}
			});
			
			sendButton.setText("send");
			
			/**
			 * send button����ť(�I����Ĳ�o���ʧ@) 
			 */
			sendButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					// TODO Auto-generated method stub
					send(); //�N�ƤJ��r�e�X
				}
			});
			
			return rootView;
		}
	}

}
