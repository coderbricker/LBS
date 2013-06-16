// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * group setting key-values
 */
public class Setting implements Parcelable {
	public static final String ELEMENT = "item";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_TYPE = "type";
	
	private String key;
	private String value;
	private String type;
	
	public Setting(String key, String value, String type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}
	
	
	public Setting(Parcel source) {
		this.key = source.readString();
		this.value = source.readString();
		this.type = source.readString();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(key);
		dest.writeString(value);
		dest.writeString(type);
		
	}

	public static final Parcelable.Creator<Setting> CREATOR = new Parcelable.Creator<Setting>() {

        @Override
        public Setting createFromParcel(Parcel source) {
            return new Setting(source);
        }

        @Override
        public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };
    
	public String getKey() {
		return key;
	}

	public void setName(String name) {
		this.key = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value1) {
		this.value = value1;
	}


	public String getType() {
		return type;
	}


	public void setType(String typ) {
		this.type = typ;
	}

	
}
