package com.anas.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anas.pokedex.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder>{

    private List<Pokemon> data;

    public PokemonListAdapter() {
        data = new ArrayList<Pokemon>();
    }

    @NonNull
    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PokemonListAdapter.ViewHolder viewHolder, int position) {
        Pokemon pokemon = data.get(position);
        viewHolder.name.setText(pokemon.getName());

        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ pokemon.getNumber() + ".png";

        Picasso.get()
                .load(imageUrl)
                .into(viewHolder.image);

    }

    public void addListItems(ArrayList<Pokemon> pokemonList) {
        data.addAll(pokemonList);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.pokemon_name);
            image = view.findViewById(R.id.pokemon_image);

        }
    }

}