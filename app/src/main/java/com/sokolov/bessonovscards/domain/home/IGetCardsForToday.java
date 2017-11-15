package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.entity.ICard;

import java.util.List;

public interface IGetCardsForToday {
    void execute(Callback callback);

    interface Callback {
        void onSuccess(List<ICard> cards);

        void onError(Exception e);
    }
}
