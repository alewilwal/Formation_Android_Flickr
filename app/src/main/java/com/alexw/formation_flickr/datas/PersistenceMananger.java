package com.alexw.formation_flickr.datas;

import android.content.Context;
import android.util.Log;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Human Booster on 25/10/2016.
 */

public class PersistenceMananger {

    public PersistenceMananger(Context context) {
        FlowManager.init(new
                FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }
    public List<FlickrObjet> getFlickrObjetByTitle(String string) {
        return SQLite.select()
                .from(FlickrObjet.class)
                .where(FlickrObjet_Table.title.like(string + "%"))
                .or(FlickrObjet_Table.title.like("%" + string + "%"))
                .queryList();
    }
    public void save(FlickrObjet flickrObjet) {
        try {
            flickrObjet.save();
        } catch (Exception e) {
            Log.w("SaveFlickrObjet", e.toString());
        }
    }
}
