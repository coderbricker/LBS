// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.friend;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;

import com.codebricker.lbsshare.bak.CommandEngine;
import com.codebricker.lbsshare.vos.GeoLocation;

/**
 * listen friends operation
 */
public class FriendListener extends IFriendListener.Stub {

	private static final String TAG = "FriendListener";
	
	private Handler mainHandler;
	
	public FriendListener(Handler mainHandler) {
		this.mainHandler = mainHandler;
	}
	
	@Override
	public void receivedMessage(String fromID, String text, GeoLocation location)
			throws RemoteException {
		Log.d(TAG, "receivedMessage + text=" + text);
		
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_MESSAGE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("text", text);
		bl.putParcelable("location", location);
		msg.setData(bl);
		
		mainHandler.sendMessage(msg);		
	}

	@Override
	public void receivedBinaryMessage(String fromID, byte[] data,
			GeoLocation location) throws RemoteException {
		Log.d(TAG, "receivedBinaryMessage");
		
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_BINARY_MESSAGE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putByteArray("text", data);
		bl.putParcelable("location", location);
		msg.setData(bl);
		
		mainHandler.sendMessage(msg);		
		
	}

	@Override
	public void receivedAddByFriend(String fromID, String nick, String tips)
			throws RemoteException {
		Log.d(TAG, "receivedAddByFriend");
		
		Message msg = new Message();
		msg.what = CommandEngine.ADD_BY_FRIEND_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("nick", nick);
		bl.putString("tips", tips);
		msg.setData(bl);

		mainHandler.sendMessage(msg);		
	}

	@Override
	public void receivedResultOfAddFriend(String friendID, String nick, boolean success)
			throws RemoteException {
		Log.d(TAG, "receivedResultOfAddFriend");
		
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_ADD_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", friendID);
		bl.putString("nick", nick);
		bl.putBoolean("result", success);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receivedRemovedByFriend(String fromID, String nick) throws RemoteException {
		Log.d(TAG, "receivedRemovedByFriend");
		
		Message msg = new Message();
		msg.what = CommandEngine.REMOVE_BY_FRIEND_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("nick", nick);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedResultOfRemoveFriend(String friendID, boolean success)
			throws RemoteException {
		Log.d(TAG, "receivedResultOfRemoveFriend");
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_REMOVE_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", friendID);
		bl.putBoolean("result", success);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedPresenceChanged(String fromID, String status)
			throws RemoteException {
		Log.d(TAG, "receivedStatusChanged");
		
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_PRESENCE_CHANGE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("presence", status);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedLocation(String fromID, GeoLocation location)
			throws RemoteException {
		Log.d(TAG, "receivedLocation");
		
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_LOCATION_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putParcelable("location", location);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedStopSharingLoaction(String toFriendId)
			throws RemoteException {
		Log.d(TAG, "receivedStopSharingLoaction");

		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_STOP_SHARE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", toFriendId);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedRequestLocation(String fromID, String tips)
			throws RemoteException {
		Log.d(TAG, "receivedRequestLocation");
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_REQUEST_LOCATION_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("tips", tips);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
		
	}

	@Override
	public void receivedFollowLocation(String fromID, String tips)
			throws RemoteException {
		Log.d(TAG, "receivedFollowLocation");
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_FOLLOW_LOCATION_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", fromID);
		bl.putString("tips", tips);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
		
	}

	@Override
	public void receiveFriendListChanged(String friendId, String nickName, 
			String email, String phone, String action) throws RemoteException {
		Log.d(TAG, "receiveFriendListChanged");
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_LIST_CHANGED_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", friendId);
		bl.putString("nick", nickName);
		bl.putString("email", email);
		bl.putString("phone", phone);
		bl.putString("action", action);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

	@Override
	public void receivedStopFollowLoaction(String friendId)
			throws RemoteException {
		Log.d(TAG, "receivedStopFollowLoaction");
		Message msg = new Message();
		msg.what = CommandEngine.FRIEND_STOP_FOLLOW_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("friendId", friendId);
		msg.setData(bl);

		mainHandler.sendMessage(msg);
	}

}
