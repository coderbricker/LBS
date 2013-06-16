// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.friend;

import com.codebricker.lbsshare.bak.client.friend.IFriendListener;
import com.codebricker.lbsshare.vos.Friend;

/** 
 * Read operations on the friend list. It supports to send message with user's current location information or not, 
 * get all friends(or online friends), request/follow some friend's current location information, and send his own
 * location information to one friend once or continuously.
 * This interface could only be retrieved by function "getFriendReadBinder" in IGeneral interface, and after user get this interface,
 * he can call "listen" function to register a listener to communicate with our lbs platform.
 * 
 */
interface IFriend {
	/** 
	 * Register a friend listener to listen all asynchronous return message of the operations on friend list, including read and write.
	 * and the listener should be the same as the friend listener in "IOperateFriend" interface if developer use read and write friend 
	 * interface both.
	 *
	 * @param listener the interface instance to receive messages of friend list.
	 * @param eventType reserved.
	 */
	void listen(IFriendListener listener, int eventType);
	
	/** 
	 * Fetch all friends.
	 *
	 * @return list each item is a friend.
	 */
	List<Friend> getAllFriends();

	
	/** 
	 * Fetch all the online friends
	 *
	 * @return list each item is a friend
	 */
	List<Friend> getOnlineFriends();
	
	/** 
	 * Send to a friend a chat message with your current location information optionally.
	 * 
	 * @param toFriendId friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 * @param text the message will be send in String.
	 * @param withLocation true, the message will contain your current location information; false, not contain.
	 */
	void sendMessage(String toFriendId, String text, boolean withLocation);
	
	/** 
	 * Send to a friend binary chat message with your current location information optionally, 
	 * and binary data has length limited, it should be less than 4*1024*1024 bytes.
	 *
	 * @param toFrindId friend Id, see the comments of parameter "toFriendId" in function "sendMessage".
	 * @param data binary data in byte format, should be less than 4*1024*1024 bytes.
	 * @param withLocation true, the message will contain your current location information; false, not contain.
	 */
	void sendBinaryMessage(String toFrindId, in byte[] data, boolean withLocation);
	
	/** 
	 * Request a friend to report his location to you, the location information will be send to you if he accepts your request.
	 * The location information will be sent to you via "receivedLocation" in IFriendListener interface. 
	 *
	 * @param friendId see the comments of parameter "toFriendId" in function "sendMessage".
	 */
	void requestLocation(String friendId);
	
	/** 
	 * Request to track a friend's location, the location information will be send to you via listener if he accepts.
	 * and the track process continued until the friend stop sharing or your stop following by yourself. 
	 * The location information will be sent to you via "receivedLocation" in IFriendListener interface. 
	 *
	 * @param friendId see the comments of parameter "toFriendId" in function "sendMessage".
	 */
	void followLocation(String friendId);
	
	/** 
	 * Stop tracking a friend. When stop following, the friend will stop sharing his location information to you.
	 *
	 * @param friendId see the comments of parameter "toFriendId" in function "sendMessage".
	 */
	void stopFollowLocation(String friendId);
	
	/** 
	 * Send your current location information to a friend once or continuously after a interval time.
	 *
	 * @param toFriendId see the comments of parameter "toFriendId" in function "sendMessage".
	 * @param isContinuesly whether send location continuously
	 * @param interval interval time with unit second, only work when isContinuesly is true.
	 */
	void shareLocation(String toFriendId, boolean isContinuesly, int interval);
	
	/** 
	 * Send your current location information to a friend once or continuously after some distance.
	 *
	 * @param toFriendId see the comments of parameter "toFriendId" in function "sendMessage".
	 * @param isContinuesly whether send location continuously
	 * @param distance distance with unit meter, only work when isContinuesly is true.
	 */
	void shareLocationByDistance(String toFriendId, boolean isContinuesly, int distance);
	
	/** 
	 * Stop send your location to a friend. When you send your location information continuously, you could stop the process.
	 *
	 * @param toFriendId see the comments of parameter "toFriendId" in function "sendMessage".
	 */
	void stopSharingLoaction(String toFriendId);

}
