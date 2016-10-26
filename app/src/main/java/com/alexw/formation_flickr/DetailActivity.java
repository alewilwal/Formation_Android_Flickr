package com.alexw.formation_flickr;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexw.formation_flickr.datas.PersistenceMananger;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView textViewTitle;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getExtras().getString("EXTRA_TITLE");
        String url = getIntent().getExtras().getString("EXTRA_URL_IMAGE");

        textViewTitle = (TextView) findViewById(R.id.textTitle);
        textViewTitle.setText(title);
        imageView = (ImageView) findViewById(R.id.imageRow);
        Picasso.with(this).load(url).resize(300, 300).placeholder(R.drawable.logow).centerCrop().into(imageView);
        addListenerOnButtonGrade();
    }
    public void addListenerOnButtonGrade(){
        FloatingActionButton myFABgrade = (FloatingActionButton) findViewById(R.id.myFABgrade);
        myFABgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }


}