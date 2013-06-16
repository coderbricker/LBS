// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.group;

import com.codebricker.lbsshare.bak.client.group.IGroupListener;
import com.codebricker.lbsshare.vos.GroupInfo;
import com.codebricker.lbsshare.vos.Member;
import com.codebricker.lbsshare.vos.Setting;

/** 
 * Read operations on group list. It supports to get joined group list, get group setting, get a group's information, 
 * send message into group, share your location into group, tell other members in group to do something, get your role in group, etc.
 * This interface could only be retrieved by function "getGroupReadBinder" in IGeneral interface, and after user get this interface,
 * he can call "listen" function to register a listener to communicate with our lbs platform.
 *
 */
interface IGroup {
	
	/**
	 * Register a group listener to listen all return message of the operations on group list, including read and write.
	 * and the listener should be the same as the group listener in IOperateGroup interface if developer
	 * use read and write group interface both.
	 *
	 * @param listener the interface instance to receive messages of group list.
	 * @param eventType reserved.
	 */
	void listen(IGroupListener listener, int eventType);
	
	/** 
	 * Get joined group list.
	 * 
	 * @return list every item is a group.
	 */
	List<GroupInfo> getJoinedGroups();
	
	/** 
	 * Get the member list in a group.
	 * 
	 * @param groupID group Id, the unique identity string of a group. It is equal field "groupId" in class GroupInfo,
	 * such as wlcscu@conference.motolbs.com, all the other "group Id" has the same meaning.
	 * @return list every item is a member
	 */
	List<Member> getGroupMembers(String groupID);
	
	/** 
	 * Get a group setting from the setting name.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param settingName get a specific setting by settingName, otherwise, get all settings
	 *  of the application if settingName is null.
	 * @return list every item is a setting.
	 */
	List<Setting> getGroupSetting(String groupID, String settingName);
	
	/** 
	 * Get group basic information including group id, group name, description.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @return GroupInfo the group's information.
	 */
	GroupInfo getGroupInfo(String groupID);
	
	/** 
	 * Send a message to all the group members.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param text message body.
	 * @param withLocation the message will contain location information if true, or not contain if false.
	 */
	void sendGroupMessage(String groupID,  String text, boolean withLocation);
	
	/** 
	 * Send your location to all the group members once or once or continuously after a interval time.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param isContinously whether send location continuously
	 * @param interval interval time with unit second, only work when isContinuesly is true.
	 */
	void shareLocation(String groupID, boolean continuously, int interval);
	
	/** 
	 * Stop sending your location to all the group members.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 */
	void stopShareLocation(String groupID);
	
	/** 
	 * Execute group setting. It means to tell other group members that we will do something.  
	 * for example, starting sharing location. The application will receive the notify(or command) to execute group setting 
	 * via function "receiveNotifyExecuteGroupSetting" in IGroupListener instance with action="start", 
	 * then the application should execute the notify(command). for example, call function "shareLocation".
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param settingName unique setting name.
	 */
	void excuteGroupSetting(String groupID, String settingName);
	
	/** 
	 * Stop execute application setting. for example, stop sharing location. The application will receive the 
	 * notify(or command) to execute group setting via function "receiveNotifyExecuteGroupSetting" in IGroupListener instance with action="stop", 
	 * then the application should execute the notify(command). for example, call function "stopShareLocation".
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param settingName unique setting name.
	 */
	void stopExcuteGroupSetting(String groupID, String settingName);
	
	/** 
	 * Get a member's role, owner, admin, moderator or none.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @param memberID member Id, the unique identity string of a member. It is equal field "account" in class Member,
	 * such as wlcscu@motolbs.com, all the other "member Id" has the same meaning.
	 */
	String getRole(String groupID, String memberID);
	
	/** 
	 * Get all the owners of a group.
	 *
	 * @param groupID see the comments of parameter "groupID" in function "getGroupMembers".
	 * @return list all the owners, each item is a member Id, such as wlcscu@motolbs.com.
	 */
	List<String> getOwners(String groupID);
	
	/** 
	 * Search some groups with key "pattern" with limited number result.
	 *
	 * @param patten the search key.
	 * @param resultCount search result count, the max return number.
	 */
	void searchGroups(String pattern, int resultCount);

	/**
	 * Accept somebody's invitation to join into a group.
	 *
	 * @groupId see the comments of parameter "groupID" in function "getGroupMembers".
	 */
	void acceptInvitationToGroup(String groupId);
	
}
