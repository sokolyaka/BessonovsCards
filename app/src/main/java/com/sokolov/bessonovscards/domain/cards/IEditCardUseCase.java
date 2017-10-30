package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.entity.ICard;

public interface IEditCardUseCase {

    void execute(ICard card, Callback callback);

    interface Callback {
        void onSuccess(ICard card);

        void onError(Exception e);
    }
}
