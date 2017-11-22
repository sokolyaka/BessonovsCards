package com.sokolov.bessonovscards.view.category.view;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public interface ICategoryView {
    void refreshData(List<ICard> cards);

    void showError(String message);

    void showMoveCardSuccessMessage();

    void showSpinner();

    void hideSpinner();

    void updateCard(ICard card);

    void showBottomBar();
}
