package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.entity.ICard;

public interface IMoveCardToPreviewsCategoryUseCase {
    void execute(ICard card, Callback callback);

    interface Callback {
        void onSuccess();

        void onError(Exception e);
    }
}
