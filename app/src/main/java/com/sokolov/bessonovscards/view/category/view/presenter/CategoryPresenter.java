package com.sokolov.bessonovscards.view.category.view.presenter;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsByCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.category.view.ICategoryView;
import com.sokolov.bessonovscards.view.category.view.interactor.ICategoryInteractor;

import java.util.List;

public class CategoryPresenter implements ICategoryPresenter {
    private final ICategoryView categoryView;
    private final ICategoryInteractor cardsInteractor;
    private ICard card;

    public CategoryPresenter(ICategoryView categoryView, ICategoryInteractor cardsInteractor) {
        this.categoryView = categoryView;
        this.cardsInteractor = cardsInteractor;
    }

    @Override
    public void onCreate() {
        cardsInteractor
                .getCardsByCategoryUseCase(
                        new IGetCardsByCategoryUseCase.Callback() {

                            @Override
                            public void onSuccess(List<ICard> cards) {
                                categoryView.refreshData(cards);
                            }

                            @Override
                            public void onError(Exception e) {
                                categoryView.showError(e.toString());
                            }
                        }
                );
    }

    @Override
    public void onMoveCardToNextCategory() {
        categoryView.showSpinner();
        cardsInteractor
                .onMoveCardToNextCategory(
                        card,
                        new IMoveCardToNextCategoryUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                categoryView.showMoveCardSuccessMessage();
                                categoryView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                categoryView.showError(e.toString());
                                categoryView.hideSpinner();
                            }
                        }
                );
    }

    @Override
    public void onMoveCardToPreviewsCategory() {
        categoryView.showSpinner();
        cardsInteractor
                .onMoveCardToPreviewsCategory(
                        card,
                        new IMoveCardToPreviewsCategoryUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                categoryView.showMoveCardSuccessMessage();
                                categoryView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                categoryView.showError(e.toString());
                                categoryView.hideSpinner();
                            }
                        }
                );
    }

    @Override
    public void onEditCard() {
        categoryView.showSpinner();
        cardsInteractor
                .onEditCard(
                        card,
                        new IEditCardUseCase.Callback() {
                            @Override
                            public void onSuccess(ICard card) {
                                categoryView.updateCard(card);
                                categoryView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                categoryView.showError(e.toString());
                                categoryView.hideSpinner();
                            }
                        });
    }

    @Override
    public void selectedCard(ICard card) {
        this.card = card;
        categoryView.showBottomBar();
    }

}
