package com.sokolov.bessonovscards.domain;

public interface IAddNewCardUseCase {

    void execute(String text, String translate, Callback callback);

    interface Callback {
        void onSuccess();

        void onError(String message);
    }
}
