package com.codebricker.lbsshare.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class Utils {
	private static final String TAG = "Utils";
	
    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            String s = str.replace(" ", "");
            if (s.equals("") || s.equals("null")) {
                return true;
            }
        }

        return false;
    }

    public static String changeToJsonString(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            try {
                jsonObject.put(key, map.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }
    
    public static String changeToJsonArrayString(List<String> lists) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < lists.size(); i++) {
            jsonArray.put(lists.get(i));
        }
        return jsonArray.toString();
    }
    /**
     * get the first string for input string split by '/'
     * @param account input string
     * @return out put string
     */
    public static String ignoreAccountSource(final String account) {
        int index = account.indexOf('/');
        if (index != -1) {
            return account.substring(0, index);
        }

        return account;
    }

    /**
     * get the last string for input string split by '/'
     * @param account input string
     * @return out put string
     */
    public static String getAccountSource(final String account) {
        int index = account.indexOf('/');
        if (index != -1) {
            return account.substring(index + 1);
        }

        return account;
    }
    

	public static final String INSTALLEDAPPSMAPNAME = "name";
	public static final String INSTALLEDAPPSMAPPACKAGE = "package";
	public static final String INSTALLEDAPPSMAPVERSION = "version";
    
    /**
     * get all installed applications.
     * <p></P>
     * @return
     */
    public static List<Map<String, Object>> fetchInstallApps(Context context) {
    	
    	PackageManager packageManager = context.getPackageManager();
    	
    	List<PackageInfo> packagesApplicationInfos = packageManager.getInstalledPackages(0);
    	int size = packagesApplicationInfos.size();
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(size);
    	Iterator<PackageInfo> it = packagesApplicationInfos.iterator();
    	
    	while (it.hasNext()) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		PackageInfo app = it.next();
    		String packageName = app.packageName;
    		String version = app.versionName;
    		String label = app.applicationInfo.loadLabel(packageManager).toString();
    		
    		map.put(INSTALLEDAPPSMAPNAME, label);
    		map.put(INSTALLEDAPPSMAPPACKAGE, packageName);
    		map.put(INSTALLEDAPPSMAPVERSION, version);
    		list.add(map);
    		
    	}
    	
    	
		return list;
    	
    }
    
    
    
	/**
	 * <p>download a file from url but not installed</P>
	 * @param context
	 * @param httpUrl
	 * @return
	 */
	public static boolean asyncDownLoadFile(final Context context, final String httpUrl, final Handler handler) {
		if (httpUrl == null || "".equals(httpUrl)) {
			return false;
		}
		
		
		String url = httpUrl.trim();
		Log.d(TAG, "downLoadFile httpUrl=" + httpUrl);
		
		try {
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
//			intent.setComponent("com.android.browser/.BrowserActivity");
//			intent.setData();
			context.startActivity(intent);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		

//		
//		final String fileName = httpUrl.substring(httpUrl.lastIndexOf("/"));
//		
//		new Thread() {
//			@Override
//			public void run() {
//				File tmpFile = new File(Environment.getExternalStorageDirectory()
//						+ "/update");
//				if (!tmpFile.exists()) {
//					tmpFile.mkdir();
//				}
//				final File file = new File(Environment.getExternalStorageDirectory()
//						+ "/update/" + fileName);
//
//				boolean res = false;
//				String reason = "";
//				
//				try {
//					URL url = new URL(httpUrl);
//
//					res = true;
//					Message msg = new Message();
//					msg.what = CommandEngine.SELF_DOWNLOAD_RESULT;
//					
//					Bundle bl = new Bundle();
//					bl.putString("path", file.getPath());
//					
//					try {
//						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//						InputStream is = conn.getInputStream();
//						FileOutputStream fos = new FileOutputStream(file);
//						byte[] buf = new byte[256];
//						conn.connect();
//						double count = 0;
//						if (conn.getResponseCode() >= 400) {
//							Log.e("Connection error", "long time");
//							reason = "Connection time out";
//						} else {
//							while (count <= 100) {
//								if (is != null) {
//									int numRead = is.read(buf);
//									if (numRead <= 0) {
//										break;
//									} else {
//										fos.write(buf, 0, numRead);
//									}
//								} else {
//									break;
//								}
//							}
//							
//						}
//
//						conn.disconnect();
//						fos.close();
//						is.close();
//
//
//						bl.putBoolean("result", res);
//						bl.putString("reason", reason);
//						msg.setData(bl);
//						handler.sendMessage(msg);
//						
//					} catch (IOException e) {
//						bl.putBoolean("result", false);
//						bl.putString("reason", e.toString());
//						msg.setData(bl);
//						handler.sendMessage(msg);
//					}
//				} catch (MalformedURLException e) {
//					throw new RuntimeException("MalformedURLException!");
//				}
//
////				Intent intent = new Intent(Intent.ACTION_VIEW);
////				intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
////				context.startActivity(intent);
//			}
//		} .start();
		
        return true;  
	}  
	
	public static boolean removePackage(Context context, String packageName) {
		Uri packageURI = Uri.parse("package:" + packageName);
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);   
		context.startActivity(uninstallIntent);
		
		return true;
	}

}
