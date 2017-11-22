package com.sokolov.bessonovscards.view.category.view.interactor;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsByCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.ICard;

public interface ICategoryInteractor {

    void getCardsByCategoryUseCase(IGetCardsByCategoryUseCase.Callback callback);

    void onMoveCardToNextCategory(ICard card, IMoveCardToNextCategoryUseCase.Callback callback);

    void onMoveCardToPreviewsCategory(ICard card, IMoveCardToPreviewsCategoryUseCase.Callback callback);

    void onEditCard(ICard card, IEditCardUseCase.Callback callback);

}
