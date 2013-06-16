// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * encapsulate the information of a Group
 */
public class GroupInfo implements Parcelable {
    /**
     * groupId, it must contains service after "@"
     */
	private String groupId;
    private String groupName;
    private String groupDescription;
          
    public GroupInfo(String group) {
        this.groupId = group;
    }
    
    public GroupInfo(String groupId, String groupName, String description) {
    	this.groupId = groupId;
    	this.groupName = groupName;
    	this.groupDescription = description;
    }
    
    public GroupInfo(Parcel pl) {
    	groupId = pl.readString();
        groupName = pl.readString();
        groupDescription = pl.readString();
    }
    
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    	dest.writeString(groupId);
        dest.writeString(groupName);
        dest.writeString(groupDescription);
    }

    public static final Parcelable.Creator<GroupInfo> CREATOR = new Parcelable.Creator<GroupInfo>() {

        @Override
        public GroupInfo createFromParcel(Parcel source) {
            return new GroupInfo(source);
        }

        @Override
        public GroupInfo[] newArray(int size) {
            return new GroupInfo[size];
        }

    };
    
    /**
     * <p>check whether the account is legal</P>
     * @return
     */
    public boolean check() {
    	if (this.groupId.contains("@")) {
    		return true;
    	} else { 
    		return false;
    	}
    }
    
    public String getGroupId() {
        return groupId;
    }
    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getGroupDescription() {
        return groupDescription;
    }
    
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }
    
}
