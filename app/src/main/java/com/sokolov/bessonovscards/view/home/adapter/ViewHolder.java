package com.sokolov.bessonovscards.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sokolov.bessonovscards.R;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;

class ViewHolder extends RecyclerView.ViewHolder {

    private final View v;

    public ViewHolder(View v) {
        super(v);
        this.v = v;
    }

    public void bind(ICategoryDisplayModel displayModel) {
        ((TextView) v.findViewById(R.id.title)).setText(displayModel.name());
        ((TextView) v.findViewById(R.id.schedule)).setText(displayModel.schedule().name());
        ((TextView) v.findViewById(R.id.cards_count)).setText(String.valueOf(displayModel.cardsCount()));
    }
}