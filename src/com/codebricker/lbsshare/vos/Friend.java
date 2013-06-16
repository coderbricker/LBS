// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * encapsulate the information of friend
 * @author carlswang
 * 
 */
public class Friend implements Parcelable {
    
    //the name of account
    private String account;
    private String nickName;
    private String email;
    private String phone;
    
    //the state of friend
    /**
     * for simple there is only two states: PRESENCE_OFFLINE(0, unavailable)/PRESENCE_ONLINE(1, available)
     */
    private String presence;  

    /**
     * a person must has its account
     */
    public Friend(String account) {
    	this.account = account;
    }

    public Friend(String account, String nick, String email, String phone, String presence) {
    	this.account = account;
    	this.nickName = nick;
    	this.email = email;
    	this.phone = phone;
    	this.presence = presence;
    }
    
    
    public Friend(Parcel pl) {
        this.account = pl.readString();
        this.nickName = pl.readString();
        this.presence = pl.readString();
        this.email = pl.readString();
        this.phone = pl.readString();
        
    }

    
    @Override
    public String toString() {
        return "FriendInfoDomain [account=" + account + ", presence=" + presence + ", nickName=" 
        		+ nickName + "email=" + email + "phone=" + phone + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(account);
        dest.writeString(nickName);
        dest.writeString(presence);
        dest.writeString(email);
        dest.writeString(phone);
    }

    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>() {

        @Override
        public Friend createFromParcel(Parcel source) {
            return new Friend(source);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
    
    
    /**
     * <p>check whether the account is legal</P>
     * @return
     */
    public boolean check() {
    	if (this.account.contains("@")) {
    		return true;
    	} else { 
    		return false;
    	}
    }
    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    

    public void setPresence(String presence) {
        this.presence = presence;
    }

    public String getPresence() {
        return presence;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
