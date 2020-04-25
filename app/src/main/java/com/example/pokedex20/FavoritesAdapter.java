package com.example.pokedex20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

public class FavoritesAdapter extends ArrayAdapter<String> {

    public FavoritesAdapter(Context context, String[] favoritesArray) {
        super(context, 0, favoritesArray);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.favs_list_item, parent, false);
        String currentPokeImageUrl = getItem(position);
        ImageView pokeImageView = convertView.findViewById(R.id.fav_pokemon);
        Picasso.get().load(currentPokeImageUrl).into(pokeImageView);
        return convertView;
    }
}
