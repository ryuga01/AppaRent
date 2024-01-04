package com.example.apparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class MainPageInformationDisplay extends AppCompatActivity  {
    ImageView imageView;
    TextView name, description, address;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_information_display);
        imageView = (ImageView) findViewById(R.id.infoImageCover);
        name = (TextView) findViewById(R.id.infoTitle);
        description = (TextView) findViewById(R.id.infoDescription);
        address = (TextView) findViewById(R.id.infoLocation);
        ratingBar = (RatingBar) findViewById(R.id.infoRatingBar);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        String photoPath = (String) b.get("photo");
        String nameText =  (String) b.get("name");
        String descText =  (String) b.get("desc");
        String locationText =  (String) b.get("address");

        name.setText(nameText);
        description.setText(descText);
        address.setText(locationText);

//
//        intent.putExtra("name", list.get(position).getName());
//        intent.putExtra("desc", list.get(position).getDescription());
//        intent.putExtra("address", list.get(position).getAddress());
//        intent.putExtra("rate", list.get(position).getRatings());
//        intent.putExtra("photo", list.get(position).getImagePath());
        System.out.println(photoPath);

        Glide.with(this).load(photoPath).into(imageView);



    }
}