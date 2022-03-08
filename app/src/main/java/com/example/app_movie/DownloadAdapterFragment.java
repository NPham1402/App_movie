package com.example.app_movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;



public class DownloadAdapterFragment extends RecyclerView.Adapter<DownloadAdapterFragment.DownloadHolder> {

    private ArrayList<FilmClass> filmClassArrayList;
    private Context context;
    public DownloadAdapterFragment(ArrayList<FilmClass> filmClassArrayList,Context context){
        this.filmClassArrayList=filmClassArrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public DownloadAdapterFragment.DownloadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_download,parent,false);
        return new DownloadAdapterFragment.DownloadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadHolder holder, int position) {
        FilmClass film=filmClassArrayList.get(position);
        Glide.with(context).load(film.getUrl_img()).into(holder.imgView_ItemDownload);
        holder.tvPlot.setText(film.description);
        holder.tvTitle.setText(film.name);
        holder.rcGenre_itemDownload.setLayoutManager(new LinearLayoutManager(context));
        holder.genreAdapter=new GenreAdapter(film.genre,context);
        holder.rcGenre_itemDownload.setAdapter(holder.genreAdapter);
    }

    @Override
    public int getItemCount() {
        return filmClassArrayList.size();
    }

    class DownloadHolder extends RecyclerView.ViewHolder{
        ImageView imgView_ItemDownload;
        TextView tvTitle,tvPlot;
        RecyclerView rcGenre_itemDownload;
        GenreAdapter genreAdapter;
        public DownloadHolder(@NonNull View itemView) {
            super(itemView);
            imgView_ItemDownload =itemView.findViewById(R.id.imgView_itemDownload);
            tvTitle=itemView.findViewById(R.id.tvTitle_itemDownload);
            tvPlot=itemView.findViewById(R.id.tvPlot_itemDownload);
            rcGenre_itemDownload=itemView.findViewById(R.id.rvGenre_itemDownload);
        }
    }
}
