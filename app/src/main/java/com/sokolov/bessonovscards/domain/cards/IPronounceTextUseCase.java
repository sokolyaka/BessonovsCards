package com.sokolov.bessonovscards.domain.cards;

public interface IPronounceTextUseCase {
    void execute(String text, Callback callback);

    interface Callback {
        void onSuccess();

        void onError();
    }
}
