package com.alexw.formation_flickr;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.alexw.formation_flickr.datas.FlickrObjet;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FlickrInterfaceListener{
    private FlickrService boundService;
    boolean bound = false;
    ListView listView;
    EditText editText;
    Context context;
    AdapterBase adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edit_text);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new AdapterBase(this);
        listView.setAdapter(adapter);
        addListenerOnButton();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, FlickrService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        context = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            boundService = binder.getService();
            boundService.setFlickrInterfaceListener(MainActivity.this);
            boundService.setContext(context);
            bound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    public void addListenerOnButton(){
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bound) {
                    boundService.getRetrofitFlickr(editText.getText().toString());
                }
                try  {
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                catch (Exception e) {

                }
            }
        });


    }

    @Override
    public void onResponseListener(List<FlickrObjet> flickrObjetList) {
        adapter.setListImages(flickrObjetList);
    }

}
