package com.example.pokedex20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText searchBar;
    TextView nameTextView;
    TextView typeTextView;
    ImageView picSpriteImageView;
    ImageView picSpriteShinyImageView;
    TextView numberTextView;
    String picSpriteUrl;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchBar = findViewById(R.id.search_bar);
        nameTextView = findViewById(R.id.name_tv);
        typeTextView = findViewById(R.id.type_tv);
        picSpriteImageView = findViewById(R.id.pic_sprite_tv);
        picSpriteShinyImageView = findViewById(R.id.pic_sprite_shiny_tv);
        numberTextView = findViewById(R.id.number_tv);

    }

    public void fetchData(View view) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://pokeapi.co/api/v2/pokemon/" + searchBar.getText().toString();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responceObject = new JSONObject(response);
                            String nameText = responceObject.getString("name");
                            nameTextView.setText("Pokemon name: "+ nameText);

                            JSONArray typesArray = responceObject.getJSONArray("types");
                            JSONObject typeObject = typesArray.getJSONObject(0).getJSONObject("type");
                            String typeText = typeObject.getString("name");
                            typeTextView.setText("Pokemon type: "+ typeText);

                            JSONObject spriteObject = responceObject.getJSONObject("sprites");
                            String picSpriteUrl = spriteObject.getString("front_default");
                            Picasso.get().load(picSpriteUrl).into(picSpriteImageView);

                            JSONObject spriteshinyObject = responceObject.getJSONObject("sprites");
                            String shinypicSpriteUrl = spriteshinyObject.getString("back_default");
                            Picasso.get().load(shinypicSpriteUrl).into(picSpriteShinyImageView);

                            String numberText = responceObject.getString("id");
                            numberTextView.setText("# "+ numberText);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                nameTextView.setText("Not available");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void addFavorite(View view){
        SharedPreferences sharedPrefs = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        String favorites = sharedPrefs.getString("favorites","");
        if(favorites.length()>0){
            editor.putString("favorites", favorites + "," + picSpriteUrl);
        }else{
            editor.putString("favorites", picSpriteUrl);
        }
        editor.putString("favorites", picSpriteUrl);
        editor.apply();
    }
    public void goFavorite(View view){
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
}

