package com.sokolov.bessonovscards.view.category.view.interactor;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsByCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;

public class CategoryInteractor implements ICategoryInteractor {
    private final IGetCardsByCategoryUseCase getCardsForTodayUseCase;
    private final IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase;
    private final IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase;
    private final IEditCardUseCase editCardUseCase;

    public CategoryInteractor(
            IGetCardsByCategoryUseCase getCardsByCategoryUseCase,
            IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase,
            IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase,
            IEditCardUseCase editCardUseCase) {

        this.getCardsForTodayUseCase = getCardsByCategoryUseCase;
        this.moveCardToNextCategoryUseCase = moveCardToNextCategoryUseCase;
        this.moveCardToPreviewsCategoryUseCase = moveCardToPreviewsCategoryUseCase;
        this.editCardUseCase = editCardUseCase;
    }

    @Override
    public void getCardsByCategoryUseCase(IGetCardsByCategoryUseCase.Callback callback) {
        getCardsForTodayUseCase.execute(callback);
    }

    @Override
    public void onMoveCardToNextCategory(ICard card, IMoveCardToNextCategoryUseCase.Callback callback) {
        moveCardToNextCategoryUseCase.execute(card, callback);
    }

    @Override
    public void onMoveCardToPreviewsCategory(ICard card, IMoveCardToPreviewsCategoryUseCase.Callback callback) {
        moveCardToPreviewsCategoryUseCase.execute(card, callback);
    }

    @Override
    public void onEditCard(ICard card, IEditCardUseCase.Callback callback) {
        editCardUseCase.execute(card, callback);
    }
}
