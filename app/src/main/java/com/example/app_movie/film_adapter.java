package com.example.app_movie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class film_adapter extends RecyclerView.Adapter<film_adapter.ViewHolder> {
    private ArrayList<FilmClass> films;
    private Context context;

    public film_adapter(ArrayList<FilmClass> films, Context context) {
        this.films = films;
        this.context = context;

    }

    @NonNull
    @Override
    public film_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
<<<<<<< HEAD
        View view= LayoutInflater.from(context).inflate(R.layout.iteam_film,parent,false);
=======
        View view= LayoutInflater.from(context).inflate(R.layout.item_film,parent,false);
>>>>>>> 1b44f0ae57aff27d72da10e3611ed8ae508826bd
        return new film_adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull film_adapter.ViewHolder holder, int position) {
        FilmClass film_class =films.get(position);
        Log.e("run",""+ film_class.getUrl_img());
        Glide.with(context).load(film_class.getUrl_img()).into(holder.imgview);

    }

    @Override
    public int getItemCount() {
        return films.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgview;
        public ViewHolder(@NonNull View view){
            super(view);
           imgview=view.findViewById(R.id.view_film);
        }
    }
}
