package com.example.app_movie;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class film_adapter extends RecyclerView.Adapter<film_adapter.ViewHolder> {
    private ArrayList<Film_object> films;
    private Context context;

    public film_adapter(ArrayList<Film_object> films, Context context) {
        this.films = films;
        this.context = context;

    }

    @NonNull
    @Override
    public film_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_film,parent,false);
        return new film_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull film_adapter.ViewHolder holder, int position) {
        Film_object film_object=films.get(position);
        Glide.with(context).load(film_object.getUrl_img()).into(holder.imgview);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview;
        public ViewHolder(@NonNull View view){
            super(view);
            Log.e("run","140201");imgview=view.findViewById(R.id.view_film);
        }
    }
}
