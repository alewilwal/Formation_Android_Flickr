package com.alexw.formation_flickr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexw.formation_flickr.datas.FlickrObjet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class AdapterBase extends BaseAdapter {

    private List<FlickrObjet> listFlickr;
    private LinearLayout linearLayoutRow;
    private Context context;
    private int position;

    public AdapterBase(Context context) {
        listFlickr = new ArrayList<>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return listFlickr.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        this.position = position;
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.row_layout, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtImage);
        textView.setText(listFlickr.get(position).getTitle());

        ImageView imageView  = (ImageView) convertView.findViewById(R.id.avatar);
        Picasso.with(context).load(listFlickr.get(position).getUrl()).resize(100, 100).placeholder(R.drawable.logow).centerCrop().into(imageView);

        linearLayoutRow = (LinearLayout) convertView.findViewById(R.id.linearRow);
        linearLayoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("EXTRA_TITLE",listFlickr.get(position).getTitle());
                intent.putExtra("EXTRA_URL_IMAGE",listFlickr.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setListImages(List<FlickrObjet> flickrList) {
        this.listFlickr = flickrList;
        notifyDataSetChanged();
    }

}
