package com.alexw.formation_flickr;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.alexw.formation_flickr.datas.FlickrObjet;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Human Booster on 17/10/2016.
 */

public class FlickrService extends Service {

    private  final  IBinder binder = new ServiceBinder();
    private Context context;
    FlickrServiceInterface service;

    public void setFlickrInterfaceListener(FlickrInterfaceListener flickrInterfaceListener) {
        this.flickrInterfaceListener = flickrInterfaceListener;
    }

    FlickrInterfaceListener flickrInterfaceListener;

    public void setContext(Context context) {
        this.context = context;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FlickrServiceInterface.class);

        return binder;
    }


    public class ServiceBinder extends Binder{
        FlickrService getService(){
            return FlickrService.this;
        }
    }

    public void getRetrofitFlickr(String search) {
        final Call<FlickrPhotos> flickrPhotosResponseCall = service.getPhotos(search);
        flickrPhotosResponseCall.enqueue(new Callback<FlickrPhotos>() {

            @Override
            public void onResponse(Call<FlickrPhotos> call, Response<FlickrPhotos> response) {
                if (call.isExecuted()) {
                    FlickrPhotos flickrPhotosResponse = response.body();
                    flickrInterfaceListener.onResponseListener(converterPhotoResponse(flickrPhotosResponse));
                    //Toast.makeText(context,"onResponse Executed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlickrPhotos> call, Throwable t) {
                Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public List<FlickrObjet> converterPhotoResponse(FlickrPhotos flickrPhotos) {
        List<Photo> photoList = flickrPhotos.getPhotos().getPhoto();
        List<FlickrObjet> flickrObjets = new ArrayList<>();
        for (Photo photo : photoList){
            String url = "https://farm"+photo.getFarm()+".static.flickr.com/"+photo.getServer()+"/"+photo.getId()+"_"+photo.getSecret()+".jpg";
            String title = photo.getTitle();

            FlickrObjet flickrObjet = new FlickrObjet(title, url);
            flickrObjets.add(flickrObjet);

        }
        return flickrObjets;
    }

}
