// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.group;

import com.codebricker.lbsshare.vos.GroupInfo;
import com.codebricker.lbsshare.vos.Setting;
import com.codebricker.lbsshare.bak.client.group.IGroupListener;

/** 
 * Write operations on Group list. It supports to create/join or remove/exit a group, remove a group member, invite a user into
 * group, change group information, set a user a owner privilege, and set group setting etc. 
 * This interface could only be retrieved by function "getGroupWriteBinder" in IGeneral interface, and after user get this interface,
 * he can call "listen" function to register a listener to communicate with our lbs platform.
 *
 */
interface IOperateGroup {
	/**
	 * Register a group listener to listen all return message of the operations on group list, including read and write.
	 * and the listener should be the same as the friend listener in IGroup interface if developer
	 * use read and write group interface both.
	 *
	 * @param listener the interface instance to receive messages of group list. 
	 * @param eventType reserved.
	 */
	void listen(IGroupListener listener, int eventType);
	
	/** 
	 * Create a group, LBS platform will generate a unique group id and return to application via function "receiveCreateGroupResult"
	 * or "receiveGroupListChanged" in IGroupListen interface.
	 *
	 * @param groupName display name of a group, it is different of groupId. 
	 * @param description the group's description.
	 */
	void createGroup(String groupName, String description);
	
	/** 
	 * Remove a group form group list. Only the owner could remove a group.
	 *
	 * @param groupID group Id, the unique identity string of a group. It is equal field "groupId" in class GroupInfo,
	 * such as wlcscu@conference.motolbs.com, all the other "group Id" has the same meaning.
	 */
	void removeGroup(String groupID);
	
	/**
	 * Exit a group, Only a user not the owner could exit a group.
	 *
	 * @param groupId see the comments of parameter "groupID" in function "removeGroup".
	 */
	void exitGroup(String groupId);
	
	/** 
	 * Change a group's information, such like change group description.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 * @param groupInfo the new groupInfo structure.
	 */
	void changeGroupInfo(String groupID, in GroupInfo groupInfo);
	
	
	/**
	 * Set a group member as owner, only group owner could set a member as owner.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 * @param memberID member Id, the unique identity string of a member. It is equal field "account" in class Member,
	 * such as wlcscu@motolbs.com, all the other "member Id" has the same meaning.
	 */
	void setMembersAsOwner(String groupID, String memberID);
	
	
	/** 
	 * Invite a user to join into this group. It needed the user's approval.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 * @param userID see the comments of parameter "memberID" in function "setMembersAsOwner".
	 */
	void inviteUser(String groupID, String userID);
	
	/** 
	 * Kick a member from a group, only group owner could kick a member.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 * @param userID see the comments of parameter "memberID" in function "setMembersAsOwner".
	 * @param memberNick user nickName in group, it must contain useful data.
	 */
	void removeMember(String groupID, String memberId, String memberNick);

	/** 
	 * Join into a group.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 */
	void joinGroup(String groupID);
	
	/** 
	 * Create/update group setting. A group setting is a list of class Setting that means several key-values.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "removeGroup".
	 * @param settingName setting name of one group.
	 * @param gSetting setting list.
	 */
	void setGroupSetting(String groupID, String settingName, in List<Setting> gSetting);
	
}
