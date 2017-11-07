package com.sokolov.bessonovscards.view.cards.view;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public interface ICardsView {
    void setData(List<ICard> cards);

    void showError(String message);

    void showMoveCardSuccessMessage();

    void showSpinner();

    void hideSpinner();

    void updateCard(ICard card);
}
