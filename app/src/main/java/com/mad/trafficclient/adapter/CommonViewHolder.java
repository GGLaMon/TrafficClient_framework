package com.mad.trafficclient.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The type Common view holder.
 */
public class CommonViewHolder {
    private SparseArray<View> array;//存放所有的view
    private int position;//子项下标
    private View convertView;//视图

    /**
     * 构造函数
     *
     * @param context   the context
     * @param position  子项下标
     * @param parent    父级布局
     * @param layoutRes 子项布局
     */
    public CommonViewHolder(Context context, int position, ViewGroup parent, int layoutRes) {
        //初始化SparseArray
        this.array = new SparseArray<>();
        this.position = position;
        //初始化视图
        this.convertView = LayoutInflater.from(context).inflate(layoutRes, parent, false);
        //设置tag
        this.convertView.setTag(this);
    }

    /**
     * 获取ViewHolder
     *
     * @param context     the context
     * @param position    the position
     * @param convertView the convert view
     * @param parent      the parent
     * @param layoutRes   the layout res
     * @return the common view holder
     */
    public static CommonViewHolder get(Context context, int position, View convertView,
                                       ViewGroup parent, int layoutRes) {
        if (convertView == null) {//如果convertView为空则返回一个新的视图
            return new CommonViewHolder(context, position, parent, layoutRes);
        }//否则根据tag取出视图并返回
        return (CommonViewHolder) convertView.getTag();
    }

    /**
     * 获取视图
     *
     * @return the convert view
     */
    public View getConvertView() {
        return convertView;
    }


    /**
     * 获取视图
     *
     * @param viewId 子布局中控件的id
     * @return the view
     */
    private View getView(int viewId) {
        //从array中取出对应的view
        View view = array.get(viewId);
        if (view == null) {
            //如果view为空，则查找view
            view = convertView.findViewById(viewId);
            //将view存放到array中
            array.put(viewId, view);
        }

        return view;
    }

    /*——————————对控件进行属性设置——————————————*/

    /**
     * Sets text.
     *
     * @param viewId the view id
     * @param s      the s
     * @return the text
     */
    public CommonViewHolder setText(int viewId, String s) {
        TextView view = (TextView) getView(viewId);
        view.setText(s);
        return this;
    }

    /**
     * Sets img res.
     *
     * @param viewId the view id
     * @param resId  the res id
     * @return the img res
     */
    public CommonViewHolder setImgRes(int viewId, int resId) {
        ImageView view = (ImageView) getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    /**
     * Sets background res.
     *
     * @param viewId the view id
     * @param res    the res
     * @return the background res
     */
    public CommonViewHolder setBackgroundRes(int viewId, int res) {
        View view = getView(viewId);
        view.setBackgroundResource(res);
        return this;
    }

    /**
     * Sets img.
     *
     * @param viewId   the view id
     * @param drawable the drawable
     * @return the img
     */
    public CommonViewHolder setImg(int viewId, Drawable drawable) {
        ImageView view = (ImageView) getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Sets checked.
     *
     * @param viewId    the view id
     * @param isChecked the is checked
     * @return the checked
     */
    public CommonViewHolder setChecked(int viewId, boolean isChecked) {
        CheckBox view = (CheckBox) getView(viewId);
        view.setChecked(isChecked);
        return this;
    }


     /*——————————对控件进行事件设置——————————————*/
    /**
     * Sets on click.
     *
     * @param viewId   the view id
     * @param listener the listener
     * @return the on click
     */
    public CommonViewHolder setOnClick(int viewId, OnClickListener listener) {
        View view = getView(viewId);
        view.setTag(position);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * Sets on checked.
     *
     * @param viewId   the view id
     * @param listener the listener
     * @return the on checked
     */
    public CommonViewHolder setOnChecked(int viewId, OnCheckedListener listener) {
        CheckBox view = (CheckBox) getView(viewId);
        view.setTag(position);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * 自定义OnClickListener.
     */
    public static abstract class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            onClick(v, (Integer) v.getTag());
        }

        /**
         * On click.
         *
         * @param v        the v
         * @param position the position
         */
        public abstract void onClick(View v, int position);
    }

    /**
     * 自定义OnCheckedChangeListener.
     */
    public static abstract class OnCheckedListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            OnChecked(buttonView, isChecked, (Integer) buttonView.getTag());
        }

        /**
         * On checked.
         *
         * @param buttonView the button view
         * @param isChecked  the is checked
         * @param position   the position
         */
        public abstract void OnChecked(CompoundButton buttonView, boolean isChecked, int position);
    }

}
