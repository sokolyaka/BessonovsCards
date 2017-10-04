package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.entity.ICard;

public interface IAddNewCardUseCase {

    void execute(ICard card, Callback callback);

    interface Callback {
        void onSuccess();

        void onError(String message);
    }
}
