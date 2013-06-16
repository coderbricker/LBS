// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.friend;

import com.codebricker.lbsshare.vos.GeoLocation;

/**
 * Friend listener to receive message or notification of friend list from lbs platform. such as chat message, location information,
 * add/remvoe friend result, notify of add/remove by some friend, etc.
 *
 * Developer could use function "listen" in IFriend or IOperateFriend interface to register a friend
 * listener instance. Developer who want to receive messages of friend list should implement all the functions here.
 *
 */
interface IFriendListener {
	/** 
	 * Receive a chat message with his location information optionally.
	 * 
	 * @param fromId the sender Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "fromID" has the same meaning if not mentioned specially.
	 * @param text message content.
	 * @param location location information or null if there is no location information.
	 */
	void receivedMessage(String fromId, String text, in GeoLocation location);
	
	/** 
	 * Received a block of binary data with his location information optionally.
	 
	 * @param fromID sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 * @param data message content.
	 * @param location location information or null if there is no location information.
	 */
	void receivedBinaryMessage(String fromID, in byte[] data, in GeoLocation location);	

	/** 
	 * A user add you as his friend.
	 *
	 * @param fromID sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 * @param nick the friend's nick name
	 * @param tips reason string.
	 */
	void receivedAddByFriend(String fromID, String nick, String tips);
	
	/** 
	 * Result of adding a friend.
	 *
	 * @param friendId friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 * @param nick nick name.
	 * @param success true or false, the result of add friend.
	 */
	void receivedResultOfAddFriend(String friendId, String nick, boolean success);
	
	/** 
	 * A Friend remove you from his friend list.
	 *
	 * @param friendId friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 * @param nick the friend's nick name.
	 */
	void receivedRemovedByFriend(String friendId, String nick);
	
	/**
	 * Result of remove a friend.
	 *
	 * @param friendId friend Id, the unique identity string of a friend. It is equal field "account" in class Friend,
	 * such as wlcscu@motolbs.com, all the other "friend Id" has the same meaning.
	 * @param success true or false, the result of add friend
	 */
	void receivedResultOfRemoveFriend(String friendId, boolean success);
	
	/**
	 * Friend list changed, when somebody is added into your friend list or is removed from your friend list, this message
	 * will be received. 
	 *
	 * @param friendId see the comments of parameter "friendId" in function "receivedResultOfAddFriend". 
	 * @param nickName nick name, it may be null(""), if action is remove, but it must contain useful information when 
	 * action is "add".
	 * @param email the friend's email.
	 * @param phone the friend's phone.
	 * @param action add/remove in string.
	 */
	void receiveFriendListChanged(String friendId, String nickName, String email, String phone, String action);
	
	
	/** 
	 * A Friend's status changed, such as available to unavailable or vice versa.
	 *
	 * @param friendId see the comments of parameter "friendId" in function "receivedResultOfAddFriend". 
	 * @param status available/unavailable, a friend's current status.
	 */
	void receivedPresenceChanged(String friendId, String status);
	
	
	/** 
	 * Received a friend's location information.
	 *
	 * @param fromID sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 * @param location the location information.
	 */
	void receivedLocation(String fromID, in GeoLocation location);
	
	
	/** 
	 * A friend stop sending his location to you.
	 *
	 * @param fromFriendId friend id, see the comments of parameter "friendId" in function "receivedResultOfAddFriend". 
	 */
	void receivedStopSharingLoaction(String fromFriendId);

	
	/** 
	 * A friend want to know your current location. If you accept, you will send your location to him, that means, developer
	 * should call "shareLocation" or "shareLocationByDistance" via IFriend instance.
	 *
	 * @param fromID sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 * @param tips reason string.
	 */
	void receivedRequestLocation(String fromID, String tips);
	
	/** 
	 * A friend want to track your location. If you accept, you will send your location to him, that means, developer
	 * should call "shareLocation" or "shareLocationByDistance" via IFriend instance. 
	 *
	 * @param fromID sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 * @param tips reason string.
	 */
	void receivedFollowLocation(String fromID, String tips);
	
	/** 
	 * A friend stop tracking you. Developer should call "stopSharingLoaction" via IFriend instance. 
	 *
	 * @param fromFriendId sender Id, see the comments of parameter "fromID" in function "receivedMessage". 
	 */
	void receivedStopFollowLoaction(String fromFriendId);

}
