// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.bak;


/**
 * handle message in main thread.
 */
public class CommandEngine {
    private static final String TAG = "CommandEngine";

    public static class Command {
        public int cmdId;
        public String fun;

        public Command(int cmdId, String fun) {
            this.cmdId = cmdId;
            this.fun = fun;
        }
    }

    public static final int AUTHETICATE_RESULT_MSG = 0;
    public static final int CONNECTION_STATE_MSG = 1;
    public static final int FRIEND_MESSAGE_MSG = 2;
    public static final int FRIEND_BINARY_MESSAGE_MSG = 3;
    public static final int ADD_BY_FRIEND_MSG = 4;
    public static final int FRIEND_ADD_RESULT_MSG = 5;
    public static final int REMOVE_BY_FRIEND_MSG = 6;
    public static final int FRIEND_REMOVE_RESULT_MSG = 7;
    public static final int FRIEND_PRESENCE_CHANGE_MSG = 8;
    public static final int FRIEND_LOCATION_MSG = 9;
    public static final int FRIEND_STOP_SHARE_MSG = 10;
    public static final int FRIEND_REQUEST_LOCATION_MSG = 11;
    public static final int FRIEND_FOLLOW_LOCATION_MSG = 12;
    public static final int FRIEND_LIST_CHANGED_MSG = 13;
    public static final int FRIEND_STOP_FOLLOW_MSG = 14;
    public static final int GROUP_CREATE_RESULT_MSG = 15;
    public static final int GROUP_REMOVE_RESULT_MSG = 16;
    public static final int GROUP_EXIT_RESULT_MSG = 17;
    public static final int GROUP_INVITE_RESULT_MSG = 18;
    public static final int GROUP_INVITE_REQUEST_MSG = 19;
    public static final int GROUP_JOIN_RESULT_MSG = 20;
    public static final int GROUP_MEBMER_LIST_CHANGED_MSG = 21;
    public static final int GROUP_MESSAGE_MSG = 22;
    public static final int GROUP_NOTIFY_EXCUTE_SETTING_MSG = 23;
    public static final int GROUP_NOTIFY_SETTING_CHANGED_MSG = 24;
    public static final int GROUP_SEARCH_RESULT_MSG = 25;
    public static final int GROUP_LOCATION_MSG = 26;
    public static final int GROUP_STOP_SHARE_MSG = 27;
    public static final int GROUP_SET_MEMBER_OWNER_MSG = 28;
    public static final int GROUP_REMOVE_MEMBER_RESULT_MSG = 29;
    public static final int GROUP_LIST_CHANGED_MSG = 30;
    public static final int INTERAL_REGISTER_RESULT_MSG = 31;
    public static final int INTERAL_GET_USER_MSG = 32;
    public static final int APPLICATION_EXIT = 33;
    public static final int INTERAL_GET_APPS_MSG = 34;
    
    //down file message
    public static final int SELF_DOWNLOAD_RESULT = 35;
    public static final int SELF_DOWNLOAD_ICON_FINISHED = 36; 
    public static final int SELF_DOWNLOAD_INSTALLED_APPS_FINISHED = 37;
    public static final int SELF_LONGIN_FINISHED = 38;
        
    

