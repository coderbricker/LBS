// Copyright (c) 2011-2014 gplocation Ltd. All rights reserved.

package com.codebricker.lbsshare.mod.main;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.codebricker.lbsshare.ConstantData;
import com.codebricker.lbsshare.R;
import com.codebricker.lbsshare.bak.SearchGroupAdapter;
import com.codebricker.lbsshare.bak.client.IGeneral;
import com.codebricker.lbsshare.bak.client.friend.IFriend;
import com.codebricker.lbsshare.bak.client.friend.IOperateFriend;
import com.codebricker.lbsshare.bak.client.group.IGroup;
import com.codebricker.lbsshare.bak.client.group.IOperateGroup;
import com.codebricker.lbsshare.bak.client.internal.IInternal;
import com.codebricker.lbsshare.common.async.MainHandler;
import com.codebricker.lbsshare.common.utils.AndroidUtil;
import com.codebricker.lbsshare.dlgs.WaitDialog;
import com.codebricker.lbsshare.mod.personal.map.ShowMapActivity;
import com.codebricker.lbsshare.mod.setting.ShareLocationSettingActivity;
import com.codebricker.lbsshare.vos.GeoLocation;
import com.codebricker.lbsshare.vos.GroupInfo;
import com.codebricker.lbsshare.vos.Login;
import com.codebricker.lbsshare.vos.Register;

/**
 * main activity in client
 */
public class MainActivity extends TabActivity {

	private static final String TAG = "LBSClientMainActivity";
	private static final int TOP_PADDING_SELECTED = 4;
	private static final int TOP_PADDING_NORMAL = 10;

	public WaitDialog progressDialog;
	public WaitDialog searchProgressDialog;

	public Dialog searchResultDialog;
	public TabHost tabHost;

	public boolean showMap = false;
	public boolean groupShowMap = false;
	public MainHandler mainHandler;
	private int[] tabMenus = { R.menu.mainmenu, R.menu.groupmainmenu };

	public Register register;
	public Login login;

	public IGeneral iGeneral;
	public IFriend iFriend;
	public IOperateFriend iOperateFriend;
	public IGroup iGroup;
	public IOperateGroup iOperateGroup;
	public IInternal internal;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		register();
		initWindow(); // get the components in main window

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");

		this.finish();

