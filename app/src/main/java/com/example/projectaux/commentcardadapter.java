package com.example.projectaux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class commentcardadapter extends RecyclerView.Adapter<commentcardadapter.MyViewHolder> {

    Context context;
    ArrayList<comment> list;

    public commentcardadapter(Context context, ArrayList<comment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.commentscard,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        comment comment = list.get(position);
        holder.comment.setText(comment.getCommentitem());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView comment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comment = itemView.findViewById(R.id.commentitem);
        }
    }

}
