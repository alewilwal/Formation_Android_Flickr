package com.alexw.formation_flickr.datas;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Human Booster on 24/10/2016.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "FlickrObjet"; // we will add the .db extension

    public static final int VERSION = 8;
}