package com.sokolov.bessonovscards.view.todayCards.presenter;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IPronounceTextUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ITextMode;
import com.sokolov.bessonovscards.view.todayCards.interactor.ITodayCardsInteractor;
import com.sokolov.bessonovscards.view.todayCards.view.ICardsView;

import java.util.Collections;
import java.util.List;

public class TodayCardsPresenter implements ITodayCardsPresenter {
    private final ICardsView cardsView;
    private final ITodayCardsInteractor cardsInteractor;
    private final ITextMode textMode;

    public TodayCardsPresenter(ICardsView cardsView, ITodayCardsInteractor cardsInteractor, ITextMode textMode) {
        this.cardsView = cardsView;
        this.cardsInteractor = cardsInteractor;
        this.textMode = textMode;
    }

    @Override
    public void onCreate() {
        cardsInteractor
                .getCardsForTodayUseCase(
                        new IGetCardsForTodayUseCase.Callback() {
                            @Override
                            public void onSuccess(List<ICard> cards) {
                                Collections.shuffle(cards);
                                cardsView.refreshData(cards, textMode);
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

    @Override
    public void onPronounce(String text) {
        cardsView.showSpinner();

        cardsInteractor
                .onPronounce(
                        text,
                        new IPronounceTextUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                cardsView.hideSpinner();
                            }

                            @Override
                            public void onError() {

                            }
                        }
                );
    }
}
