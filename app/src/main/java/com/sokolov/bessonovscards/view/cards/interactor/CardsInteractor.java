package com.sokolov.bessonovscards.view.cards.interactor;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IPronounceTextUseCase;
import com.sokolov.bessonovscards.entity.ICard;

public class CardsInteractor implements ICardsInteractor {
    private final IGetShuffleCardsByCategory getShuffleCardsByCategory;
    private final IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase;
    private final IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase;
    private final IEditCardUseCase editCardUseCase;
    private final IPronounceTextUseCase pronounceUseCase;

    public CardsInteractor(
            IGetShuffleCardsByCategory getShuffleCardsByCategory,
            IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase,
            IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase,
            IEditCardUseCase editCardUseCase,
            IPronounceTextUseCase pronounceUseCase) {

        this.getShuffleCardsByCategory = getShuffleCardsByCategory;
        this.moveCardToNextCategoryUseCase = moveCardToNextCategoryUseCase;
        this.moveCardToPreviewsCategoryUseCase = moveCardToPreviewsCategoryUseCase;
        this.editCardUseCase = editCardUseCase;
        this.pronounceUseCase = pronounceUseCase;
    }

    @Override
    public void getShuffleCardsByCategory(IGetShuffleCardsByCategory.Callback callback) {
        getShuffleCardsByCategory.execute(callback);
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

    @Override
    public void onPronounce(String text, IPronounceTextUseCase.Callback callback) {
        pronounceUseCase.execute(text, callback);
    }
}
