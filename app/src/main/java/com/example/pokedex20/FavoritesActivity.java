package com.example.pokedex20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        SharedPreferences sharedPrefs = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);

        String defaultUrl = "https://via.placeholder.com/300x400.png?text=No+Picture+Found";
        String favorites = sharedPrefs.getString("favorites",defaultUrl);
        String[] favoritesArray = favorites.split(",");

        ListView favoritesListView = findViewById(R.id.favs_list_view);

        FavoritesAdapter pokeAdapter = new FavoritesAdapter(this, favoritesArray );
        favoritesListView.setAdapter(pokeAdapter);

    }
}
