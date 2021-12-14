package com.android.mfcolak.nuevoproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
    private Context context;
    private List<Poster> posterList;

    public ImageAdapter(Context context , List<Poster> posters){
        this.context = context;
        posterList = posters;

    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        Poster poster = posterList.get(position);


            holder.title.setText(poster.getTitle());

       // Glide.with(context).load(poster.getPoster()).into(holder.imageView);
        ImageView imgThumb = holder.imageView;
        GlideUrl url = new GlideUrl(poster.getImage(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "5")
                .build());

        Glide.with(context).load(url).into(imgThumb);



        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title" , poster.getTitle());
                bundle.putString("poster" , poster.getImage());


                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageView firsImage;
        TextView title;
        ConstraintLayout constraintLayout;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            firsImage = itemView.findViewById(R.id.firs_image);
            title = itemView.findViewById(R.id.title_id);
            constraintLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
