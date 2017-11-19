package com.sokolov.bessonovscards.view.todayCards.interactor;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IPronounceTextUseCase;
import com.sokolov.bessonovscards.entity.ICard;

public class TodayCardsInteractor implements ITodayCardsInteractor {
    private final IGetCardsForTodayUseCase getCardsForTodayUseCase;
    private final IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase;
    private final IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase;
    private final IEditCardUseCase editCardUseCase;
    private final IPronounceTextUseCase pronounceUseCase;

    public TodayCardsInteractor(
            IGetCardsForTodayUseCase getCardsForTodayUseCase,
            IMoveCardToNextCategoryUseCase moveCardToNextCategoryUseCase,
            IMoveCardToPreviewsCategoryUseCase moveCardToPreviewsCategoryUseCase,
            IEditCardUseCase editCardUseCase,
            IPronounceTextUseCase pronounceUseCase) {

        this.getCardsForTodayUseCase = getCardsForTodayUseCase;
        this.moveCardToNextCategoryUseCase = moveCardToNextCategoryUseCase;
        this.moveCardToPreviewsCategoryUseCase = moveCardToPreviewsCategoryUseCase;
        this.editCardUseCase = editCardUseCase;
        this.pronounceUseCase = pronounceUseCase;
    }

    @Override
    public void getCardsForTodayUseCase(IGetCardsForTodayUseCase.Callback callback) {
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

    @Override
    public void onPronounce(String text, IPronounceTextUseCase.Callback callback) {
        pronounceUseCase.execute(text, callback);
    }
}
