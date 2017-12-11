package com.sokolov.bessonovscards.view.category.view.presenter;

import com.sokolov.bessonovscards.entity.ICard;

public interface ICategoryPresenter {
    void onCreate();

    void onMoveCardToNextCategory();

    void onMoveCardToPreviewsCategory();

    void onEditCard();

    void selectedCard(ICard card);
}
