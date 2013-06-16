package com.codebricker.lbsshare;

public class ConstantData {
    
    public static final int MENU_SHARELOCATION = 0;
    public static final int MENU_REQUESTLOCATION = 1;
    public static final int MENU_FOLLOWLOCATION = 2;
    public static final int MENU_SHARECONTINUSE = 3;
    public static final int MENU_REMOVEFRIEND = 4;
    public static final int PRESENCE_ONLINE = 1;
    public static final int PRESENCE_OFFLINE = 0;
    
    public static final int CONNECTION_UNKNOWN = 0;
    public static final int CONNECTION_CONNECTED = 1;
    public static final int CONNECTION_FAILED = 2;
    public static final int CONNECTION_DISCONNECTED = 3;
    
    public static final int REQUEST_MAP_ACTIVITY = 1;
    public static final int REQUEST_GROUPMAP_ACTIVITY = 200;
    public static final int REQUEST_REGISTER_ACTIVITY = 2;
    
    public static final int SHARE_STATE = 100;
    
    
    // engine handle all messages

    public static final int SHARE_LOCATION_MSG = 102;
    public static final int SHARE_LOCATION_RETURN_MSG = 103;
    public static final int REQUEST_LOCATION_MSG = 104;
    public static final int REQUEST_LOCATION_RETURN_MSG = 105;
    public static final int REQUEST_ROSTER_MSG = 106;
    public static final int REQUEST_FRIENDS_RETURN_MSG = 107;

    public static final int ADD_FRIEND_MSG = 109;
    public static final int ADD_FRIEND_RETURN_MSG = 110;
    public static final int REMOVE_FRIEND_MSG = 111;
    public static final int REMOVE_FRIEND_RETURN_MSG = 112;
    
    
    public static final int FOLLOW_LOCATION_MSG = 115;
    public static final int FOLLOW_LOCATION_RETURN_MSG = 116;

    public static final int SHARE_LOCATION_DISTANCE_MSG = 117;
    // we use SHARE_LOCATION_RETURN_MSG to replace SHARE_LOCATION_DISTANCE_RETURN_MSG
    public static final int SHARE_LOCATION_DISTANCE_RETURN_MSG = 118;
    
    public static final int SET_GROUP_SETTING_MSG = 121;
    public static final int SET_GROUP_SETTING_RETURN_MSG = 122;
    public static final int QUERY_GROUP_SETTING_MSG = 123;
    public static final int QUERY_GROUP_SETTING_RETURN_MSG = 124;
    
    public static final int QUERY_GROUP_INFORMATION_MSG = 125;
   

    public static final int CREATE_GROUP_MSG = 201;
    public static final int CREATE_GROUP_RETURN_MSG = 202;
    
    public static final int GET_GROUP_MSG = 203;
    public static final int GET_GROUP_RETURN_MSG = 204;
    
    public static final int ADD_GROUP_MEMBER_MSG = 205;
    public static final int ADD_GROUP_MEMBER_RETURN_MSG = 206;
    
    public static final int SHARE_LOCATION_GROUP_MSG = 207;
    
    
    public static final int JOIN_GROUP_MSG = 209;
    public static final int OPERATION_RESULT_RETURN_MSG = 210;
    
    public static final int JOIN_GROUP_ANSWER = 211;
    public static final int QUERY_GROUPS_IN_SERVER_MSG = 212;
    public static final int QUERY_GROUPS_IN_SERVER_RETURN_MSG = 213;

    public static final int GROUP_SHARE_STATE_MSG = 214;
    public static final int GET_GROUP_USER_MSG = 215;
    public static final int RECEIVE_NOTIFICATION = 216;
    public static final int GET_ONE_GROUP_MSG = 217;
    
    public static final int DIALOG_ABOUT = 300;
    public static final int DIALOG_CREATE_GROUP = 301;
    public static final int DIALOG_SEARCH_GROUP = 302;
    public static final int DIALOG_ADD_FRIEND = 303;
    public static final int DIALOG_FRIEND_INPUT_ILLEGAL = 304;
    public static final int DIALOG_GROUP_INPUT_ILLEGAL = 305;
    public static final int DIALOG_SEARCH_RESULT_GROUP = 306;
    
    public static final int DEFAULT_GROUP_SETTING_INTERVAL = 20;
    public static final String DEFAULT_APPID = "1234567890";
    public static final String APPLIED_APPID = "KLCDVNKURD483WSSUQJ8R8OD";
    public static final String APPLIED_KEY = "JNGEOY0X";
    
    public static final String CLIENT_ACTION_ADD_FRIEND = "addFriend";
    public static final String CLIENT_ACTION_REMOVE_FRIEND = "remvoeFriend";
    public static final String CLIENT_ACTION_CREATE_GROUP = "createGroup";
    public static final String CLIENT_ACTION_REMOVE_GROUP = "removeGroup";
    public static final String CLIENT_ACTION_EXIT_GROUP = "exitGroup";
    
    public static final String CLIENT_ACTION_INVITE_USER_INTO_GROUP = "inviteUserIntoGroup";
    public static final String CLIENT_ACTION_JOIN_GROUP = "joinGroup";
    public static final String CLIENT_ACTION_REMOVE_MEMBER_FROM_GROUP = "removeMemberFromGroup";
    public static final String CLIENT_ACTION_SET_MEMBER_AS_OWNER = "setMemberAsOwner";
    
    public static final String NOTIFY_UPDATE_GROUP_SETTING = "UPDATEGROUPSETTING";
    
    
    public static final String Description_Regex = "^[^<>]*$";
    
    public static final String Name_Regex = "^[\\w-_\u4e00-\u9fa5]{5,16}$";
    public static final String GroupName_Regex = "^[\\w-_\u4e00-\u9fa5]{5,16}$";
    public static final String Email_Regex = 
//    		"(\\w+\\.?)*\\w+@\\w*(\\.\\w*)+";
    "^\\w+([-\\+\\.]\\w+)*@\\w+([-\\.]\\w+)*\\.\\w+([-\\.]\\w+)*$";
    public static final String Phone_Regex =
    		"^(1(3|5|8)[0-9])\\d{8}$";
//    		"\\+?\\d*";
    

}
