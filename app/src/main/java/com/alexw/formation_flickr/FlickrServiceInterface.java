package com.alexw.formation_flickr;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Human Booster on 17/10/2016.
 */

public interface FlickrServiceInterface {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&per_page=5&format=json&nojsoncallback=1&api_key=4031fcfdc05e34063e1ea524026021d9")
    Call <FlickrPhotos> getPhotos(@Query("tags") String query);
}
