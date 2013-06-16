// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * group member
 */
public class Member implements Parcelable {
	public String account;
	public String nick;
	public String role;
	
	public Member(String account, String nick, String role) {
		this.account = account;
		this.nick = nick;
		this.role = role;
	}
	
	public Member(Parcel source) {
		this.account = source.readString();
		this.nick = source.readString();
		this.role = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(account);
		arg0.writeString(nick);
		arg0.writeString(role);
		
	}
	
	public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>() {

        @Override
        public Member createFromParcel(Parcel source) {
            return new Member(source);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };
}