    public static Command[] engineCommands = {new Command(AUTHETICATE_RESULT_MSG, "autheticate"),
        new Command(CONNECTION_STATE_MSG, "connectionState"), 
        new Command(FRIEND_MESSAGE_MSG, "message"),
        new Command(FRIEND_BINARY_MESSAGE_MSG, "binaryMessage"), 
        new Command(ADD_BY_FRIEND_MSG, "addByFriend"),
        new Command(FRIEND_ADD_RESULT_MSG, "addFriendResult"), new Command(REMOVE_BY_FRIEND_MSG, "removeByFriend"),
        new Command(FRIEND_REMOVE_RESULT_MSG, "removeFriendResult"),
        new Command(FRIEND_PRESENCE_CHANGE_MSG, "friendPresenceChanged"),
        new Command(FRIEND_LOCATION_MSG, "friendLocation"), new Command(FRIEND_STOP_SHARE_MSG, "friendStopShare"),
        new Command(FRIEND_REQUEST_LOCATION_MSG, "friendRequestLoaction"),
        new Command(FRIEND_FOLLOW_LOCATION_MSG, "friendFollowLoaction"),
        new Command(FRIEND_LIST_CHANGED_MSG, "friendListChanged"),
        new Command(FRIEND_STOP_FOLLOW_MSG, "friendStopFollow"),
        new Command(GROUP_CREATE_RESULT_MSG, "groupCreateResult"),
        new Command(GROUP_REMOVE_RESULT_MSG, "groupRemoveResult"),
        new Command(GROUP_EXIT_RESULT_MSG, "groupExitResult"),
        new Command(GROUP_INVITE_RESULT_MSG, "groupInviteResult"),
        new Command(GROUP_INVITE_REQUEST_MSG, "groupInviteRequest"),
        new Command(GROUP_JOIN_RESULT_MSG, "groupJoinResult"),
        new Command(GROUP_MEBMER_LIST_CHANGED_MSG, "groupMemberListChanged"),
        new Command(GROUP_MESSAGE_MSG, "groupMessage"),
        new Command(GROUP_NOTIFY_EXCUTE_SETTING_MSG, "groupNotiyExcuteSetting"),
        new Command(GROUP_NOTIFY_SETTING_CHANGED_MSG, "groupNotiySettingChanged"),
        new Command(GROUP_SEARCH_RESULT_MSG, "groupSearchResult"), new Command(GROUP_LOCATION_MSG, "groupLocation"),
        new Command(GROUP_STOP_SHARE_MSG, "groupStopShare"),
        new Command(GROUP_SET_MEMBER_OWNER_MSG, "groupSetMemberOwner"),
        new Command(GROUP_REMOVE_MEMBER_RESULT_MSG, "groupRemoveMemberResult"),
        new Command(GROUP_LIST_CHANGED_MSG, "groupListChanged"),
        new Command(INTERAL_REGISTER_RESULT_MSG, "internalRegisterResult"),
        new Command(INTERAL_GET_USER_MSG, "internalGetUser"),
        new Command(APPLICATION_EXIT, "exitApplication"),
        new Command(INTERAL_GET_APPS_MSG, "internalGetApps"),
        new Command(SELF_DOWNLOAD_RESULT, "selfDownloadTip"),
        new Command(SELF_DOWNLOAD_ICON_FINISHED, "selfDownloadIconEnd"),
        new Command(SELF_DOWNLOAD_INSTALLED_APPS_FINISHED, "selfDownloadInstalledAppEnd"),
        new Command(SELF_LONGIN_FINISHED, "selfLoginEnd"),     
    };
//
//    /**
//     * transfer message to the aim handler
//     */
//    private Context context = null;
//    @SuppressWarnings("unused")
//	private ShareLocationEngine shareLocationEngine = null;
//
//    public CommandEngine(Context context, ShareLocationEngine locationEngine) {
//        this.context = context;
//        this.shareLocationEngine = locationEngine;
//    }
//
//    /**
//     * <p>dynamically decide which function should be called</P>
//     * @param funName method name
//     * @param message, method param is message
//     * @return
//     * @throws NoSuchMethodException
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     */
//    public Object dynaCall(String funName, Message message) throws NoSuchMethodException, InvocationTargetException,
//        IllegalAccessException {
//        Method meth = this.getClass().getMethod(funName, message.getClass());
//        return (meth.invoke(this, message));
//    }
//
//    
//    private void initInterface() {
//    	IGeneral iGeneral = ((LBSClientMainActivity) context).iGeneral;
//	 	MainHandler mainHandler = ((LBSClientMainActivity) context).mainHandler;
//	 	Register register = ((LBSClientMainActivity) context).register;
//	 	
//    	try {
//    		IBinder binder = iGeneral.getFriendReadBinder(ConstantData.DEFAULT_APPID);
//    		Log.d(TAG, "binder = " + binder);
//    		if (binder != null) {
//    			((LBSClientMainActivity) context).iFriend = IFriend.Stub.asInterface(binder);
//            	Log.d(TAG, "1 iFriend" + ((LBSClientMainActivity) context).iFriend);
//            	
//				ArrayList<Friend> friendList = (ArrayList<Friend>) ((LBSClientMainActivity) context)
//						.iFriend.getAllFriends();
//				((MainApplication) ((LBSClientMainActivity) context).getApplication()).friendManager
//					.init(friendList);
//				Log.d(TAG, "getAllFriends" + friendList);
//    		}
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//        
//    	try {
//    		IBinder binder = iGeneral.getFriendWriteBinder(
//        			ConstantData.DEFAULT_APPID);
//    		Log.d(TAG, "binder = " + binder);
//    		if (binder != null) {
//    			((LBSClientMainActivity) context).iOperateFriend = IOperateFriend.Stub.asInterface(binder);
//            	Log.d(TAG, "2 iOperateFriend" + ((LBSClientMainActivity) context).iOperateFriend);
//            	
//        		FriendListener listenerFriend = new FriendListener(mainHandler);
//        		((LBSClientMainActivity) context).iOperateFriend.listen(listenerFriend, 0);
//    		}
//    		
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//    	
//    	
//    	try {
//
//    		IBinder binder = iGeneral.getGroupReadBinder(
//        			ConstantData.DEFAULT_APPID);
//    		Log.d(TAG, "binder = " + binder);
//    		if (binder != null) {
//    			((LBSClientMainActivity) context).iGroup = IGroup.Stub.asInterface(iGeneral.getGroupReadBinder(
//            			ConstantData.DEFAULT_APPID));
//            	Log.d(TAG, "3 iGroup " + ((LBSClientMainActivity) context).iGroup);
//            	
//				ArrayList<GroupInfo> groups = (ArrayList<GroupInfo>) ((LBSClientMainActivity) context)
//						.iGroup.getJoinedGroups();
//
//				GroupManager groupManager = ((MainApplication) ((LBSClientMainActivity) context)
//						.getApplication()).groupManager;
//				GroupSettingManager groupSettingManager = ((MainApplication) ((LBSClientMainActivity) context)
//						.getApplication()).groupSettingManager;
//				groupManager.init(((LBSClientMainActivity) context).iGroup, groupSettingManager);
//				
//				Log.d(TAG, "getJoindGroups" + groups);
//    		}
//        	
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//    	
//    	try {
//    		IBinder binder = iGeneral.getGroupWriteBinder(
//        			ConstantData.DEFAULT_APPID);
//    		Log.d(TAG, "binder = " + binder);
//    		if (binder != null) {
//
//    			((LBSClientMainActivity) context).iOperateGroup = IOperateGroup.Stub.asInterface(iGeneral
//    					.getGroupWriteBinder(ConstantData.DEFAULT_APPID));
//            	Log.d(TAG, "4 iOperateGroup " + ((LBSClientMainActivity) context).iOperateGroup);
//            	
//        		GroupListener listenerGroup = new GroupListener(mainHandler);
//        		((LBSClientMainActivity) context).iOperateGroup.listen(listenerGroup, 0);
//    		}
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//    	
//    	
//    	try {
//    		IBinder binder = iGeneral.getInternalBinder(
//        			ConstantData.DEFAULT_APPID);
//    		Log.d(TAG, "binder = " + binder);
//    		if (binder != null) {
//    			((LBSClientMainActivity) context).internal = IInternal.Stub.asInterface(iGeneral.getInternalBinder(
//            			ConstantData.DEFAULT_APPID));
//            	Log.d(TAG, "5 IInternal " + ((LBSClientMainActivity) context).internal);
//            	
//        		InternalListener listenerInternal = new InternalListener(mainHandler);
//        		((LBSClientMainActivity) context).internal.listen(listenerInternal, 0);
//    		}
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//    	
//    	// register
//    	if (register != null && register.getRegisterTimes() < 1) {
//    		try {
//    			((LBSClientMainActivity) context).internal.setUserMap(register.getUserName(), 
//    					register.getPassWord(), register.getEmail(), register.getPhone());
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//    	}
//    	
//    	
//    	// get the third applications
//    	try {
//			((LBSClientMainActivity) context).internal.searchThirdApps("", 100);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//    	
//    }
//    
//    
//    /**
//     * <p>autheticate result</P>
//     * @return
//     */
//    public int autheticate(Message msg) {
//		
//		Bundle bl = msg.getData();
//		boolean res = bl.getBoolean("result");
////		String reason = bl.getString("reason");
//		
//		Login login = ((LBSClientMainActivity) context).login;
//		// for test
//		if (res && login != null) {
//		 	IGeneral iGeneral = ((LBSClientMainActivity) context).iGeneral;
//		 	MainHandler mainHandler = ((LBSClientMainActivity) context).mainHandler;
//			try {
//        		IBinder binder = iGeneral.getInternalBinder(
//            			ConstantData.DEFAULT_APPID);
//        		Log.d(TAG, "binder = " + binder);
//        		if (binder != null) {
//        			((LBSClientMainActivity) context).internal = IInternal.Stub.asInterface(iGeneral.getInternalBinder(
//	            			ConstantData.DEFAULT_APPID));
//	            	Log.d(TAG, "5 IInternal " + ((LBSClientMainActivity) context).internal);
//	            
//            		InternalListener listenerInternal = new InternalListener(mainHandler);
//            		((LBSClientMainActivity) context).internal.listen(listenerInternal, 0);
//            		
//            		((LBSClientMainActivity) context).internal.login(login.getEmail(), login.getPassWord());
//            		
//        		}
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//			
//			return 0;
//		} else if (res) {
//			initInterface();
//        	((LBSClientMainActivity) context).progressDialog.dismiss();
//		} else {
//			((LBSClientMainActivity) context).progressDialog.dismiss();
//			
//			
//			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//	    	final Dialog dialog = new Dialog(context, R.style.Dialog_No_Title);
//	    	
//			View view = inflater.inflate(R.layout.dialog, null);
//			dialog.setContentView(view);
//			
//			TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
//			TextView msgView = (TextView) view.findViewById(R.id.dialog_message);
//			
//			titleView.setText(R.string.Prompt);
//			msgView.setText(R.string.authenticate_failed);
//			
//			Button okBtn = (Button) view.findViewById(R.id.dialog_signle_ok_btn);
//			okBtn.setVisibility(View.VISIBLE);
//			okBtn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//					((LBSClientMainActivity) context).finish();
//				}
//			});
//			
//			view.findViewById(R.id.dialog_layout_two_button).setVisibility(View.GONE);
//			dialog.show();
//		}
//    	
//
//		
//    	
//        return 0;
//    }
//
//    /**
//     * <p>connection state</P>
//     * @param msg
//     * @return
//     */
//    public int connectionState(Message msg) {
//        Bundle bl = msg.getData();
//        int state = bl.getInt("status");
//
//        //connected
////        if (state == ConstantData.CONNECTION_CONNECTED) {
////
////        } else 
//        if (state == ConstantData.CONNECTION_FAILED) {
//            ((LBSClientMainActivity) context).progressDialog.dismiss();
//
//            ((LBSClientMainActivity) context).popupSingleButtonDialog(context.getString(R.string.connect_failed),
//                context.getString(R.string.restart_app)).show();
//        } else {
//            ((LBSClientMainActivity) context).popupSingleButtonDialog(context.getString(R.string.connect_disconnectd),
//                context.getString(R.string.disconnectd_info)).show();
//        }
//
//        return state;
//    }
//
//    /**
//     * <p>get message</P>
//     * @param msg
//     * @return
//     */
//    public int message(Message msg) {
////        Bundle bl = msg.getData();
////        String friendId = bl.getString("friendId");
////        String text = bl.getString("text");
////        GeoLocation location = bl.getParcelable("location");
//
//        return 0;
//    }
//
//    /**
//     * <p>group get a message</P>
//     * @param msg
//     * @return
//     */
//    public int groupMessage(Message msg) {
////        Bundle bl = msg.getData();
////        String groupId = bl.getString("groupId");
////        String text = bl.getString("text");
////        GeoLocation location = bl.getParcelable("location");
//
//        return 0;
//    }
//
//    /**
//     * <p>get binary message</P>
//     * @param msg
//     * @return
//     */
//    public int binaryMessage(Message msg) {
////        Bundle bl = msg.getData();
////        String friendId = bl.getString("friendId");
////        byte[] text = bl.getByteArray("text");
////        GeoLocation location = bl.getParcelable("location");
//
//        return 0;
//    }
//
//    /**
//     * <p>get binary message</P>
//     * @param msg
//     * @return
//     */
//    public int addByFriend(Message msg) {
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        String nick = bl.getString("nick");
//        String tips = bl.getString("tips");
//
//        Log.d(TAG, "addByFriend+" + friendId + "tips" + tips);
//        Toast.makeText(context, String.format(context.getString(R.string.added_by_somebody), nick), 
//        		Toast.LENGTH_LONG).show();
//        return 0;
//    }
//
//    /**
//     * <p>the result of you add friend</P>
//     * @param msg
//     * @return
//     */
//    public int addFriendResult(Message msg) {
//        Bundle bl = msg.getData();
////        String friendId = bl.getString("friendId");
////        String nick = bl.getString("nick");
//        boolean result = bl.getBoolean("result");
//
////        if (result) {
////            MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
////            final FriendManager friendManager = mainApplication.friendManager;
////            friendManager.add(friendId, nick, "unavailable");
////        }
//        String message = "";
//        if (result) {
//        	message = (String) context.getText(R.string.add_somebody_success);
//        } else {
//        	message = (String) context.getText(R.string.add_somebody_failed);
//        }
//
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//        
//        return 0;
//    }
//
//    /**
//     * <p>you are removed by a friend in his friend list</P>
//     * @param msg
//     * @return
//     */
//    public int removeByFriend(Message msg) {
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        String nick = bl.getString("nick");
//
//        Log.d(TAG, "removeByFriend+" + friendId);
//        Toast.makeText(context, String.format(context.getString(R.string.removed_by_somebody), nick), 
//        		Toast.LENGTH_LONG).show();
//        return 0;
//    }
//
//    /**
//     * <p>the result of you remove a friend</P>
//     * @param msg
//     * @return
//     */
//    public int removeFriendResult(Message msg) {
//        Log.d(TAG, "removeFriendResult");
//
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        boolean result = bl.getBoolean("result");
//
//        if (result) {
//            MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//            final FriendManager friendManager = mainApplication.friendManager;
//            friendManager.remove(friendId);
//        }
//        
//        
//        String message = "";
//        if (result) {
//        	message = (String) context.getText(R.string.remove_somebody_success);
//        } else {
//        	message = (String) context.getText(R.string.remove_somebody_failed);
//        }
//
//        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//
//        return 0;
//    }
//
//    /**
//     * <p>a friend's presence changed</P>
//     * @param msg
//     * @return
//     */
//    public int friendPresenceChanged(Message msg) {
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        String presence = bl.getString("presence");
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        friendManager.updatePresence(friendId, presence);
//
//        return 0;
//    }
//
//    /**
//     * <p>receive a friend's location</P>
//     * @param msg
//     * @return
//     */
//    public int friendLocation(Message msg) {
//        Log.d(TAG, "friendLocation");
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        GeoLocation location = bl.getParcelable("location");
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        friendManager.setFriendLocation(friendId, location);
//        friendManager.setFriendLocationCome(friendId, true);
//
//        int loc = friendManager.contain(friendId);
//        if (loc > -1) {
//        	String nick = friendManager.getFriendList().get(loc).getNickName();
//			FriendStatus friendStatus = friendManager.getFriendStatus().get(loc);
//			if (friendStatus.request || (friendStatus.follow && !friendStatus.followReturn)) {
//				if (friendStatus.request) {
//					friendStatus.request = false;
//				} 
//				
////				else if (friendStatus.follow) {
////					friendStatus.follow = false;
////				}				
//				if (location != null 
//						&& !((LBSClientMainActivity) context).showMap) {
//					friendStatus.locationCome = false;
//					Intent intent = new Intent(context, ShowMapActivity.class);
//					intent.putExtra("account", friendId);
//					intent.putExtra("nick", nick);
//					intent.putExtra("location", location);
//					
//					Log.d(TAG, "nickName=" + nick);
//					
//					try {
//						((LBSClientMainActivity) (context)).startActivityForResult(intent,
//										ConstantData.REQUEST_MAP_ACTIVITY);
//						((LBSClientMainActivity) context).showMap = true;
//						if (friendStatus.follow) {
//							friendStatus.followReturn = true;
//						}
//					} catch (Exception e) {
//						Toast.makeText(context, e.getMessage(),
//								Toast.LENGTH_LONG).show();
//					}
//				}
//			}
//
//            Intent broadcast = new Intent();
//            broadcast.putExtra("account", friendId);
//            broadcast.putExtra("nick", nick);
//            broadcast.putExtra("location", location);
//            broadcast.setAction("cn.com.lbs.location");
//
//            Log.d(TAG, "nickName=" + nick);
//            // Context context = mContext.getApplicationContext();
//            context.sendBroadcast(broadcast);
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>a friend stop share his location to you</P>
//     * @param msg
//     * @return
//     */
//    public int friendStopShare(Message msg) {
////        Bundle bl = msg.getData();
////        String friendId = bl.getString("friendId");
//
//        return 0;
//    }
//
//    /**
//     * <p>a friend request your location</P>
//     * @param msg
//     * @return
//     */
//    public int friendRequestLoaction(Message msg) {
//        Bundle bl = msg.getData();
//        final String friendId = bl.getString("friendId");
//        //    	String tips = bl.getString("tips");	
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        final int loc = friendManager.contain(friendId);
//
//        
//        Preferences preferences = new Preferences(context);
//        String shareAlways = preferences.getParameter(ShareLocationEngine.ALWAYS_SHARE_LOCATION);
//        
//        
//        if (loc > -1 && !"true".equals(shareAlways) && !friendManager.getFriendStatus().get(loc).agreeRequest) {
//        	LayoutInflater layoutInflater = (LayoutInflater) ((LBSClientMainActivity) context).getLayoutInflater();
//			final Dialog dialog = new Dialog(context, R.style.Dialog_No_Title);
//	    	
//			View view = layoutInflater.inflate(R.layout.dialog, null);
//			dialog.setContentView(view);
//			dialog.show();
//			
//			
//			TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
//			TextView msgView = (TextView) view.findViewById(R.id.dialog_message);
//			
//			String friendNick = friendManager.getFriendList().get(loc).getNickName();
//			titleView.setText(context.getString(R.string.location_request));
//			msgView.setText(String.format(context.getString(R.string.from), friendNick));
//			
//			Button okBtn = (Button) view.findViewById(R.id.dialog_ok);
//			okBtn.setText(R.string.accept);
//			okBtn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//					friendManager.getFriendStatus().get(loc).agreeRequest = true;
//                    try {
//                        ((LBSClientMainActivity) context).iFriend.shareLocation(friendId, false, 0);
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//				}
//			});
//			
//			
//			Button cancelBtn = (Button) view.findViewById(R.id.dialog_cancel);
//			cancelBtn.setText(R.string.reject);
//			cancelBtn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//				}
//			});
//          
//        } else {
//            try {
//                ((LBSClientMainActivity) context).iFriend.shareLocation(friendId, false, 0);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>a friend want to follow your location</P>
//     * @param msg
//     * @return
//     */
//    public int friendFollowLoaction(Message msg) {
//        Bundle bl = msg.getData();
//        final String friendId = bl.getString("friendId");
//        //    	String tips = bl.getString("tips");	
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        final int loc = friendManager.contain(friendId);
//
//        
//        Preferences preferences = new Preferences(context);
//        String shareAlways = preferences.getParameter(ShareLocationEngine.ALWAYS_SHARE_LOCATION);
//        
//        
//        if (loc > -1 && !"true".equals(shareAlways) && !friendManager.getFriendStatus().get(loc).agreeFollow) {
//        	LayoutInflater layoutInflater = (LayoutInflater) ((LBSClientMainActivity) context).getLayoutInflater();
//			final Dialog dialog = new Dialog(context, R.style.Dialog_No_Title);
//	    	
//			View view = layoutInflater.inflate(R.layout.dialog, null);
//			dialog.setContentView(view);
//			dialog.show();
//			
//			
//			TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
//			TextView msgView = (TextView) view.findViewById(R.id.dialog_message);
//			
//
//			String friendNick = friendManager.getFriendList().get(loc).getNickName();
//			titleView.setText(context.getString(R.string.location_Follow_request));
//			msgView.setText(String.format(context.getString(R.string.from), friendNick));
//			
//			Button okBtn = (Button) view.findViewById(R.id.dialog_ok);
//			okBtn.setText(R.string.accept);
//			okBtn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//                    dialog.dismiss();
//                    friendManager.getFriendStatus().get(loc).agreeFollow = true;
//                    friendManager.setFriendShareContinous(friendId, true);
////                    friendManager.getFriendStatus().get(loc).shareContinuous = true;
//                    ShareLocationEngine.shareContinously(context, friendId);
//				}
//			});
//			
//			
//			Button cancelBtn = (Button) view.findViewById(R.id.dialog_cancel);
//			cancelBtn.setText(R.string.reject);
//			cancelBtn.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					dialog.dismiss();
//				}
//			});	
//        } else {
//        	friendManager.setFriendShareContinous(friendId, true);
//            ShareLocationEngine.shareContinously(context, friendId);
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>friend list changed</P>
//     * @param msg
//     * @return
//     */
//    public int friendListChanged(Message msg) {
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//        String nick = bl.getString("nick");
//        String email = bl.getString("email");
//        String phone = bl.getString("phone");
//        String action = bl.getString("action");
//
//        Log.d(TAG, "friendListChanged+" + friendId + " nick=" + nick  
//        		+ " email=" + email + " phone=" + phone + " action =" + action);
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        if (action.equals("add")) {
//            friendManager.add(friendId, nick, email, phone, "unavailable");
//        } else if (action.equals("remove")) {
//            friendManager.remove(friendId);
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>a friend stop follow you</P>
//     * @param msg
//     * @return
//     */
//    public int friendStopFollow(Message msg) {
//        Bundle bl = msg.getData();
//        String friendId = bl.getString("friendId");
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final FriendManager friendManager = mainApplication.friendManager;
//        friendManager.setFriendShareContinous(friendId, false);
//        
//        return 0;
//    }
//
//    /**
//     * <p>the result of create a group</P>
//     * @param msg
//     * @return
//     */
//    public int groupCreateResult(Message msg) {
//        Bundle bl = msg.getData();
//        //    	String groupId = bl.getString("groupId");	
//        String groupName = bl.getString("groupName");
//        @SuppressWarnings("unused")
//		String reason = bl.getString("reason");
//        boolean result = bl.getBoolean("result");
//
//        String message = "";
//        if (result) {
//            message = String.format(context.getString(R.string.create_group_result), groupName,
//                context.getString(R.string.successful));
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//            
//        } else {
//            message = String.format(context.getString(R.string.create_group_result), groupName,
//            		context.getString(R.string.failed));
//            
//            ((LBSClientMainActivity) context).popupSingleButtonDialog(
//            		context.getString(R.string.message), message).show();
//        }
//
//        
//        return 0;
//    }
//
//    /**
//     * <p>the result of remove a gorup</P>
//     * @param msg
//     * @return
//     */
//    public int groupRemoveResult(Message msg) {
////        Bundle bl = msg.getData();
////        String groupId = bl.getString("groupId");
////        String reason = bl.getString("reason");
////        boolean result = bl.getBoolean("result");
//
//        return 0;
//    }
//
//    /**
//     * <p>the result of exit a group</P>
//     * @param msg
//     * @return
//     */
//    public int groupExitResult(Message msg) {
////        Bundle bl = msg.getData();
////        String groupId = bl.getString("groupId");
////        String reason = bl.getString("reason");
////        boolean result = bl.getBoolean("result");
//
//        return 0;
//    }
//
//    /**
//     * <p>the result of invite a user</P>
//     * @param msg
//     * @return
//     */
//    public int groupInviteResult(Message msg) {
////        Bundle bl = msg.getData();
////        String groupId = bl.getString("groupId");
////        String userId = bl.getString("userId");
////        String reason = bl.getString("reason");
////        boolean result = bl.getBoolean("result");
//
//        return 0;
//    }
//
//    /**
//     * <p>somebody invite you into a group</P>
//     * @param msg
//     * @return
//     */
//    public int groupInviteRequest(Message msg) {
//        Log.d(TAG, "groupInviteRequest");
//        Bundle bl = msg.getData();
//        final String groupId = bl.getString("groupId");
//        String inviter = bl.getString("inviter");
//        //    	String reason = bl.getString("reason");	
//
//        String message = String.format(context.getString(R.string.invite_you), inviter, groupId);
//        
//        LayoutInflater layoutInflater = (LayoutInflater) ((LBSClientMainActivity) context).getLayoutInflater();
//		final Dialog dialog = new Dialog(context, R.style.Dialog_No_Title);
//    	
//		View view = layoutInflater.inflate(R.layout.dialog, null);
//		dialog.setContentView(view);
//		dialog.show();
//		
//		
//		TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
//		TextView msgView = (TextView) view.findViewById(R.id.dialog_message);
//		
//		titleView.setText(context.getString(R.string.message));
//		msgView.setText(message);
//		
//		Button okBtn = (Button) view.findViewById(R.id.dialog_ok);
//		okBtn.setText(R.string.accept);
//		okBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//				try {
//					((LBSClientMainActivity) context).iGroup.acceptInvitationToGroup(groupId);
//				} catch (RemoteException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		
//		Button cancelBtn = (Button) view.findViewById(R.id.dialog_cancel);
//		cancelBtn.setText(R.string.reject);
//		cancelBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//        return 0;
//    }
//
//    /**
//     * <p>the result of join a group</P>
//     * @param msg
//     * @return
//     */
//    public int groupJoinResult(Message msg) {
////        Bundle bl = msg.getData();
////        String groupId = bl.getString("groupId");
////        String reason = bl.getString("reason");
////        boolean result = bl.getBoolean("result");
//
//        return 0;
//    }
//
//    /**
//     * <p>group member list changed</P>
//     * @param msg
//     * @return
//     */
//    public int groupMemberListChanged(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String action = bl.getString("action");
//        String memberId = bl.getString("memberId");
//        String nick = bl.getString("nick");
//        String role = bl.getString("role");
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final GroupManager groupManager = mainApplication.groupManager;
//
//        if (action.equals("add")) {
//            groupManager.addMember(groupId, memberId, nick, role);
//        } else if (action.equals("remove")) {
//            groupManager.removeMember(groupId, memberId);
//            
//            // add a toast if yourself is removed.
//            String user = ((MainApplication) ((LBSClientMainActivity) context)
//            		.getApplication()).userInfo.getUserId();
//            if (memberId.equals(user)) {
//            	Toast.makeText(context, String.format(context.getString(R.string.you_are_removed_from), groupId), 
//            			Toast.LENGTH_LONG).show();
//            }
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>notify will excute setting</P>
//     * @param msg
//     * @return
//     */
//    public int groupNotiyExcuteSetting(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String action = bl.getString("action");
////        String settingName = bl.getString("settingName");
//        
//        Log.d(TAG, "groupNotiyExcuteSetting action=" + action);
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        Group group = mainApplication.groupManager.getGroup(groupId);
//        
//        if ("start".equals(action)) {
//            GroupSettingManager groupSettingManager = mainApplication.groupSettingManager;
//            String interval = groupSettingManager.get(groupId, groupId).getSettingValue("interval");
//            try {
//            	
//            	int interalTime = 60;
//            	if (interval != null && !"".equals(interval)) {
//            		interalTime = Integer.parseInt(interval);            		
//            	}
//            	
//                ((LBSClientMainActivity) context).iGroup.shareLocation(groupId, true, interalTime);
//                group.setShared(true);
//                
////                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
////                View groupDetailView = inflater.inflate(R.layout.group_detail, null);
////                ImageView shareLocationImageView = (ImageView) groupDetailView
////                    .findViewById(R.id.group_sharelocation_check);
////                
////               shareLocationImageView.setBackgroundResource(R.drawable.checkbox_selected);
//                    
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        } else if ("end".equals(action)) {
//            try {
//                ((LBSClientMainActivity) context).iGroup.stopShareLocation(groupId);
//                ((MainApplication) (((LBSClientMainActivity) context).getApplication())).groupManager
//                    .getGroupLocations().remove(groupId);
//                group.setShared(false);
//                
////                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
////                View groupDetailView = inflater.inflate(R.layout.group_detail, null);
////                ImageView shareLocationImageView = (ImageView) groupDetailView
////                    .findViewById(R.id.group_sharelocation_check);
////               shareLocationImageView.setBackgroundResource(R.drawable.checkbox);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//        ((MainApplication) ((LBSClientMainActivity) context).getApplication()).groupManager.update();
//        return 0;
//    }
//
//    /**
//     * <p>group setting changed</P>
//     * @param msg
//     * @return
//     */
//    public int groupNotiySettingChanged(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String settingName = bl.getString("settingName");
//
//        try {
//            List<Setting> settings = ((LBSClientMainActivity) context).iGroup.getGroupSetting(groupId, settingName);
//            MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//            GroupSettingManager settingManager = mainApplication.groupSettingManager;
//            settingManager.add(new GroupSetting(groupId, settingName, settings));
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
//    }
//
//    /**
//     * <p>the result of search</P>
//     * @param msg
//     * @return
//     */
//    public int groupSearchResult(Message msg) {
//        Bundle bl = msg.getData();
//        //    	String key = bl.getString("key");
//        //    	ArrayList<GroupInfo> groups = bl.getParcelableArrayList("searchGroups");
//
//        ((LBSClientMainActivity) context).searchProgressDialog.dismiss();
//        ((LBSClientMainActivity) context).createSearchGroupResultDialog(bl);
//
//        return 0;
//    }
//
//    /**
//     * <p>get group location</P>
//     * @note fromId is the format of groupId/userNickName
//     * @param msg
//     * @return
//     */
//    public int groupLocation(Message msg) {
//        Bundle bl = msg.getData();
//        String fromId = bl.getString("fromId");
//        GeoLocation location = bl.getParcelable("location");
//
//        ((LBSClientMainActivity) context).tabGroup.updateGroupLocationSharing(fromId, location);
//
//        return 0;
//    }
//
//    /**
//     * <p>somebody stop share location</P>
//     * @param msg
//     * @return
//     */
//    public int groupStopShare(Message msg) {
////        Bundle bl = msg.getData();
////        String fromId = bl.getString("fromId");
//
//        return 0;
//    }
//
//    /**
//     * <p>the result of set member as owner</P>
//     * @param msg
//     * @return
//     */
//    public int groupSetMemberOwner(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String memberId = bl.getString("memberId");
//        String reason = bl.getString("reason");
//        boolean result = bl.getBoolean("reason");
//
//        Log.d(TAG, "groupSetMemberOwner" + groupId + memberId + reason + result);
//
//        return 0;
//    }
//
//    /**
//     * <p>remove a group member</P>
//     * @param msg
//     * @return
//     */
//    public int groupRemoveMemberResult(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String memberId = bl.getString("memberId");
//        String reason = bl.getString("reason");
//        boolean result = bl.getBoolean("result");
//
//        Log.d(TAG, "groupRemoveMemberResult" + groupId + memberId + reason + result);
//
//        return 0;
//    }
//
//    /**
//     * <p>group list changed</P>
//     * @param msg
//     * @return
//     */
//    public int groupListChanged(Message msg) {
//        Bundle bl = msg.getData();
//        String groupId = bl.getString("groupId");
//        String action = bl.getString("action");
//
//        MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//        final GroupManager groupManager = mainApplication.groupManager;
//        if (action.equals("add")) {
//            String tap = String.format(context.getString(R.string.you_are_joined), groupId);
//            ((LBSClientMainActivity) context).popupSingleButtonDialog(context.getString(R.string.message), tap).show();
//
//            groupManager.addGroup(groupId);
//        } else if (action.equals("remove")) {
//            groupManager.removeGroup(groupId);
//
//            Dialog dialog = ((LBSClientMainActivity) context).tabGroup.getGroupDetailDialog();
//            if (dialog != null && dialog.isShowing()) {
//                dialog.dismiss();
//            }
//        }
//
//        return 0;
//    }
//
//    
//    public int internalRegisterResult(Message msg) {
//    	Bundle bl = msg.getData();
////        String email = bl.getString("email");
//        boolean result = bl.getBoolean("result");
//        
//        // write into shared preference
//        if (result) {
//        	Register register = ((LBSClientMainActivity) context).register;
//        	register.setRegisterTimes(1);
//        	register.saveInPreference(context);
//        	
//        	//retreive the user information.
//        	try {
//        		((MainApplication) ((LBSClientMainActivity) context)
//                		.getApplication()).userInfo = ((LBSClientMainActivity) context).iGeneral.getUserInfo();
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//        	
//        	Toast.makeText(context, context.getString(R.string.register_success), Toast.LENGTH_LONG).show();
//        } else {
//        	Toast.makeText(context, context.getString(R.string.register_failed), Toast.LENGTH_LONG).show();
//        }
//        
//        return 0;
//    }
//    
//    
//    public int internalGetUser(Message msg) {
//    	Bundle bl = msg.getData();
////        String email = bl.getString("email");
////        String phone = bl.getString("phone");
//        String userId = bl.getString("userId");
//        String nick = bl.getString("nick");
//        boolean result = bl.getBoolean("result");
//        
//        // write into shared preference
//        if (result && userId != null && !"".equals(userId)) {
//        	try {
//				((LBSClientMainActivity) context).iOperateFriend.addFriend(nick, userId);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
//        } else {
//        	String message = (String) context.getText(R.string.add_somebody_failed);
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//        }
//        
//        return 0;
//    }
//    
//    
//    public int exitApplication(Message msg) {
//    	LayoutInflater inflater = ((LBSClientMainActivity) context).getLayoutInflater();
//    	final Dialog dialog = new Dialog((LBSClientMainActivity) context, R.style.Dialog_No_Title);
//    	
//		View view = inflater.inflate(R.layout.dialog, null);
//		dialog.setContentView(view);
//		
//		TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
//		TextView msgView = (TextView) view.findViewById(R.id.dialog_message);
//		
//		titleView.setText(R.string.Prompt);
//		msgView.setText(R.string.connect_failed);
//		
//		Button okBtn = (Button) view.findViewById(R.id.dialog_signle_ok_btn);
//		okBtn.setVisibility(View.VISIBLE);
//		okBtn.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//				((LBSClientMainActivity) context).finish();
//			}
//		});
//		
//		view.findViewById(R.id.dialog_layout_two_button).setVisibility(View.GONE);
//		dialog.show();
//		
//    	return 0;
//    }
//    
//    
//    public int internalGetApps(Message msg) {
//    	Log.d(TAG, "internalGetApps");
//    	Bundle bl = msg.getData();
////    	String key = bl.getString("key");
////    	int count = bl.getInt("count");
//    	ArrayList<ThirdApp> apps = bl.getParcelableArrayList("apps");
//    			
//
//    	if (apps != null) {
//    		MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//    		final ThirdAppManager thirdAppManager = mainApplication.thirdAppManager;
//    		
//    		thirdAppManager.init(apps);	
//    	}
//		
//    	return 0;
//    }
//    
//    
//    public int selfDownloadTip(Message msg) {
//    	Log.d(TAG, "selfDownloadTip");
//    	
//    	Bundle bl = msg.getData();
//    	boolean res = bl.getBoolean("result");
//    	String reason = bl.getString("reason");
//    	String path = bl.getString("path");
//    	
//    	if (res) {
//    		Toast.makeText(context, "download file into " + path, Toast.LENGTH_LONG).show();
//    	} else {
//    		Toast.makeText(context, "download file failed! " + reason, Toast.LENGTH_LONG).show();
//    	}
//    	
//    	return 0;
//    	
//    }
//    
//    public int selfDownloadIconEnd(Message msg) {
//    	Log.d(TAG, "selfDownloadIconEnd");
//    	((LBSClientMainActivity) context).tabApps.adapter.notifyDataSetChanged();
//    	
//    	return 0;
//    }
//    
//    
//    public int selfDownloadInstalledAppEnd(Message msg) {
//    	Log.d(TAG, "selfDownloadInstalledAppEnd");
//    	MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//		final ThirdAppManager thirdAppManager = mainApplication.thirdAppManager;
//		
//		thirdAppManager.updateInstalledStatus();
//		
//    	return 0;
//    }
//    
//    
//    public int selfLoginEnd(Message msg) {
//    	Log.d(TAG, "selfLoginEnd");
//    	Bundle bl = msg.getData();
//    	
//    	String email = bl.getString("email");
//    	String userId = bl.getString("userId");
//    	String nick = bl.getString("nick");
//    	String phone = bl.getString("phone");
//    	boolean res = bl.getBoolean("result");
//    	
//    	if (res) {
//    		initInterface();
//    		
//    		Register register = new Register(nick, "", email, phone);
//    		register.setRegisterTimes(1);
//    		register.saveInPreference(context);
//    		MainApplication mainApplication = (MainApplication) ((LBSClientMainActivity) context).getApplication();
//    		mainApplication.userInfo.setEmail(email);
//    		mainApplication.userInfo.setNick(nick);
//    		mainApplication.userInfo.setPhone(phone);
//    		mainApplication.userInfo.setUserId(userId);
//    		
//    	}
//    	
//    	((LBSClientMainActivity) context).progressDialog.dismiss();
//    	
//    	return 0;
//    }
}
