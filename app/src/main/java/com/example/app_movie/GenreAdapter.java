package com.example.app_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {
    List<String> arrayList;
    Context context;
    public GenreAdapter(List<String> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreHolder(LayoutInflater.from(context).inflate(R.layout.genre_of_item_download,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        String genre=arrayList.get(position);
        holder.textView.setText(genre);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class GenreHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public GenreHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tvGenre_rcGenre_itemDownload);
        }
    }
}
