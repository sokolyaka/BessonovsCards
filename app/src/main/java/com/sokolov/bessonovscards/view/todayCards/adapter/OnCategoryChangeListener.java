package com.sokolov.bessonovscards.view.todayCards.adapter;

import com.sokolov.bessonovscards.entity.ICard;

public interface OnCategoryChangeListener {

    void onNext(ICard card);
    void onPreviews(ICard card);
}
