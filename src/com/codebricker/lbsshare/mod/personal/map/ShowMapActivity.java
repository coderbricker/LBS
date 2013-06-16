// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.mod.personal.map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.codebricker.lbsshare.ConstantData;
import com.codebricker.lbsshare.R;
import com.codebricker.lbsshare.vos.GeoLocation;

/**
 * 
 * used for friend to show map
 */
public class ShowMapActivity extends Activity {
	private static final String TAG = "ShowMapActivity";
	private com.baidu.mapapi.map.MapView mapView;

	// private static final String TAG = "ShowMapActivity";

	private int lat = 400106900;
	private int lon = 1164688325;
	private MapController mapController;
	private String account = null;
	// private String nick = null;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case ConstantData.SHARE_STATE:
				int latitude = msg.arg1;
				int longitude = msg.arg2;
				String userName = (String) msg.obj;

				if (account.equals(userName)) {
					GeoPoint point = new GeoPoint(latitude, longitude);
					setPoint(userName, point);
				}
				break;

			default:
				break;
			}
		}
	};

	/**
	 * a broadcastReceiver for receive location in another activity
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 *      android.content.Intent)
	 */
	private BroadcastReceiver receiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			GeoLocation locationDomain = (GeoLocation) arg1
					.getParcelableExtra("location");
			Log.d("ShowMapActivity",
					"LocationDomain=" + String.valueOf(locationDomain));
			int latitude = (int) (Double.parseDouble(locationDomain
					.getLatitude()) * 1000000);
			int longitude = (int) (Double.parseDouble(locationDomain
					.getLongitude()) * 1000000);
			// String userName = arg1.getStringExtra("account");
			String nick = arg1.getStringExtra("nick");

			Message message = handler.obtainMessage();
			message.arg1 = latitude;
			message.arg2 = longitude;
			message.obj = nick;
			message.what = ConstantData.SHARE_STATE;
			handler.sendMessage(message);

		}
	};

	/**
	 * 
	 * when a new location point received setPoint for update mapUI
	 * 
	 * @param geoPoint
	 *            the new location point
	 */
	private void setPoint(String userName, GeoPoint geoPoint) {
//		Drawable drawable = this.getResources().getDrawable(R.drawable.point);
//		CustomItemizedOverlay overLay = new CustomItemizedOverlay(drawable,
//				this, mapView);
//		OverlayItem overlayitem = new OverlayItem(geoPoint,
//				ShowMapActivity.this.getString(R.string.map_item_title),
//				String.format(this.getString(R.string.map_item_iam), userName));
//		// ShowMapActivity.this.getString(R.string.map_item_iam_bejing));
//		overLay.addOverlay(overlayitem);
//
//		List<Overlay> overlays = mapView.getOverlays();
//		overlays.clear();
//		overlays.add(overLay);
//		try {
//			mapController.setCenter(geoPoint);
//			mapController.setZoom(18);
//			mapController.animateTo(geoPoint);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private BMapManager mBMapMan;

	/**
	 * Called when the activity is first created. (non-Javadoc)
	 * 
	 * @see com.google.android.maps.MapActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("64839263559E1F67A25D4BD745C666AAE820B880", null);
		setContentView(R.layout.map);
		Log.d(TAG, "show map activity");
		// BroadcastReceiver
		this.registerReceiver(receiver, new IntentFilter("cn.com.lbs.location"));

		Intent intent = this.getIntent();
		GeoLocation locationDomain = (GeoLocation) intent
				.getParcelableExtra("location");
		Log.d("ShowMapActivity",
				"LocationDomain=" + String.valueOf(locationDomain));
		lat = (int) (Double.parseDouble(locationDomain.getLatitude()) * 1000000);
		lon = (int) (Double.parseDouble(locationDomain.getLongitude()) * 1000000);
		account = intent.getStringExtra("account");
		String nick = intent.getStringExtra("nick");

		mapView = (MapView) findViewById(R.id.my_map);

		mapController = mapView.getController();
		mapView.setBuiltInZoomControls(true);
		GeoPoint point = new GeoPoint(lat, lon);
		Log.d(TAG, "nickName=" + nick);
		setPoint(nick, point);
		mapController.setCenter(point);//设置地图中心点

	}
	@Override
	protected void onDestroy(){
		mapView.destroy();
	        if(mBMapMan!=null){
	                mBMapMan.destroy();
	                mBMapMan=null;
	        }
	        super.onDestroy();
	        unregisterReceiver(receiver);
			Log.d(TAG, "onDestroy");
	}
	@Override
	protected void onPause(){
		mapView.onPause();
	        if(mBMapMan!=null){
	                mBMapMan.stop();
	        }
	        super.onPause();
	}
	@Override
	protected void onResume(){
		mapView.onResume();
	        if(mBMapMan!=null){
	                mBMapMan.start();
	        }
	        super.onResume();
	}
	/**
	 * Called when the activity is finished. (non-Javadoc)
	 * 
	 * @see android.app.Activity#finish()
	 */
	@Override
	public void finish() {
		Log.d(TAG, "finish");
		if (account != null) {
			Intent returnIntend = new Intent();
			returnIntend.putExtra("account", account);
			setResult(RESULT_OK, returnIntend);
		}

		super.finish();
	}

}
