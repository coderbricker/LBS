// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.internal;




/**
 * Internal listener to receive internal message or notification from lbs platform.  Developer should not use this interface.
 */
interface IInternalListener {
	/** 
	 * The result of store user information. The email address is the unique information to identity a user.
	 *
	 * @param email a user has a unique email address when register.
	 * @param result the result of store process.
	 */
	void receiveResultSetUserMap(String email, boolean result);
	
	/** 
	 * Get the user information including userId/nick/email/phone from our lbs server.
	 *
	 * @param email a user has a unique email address when register.
	 * @param phone a user may have a phone number.
	 * @param userId user id, the unique identity string of a friend.
	 * @param nick user's nick name, stored as vcard information.
	 * @param result the result of getting user information process.
	 */
	void receiveGetUserMap(String email, String phone, String userId, String nick, boolean result);	
	
	/**
	 * 
	 * Get the search result of third application informations developed based on lbs platform.
	 * 
	 * @param key the search key of third application.
	 * @param apps the list of lbs applications.
	 * @param realCount the real count of lbs applications. 
	 */
	
	/**
	 * 
	 * Get the user login return message.
	 * 
	 * @param email the user information.
	 * @param userId the user Id, the unique identity string of a friend.
	 * @param nick the user nick name.
	 * @param phone the user's phone number.
	 * @param result the user login result.
	 */
	void receiveLoginResult(String email, String userId, String nick, String phone, boolean result);
}
