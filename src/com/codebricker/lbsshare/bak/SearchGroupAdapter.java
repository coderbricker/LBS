package com.codebricker.lbsshare.bak;

import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.RemoteException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.codebricker.lbsshare.R;
import com.codebricker.lbsshare.mod.main.MainActivity;
import com.codebricker.lbsshare.vos.GroupInfo;

public class SearchGroupAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GroupInfo> domains;

    public SearchGroupAdapter(Context context, List<GroupInfo> domains) {
        this.context = context;
        this.domains = domains;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public Object getChild(int arg0, int arg1) {
        return domains.get(arg0).getGroupDescription();
    }

    @Override
    public long getChildId(int arg0, int arg1) {
        return 0;
    }

    @Override
    public int getChildrenCount(int arg0) {
        return 1;
    }

    @Override
    public long getCombinedChildId(long arg0, long arg1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long arg0) {
        return 0;
    }

    @Override
    public Object getGroup(int arg0) {
        return domains.get(arg0);
    }

    @Override
    public int getGroupCount() {
        return domains.size();
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupCollapsed(int arg0) {

    }

    @Override
    public void onGroupExpanded(int arg0) {
        //        notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver arg0) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver arg0) {

    }

    
    private class ViewHolder {
    	TextView groupName;
    	TextView groupDescription;
    	Button  joinBtn;
    }
    
    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

    	ViewHolder viewHolder = null;
    	if (convertView == null) {
    		LayoutInflater inflater = ((MainActivity) context).getLayoutInflater();
   		 
    		convertView = inflater.inflate(R.layout.search_group_adapter_child, null);
    		viewHolder = new ViewHolder();
    		viewHolder.groupName = (TextView) convertView.findViewById(R.id.search_group_adapter_child_groupname);
    		viewHolder.groupDescription = (TextView) convertView.findViewById(
    				R.id.search_group_adapter_child_groupdescription);
    		viewHolder.joinBtn = (Button) convertView.findViewById(R.id.search_group_adapter_child_join);
            
   	     	convertView.setTag(viewHolder);
    	} else {
    		viewHolder = (ViewHolder) convertView.getTag();
    	}
    	

    	viewHolder.groupName.setText(domains.get(groupPosition).getGroupName());
    	viewHolder.groupName.setPadding(30, 0, 0, 0);
        
    	viewHolder.groupDescription.setText(domains.get(groupPosition).getGroupDescription());
    	viewHolder.groupDescription.setPadding(30, 0, 0, 0);
      
    	viewHolder.joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String groupId = domains.get(groupPosition).getGroupId(); 
                try {
					((MainActivity) context).iOperateGroup.joinGroup(groupId);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
                
               
                if (((MainActivity) context).searchResultDialog != null
                		&& ((MainActivity) context).searchResultDialog.isShowing()) {
                	((MainActivity) context).searchResultDialog.dismiss();
                }
            }
        });

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
    	TextView groupTv = null;
    	if (convertView == null) {
    		 LayoutInflater inflater = ((MainActivity) context).getLayoutInflater();
    		 
    		 convertView = inflater.inflate(R.layout.search_group_adapter_parent, null);
    		 groupTv = (TextView) convertView.findViewById(R.id.search_group_adapter_parent_textview);
    	     
    	     convertView.setTag(R.id.search_group_adapter_parent_textview, groupTv);
    	} else {
    		groupTv = (TextView) convertView.getTag(R.id.search_group_adapter_parent_textview);
    	}
    	
        groupTv.setPadding(50, 0, 0, 0);
        groupTv.setText(domains.get(groupPosition).getGroupName());
        groupTv.setHeight(60);
        groupTv.setGravity(Gravity.CENTER_VERTICAL);
        
        return convertView;
    }

}
