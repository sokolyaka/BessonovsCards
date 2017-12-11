package com.sokolov.bessonovscards.view.category.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.entity.ICard;

class ViewHolder extends RecyclerView.ViewHolder {

    private final View v;

    public ViewHolder(View v) {
        super(v);
        this.v = v;
    }

    public void bind(ICard card, OnItemClickListener onItemClickListener) {
        ((TextView) v.findViewById(R.id.tv_text)).setText(card.text());
        v.setOnClickListener(v1 -> onItemClickListener.onItemClick(card));
    }
}