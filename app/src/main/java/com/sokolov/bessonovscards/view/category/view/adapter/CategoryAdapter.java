package com.sokolov.bessonovscards.view.category.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter {

    private final List<ICard> cards;
    private final OnItemClickListener onItemClickListener;

    public CategoryAdapter(List<ICard> cards, OnItemClickListener onItemClickListener) {
        this.cards = cards;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.category_card, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder)
                .bind(
                        cards.get(position),
                        onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public void setCards(List<ICard> cards) {
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

}
