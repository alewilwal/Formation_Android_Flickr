package com.alexw.formation_flickr;

import java.util.List;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrPhotos {
    private Photos photos;
    private String stat;

    public FlickrPhotos(){

    }

    public void setPhotos(Photos photos){ this.photos = photos;}
    public void  setStat(String stat){ this.stat = stat;}
    public  Photos getPhotos(){
        return photos;
    }
    public  String getStat(){
        return stat;
    }



}
