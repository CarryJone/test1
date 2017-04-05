package com.gao.bryan.myportsip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.portsip.PortSipSdk;
import com.portsip.sipsample.util.SipContact;

import java.util.List;

public class MessageFragment extends Fragment {

	EditText etContact, etStatus, etmsgdest, etMessage;
	Button btSendmessage, btSendStatus;
	ImageButton btAddContact;
	ListView lvContacts;
	int selectItem;
	OnClickListener clickListener;
	MyApplication myApplication;
	PortSipSdk mSipSdk;
	Context context = null;
	BaseListAdapter mAdapter;
	List<SipContact> contacts = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		context = getActivity();
		myApplication = (MyApplication) context.getApplicationContext();
		mSipSdk = myApplication.getPortSIPSDK();
		clickListener = new BtOnclickListen();
		contacts = myApplication.getSipContacts();
		View view = inflater.inflate(R.layout.messageview, null);
		lvContacts = (ListView) view.findViewById(R.id.lvcontacs);
		((Button) view.findViewById(R.id.btupdate))
				.setOnClickListener(clickListener);
		((Button) view.findViewById(R.id.btclear))
				.setOnClickListener(clickListener);

		mAdapter = new BaseListAdapter(context);

		lvContacts.setAdapter(mAdapter);
		lvContacts.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
				selectItem = arg2;
				mAdapter.notifyDataSetChanged();
			}
		});

		etStatus = (EditText) view.findViewById(R.id.etstatus);
		etMessage = (EditText) view.findViewById(R.id.etmessage);
		etContact = (EditText) view.findViewById(R.id.etcontact);
		etmsgdest = (EditText) view.findViewById(R.id.etmsgdest);

		btSendmessage = (Button) view.findViewById(R.id.btsendmsg);
		btSendmessage.setOnClickListener(clickListener);
		btSendStatus = (Button) view.findViewById(R.id.btsendstatus);
		btSendStatus.setOnClickListener(clickListener);
		btAddContact = (ImageButton) view.findViewById(R.id.btaddcontact);
		btAddContact.setOnClickListener(clickListener);

		return view;
	}

	class BtOnclickListen implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btsendmsg:
				BtnSend_Click();
				break;
			case R.id.btsendstatus:
				BtnSetStatus_Click();
				break;
			case R.id.btaddcontact:
				BtnAddContact_Click();
				break;
			case R.id.btdelcontact:
				BtnDelContact_Click();
				break;
			case R.id.btclear:
				BtnClearContact_Click();
				break;
			case R.id.btupdate:
				BtnUpdateContact_Click();
				break;
			default:
				break;
			}
		}
	}

	private void BtnAddContact_Click() {
		if (!myApplication.isOnline()) {
			return;
		}
		String sendTo = etContact.getText().toString();
		if (sendTo == null || sendTo.length() <= 0) {
			return;
		}

		String subject = "Hello";
		mSipSdk.presenceSubscribeContact(sendTo, subject);

		for (int i = 0; i < contacts.size(); i++)// already added
		{
			SipContact tempReference = contacts.get(i);
			String SipUri = tempReference.getSipAddr();
			if (SipUri.equals(sendTo)) {
				tempReference.setSubscribed(true);
				updateLV();
				return;
			}
		}
		SipContact newContact = new SipContact();
		newContact.setSipAddr(sendTo);
		newContact.setSubstatus(false);// off line
		newContact.setSubscribed(true);// weigher send my status to remote
										// subscribe
		newContact.setAccept(false); // weigher rev remote subscribe
		newContact.setSubId(0);

		contacts.add(newContact);
		updateLV();
	}

	private void BtnDelContact_Click() {
		if (contacts.size() > 0) {
			contacts.remove(selectItem);
		}

		updateLV();
	}

	private void BtnUpdateContact_Click() {
		for (int i = 0; i < contacts.size(); ++i) {
			SipContact tempReference = contacts.get(i);
			String SipUri = tempReference.getSipAddr();
			String subject = "Hello";
			long subscribeId = tempReference.getSubId();
			if (tempReference.isSubscribed()) {
				mSipSdk.presenceSubscribeContact(SipUri, subject);
			}

			String statusText = etStatus.getText().toString();

			if (tempReference.isAccept() && subscribeId != -1) {
				mSipSdk.presenceOnline(subscribeId, statusText);
			}
		}
	}

	private void BtnClearContact_Click() {
		contacts.clear();
		updateLV();
	}

	private void updateLV() {
		mAdapter.notifyDataSetChanged();
	}

	private void BtnSetStatus_Click() {
		if (!myApplication.isOnline()) {
			return;
		}

		String content = etStatus.getText().toString();
		if (content == null || content.length() <= 0) {
			showTips("please input status description string");
			return;
		}

		for (int i = 0; i < contacts.size(); ++i) {
			SipContact tempReferece = contacts.get(i);
			long subscribeId = tempReferece.getSubId();

			String statusText = etStatus.getText().toString();
			if (tempReferece.isAccept() && subscribeId != -1) {
				mSipSdk.presenceOnline(subscribeId, statusText);
			}
		}
	}

	private void BtnSend_Click() {
		if (!myApplication.isOnline()) {
			return;
		}

		String content = etMessage.getText().toString();
		String sendTo = etmsgdest.getText().toString();
		if (sendTo == null || sendTo.length() <= 0) {
			Toast.makeText(context, "Please input send to target",
					Toast.LENGTH_SHORT).show();
			return;
		}

		if (content == null || content.length() <= 0) {
			Toast.makeText(context, "Please input message content",
					Toast.LENGTH_SHORT).show();
			return;
		}

		mSipSdk.sendOutOfDialogMessage(sendTo, "text", "plain",
				content.getBytes(), content.length());
	}

	void showTips(String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	private class BaseListAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		public BaseListAdapter(Context mContext) {
			inflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return contacts.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tvRefrence;
			ImageButton btDelContact;

			SipContact contactRefrence;
			contactRefrence = contacts.get(position);
			convertView = inflater.inflate(R.layout.viewlistitem, null);
			tvRefrence = (TextView) convertView.findViewById(R.id.tvsipaddr);
			tvRefrence.setText(contactRefrence.getSipAddr());
			tvRefrence = (TextView) convertView
					.findViewById(R.id.tvsubdescription);
			tvRefrence.setText(contactRefrence.currentStatusToString());
			btDelContact = (ImageButton) convertView
					.findViewById(R.id.btdelcontact);
			btDelContact.setOnClickListener(clickListener);
			return convertView;
		}
	}

	private BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			updateLV();
		}
	};

	@Override
	public void onResume() {
		super.onResume();
		IntentFilter mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(MyApplication.CONTACT_CHANG);
		context.registerReceiver(mReceiver, mIntentFilter);
	}

	@Override
	public void onPause() {
		super.onPause();
		context.unregisterReceiver(mReceiver);
	}

}
