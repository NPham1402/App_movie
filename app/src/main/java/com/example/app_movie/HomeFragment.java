package com.example.app_movie;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    Spinner spinner;
FirebaseFirestore firebaseFirestore;
ArrayList<FilmClass>fo;
film_adapter film_adapter;
ClipData.Item item;
RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner=view.findViewById(R.id.the_loai);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.the_loai_array, R.layout.spiner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        fo=new ArrayList<>();
        recyclerView=view.findViewById(R.id.list_all);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Flim")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String name= (String) document.getData().get("name");
                                String description = (String) document.getData().get("description");
                                String url= (String) document.getData().get("url");
                                List<String> genre = (List) document.getData().get("genre");
                                List<String> director = (List) document.getData().get("director");
                                Log.d("Testdata", document.getId() + " => " +url);
                                FilmClass film_class =new FilmClass(director,genre,name,description,url);
                                fo.add(film_class);
                                Log.e("count",fo.get(0).getUrl_img()+"");
                            }
                            film_adapter=new film_adapter(fo,getContext());
                            SharedPreferences  mPrefs = getActivity().getSharedPreferences("oject", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = mPrefs.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(fo);
                            prefsEditor.putString("MyObject", json);
                            prefsEditor.commit();
                            LinearLayoutManager llm = new LinearLayoutManager(getContext());
                            llm.setOrientation(LinearLayoutManager.HORIZONTAL);
                            recyclerView.setLayoutManager(llm);
                            recyclerView.setAdapter( film_adapter );
                        } else {
                        }
                    }
                });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fg_home, container, false);
    }
}