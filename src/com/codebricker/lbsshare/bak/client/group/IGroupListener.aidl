// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.

package com.codebricker.lbsshare.bak.client.group;
import com.codebricker.lbsshare.vos.GeoLocation;
import com.codebricker.lbsshare.vos.GroupInfo;

/** 
 * Group listener to receive message or notification of group list from lbs platform. such as chat message, location information,
 * create/remove/exit/join group result, notify of group list changed, group member list changed, group setting changed, and execute
 * group setting, and search group result etc.
 *
 * Developer could use function "listen" in IGroup or IOperateGroup interface to register a group
 * listener instance. Developer who want to receive messages of group list should implement all the functions here.
 *
 */
interface IGroupListener {
	/** 
	 * Result of create a group. If failed, the reason will be set, and if success, the reason is null or ""; 
	 *
	 * @param groupName the display name of a group.
	 * @param groupID group Id, the unique identity string of a group. It is equal field "groupId" in class GroupInfo,
	 * such as wlcscu@conference.motolbs.com, all the other "group Id" has the same meaning.
	 * @param result create success or failed.
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */
	void receiveCreateGroupResult(String groupName, String groupID, boolean result, String reason);
	
	/**
	 * Result of removing a group.
	 * 
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param result success or failed.
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveRemoveGroupResult(String groupId, boolean result, String reason);
	
	/**
	 * Result of exiting a group.
	 * 
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param result success or failed.
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveExitGroupResult(String groupId, boolean result, String reason);
	
	/**
	 * Group list changed when add or remove a group.
	 * 
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param action add/remove.
	 */
	void receiveGroupListChanged(String groupId, String action);
	
	
	/**
	 * Result of inviting a user into group.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param userId member Id, the unique identity string of a member. It is equal field "account" in class Member,
	 * such as wlcscu@motolbs.com, all the other "member Id" has the same meaning.
	 * @param result success or failed
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveInviteUserResult(String groupId, String userId, boolean result, String reason);
	
	/**
	 * Somebody want to invite you to join into a group.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param inviter the inviter id. It is equal field "account" in class Member.
	 * @param reason the reason is set if failed, or null if success
	 */ 
	void receiveInviteRequest(String groupId, String inviterId, String reason);
		
	/**
	 * Result of joining group success.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param result success or failed
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveJoinResult(String groupId, boolean result, String reason);
	
	/**
	 * Result of removing member from group.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param memberId see the comments of parameter "userId" in function "receiveInviteUserResult".
	 * @param result success or failed
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveRemoveMemberResult(String groupId, String memberId, boolean result, String reason);
	
	/**
	 * Result of setting a member as owner.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param memberId see the comments of parameter "userId" in function "receiveInviteUserResult".
	 * @param result success or failed
	 * @param reason the reason is set if failed, or null(or "") if success.
	 */ 
	void receiveSetMemberAsOwnerResult(String groupId, String memberId, boolean result, String reason);
	
	
	
	/** 
	 * Group member list changed, such as somebody joins/exits the group.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param memberId see the comments of parameter "userId" in function "receiveInviteUserResult".
	 * @param nick the nickName of member.
	 * @param role member's role, such as owner, none
	 * @param action add/remove
	 */
	void receiveMemberListChanged(String groupID, String memberId, String nick, String role, String action);
		
	/** 
	 * Received group message.
	 *
	 * @param fromID in format of groupid/usernick.
	 * @param text message context.
	 * @param location location information or null.
	 */
	void receiveMessage(String fromID, String text, in GeoLocation location);
	
	/** 
	 * Received a group member's location.
	 *
	 * @param fromID in format of groupid/usernick.
	 * @param location location information, the usernick's current location.
	 */
	void receiveLocation(String fromID, in GeoLocation location);
	
	/** 
	 * Received stopping sharing location to group. Developer should call function "stopShareLocation" in IGroup instance
	 * after receive this message.
	 *
	 * @param fromID in format of groupid/usernick.
	 */
	void receiveStopShareLocation(String fromID);
	
	
	/** 
	 * Notify of executing a group setting or stopping executing. If group setting is about shareing location, 
	 * developer should call function "shareLocation" in IGroup instance after receive executing a group setting or call function 
	 * "stopShareLocation" in IGroup instance after receive stopping executing.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param settingName the name of setting, in String.
	 * @param action start/end.
	 */
	void receiveNotifyExecuteGroupSetting(String groupID, String settingName, String action);
	
	/** 
	 * Notify of application setting is changed, developer should retrieve it via call function "getGroupSetting" in IGroup interface.
	 *
	 * @param groupId  see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param settingName the name of setting, in String.
	 */
	void receiveNotifyGroupSettingChanged(String groupId, String settingName);	
	
	
	/** 
	 * Result of searched groups. It is the response to function "searchGroups" in IGroup instance.
	 *
	 * @param key search key
	 * @param groups the return list, every item is a group
	 * @param count the actual result count. It may be bigger than the parameter groups's size. 
	 */
	void receiveSearchedGroupsResult(String key, in List<GroupInfo> groups, int count);
	
	/** 
	 * Result of change group information.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "receiveCreateGroupResult".
	 * @param groupInfo the group changed information.
	 * @param res the result, true or false. 
	 */
	void receiveChangeGroupInfoResult(String groupId, in GroupInfo groupInfo, boolean res);
}
