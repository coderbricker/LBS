// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client;


/**
 * General listener to receive message or notification from lbs platform. such as the authentication result,
 * and lbs platform working status changed, etc. It is corresponding to "IGeneral" interface, which means it retrieve the asynchronous
 * return value of function in "IGeneral" interface.
 *
 * Developer can use function "authenticate" or "registerInToLBSPlatform" in IGeneral interface to register a
 * general listener instance, and make sure the instace is the same in the two functions, and user who want to receive general message 
 * should implement all the functions here.
 */
interface IGeneralListener {
	/**
	 * It is the feed back information of application's authenticate request.
	 * 
	 * @param succedded a return value, true if succeeded to authenticate; false if failed, and error message could be retrieved from parameter "reason".
	 * @param reason a return value, the error message if authenticate failed, or null("") if authenticate successfully. 
	 */
	void receiveAuthenticateResult(boolean succeeded, String reason);
	
	/**
	 * Notify applications that the working status of lbs platform has been changed. Only when statusType equals "1" means
	 * the platform works well. the statusType is also the status of the connection to our lbs server, which contains four statuses.
	 *  <ul>
	 *  <li>CONNECTION_UNKNOWN - 0, unknown status, when lbs platform is not initialized finished.</li> 
	 *  <li>CONNECTION_CONNECTED - 1, connected to our lbs server.</li>
	 *  <li>CONNECTION_FAILED - 2, connected failed.</li>
	 *  <li>CONNECTION_DISCONNECTED - 3, disconnected to our lbs server because of some reason, such as io error, network error etc.</li>
	 *  </ul>
	 *
	 * @param statusType a return value, indicate the lbs platform working status.
	 * @param statusMsg readable note.
	 */
	void receiveServiceStatusChanged(int statusType, String statusMsg);
	
	/**
	 * 
	 * Lbs platform will send unuseful information continuously to identity whether client disconnect this platform.
	 */
	void receiveUnusefulInfo();
	
}