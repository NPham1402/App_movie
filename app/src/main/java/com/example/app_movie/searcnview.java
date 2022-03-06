package com.example.app_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class searcnview extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Film_object> film_object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcnview);
        recyclerView=findViewById(R.id.rv_search);
        searchView=findViewById(R.id.searchView_searchview);
        Gson gson = new Gson();
        SharedPreferences mPrefs =getSharedPreferences("oject", Context.MODE_PRIVATE);
        String json = mPrefs.getString("MyObject", "");
        Type collectionType = new TypeToken<Collection<Film_object>>(){}.getType();
        film_object = gson.fromJson(json,collectionType);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
       adapter_search film_adapter=new adapter_search(film_object,this);
        recyclerView.setAdapter( film_adapter );
    }
}