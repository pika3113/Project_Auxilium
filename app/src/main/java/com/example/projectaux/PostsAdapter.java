package com.example.projectaux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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

        boolean isVisible = posts.visibility;
        holder.constraintLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {

        return list.size();

    }


    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, level, lang, desc, author;
        EditText comment;
        Button postcomment;
        LinearLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleposts);
            level = itemView.findViewById(R.id.level);
            lang = itemView.findViewById(R.id.lang);
            desc = itemView.findViewById(R.id.descposts);
            author = itemView.findViewById(R.id.author);
            constraintLayout = itemView.findViewById(R.id.expandedlayout);
            comment = itemView.findViewById(R.id.commentmade);
            postcomment = itemView.findViewById(R.id.commentbutton);

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posts posts = list.get(getAdapterPosition());
                    posts.setVisibility(!posts.isVisibility());
                    notifyItemChanged(getAdapterPosition());

                }
            });



        }




    }




}



