package com.example.bohdan.pagingwikigwarsnew.paging_library;

import android.arch.paging.AsyncPagedListDiffer;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bohdan.pagingwikigwarsnew.Model;
import com.example.bohdan.pagingwikigwarsnew.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ModelAdapter extends PagedListAdapter<Model, ModelAdapter.ModelViewHolder> {

    private final AsyncPagedListDiffer<Model> mDiffer
            = new AsyncPagedListDiffer(this, DIFF_CALLBACK);

    @Override
    public void submitList(PagedList<Model> pagedList) {
        mDiffer.submitList(pagedList);
    }

    @Override
    public int getItemCount() {
        if (mDiffer == null)
            return 0;
        return mDiffer.getItemCount();
    }

    public ArrayList<Model> model;
    public Context context_;

    public ModelAdapter() {
        super(DIFF_CALLBACK);
        //this.context_ = context;
    }

   /* @Override
    public int getItemCount() {
        *//*if (model == null)
            return 0;*//*
        return model.size();
    }*/

    @NonNull
    @Override
    public ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {

      //Model  model_ = model.get(position);
        if(mDiffer!=null){
            holder.textView.setText(mDiffer.getItem(position).getDescription());
            holder.textViewTwo.setText(String.valueOf(mDiffer.getItem(position).getId()));
            Picasso.get().load(mDiffer.getItem(position).getIcon()).into(holder.imageView);
        }

        if (model!=null){
            holder.textView.setText(model.get(position).getId());
            holder.textViewTwo.setText(model.get(position).getDescription());
            Picasso.get().load(model.get(position).getIcon()).into(holder.imageView);
        }
    }


    public class ModelViewHolder extends RecyclerView.ViewHolder{

        TextView textView, textViewTwo;
        ImageView imageView;


        public ModelViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_id);
            textViewTwo = itemView.findViewById(R.id.textView2_description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }


    public static final DiffUtil.ItemCallback<Model> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Model>() {
                @Override
                public boolean areItemsTheSame(Model oldItem, Model newItem) {
                    return oldItem.getId()==newItem.getId();
                    //return true;
                }

                @Override
                public boolean areContentsTheSame(Model oldItem, Model newItem) {
                   return oldItem.equals(newItem);
                   // return true;
                }
            };
}
