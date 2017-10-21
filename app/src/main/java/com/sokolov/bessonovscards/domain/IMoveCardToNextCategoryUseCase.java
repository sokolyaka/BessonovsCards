package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.entity.ICard;

public interface IMoveCardToNextCategoryUseCase {

    void execute(ICard card, Callback callback);

    interface Callback {
        void onSuccess();

        void onError(Exception e);
    }
}
