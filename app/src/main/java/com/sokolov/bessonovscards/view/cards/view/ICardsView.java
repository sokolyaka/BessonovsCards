package com.sokolov.bessonovscards.view.cards.view;

import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ITextMode;

import java.util.List;

public interface ICardsView {
    void refreshData(List<ICard> cards, ITextMode textMode);

    void showError(String message);

    void showMoveCardSuccessMessage();

    void showSpinner();

    void hideSpinner();

    void updateCard(ICard card);
}
