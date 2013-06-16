package com.codebricker.lbsshare.bak.client.group;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import com.codebricker.lbsshare.bak.CommandEngine;
import com.codebricker.lbsshare.vos.GeoLocation;
import com.codebricker.lbsshare.vos.GroupInfo;

public class GroupListener extends IGroupListener.Stub {
	private static final String TAG = "GroupListener";
	private Handler mainHandler;
	
	public GroupListener(Handler mainHandler) {
		this.mainHandler = mainHandler;
	}
	
	@Override
	public void receiveCreateGroupResult(String groupName, String groupId,
			boolean result, String reason) throws RemoteException {
		Log.d(TAG, "receiveCreateGroupResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_CREATE_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupName", groupName);
		bl.putString("groupId", groupId);
		bl.putBoolean("result", result);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);		
	}

	@Override
	public void receiveRemoveGroupResult(String groupId, boolean result,
			String reason) throws RemoteException {
		Log.d(TAG, "receiveRemoveGroupResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_REMOVE_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putBoolean("result", result);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);		
		
	}

	@Override
	public void receiveExitGroupResult(String groupId, boolean result,
			String reason) throws RemoteException {
		Log.d(TAG, "receiveExitGroupResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_EXIT_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putBoolean("result", result);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveInviteUserResult(String groupId, String userId,
			boolean result, String reason) throws RemoteException {
		Log.d(TAG, "receiveInviteUserResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_INVITE_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("userId", userId);
		bl.putBoolean("result", result);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveInviteRequest(String groupId, String inviter, String reason)
			throws RemoteException {
		Log.d(TAG, "receiveInviteRequest");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_INVITE_REQUEST_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("inviter", inviter);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveJoinResult(String groupId, boolean result, String reason)
			throws RemoteException {
		Log.d(TAG, "receiveJoinResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_JOIN_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putBoolean("result", result);
		bl.putString("reason", reason);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}
	

	@Override
	public void receiveMemberListChanged(String groupId, String memberID, String nick, String role, String action)
			throws RemoteException {

		Log.d(TAG, "receiveMemberListChanged");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_MEBMER_LIST_CHANGED_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("memberId", memberID);
		bl.putString("nick", nick);
		bl.putString("role", role);
		bl.putString("action", action);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveMessage(String fromID, String text, GeoLocation location)
			throws RemoteException {
		Log.d(TAG, "receiveMessage");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_MESSAGE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("fromId", fromID);
		bl.putString("text", text);
		bl.putParcelable("location", location);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveNotifyExecuteGroupSetting(String groupID,
			String settingName, String action) throws RemoteException {
		Log.d(TAG, "receiveNotifyExecuteAppSetting" + action);
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_NOTIFY_EXCUTE_SETTING_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupID);
		bl.putString("settingName", settingName);
		bl.putString("action", action);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}

	@Override
	public void receiveNotifyGroupSettingChanged(String groupId, String settingName)
			throws RemoteException {
		Log.d(TAG, "receiveNotifyAppSettingChanged");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_NOTIFY_SETTING_CHANGED_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("settingName", settingName);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}

	@Override
	public void receiveSearchedGroupsResult(String key, List<GroupInfo> groups, int count)
			throws RemoteException {

		Log.d(TAG, "receiveSearchedGroupsResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_SEARCH_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("key", key);
		bl.putParcelableArrayList("searchGroups", (ArrayList<? extends Parcelable>) groups);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveLocation(String fromID, GeoLocation location)
			throws RemoteException {
		Log.d(TAG, "receiveLocation");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_LOCATION_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("fromId", fromID);
		bl.putParcelable("location", location);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}

	@Override
	public void receiveStopShareLocation(String fromID) throws RemoteException {
		Log.d(TAG, "receiveStopShareLocation");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_STOP_SHARE_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("fromId", fromID);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveSetMemberAsOwnerResult(String groupId, String memberId,
			boolean result, String reason) throws RemoteException {
		Log.d(TAG, "receiveSetMemberAsOwnerResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_SET_MEMBER_OWNER_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("memberId", memberId);
		bl.putString("reason", reason);
		bl.putBoolean("result", result);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
	}

	@Override
	public void receiveRemoveMemberResult(String groupId, String memberId,
			boolean result, String reason) throws RemoteException {
		Log.d(TAG, "receiveRemoveMemberResult");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_REMOVE_MEMBER_RESULT_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("memberId", memberId);
		bl.putString("reason", reason);
		bl.putBoolean("result", result);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}

	@Override
	public void receiveGroupListChanged(String groupId, String action)
			throws RemoteException {
		Log.d(TAG, "receiveGroupListChanged");
		Message msg = new Message();
		msg.what = CommandEngine.GROUP_LIST_CHANGED_MSG;
		
		Bundle bl = new Bundle();
		bl.putString("groupId", groupId);
		bl.putString("action", action);
		msg.setData(bl);

		mainHandler.sendMessage(msg);	
		
	}

	@Override
	public void receiveChangeGroupInfoResult(String groupId,
			GroupInfo groupInfo, boolean res) throws RemoteException {
		Log.d(TAG, "receiveGroupListChanged +" + groupId + res);
	}

}
