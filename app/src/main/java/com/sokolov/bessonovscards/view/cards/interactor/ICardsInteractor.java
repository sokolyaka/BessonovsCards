package com.sokolov.bessonovscards.view.cards.interactor;

import com.sokolov.bessonovscards.domain.cards.IGetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;

public interface ICardsInteractor {

    void getShuffleCardsByCategory(IGetShuffleCardsByCategory.Callback callback);

    void onMoveCardToNextCategory(ICard card, IMoveCardToNextCategoryUseCase.Callback callback);

    void onMoveCardToPreviewsCategory(ICard card, IMoveCardToPreviewsCategoryUseCase.Callback callback);
}
