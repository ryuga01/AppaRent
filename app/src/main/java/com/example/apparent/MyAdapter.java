package com.example.apparent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<ApartmentUnit> list;


    public MyAdapter(Context context, ArrayList<ApartmentUnit> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ApartmentUnit user = list.get(position);
        holder.name.setText(user.getName());
        holder.ratingText.setText(Double.toString(user.getRatings()));
        holder.ratingBar.setRating((float) user.getRatings());
        holder.image.setBackground(Drawable.createFromPath(user.getPhoto()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, address ,ratingText ;
        View image;

        RatingBar ratingBar;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.apartmentName);
            image = itemView.findViewById(R.id.apartmentBackgroundImage);
            ratingText = itemView.findViewById(R.id.apartmentNumberOfRatings);
            ratingBar  = itemView.findViewById(R.id.apartmentRating);




        }
    }

}