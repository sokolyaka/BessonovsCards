package com.sokolov.bessonovscards.domain.cards;

public interface IDeleteCardUseCase {

    void execute(String cardUuid, Callback callback);

    interface Callback{
        void onSuccess();

        void onError(Exception e);
    }
}
