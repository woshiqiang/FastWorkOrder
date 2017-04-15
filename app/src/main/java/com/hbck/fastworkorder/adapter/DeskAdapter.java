package com.hbck.fastworkorder.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbck.fastworkorder.R;
import com.hbck.fastworkorder.bean.local.DesktopApps;

import java.util.List;

import static com.hbck.fastworkorder.R.id.ivAppIcon;
import static com.hbck.fastworkorder.R.id.tvAppName;

public class DeskAdapter extends BaseAdapter {
    private Context context;
    private List<DesktopApps> list;

    public DeskAdapter(Context context, List<DesktopApps> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_desk, null);
            holder.ivAppIcon = (ImageView) convertView.findViewById(ivAppIcon);
            holder.tvAppName = (TextView) convertView.findViewById(tvAppName);
            holder.msg_number = (TextView) convertView.findViewById(R.id.msg_number);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivAppIcon.setBackgroundResource(list.get(position).getImg());
        holder.tvAppName.setText(list.get(position).getName());
        int count = list.get(position).getCount();
        if (count>0){
            holder.msg_number.setText(""+count);
            holder.msg_number.setVisibility(View.VISIBLE);
        }else {
            holder.msg_number.setVisibility(View.GONE);
        }
        return convertView;
    }


    class ViewHolder {
        ImageView ivAppIcon;
        TextView tvAppName;
        TextView msg_number;
    }
}