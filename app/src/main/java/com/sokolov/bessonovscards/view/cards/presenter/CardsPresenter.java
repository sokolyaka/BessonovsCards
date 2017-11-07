package com.sokolov.bessonovscards.view.cards.presenter;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.cards.interactor.ICardsInteractor;
import com.sokolov.bessonovscards.view.cards.view.ICardsView;

import java.util.List;

public class CardsPresenter implements ICardsPresenter {
    private final ICardsView cardsView;
    private final ICardsInteractor cardsInteractor;

    public CardsPresenter(ICardsView cardsView, ICardsInteractor cardsInteractor) {
        this.cardsView = cardsView;
        this.cardsInteractor = cardsInteractor;
    }

    @Override
    public void onCreate() {
        cardsInteractor
                .getShuffleCardsByCategory(
                        new IGetShuffleCardsByCategory.Callback() {
                            @Override
                            public void onSuccess(List<ICard> cards) {
                                cardsView.setData(cards);
                            }

                            @Override
                            public void onError(Exception e) {
                                cardsView.showError(e.toString());
                            }
                        }
                );
    }

    @Override
    public void onMoveCardToNextCategory(ICard card) {
        cardsView.showSpinner();
        cardsInteractor
                .onMoveCardToNextCategory(
                        card,
                        new IMoveCardToNextCategoryUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                cardsView.showMoveCardSuccessMessage();
                                cardsView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                cardsView.showError(e.toString());
                                cardsView.hideSpinner();
                            }
                        }
                );
    }

    @Override
    public void onMoveCardToPreviewsCategory(ICard card) {
        cardsView.showSpinner();
        cardsInteractor
                .onMoveCardToPreviewsCategory(
                        card,
                        new IMoveCardToPreviewsCategoryUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                cardsView.showMoveCardSuccessMessage();
                                cardsView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                cardsView.showError(e.toString());
                                cardsView.hideSpinner();
                            }
                        }
                );
    }

    @Override
    public void onEditCard(ICard card) {
        cardsView.showSpinner();
        cardsInteractor
                .onEditCard(
                        card,
                        new IEditCardUseCase.Callback() {
                            @Override
                            public void onSuccess(ICard card) {
                                cardsView.updateCard(card);
                                cardsView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                cardsView.showError(e.toString());
                                cardsView.hideSpinner();
                            }
                        });
    }
}
