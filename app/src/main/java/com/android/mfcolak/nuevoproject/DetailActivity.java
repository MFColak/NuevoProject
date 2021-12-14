package com.android.mfcolak.nuevoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Body> bodyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        bodyList = new ArrayList<>();
        fetchBody();


        ImageView imageView = findViewById(R.id.poster_image);
        TextView title = findViewById(R.id.tittle_id);
        TextView body = findViewById(R.id.boddy_id);


        Bundle bundle = getIntent().getExtras();

        String mTitle = bundle.getString("title");
        String mPoster = bundle.getString("poster");

        Glide.with(this).load(mPoster).into(imageView);
        title.setText(mTitle);


    }
    private void fetchBody(){
        String url = "https://jsonplaceholder.typicode.com/comments?postId=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response ->
                {

                    for (int i = 0 ; i < response.length() ; i ++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String body = jsonObject.getString("body");

                            Body boddy = new Body(body);
                            bodyList.add(boddy);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        DetailAdapter adapter = new DetailAdapter(DetailActivity.this , bodyList);

                        recyclerView.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

}