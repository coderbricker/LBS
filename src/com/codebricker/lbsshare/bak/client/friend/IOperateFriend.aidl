// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.friend;
import com.codebricker.lbsshare.bak.client.friend.IFriendListener;

/**
 * Write operations on friend list. It supports to add or remove a friend.
 * This interface could only be retrieved by function "getFriendWriteBinder" in IGeneral interface, and after user get this interface,
 * he can call "listen" function to register a listener to communicate with our lbs platform.
 *
 */
interface IOperateFriend {
	/** 
	 * Register a friend listener to listen all return message of the operations on friend list, including read and write.
	 * and the listener should be the same as the friend listener in IFriend interface if developer
	 * use read and write friend interface both.
	 *
	 * @param listener the interface instance to receive messages of friend list.
	 * @param eventType reserved.
	 */
	void listen(IFriendListener listener, int eventType);
	
	/** 
	 * Add a friend into the friend list.
	 * 
	 * @param nick friend's nick name, such as wlcscu. 
	 * @param userID friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 */
	void addFriend(String nick, String userID);
	
	/** 
	 * Remove a friend from the friend list.
	 *
	 * @param friendID friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 */
	void removeFriend(String friendID);

}