		android.os.Process.killProcess(android.os.Process.myPid());
	}

	/**
	 * when Options Item Selected call this (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int value = item.getItemId();
		switch (value) {
		case R.id.add_friend:
			this.onCreateDialog(ConstantData.DIALOG_ADD_FRIEND, null);
			break;
		case R.id.setting:
			Intent intent = new Intent(this, ShareLocationSettingActivity.class);
			this.startActivity(intent);
			break;
		case R.id.aboutmenu:
			GeoLocation location = new GeoLocation();
			location.setAccuracy("121");
			location.setLatitude("123");
			location.setLongitude("37");
			location.setCountry("china");
			if (location != null) {

				Intent intent1 = new Intent(this, ShowMapActivity.class);
				intent1.putExtra("account", "test");
				intent1.putExtra("location", location);
				try {
					(this).startActivityForResult(intent1, ConstantData.REQUEST_MAP_ACTIVITY);
				} catch (Exception e) {
					Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
			// in the friend tap, make sure who am i
			// this.onCreateDialog(ConstantData.DIALOG_ABOUT, null);
			break;
		case R.id.creategroup:
			// int the group tap, create for a new group
			this.onCreateDialog(ConstantData.DIALOG_CREATE_GROUP, null);
			break;
		case R.id.searchgroup:
			// int the group tap, search a sharedgroup to join in
			this.onCreateDialog(ConstantData.DIALOG_SEARCH_GROUP, null);
			break;
		case R.id.register_menu:
		case R.id.group_register_menu:
			// int the group tap, search a sharedgroup to join in
			Intent intent1 = new Intent(this, MainActivity.class);
			intent1.putExtra("LBSClientMainActivity", true);
			this.startActivityForResult(intent1, ConstantData.REQUEST_REGISTER_ACTIVITY);
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * set OptionsMenu info (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		menu.clear();
		MenuInflater inflater = getMenuInflater();
		switch (tabHost.getCurrentTab()) {
		case 0:
			inflater.inflate(tabMenus[0], menu);

			if ((register != null && register.getRegisterTimes() > 0) || login != null) {
				menu.findItem(R.id.register_menu).setVisible(false);
			}
			break;
		case 1:
			inflater.inflate(tabMenus[1], menu);
			if ((register != null && register.getRegisterTimes() > 0) || login != null) {
				menu.findItem(R.id.group_register_menu).setVisible(false);
			}
			break;
		// case 2:
		// inflater.inflate(tabMenus[2], menu);
		// break;
		default:
			break;
		}

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * <p>
	 * product progress dialog
	 * </P>
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
	public Dialog popupProgressDialog(String title, String message) {
		LayoutInflater inflater = this.getLayoutInflater();
		final Dialog dialog = new Dialog(this, R.style.Dialog_No_Title);

		View view = inflater.inflate(R.layout.progress_dialog, null);
		dialog.setContentView(view);

		TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
		TextView msgView = (TextView) view.findViewById(R.id.dialog_message);

		titleView.setText(title);
		msgView.setText(message);

		return dialog;
	}

	/**
	 * <p>
	 * product a single button dialog
	 * </P>
	 * 
	 * @param title
	 * @param message
	 * @return
	 */
	public Dialog popupSingleButtonDialog(String title, String message) {
		LayoutInflater inflater = this.getLayoutInflater();
		final Dialog dialog = new Dialog(this, R.style.Dialog_No_Title);

		View view = inflater.inflate(R.layout.dialog, null);
		dialog.setContentView(view);

		TextView titleView = (TextView) view.findViewById(R.id.dialog_title);
		TextView msgView = (TextView) view.findViewById(R.id.dialog_message);

		titleView.setText(title);
		msgView.setText(message);

		Button okBtn = (Button) view.findViewById(R.id.dialog_signle_ok_btn);
		okBtn.setVisibility(View.VISIBLE);
		okBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		view.findViewById(R.id.dialog_layout_two_button).setVisibility(View.GONE);

		return dialog;
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle bl) {
		LayoutInflater inflater = this.getLayoutInflater();
		switch (id) {
		case ConstantData.DIALOG_ADD_FRIEND:
			final Dialog addDialog = new Dialog(this, R.style.Dialog_Fullscreen);

			View addView = inflater.inflate(R.layout.add_friend, null);
			addDialog.setContentView(addView);
			final EditText input = (EditText) addView.findViewById(R.id.add_friend_edit);

			Button okBtn = (Button) addView.findViewById(R.id.add_friend_ok);
			Button cacelBtn = (Button) addView.findViewById(R.id.add_friend_cancel);

			okBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					String phone = "";
					String email = "";
					String name = input.getText().toString().trim();
					if (name == null || "".equals(name.trim())) {
						MainActivity.this.onCreateDialog(ConstantData.DIALOG_FRIEND_INPUT_ILLEGAL, null);
						return;
					} else {
						if (name.matches(ConstantData.Email_Regex) || name.matches(ConstantData.Phone_Regex)) {
							if (name.matches(ConstantData.Email_Regex)) {
								email = name;
							} else {
								phone = name;
							}

							addDialog.dismiss();
						} else {
							MainActivity.this.onCreateDialog(ConstantData.DIALOG_FRIEND_INPUT_ILLEGAL, null);
						}
					}
				}
			});

			cacelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					addDialog.dismiss();
				}
			});

			addDialog.show();
			input.requestFocus();

			break;

		case ConstantData.DIALOG_FRIEND_INPUT_ILLEGAL:
			popupSingleButtonDialog(getString(R.string.lab_add_friend), getString(R.string.your_friend_name_is_illegal))
					.show();
			break;

		case ConstantData.DIALOG_GROUP_INPUT_ILLEGAL:
			popupSingleButtonDialog(getString(R.string.lab_create_group), getString(R.string.group_illegal)).show();

			break;

		case ConstantData.DIALOG_ABOUT:
			// MainApplication mainApplication = (MainApplication) this
			// .getApplication();
			// userInfo = mainApplication.userInfo;
			//
			// if (("".equals(userInfo.getEmail()) && register != null &&
			// register
			// .getRegisterTimes() > 0)) {
			// try {
			// userInfo = iGeneral.getUserInfo();
			// } catch (RemoteException e) {
			// e.printStackTrace();
			// }
			// }
			//
			// Log.d(TAG, "user=" + userInfo.getNick());
			//
			// final Dialog aboutDialog = new Dialog(this,
			// R.style.Dialog_Fullscreen);
			//
			// View aboutView = inflater.inflate(R.layout.about, null);
			// aboutDialog.setContentView(aboutView);
			//
			// TextView userView = (TextView) aboutView
			// .findViewById(R.id.about_user);
			// if (register != null && register.getRegisterTimes() > 0) {
			// userView.setText(getString(R.string.register_user_name) + " "
			// + register.getUserName());
			// } else {
			// userView.setText(getString(R.string.register_user_name) + " "
			// + userInfo.getNick());
			// }
			//
			// TextView phoneView = (TextView) aboutView
			// .findViewById(R.id.about_phone);
			// if (register != null && register.getRegisterTimes() > 0) {
			// phoneView.setText(getString(R.string.register_mobile) + " "
			// + register.getPhone());
			// } else {
			// phoneView.setText(getString(R.string.register_mobile));
			// }
			//
			// TextView emailView = (TextView) aboutView
			// .findViewById(R.id.about_email);
			// if (register != null && register.getRegisterTimes() > 0) {
			// emailView.setText(getString(R.string.register_email) + " "
			// + register.getEmail());
			// } else {
			// emailView.setText(getString(R.string.register_email));
			// }
			//
			// aboutDialog.show();

			break;

		case ConstantData.DIALOG_CREATE_GROUP:
			final Dialog createDialog = new Dialog(this, R.style.Dialog_Fullscreen);

			View createGroupView = inflater.inflate(R.layout.create_group, null);
			createDialog.setContentView(createGroupView);
			createDialog.setTitle(R.string.lab_create_group);

			final EditText groupName = (EditText) createGroupView.findViewById(R.id.create_group_name);
			final EditText description = (EditText) createGroupView.findViewById(R.id.create_group_description);
			final Button createGroupBtn = (Button) createGroupView.findViewById(R.id.create_group_create);

			final String rules = ConstantData.GroupName_Regex;
			createGroupBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String shareGroupName = groupName.getText().toString().trim();
					String shareDescription = description.getText().toString().trim();
					if (shareGroupName != null && shareGroupName.matches(rules)
							&& shareDescription.matches(ConstantData.Description_Regex)) {
						// try {
						// this.iOperateGroup.createGroup(shareGroupName,
						// shareDescription);
						// } catch (RemoteException e) {
						// e.printStackTrace();
						// }

						createDialog.dismiss();
					} else {
						MainActivity.this.onCreateDialog(ConstantData.DIALOG_GROUP_INPUT_ILLEGAL, null);
					}
				}
			});

			final Button cancelBtn = (Button) createGroupView.findViewById(R.id.create_group_cancel);
			cancelBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					createDialog.dismiss();
				}
			});

			createDialog.show();
			break;

		case ConstantData.DIALOG_SEARCH_GROUP:
			View searchGroupView = inflater.inflate(R.layout.search_group, null);
			final Dialog dialog = new Dialog(this, R.style.Dialog_Fullscreen);
			dialog.setContentView(searchGroupView);
			dialog.show();

			final EditText searchGroupName = (EditText) searchGroupView.findViewById(R.id.search_group_name);
			Button searchGroupBtn = (Button) searchGroupView.findViewById(R.id.search_group_search);

			searchGroupBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String shareGroupName = searchGroupName.getText().toString().trim();
					if (shareGroupName != null && !"".equals(searchGroupName)) {
						try {
							iGroup.searchGroups(shareGroupName, 30);
						} catch (RemoteException e) {
							e.printStackTrace();
						}

						// this.dismissDialog(ConstantData.DIALOG_SEARCH_GROUP);
						dialog.dismiss();
						searchProgressDialog = new WaitDialog(MainActivity.this);

						searchProgressDialog.setMessage(getString(R.string.searching));
						searchProgressDialog.show();
					}

				}
			});
			break;

		case ConstantData.DIALOG_SEARCH_RESULT_GROUP:
			ArrayList<GroupInfo> groups = bl.getParcelableArrayList("searchGroups");

			if (groups == null || groups.size() == 0) {
				popupSingleButtonDialog(getString(R.string.search_group_search),
						getString(R.string.search_group_no_result)).show();
			} else {
				searchResultDialog = new Dialog(this, R.style.Dialog_Fullscreen);
				View groupResultView = inflater.inflate(R.layout.search_group_adapter, null);
				searchResultDialog.setContentView(groupResultView);
				searchResultDialog.show();

				ExpandableListView listView = (ExpandableListView) searchResultDialog
						.findViewById(R.id.searchgroupexpandablelistView);
				SearchGroupAdapter searchGroupAdapter = new SearchGroupAdapter(this, groups);
				listView.setAdapter(searchGroupAdapter);
			}

			break;

		default:
			break;
		}

		return super.onCreateDialog(id);
		// return super.onCreateDialog(id, bl);

	}

	/**
	 * <p>
	 * create group result dialog
	 * </P>
	 * 
	 * @param bl
	 */
	public void createSearchGroupResultDialog(Bundle bl) {
		this.onCreateDialog(ConstantData.DIALOG_SEARCH_RESULT_GROUP, bl);
	}

	/**
	 * when another activity which was started by LBSClientMainActivity with
	 * requestCode finished,do sth for it (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 *      android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case ConstantData.REQUEST_MAP_ACTIVITY:
			showMap = false;
			break;
		case ConstantData.REQUEST_GROUPMAP_ACTIVITY:
			groupShowMap = false;
			break;
		case ConstantData.REQUEST_REGISTER_ACTIVITY:
			if (data != null) {
				register = data.getParcelableExtra("register");
				// register
				if (register != null && register.getRegisterTimes() < 1) {
					internal.setUserMap(register.getUserName(), register.getPassWord(), register.getEmail(),
							register.getPhone());
				}
			}

			break;

		default:
			break;
		}
	}

	private void register() {
		register = this.getIntent().getParcelableExtra("register");
		login = this.getIntent().getParcelableExtra("login");
		// if (register != null )

	}

	/**
	 * init the main window and get the component in UI.
	 */
	private void initWindow() {
		progressDialog = new WaitDialog(this);

		tabHost = getTabHost();

		final View friendView = AndroidUtil.inflate(R.layout.tab_head);

		final View friendHeadView = friendView.findViewById(R.id.tab_head);
		ImageView tabImg = (ImageView) friendView.findViewById(R.id.tab_image);
		Drawable bgFrd = AndroidUtil.getDrawable(R.drawable.tab_friends_selector);
		tabImg.setBackgroundDrawable(bgFrd);
		final TextView friendTextView = ((TextView) friendView.findViewById(R.id.tab_text));
		friendTextView.setText(AndroidUtil.getString(R.string.tab_friends));

		String tabFrdTxt = AndroidUtil.getString(R.string.tab_friends);
		TabSpec tabFrd = tabHost.newTabSpec(tabFrdTxt).setIndicator(friendView).setContent(R.id.Friends);
		tabHost.addTab(tabFrd);

		final View groupView = AndroidUtil.inflate(R.layout.tab_head);
		final View groupHeadView = groupView.findViewById(R.id.tab_head);
		String tabGrpTxt = AndroidUtil.getString(R.string.tab_groups);
		TabSpec tabGrp = tabHost.newTabSpec(tabGrpTxt).setIndicator(groupView).setContent(R.id.Groups);
		((ImageView) groupView.findViewById(R.id.tab_image)).setBackgroundDrawable(AndroidUtil
				.getDrawable(R.drawable.tab_group_selector));
		final TextView groupTextView = ((TextView) groupView.findViewById(R.id.tab_text));
		groupTextView.setText(getString(R.string.tab_groups));
		tabHost.addTab(tabGrp);

		final View appView = AndroidUtil.inflate(R.layout.tab_head);
		final View appHeadView = appView.findViewById(R.id.tab_head);
		((ImageView) appView.findViewById(R.id.tab_image)).setBackgroundDrawable(AndroidUtil
				.getDrawable(R.drawable.tab_application_selector));
		final TextView appTextView = ((TextView) appView.findViewById(R.id.tab_text));
		appTextView.setText(getString(R.string.tab_apps));
		tabHost.addTab(tabHost.newTabSpec(this.getString(R.string.tab_apps)).setIndicator(appView)
				.setContent(R.id.Apps));

		final int selectedColor = AndroidUtil.getColor(R.color.text_default_color);
		final int normalColor = AndroidUtil.getColor(R.color.menu_default_color);

		// setting the current tab to display
		tabHost.setCurrentTab(0);
		friendView.setPadding(0, TOP_PADDING_SELECTED, 0, 0);
		groupView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);
		appView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);
		friendTextView.setTextColor(selectedColor);
		friendHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_press));
		groupHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
		appHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {

				if (tabId.equals(getString(R.string.tab_friends))) {
					friendView.setPadding(0, TOP_PADDING_SELECTED, 0, 0);
					groupView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);
					appView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);

					friendTextView.setTextColor(selectedColor);
					groupTextView.setTextColor(normalColor);
					appTextView.setTextColor(normalColor);

					friendHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_press));
					groupHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
					appHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
				} else if (tabId.equals(getString(R.string.tab_groups))) {
					groupView.setPadding(0, TOP_PADDING_SELECTED, 0, 0);
					friendView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);
					appView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);

					groupTextView.setTextColor(selectedColor);
					friendTextView.setTextColor(normalColor);
					appTextView.setTextColor(normalColor);

					groupHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_press));
					friendHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
					appHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
				} else if (tabId.equals(getString(R.string.tab_apps))) {
					appView.setPadding(0, TOP_PADDING_SELECTED, 0, 0);
					friendView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);
					groupView.setPadding(0, TOP_PADDING_NORMAL, 0, 0);

					appTextView.setTextColor(selectedColor);
					friendTextView.setTextColor(normalColor);
					groupTextView.setTextColor(normalColor);

					appHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_press));
					groupHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
					friendHeadView.setBackgroundDrawable(AndroidUtil.getDrawable(R.drawable.tab_head_bg));
				}

			}
		});

	}

}
