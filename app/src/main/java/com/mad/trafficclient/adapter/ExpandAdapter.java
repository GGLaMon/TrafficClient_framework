package com.mad.trafficclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by Android Studio.
 * Author: 蔡明鹏
 *
 * @param <G> group的类型
 * @param <C> child的类型
 */
public abstract class ExpandAdapter<G, C> extends BaseExpandableListAdapter {
    private Context context;
    private List<G> groupList;
    private List<List<C>> childList;
    private int groupLayout;
    private int childLayout;

    /**
     * 构造函数.
     *
     * @param context     the context
     * @param groupList   the group list
     * @param childList   the child list
     * @param groupLayout the group layout
     * @param childLayout the child layout
     */
    public ExpandAdapter(Context context, List<G> groupList, List<List<C>> childList, int groupLayout, int childLayout) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(context, groupPosition, convertView, parent, groupLayout);
        convertGroup(holder, groupList.get(groupPosition), groupPosition);
        return holder.getConvertView();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(context, groupPosition, convertView, parent, childLayout);
        convertChild(holder, childList.get(groupPosition).get(childPosition), childPosition);
        return holder.getConvertView();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 输出holder和 group的数据.
     *
     * @param holder   the holder
     * @param item     the item
     * @param position the position
     */
    public abstract void convertGroup(CommonViewHolder holder, G item, int position);

    /**
     * 输出holder和 child的数据.
     *
     * @param holder   the holder
     * @param item     the item
     * @param position the position
     */
    public abstract void convertChild(CommonViewHolder holder, C item, int position);
}
