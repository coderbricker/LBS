package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {

	@SuppressWarnings("unused")
	private static final String ATTR_PASS_WORD = "passWord";
	@SuppressWarnings("unused")
	private static final String ATTR_EMAIL = "email";
	
	
	private String passWord;
	private String email;
	
	public Login() {
		
	}
	
	public Login(String email, String passWord) {
		this.passWord = passWord;
		this.email = email;
	}
	
	
	public Login(Parcel dest) {
		this.email = dest.readString();
		this.passWord = dest.readString();	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(email);
		dest.writeString(passWord);
	}

	public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {

        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
    

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord1) {
		this.passWord = passWord1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email1) {
		this.email = email1;
	}

}
