// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client.internal;

import com.codebricker.lbsshare.bak.client.internal.IInternalListener;

/** 
 * The internal interface of lbs platform, it is reserved. Developer should not use this interface.
 * This interface could only be retrieved by function "getInternalBinder" in IGeneral interface, and after user get this interface,
 * he can call "listen" function to register a listener to communicate with our lbs platform.
 * 
 */
interface IInternal {
	/** 
	 * Register a internal listener to listen all asynchronous return message of the operations about internal handles.
	 *
	 * @param listener the interface instance to receive messages of internal handles.
	 * @param eventType reserved.
	 */
	void listen(IInternalListener listener, int eventType);
	
	/** 
	 * Remove an application's authenticate string.
	 *
	 * @param appId the application's id.
	 */
	boolean removeAuthenticate(String appId);
	
	/** 
	 * whether an application is authenticated.
	 *
	 * @param appId the application's id.
	 */
	boolean getAuthenticate(String appId);
	
	
	/** 
	 * Store user information including password/email/phone in our lbs server.
	 *
	 * @param user user id, the unique identity string of a friend.
	 * @param password used when login.
	 * @param email a user has a unique email address when register.
	 * @param phone a user may have a phone number.
	 */
	void setUserMap(String user, String password, String email, String phone);

	/** 
	 * Get user information including user id from our lbs server.
	 *
	 * @param email a user has a unique email address when register.
	 * @param phone a user may have a phone number.
	 */
	void getUserMap(String email, String phone);
	
	/** 
	 * Re login in the platform with new user and password.
	 *
	 * @param email a user has a unique email address when register.
	 * @param pass the user's password.
	 */
	void login(String email, String pass);
	
	
	/**
	 * Search the third applications developed by others based on lbs platform.
	 *
	 * @param key the search key, if key == "" or null, then return all the applications' information.
	 * @param maxCount the number user want to get.
	 */
	void searchThirdApps(String key, int maxCount);
	
	
	/**
	 * Set when(hour) to start share location, and when(hour) to end share location.
	 *
	 * @param start the start hour in format of 24 hours .
	 * @param end the end hour in format of 24 hours.
	 */
	void setShareLocationTime(String startHour, String endHour);
}


