package com.example.projectaux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    Context context;

    ArrayList<posts> list;
    

    public PostsAdapter(Context context, ArrayList<posts> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.posts,parent, false);

        return new MyViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        posts posts = list.get(position);
        holder.title.setText(posts.getTitle());
        holder.level.setText(posts.getSubject());
        holder.lang.setText(posts.getSec());
        holder.desc.setText(posts.getDesc());
        holder.author.setText(posts.getAuthor());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, level, lang, desc, author;

        CardView posts1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleposts);
            level = itemView.findViewById(R.id.level);
            lang = itemView.findViewById(R.id.lang);
            desc = itemView.findViewById(R.id.descposts);
            author = itemView.findViewById(R.id.author);



        }


    }


}

