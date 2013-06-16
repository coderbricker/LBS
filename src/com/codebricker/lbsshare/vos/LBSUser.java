// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * encapsulate the information of LBS platform user
 * will return to client
 */
public class LBSUser implements Parcelable {
    private String userId;
    private String nick;
    private String email;
    private String phone;

    public LBSUser(String userId, String nick, String email, String phone) {
        this.userId = userId;
        this.nick = nick;
        this.email = email;
        this.phone = phone;
    }
    
    public LBSUser(Parcel source) {
    	userId = source.readString();
    	nick = source.readString();
    	email = source.readString();
    	phone = source.readString();
    }
        
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userId);
        dest.writeString(nick);
        dest.writeString(email);
        dest.writeString(phone);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick1) {
		this.nick = nick1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email1) {
		this.email = email1;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone1) {
		this.phone = phone1;
	}
	
	@Override
	public String toString(){
		return "userId =" + userId + " userNick = " + nick + " phone = " + phone
				+ " email = " + email;
		
	}

	public static final Parcelable.Creator<LBSUser> CREATOR = new Parcelable.Creator<LBSUser>() {

        @Override
        public LBSUser createFromParcel(Parcel source) {
            return new LBSUser(source);
        }

        @Override
        public LBSUser[] newArray(int size) {
            return new LBSUser[size];
        }
    };

}
