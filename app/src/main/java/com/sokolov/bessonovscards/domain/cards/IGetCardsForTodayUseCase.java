package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public interface IGetCardsForTodayUseCase {
    void execute(Callback callback);

    interface Callback {
        void onSuccess(List<ICard> cards);

        void onError(Exception e);
    }
}
