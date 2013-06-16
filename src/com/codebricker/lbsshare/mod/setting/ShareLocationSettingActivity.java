// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.
package com.codebricker.lbsshare.mod.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.codebricker.lbsshare.R;
import com.codebricker.lbsshare.bak.ShareLocationEngine;
import com.codebricker.lbsshare.vos.Preferences;

/**
 * show ShareLocationSetting and update ShareLocationSetting
 * 
 */
public class ShareLocationSettingActivity extends Activity implements OnClickListener {
//    @SuppressWarnings("unused")
//    private static final String TAG = "locationsetting";
//
//    @SuppressWarnings("unused")
//    private static final int SHARE_PERIODICALLY = 0x100;
//    @SuppressWarnings("unused")
//    private static final int SHARE_BY_DISTANCE = 0x101;

    public static final int SHARE_PERIODICALLY_TWO_MINUTES = 2;
    private static final int SHARE_PERIODICALLY_FIVE_MINUTES = 5;
    private static final int SHARE_PERIODICALLY_TEN_MINUTES = 10;
    private static final int SHARE_PERIODICALLY_THIRTY_MINUTES = 30;
    private static final int SHARE_DISTANCE_TEN_METERS = 10;
    private static final int SHARE_DISTANCE_HUNDRED_METERS = 100;
    private static final int SHARE_DISTANCE_THUNDRED_METERS = 1000;
    private static final int SHARE_DISTANCE_FIVETHUNDRED_METERS = 5000;
    //    private static final int SHARE_DISTANCE_TENTHUNDRED_METERS = 10000;

    private RadioButton radioSharePeriodically;
    private RadioButton radioSharePeriodTwo;
    private RadioButton radioSharePeriodFive;
    private RadioButton radioSharePeriodTen;
    private RadioButton radioSharePeriodThirty;
    private int sharePeriodcallyTime = 0;

    private RadioButton radioShareByDis;
    private RadioButton radioShareDistanceTen;
    private RadioButton radioShareDistanceHundred;
    private RadioButton radioShareDistanceThousand;
    private RadioButton radioShareDistanceFiveThousand;
    private int shareDistanceValue = 0;

    private CheckBox shareAlways;
    
