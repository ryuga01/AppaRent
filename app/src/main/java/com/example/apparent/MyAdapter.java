package com.example.apparent;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<ApartmentUnit> list;
     RecyclerviewClickListener listener;



    public MyAdapter(Context context, ArrayList<ApartmentUnit> list, RecyclerviewClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
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
        Drawable drawable = holder.ratingBar.getProgressDrawable();
       // holder.ratingBar.setRating((float) user.getRatings());
//        holder.image.setBackgroundColor(0xFF00FF00);
        Glide.with(holder.itemView.getContext()).load(user.getImagePath()).thumbnail().into(holder.imageView);



        System.out.println("The Image pathX "+user.getImagePath());
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerviewClickListener {
        void onClick(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView name, address ,ratingText ;
        ImageView imageView;
        RatingBar ratingBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.apartmentName);
            imageView = itemView.findViewById(R.id.apartmentBackgroundImage);
            ratingText = itemView.findViewById(R.id.apartmentNumberOfRatings);
            ratingBar  = itemView.findViewById(R.id.apartmentRating);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if(listener != null){
//                        int position = getAdapterPosition();
//                        if(position != RecyclerView.NO_POSITION){
//                            listener.onClick(view,position);
//                        }
//                    }
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context.getApplicationContext(),
                            MainPageInformationDisplay.class);
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("desc", list.get(position).getDescription());
                    intent.putExtra("address", list.get(position).getAddress());
                    intent.putExtra("rate", list.get(position).getRatings());
                    intent.putExtra("photo", list.get(position).getImagePath());
                    context.startActivity(intent);

                }
            });
        }
        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());

        }
    }


}