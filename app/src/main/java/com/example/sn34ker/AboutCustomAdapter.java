package com.example.sn34ker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class AboutCustomAdapter extends BaseAdapter {

    List<Integer> adapterDevsPicList = new ArrayList<>();

    public AboutCustomAdapter(List<Integer> adapterDevsPicList) {
        this.adapterDevsPicList = adapterDevsPicList;
    }

    @Override
    public int getCount() {
        return adapterDevsPicList.size();
    }

    @Override
    public Object getItem(int position) {
        return adapterDevsPicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater myLayoutInflater = LayoutInflater.from(parent.getContext());
            convertView = myLayoutInflater.inflate(R.layout.about_items, parent, false);
        }

        ImageView devsImageView = convertView.findViewById(R.id.devsImageView);
        devsImageView.setImageResource(adapterDevsPicList.get(position));
        return convertView;
    }
}
