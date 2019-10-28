package com.anas.pokedex.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.Toast;

import com.anas.pokedex.PokemonListAdapter;
import com.anas.pokedex.R;
import com.anas.pokedex.api.Client;
import com.anas.pokedex.api.Service;
import com.anas.pokedex.model.Pokemon;
import com.anas.pokedex.model.PokemonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private PokemonListAdapter listAdapter;

    private int offset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        loadJSON(offset);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerview);
        listAdapter = new PokemonListAdapter();
        recyclerView.setAdapter(listAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItemCount = layoutManager.findFirstVisibleItemPosition();

                        if(visibleItemCount + pastVisibleItemCount >= totalItemCount){
                            offset += 20;
                            loadJSON(offset);
                        }


                }
            }
        });

        offset = 0;

    }

    private void loadJSON(int offset) {

        try {

            Client client = new Client();
            Service apiService = client.getClient().create(Service.class);

            Call<PokemonResponse> call = apiService.getResults(20, offset);

            call.enqueue(new Callback<PokemonResponse>() {
                @Override
                public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<Pokemon> pokemonList = pokemonResponse.getResults();
                    listAdapter.addListItems(pokemonList);

                    for (int i = 0; i < pokemonList.size(); i ++){

                        Pokemon pokemon = pokemonList.get(i);
                        Log.i(TAG, "Pokemon: " + pokemon.getName() + ", " + pokemon.getUrl() );
                    }

                }

                @Override
                public void onFailure(Call<PokemonResponse> call, Throwable t) {

                    Log.e(TAG, t.getMessage());
                    Toast.makeText(MainActivity.this, "An Error Occured", Toast.LENGTH_LONG).show();

                }
            });

        }catch(Exception e){
            Log.e(TAG, e.getMessage());
        }

    }

}

