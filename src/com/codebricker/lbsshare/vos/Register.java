package com.codebricker.lbsshare.vos;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;


public class Register implements Parcelable {

	private static final String ATTR_USER_NAME = "userName";
	private static final String ATTR_PASS_WORD = "passWord";
	private static final String ATTR_EMAIL = "email";
	private static final String ATTR_PHONE = "phone";
	private static final String ATTR_REGISTER_TIMES = "registerTimes";
	
	
	private String userName;
	private String passWord;
	private String email;
	private String phone;
	private int registerTimes;
	
	public Register() {
		
	}
	
	public Register(String userName, String passWord, String email, String phone) {
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.phone = phone;
	}
	
	
	public Register(Parcel dest) {
		this.userName = dest.readString();
		this.passWord = dest.readString();
		this.email = dest.readString();
		this.phone = dest.readString();
		this.registerTimes = dest.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(userName);
		dest.writeString(passWord);
		dest.writeString(email);
		dest.writeString(phone);
		dest.writeInt(registerTimes);
	}

	public static final Parcelable.Creator<Register> CREATOR = new Parcelable.Creator<Register>() {

        @Override
        public Register createFromParcel(Parcel source) {
            return new Register(source);
        }

        @Override
        public Register[] newArray(int size) {
            return new Register[size];
        }
    };
	
    public void saveInPreference(Context context) {
    	Preferences preferences = new Preferences(context);
    	preferences.saveRegisterValue(ATTR_REGISTER_TIMES, registerTimes);
    	preferences.saveRegisterValue(ATTR_USER_NAME, userName);
    	preferences.saveRegisterValue(ATTR_PASS_WORD, passWord);
    	preferences.saveRegisterValue(ATTR_EMAIL, email);
    	preferences.saveRegisterValue(ATTR_PHONE, phone);
    }
    
    
    public void getRegisterFromePreference(Context context) {
    	Preferences preferences = new Preferences(context);
    	String times = preferences.getRegisterValue(ATTR_REGISTER_TIMES);
    	String name = preferences.getRegisterValue(ATTR_USER_NAME);
    	String password = preferences.getRegisterValue(ATTR_PASS_WORD);
    	String email = preferences.getRegisterValue(ATTR_EMAIL);
    	String phone = preferences.getRegisterValue(ATTR_PHONE);
    	if (times != null && !times.equals("") && name != null && password != null
    		&& email != null && phone != null) {
    		this.email = email;
    		this.passWord = password;
    		this.userName = name;
    		this.phone = phone;
    		this.registerTimes = Integer.parseInt(times); 
    	}
    }
    
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName1) {
		this.userName = userName1;
	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone1) {
		this.phone = phone1;
	}

	public int getRegisterTimes() {
		return registerTimes;
	}

	public void setRegisterTimes(int registerTimes) {
		this.registerTimes = registerTimes;
	}

}
