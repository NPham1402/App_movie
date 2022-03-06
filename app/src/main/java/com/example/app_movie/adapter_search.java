package com.example.app_movie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter_search extends RecyclerView.Adapter<adapter_search.ViewHolder> {
    private ArrayList<Film_object> films;
    private Context context;

    public adapter_search(ArrayList<Film_object> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public adapter_search.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.iteam_search,parent,false);
        return new adapter_search.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_search.ViewHolder holder, int position) {
       Film_object film_object= films.get(position);
        Glide.with(context).load(film_object.getUrl_img()).into(holder.imageView);
        Log.e("search",film_object.getName());
        holder.textView.setText(film_object.getName());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{
        ConstraintLayout constraintLayout;
        TextView textView;
        ImageView imageView;
        ImageButton imageButton;
        public ViewHolder(@NonNull View view) {
            super(view);
            constraintLayout=view.findViewById(R.id.seacrh_layout);
            textView=view.findViewById(R.id.titile_serch);
            imageView=view.findViewById(R.id.imageView_search);
            imageButton=view.findViewById(R.id.imgButton_play_search);
        }
    }
}
