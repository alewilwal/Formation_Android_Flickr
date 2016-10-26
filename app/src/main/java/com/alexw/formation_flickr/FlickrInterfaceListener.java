package com.alexw.formation_flickr;

import com.alexw.formation_flickr.datas.FlickrObjet;

import java.util.List;

/**
 * Created by Human Booster on 18/10/2016.
 */

public interface FlickrInterfaceListener {
    public void onResponseListener (List<FlickrObjet> flickrObjet);

}