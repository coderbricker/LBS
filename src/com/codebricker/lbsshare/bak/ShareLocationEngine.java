// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.

package com.codebricker.lbsshare.bak;

import android.content.Context;
import android.util.Log;

import com.codebricker.lbsshare.vos.GeoLocation;
import com.codebricker.lbsshare.vos.Preferences;

/**
 * the engine for share location
 */
public class ShareLocationEngine {

    private static final String TAG = "ShareLocationEngine";
    public static final int SHARE_MODE_ONE_TIME = 100;
    public static final int SHARE_MODE_PERIODICALLY = 101;
    public static final int SHARE_MODE_BY_DISTANCELY = 102;

    public static final String SHARE_LOCATION_GAP_TIME = "gapTime";
    public static final String SHARE_LOCATION_DISTANCE = "distance";
    public static final String SHARE_LOCATION_MODE = "shareLocationMode";
    public static final String ALWAYS_SHARE_LOCATION = "alwaysshare";
    

    private GeoLocation location;
    private ShareDistanceTimeGapStrategy shareDistanceStrategy;

    public ShareLocationEngine(GeoLocation location) {
        this.setLocation(location);
        this.shareDistanceStrategy = new ShareDistanceTimeGapStrategy();
    }
    
    
    /**
     * <p>share location according to setting preference</P>
     * @param context
     * @param toFriend
     */
    public static void shareContinously(final Context context, String toFriend) {
    	Preferences setting = new Preferences(context);
        int shareLocationMode = Integer.parseInt(setting.getParameter(ShareLocationEngine.SHARE_LOCATION_MODE));
        Log.d(TAG, "shareContinously" + shareLocationMode);
        
        if (shareLocationMode == ShareLocationEngine.SHARE_MODE_PERIODICALLY) {
        	int time = Integer.parseInt(setting.getParameter(ShareLocationEngine.SHARE_LOCATION_GAP_TIME));
//        	try {
//        		((LBSClientMainActivity) context).iFriend.shareLocation(toFriend, true, time);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
        } else if (shareLocationMode == ShareLocationEngine.SHARE_MODE_BY_DISTANCELY) {
        	int distance = Integer.parseInt(setting.getParameter(ShareLocationEngine.SHARE_LOCATION_DISTANCE));
//        	try {
//        		((LBSClientMainActivity) context).iFriend.shareLocationByDistance(toFriend, true, distance);
//			} catch (RemoteException e) {
//				e.printStackTrace();
//			}
        }
    }

    public void setmShareDistanceStrategy(ShareDistanceTimeGapStrategy mShareDistanceStrategy) {
        this.shareDistanceStrategy = mShareDistanceStrategy;
    }

    public ShareDistanceTimeGapStrategy getmShareDistanceStrategy() {
        return shareDistanceStrategy;
    }

    public GeoLocation getLocation() {
		return location;
	}


	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	public static class ShareDistanceTimeGapStrategy {
        // default value in milliseconds
        public static final int TIME_GAP_MIN_DEFAULT = 1000;

        // max value in milliseconds = 1hour
        public static final int TIME_GAP_MAX_DEFAULT = 1000 * 3600;

        private int timeGap = TIME_GAP_MIN_DEFAULT;

        public ShareDistanceTimeGapStrategy() {

        }

        public int chooseTime(final int time) {
            if (time > TIME_GAP_MIN_DEFAULT && time < TIME_GAP_MAX_DEFAULT) {
                timeGap = time;
            }

            return timeGap;
        }

        public void setTimeGap(int timeGap) {
            this.timeGap = timeGap;
        }

        public int getTimeGap() {
            return timeGap;
        }
    }

}
