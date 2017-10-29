package com.sokolov.bessonovscards.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter {

    private final List<ICategoryDisplayModel> categories;
    private final OnItemClickListener onItemClickListener;

    public CategoryAdapter(List<ICategoryDisplayModel> categories, OnItemClickListener onItemClickListener) {
        this.categories = categories;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.home_category, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder)
                .bind(
                        categories.get(position),
                        onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<ICategoryDisplayModel> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

}
