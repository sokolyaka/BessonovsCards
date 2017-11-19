package com.sokolov.bessonovscards.view.todayCards.presenter;

import com.sokolov.bessonovscards.entity.ICard;

public interface ICardsPresenter {
    void onCreate();

    void onMoveCardToNextCategory(ICard card);

    void onMoveCardToPreviewsCategory(ICard card);

    void onEditCard(ICard card);

    void onPronounce(String text);
}
