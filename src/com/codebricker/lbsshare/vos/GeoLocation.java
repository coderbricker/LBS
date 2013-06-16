// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * encapsulate the information of a location
 */
public class GeoLocation implements Parcelable {

    private String from = "";
    private String to = "";
    private String accuracy = "";
    private String country = "";
    private String latitude = "";
    private String locality = "";
    private String longitude = "";
    private String language = "";
     
    public GeoLocation() {
        
    }

    public GeoLocation(Parcel pl) {
        from = pl.readString();
        to = pl.readString();
        accuracy = pl.readString();
        country = pl.readString();
        latitude = pl.readString();
        locality = pl.readString();
        longitude = pl.readString();
        language = pl.readString();
        
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(accuracy);
        dest.writeString(country);
        dest.writeString(latitude);
        dest.writeString(locality);
        dest.writeString(longitude);
        dest.writeString(language);
        
    }

    public static final Parcelable.Creator<GeoLocation> CREATOR = new Parcelable.Creator<GeoLocation>() {

        @Override
        public GeoLocation createFromParcel(Parcel source) {
            return new GeoLocation(source);
        }

        @Override
        public GeoLocation[] newArray(int size) {
            return new GeoLocation[size];
        }

    };

    @Override
    public String toString() {
        return "LocationDomain [accuracy=" + accuracy + ", country=" + country + ", from=" + from + ", language="
            + language + ", latitude=" + latitude + ", locality=" + locality + ", longitude=" + longitude + ", to="
            + to + "]";
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * <p>to jsonobject</P>
     * @param type
     * @return
     */
    public JSONObject toJObject() {
    	JSONObject jsonObject = new JSONObject();
    	try {
			jsonObject.put("from", from);
	    	jsonObject.put("to", to);
	    	jsonObject.put("accuracy", accuracy);
	    	jsonObject.put("country", country);
	    	jsonObject.put("language", language);
	    	jsonObject.put("latitude", latitude);
	    	jsonObject.put("locality", locality);
	    	jsonObject.put("longitude", longitude);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
    }

}
