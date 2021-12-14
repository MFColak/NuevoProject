package com.android.mfcolak.nuevoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Poster> posterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        posterList = new ArrayList<>();

        fetchImagesAndTitle();
        ImageView img = findViewById(R.id.firs_image);
        String firstUrl="https://via.placeholder.com/600/92c952";
        GlideUrl glideUrl = new GlideUrl(firstUrl, new LazyHeaders.Builder().addHeader("User-Agent", "5").build());
        Glide.with(img.getContext()).load(glideUrl).into(img);

    }


    private void fetchImagesAndTitle() {

        String url = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {

                    for (int i = 0 ; i < response.length() ; i ++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String title = jsonObject.getString("title");  //=title
                            String image = jsonObject.getString("url"); //=poster

                            Poster poster = new Poster(title , image);
                            posterList.add(poster);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ImageAdapter adapter = new ImageAdapter(MainActivity.this , posterList);

                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }



}