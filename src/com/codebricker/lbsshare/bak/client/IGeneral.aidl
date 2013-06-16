// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak.client;

import com.codebricker.lbsshare.bak.client.IGeneralListener;
import com.codebricker.lbsshare.vos.LBSUser;

/** 
 * The basic interface of lbs platform, developers could use it to authenticate his application, get friend/group/pubsub interface,
 * get 3rd applications based on this platform, get the status of platform, and get user information on this platform etc.
 * All the interfaces on lbs platform are consisted of general interface, friend(read) interface, operate friend(write) interface,
 * group(read) interface, operate group(write) interface, publish interface, subscribe interface, internal interface(reserved) and 
 * listener interface for each interface.
 * all the other interfaces except listener interface can only be retrieved by general interface.
 * This platform runs as a service, and the action name of this interface is "com.codebricker.lbsshare.bak.client.IGeneral",      
 * and developers should use function "bindService" to get the instance of this interface. And if the application want to get the
 * message returned from lbs platform, developers should call function "listen" to add listener interface.
 *  
 * @brief The basic interface of lbs platform, developers should bind it firstly before retrieved any other interface on lbs platform.
 * @note A developer should call function "authenticate" firstly when the application developed based on this platform runs at the first time, 
 * after it receives authenticate successful message via function "receiveAuthenicateResult" in IGeneralListener interface. If the application
 * developed by you is not authenticated(the appId string is illegal), it will receive not authenticated RemoteException.  
 *
 */
interface IGeneral {
	/**
	 * Authorize your application on lbs platform, this function should run firstly after the application is installed. Before using this function,  
	 * a developer should apply an appId and certificate on our register platform, see website:****
	 * 
	 * @param appName the name of the application developed based on lbs platform.
	 * @param appId applied from lbs register platform.
	 * @param vendor the owner of the application.
	 * @param certificate a string that lbs register platform generated while applied for lbs platform's apis successfully.
	 * @param listener listen the result of authenticate, the listener should be the same listener as the parameter in function
	 * "registerInToLBSPlatform" if developer use both of the two functions.
	 */
	void authenticate(String appName, String appId, String vendor, String certificate, IGeneralListener listener);
	
	/**
	 * Register into lbs platform to receive general messages from lbs platform.
	 * It is different from the function "authenticate". Function "authenticate" will send the user's authenticate information to our lbs server, 
	 * then the verify information will be feed back into lbs platform, so this function should be called firstly, while function                
	 * "registerInToLBSPlatform" only register a listener on lbs platform to receive general message, but if the application is not authenticated    
	 * successfully, this function will return false and remote exception will be also send.
	 *
	 * @param packageName the package name of the application developed based on lbs platform.
	 * @param appId applied from lbs register platform.
	 * @param listener listen some general information from our lbs server, the listener should be the same listener as that of function 
	 * "authenticate" if developer use both functions.
	 * @param eventTypes reserved.
	 * @return true/false, true if the application is already authenticated successfully, false if not.
	 */
	boolean registerInToLBSPlatform(String packageName, String appId, IGeneralListener listener, int eventTypes);
	
	/**
	 * Check whether lbs platform works. If the lbs platform works, all the interfaces on lbs platform can return right information, if not, error or 
	 * wrong information will send to application via listener interface. And if the lbs platform changes status from working to not working, 
	 * the application will receive message via "receiveServiceStatusChanged" in IGeneralListener interface, vice versa.               
	 *
	 * @return true, applications could call the functions in interfaces to retrieve right message; false, lbs platform could not handle applications' requests.
	 */
	boolean isServiceAvailable();
	
	/**
	 * Get the user information on lbs platform. The user information contains the user id, nick name, phone, email, and etc. 
	 *
	 * @return user's account information or null if there is none.
	 */
	LBSUser getUserInfo();
	
	
	
	/**
	 * Get friend read operation binder, please refer to IFriend.aidl. It is the only way to retrieve friend read interface, and 
	 * when user get the IBinder interface instance, he must use function "asInterface" to change into the IBinder to Aidl interface. It is 
	 * like the process of bindering a servcie, and the same as all the other "getXXBinder" functions.
	 *
	 * @param appId applied from lbs register platform.
	 * @return friend read binder or remote exception if the appid is not authenticated.
	 */
	IBinder getFriendReadBinder(String appId);
	
	/**
	 * Get friend write operation binder, please refer to IOperateFriend.aidl. See the comments of function "getFriendReadBinder".
	 *
	 * @param appId applied from lbs register platform.
	 * @return friend write binder or remote exception if the appid is not authenticated.
	 */
	IBinder getFriendWriteBinder(String appId);
	
	
	/**
	 * Get Group read operation binder, please refer to IGroup.aidl. See the comments of function "getFriendReadBinder".
	 *
	 * @param appId applied from lbs register platform.
	 * @return group read binder or remote exception if the appid is not authenticated.
	 */
	IBinder getGroupReadBinder(String appId);
	
	
	/**
	 * Get Group write operation binder, please refer to IOperateGroup.aidl. See the comments of function "getFriendReadBinder".
	 *
	 * @param appId applied from lbs register platform.
	 * @return group write binder or remote exception if the appid is not authenticated.
	 */
	IBinder getGroupWriteBinder(String appId);
	
	/**
	 * Get publish binder, please refer to IPublish.aidl. See the comments of function "getFriendReadBinder".
	 *
	 * @param appId applied from lbs register platform.
	 * @return publish binder or remote exception if the appid is not authenticated.
	 */
	IBinder getPublishBinder(String appId);
	
	/**
	 * Get subscribe binder, please refer to ISubscribe.aidl. See the comments of function "getFriendReadBinder".
	 *
	 * @param appId applied from lbs register platform.
	 * @return subscribe binder or remote exception if the appid is not authenticated.
	 */
	IBinder getSubscribeBinder(String appId);
	
	/**
	 * Get internal binder, please refer to IInternal.aidl. See the comments of function "getFriendReadBinder".
	 * this interface is reserved, developer should not use this interface.
	 *
	 * @param appId applied from lbs register platform.
	 * @return internal binder or remote exception if the appid is not authenticated.
	 */
	IBinder getInternalBinder(String appId);
}