    private Button btnLocationSettingSave;
    private Button btnLocationSettingCancle;
    private Preferences service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_location_setting);
        service = new Preferences(this);
        initWindow();
    }

    /**
     * 
     *init the main window and get the component in UI.
     */
    private void initWindow() {
        radioSharePeriodically = (RadioButton) findViewById(R.id.mRadioSharePeriodically);
        radioSharePeriodically.setOnClickListener(this);
        radioSharePeriodically.setChecked(true);

        //period radio group
        radioSharePeriodTwo = (RadioButton) findViewById(R.id.two);
        radioSharePeriodTwo.setOnClickListener(this);
        radioSharePeriodTwo.setChecked(true);

        radioSharePeriodFive = (RadioButton) findViewById(R.id.five);
        radioSharePeriodFive.setOnClickListener(this);
        radioSharePeriodTen = (RadioButton) findViewById(R.id.ten);
        radioSharePeriodTen.setOnClickListener(this);
        radioSharePeriodThirty = (RadioButton) findViewById(R.id.halfhour);
        radioSharePeriodThirty.setOnClickListener(this);

        radioShareByDis = (RadioButton) findViewById(R.id.mRadioShareByDis);
        radioShareByDis.setOnClickListener(this);
        //distance radio group
        radioShareDistanceTen = (RadioButton) findViewById(R.id.tenmeters);
        radioShareDistanceTen.setOnClickListener(this);
        radioShareDistanceHundred = (RadioButton) findViewById(R.id.hundred);
        radioShareDistanceHundred.setOnClickListener(this);
        radioShareDistanceThousand = (RadioButton) findViewById(R.id.thousand);
        radioShareDistanceThousand.setOnClickListener(this);
        radioShareDistanceFiveThousand = (RadioButton) findViewById(R.id.fivethousand);
        radioShareDistanceFiveThousand.setOnClickListener(this);

        shareAlways = (CheckBox) findViewById(R.id.share_always_allow);
        Preferences preferences = new Preferences(this);
        String shareAlwaysStr = preferences.getParameter(ShareLocationEngine.ALWAYS_SHARE_LOCATION);
        if ("true".equals(shareAlwaysStr)) {
        	shareAlways.setChecked(true);
        } else {
        	shareAlways.setChecked(false);
        }
        
        btnLocationSettingSave = (Button) findViewById(R.id.btnLocationSettingSave);
        btnLocationSettingSave.setOnClickListener(this);
        btnLocationSettingCancle = (Button) findViewById(R.id.btnLocationSettingCancle);
        btnLocationSettingCancle.setOnClickListener(this);
        
        setPeriodRadioGroupEnable(true);

        int mode = Integer.valueOf(service.getParameter(ShareLocationEngine.SHARE_LOCATION_MODE));
        switch (mode) {
        case ShareLocationEngine.SHARE_MODE_ONE_TIME:
            break;
        case ShareLocationEngine.SHARE_MODE_PERIODICALLY:
            radioSharePeriodically.setChecked(true);
            String gapTime = service.getParameter(ShareLocationEngine.SHARE_LOCATION_GAP_TIME);
            sharePeriodcallyTime = Integer.parseInt(gapTime);

            setDistanceRadioGroupEnable(false);
            if (sharePeriodcallyTime == SHARE_PERIODICALLY_TWO_MINUTES) {
                radioSharePeriodTwo.setChecked(true);
            } else if (sharePeriodcallyTime == SHARE_PERIODICALLY_FIVE_MINUTES) {
                radioSharePeriodFive.setChecked(true);
            } else if (sharePeriodcallyTime == SHARE_PERIODICALLY_TEN_MINUTES) {
                radioSharePeriodTen.setChecked(true);
            } else if (sharePeriodcallyTime == SHARE_PERIODICALLY_THIRTY_MINUTES) {
                radioSharePeriodThirty.setChecked(true);
            }
            break;
        case ShareLocationEngine.SHARE_MODE_BY_DISTANCELY:
            radioShareByDis.setChecked(true);
            String distance = service.getParameter(ShareLocationEngine.SHARE_LOCATION_DISTANCE);
            shareDistanceValue = Integer.parseInt(distance);

            setPeriodRadioGroupEnable(false);
            if (shareDistanceValue == SHARE_DISTANCE_TEN_METERS) {
                radioShareDistanceTen.setChecked(true);
            } else if (shareDistanceValue == SHARE_DISTANCE_HUNDRED_METERS) {
                radioShareDistanceHundred.setChecked(true);
            } else if (shareDistanceValue == SHARE_DISTANCE_THUNDRED_METERS) {
                radioShareDistanceThousand.setChecked(true);
            } else if (shareDistanceValue == SHARE_DISTANCE_FIVETHUNDRED_METERS) {
                radioShareDistanceFiveThousand.setChecked(true);
            }

            break;
        default:
            break;
        }
    }

    private void setPeriodRadioGroupEnable(final boolean enable) {
        radioSharePeriodTwo.setEnabled(enable);
        radioSharePeriodFive.setEnabled(enable);
        radioSharePeriodTen.setEnabled(enable);
        radioSharePeriodThirty.setEnabled(enable);
        if (!enable) {
            ((RadioButton) findViewById(R.id.timehide)).setChecked(true);
        }
    }

    private void setDistanceRadioGroupEnable(final boolean enable) {
        radioShareDistanceTen.setEnabled(enable);
        radioShareDistanceHundred.setEnabled(enable);
        radioShareDistanceThousand.setEnabled(enable);
        radioShareDistanceFiveThousand.setEnabled(enable);
        if (!enable) {
            ((RadioButton) findViewById(R.id.distancehide)).setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.mRadioSharePeriodically:
            setPeriodRadioGroupEnable(true);
            setDistanceRadioGroupEnable(false);
            radioSharePeriodTwo.setChecked(true);
            sharePeriodcallyTime = SHARE_PERIODICALLY_TWO_MINUTES;
            break;
        case R.id.two:
            sharePeriodcallyTime = SHARE_PERIODICALLY_TWO_MINUTES;
            break;
        case R.id.five:
            sharePeriodcallyTime = SHARE_PERIODICALLY_FIVE_MINUTES;
            break;
        case R.id.ten:
            sharePeriodcallyTime = SHARE_PERIODICALLY_TEN_MINUTES;
            break;
        case R.id.halfhour:
            sharePeriodcallyTime = SHARE_PERIODICALLY_THIRTY_MINUTES;
            break;

        case R.id.mRadioShareByDis:
            setPeriodRadioGroupEnable(false);
            setDistanceRadioGroupEnable(true);
            radioShareDistanceTen.setChecked(true);
            shareDistanceValue = SHARE_DISTANCE_TEN_METERS;
            break;
        case R.id.tenmeters:
            shareDistanceValue = SHARE_DISTANCE_TEN_METERS;
            break;
        case R.id.hundred:
            shareDistanceValue = SHARE_DISTANCE_HUNDRED_METERS;
            break;
        case R.id.thousand:
            shareDistanceValue = SHARE_DISTANCE_THUNDRED_METERS;
            break;
        case R.id.fivethousand:
            shareDistanceValue = SHARE_DISTANCE_FIVETHUNDRED_METERS;
            break;

        case R.id.btnLocationSettingSave:
            if (radioSharePeriodically.isChecked()) {
                service.saveParameter(ShareLocationEngine.SHARE_LOCATION_MODE,
                    ShareLocationEngine.SHARE_MODE_PERIODICALLY);
                service.saveParameter(ShareLocationEngine.SHARE_LOCATION_GAP_TIME, sharePeriodcallyTime);
            } else if (radioShareByDis.isChecked()) {
                service.saveParameter(ShareLocationEngine.SHARE_LOCATION_MODE,
                    ShareLocationEngine.SHARE_MODE_BY_DISTANCELY);
                service.saveParameter(ShareLocationEngine.SHARE_LOCATION_DISTANCE, shareDistanceValue);
            } else {
                service.saveParameter(ShareLocationEngine.SHARE_LOCATION_MODE, ShareLocationEngine.SHARE_MODE_ONE_TIME);
            }
            
            if (shareAlways.isChecked()) {
            	service.saveParameter(ShareLocationEngine.ALWAYS_SHARE_LOCATION, "true");
            } else {
            	service.saveParameter(ShareLocationEngine.ALWAYS_SHARE_LOCATION, "false");
            }
            
            
            ShareLocationSettingActivity.this.finish();
            break;
        case R.id.btnLocationSettingCancle:
            ShareLocationSettingActivity.this.finish();
            break;
        default:
            break;
        }
    }
}
