// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.vos;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.codebricker.lbsshare.common.utils.Utils;

public class Preferences {

    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public void saveParameter(String name, String value) {
        SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_WORLD_READABLE);
        Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public void saveParameter(String name, int value) {
        SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_WORLD_READABLE);
        Editor editor = preferences.edit();
        editor.putString(name, String.valueOf(value));
        editor.commit();
    }

    public String getParameter(String name) {
        SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        String value = String.valueOf(map.get(name));
        if (Utils.isEmpty(value)) {
            return "";
        } else {
            return value;
        }
    }
    
    
    public void saveRegisterValue(String name, String value) {
    	SharedPreferences preferences = context.getSharedPreferences("Register", Context.MODE_WORLD_READABLE);
        Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }
    
    public void saveRegisterValue(String name, int value) {
    	SharedPreferences preferences = context.getSharedPreferences("Register", Context.MODE_WORLD_READABLE);
        Editor editor = preferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }
    
    public String getRegisterValue(String name) {
    	 SharedPreferences preferences = context.getSharedPreferences("Register", Context.MODE_PRIVATE);
         Map<String, ?> map = preferences.getAll();
         String value = String.valueOf(map.get(name));
         if (Utils.isEmpty(value)) {
             return "";
         } else {
             return value;
         }
    }
}
