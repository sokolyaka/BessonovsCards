package com.sokolov.bessonovscards.domain;

import android.text.TextUtils;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

public class AddNewCardUseCase implements IAddNewCardUseCase {
    private final ICardRepository cardRepository;

    public AddNewCardUseCase(ICardRepository cardRepository) {

        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(ICard card, Callback callback) {
        if (TextUtils.isEmpty(card.text())) {
            callback.onError("No text");
        } else if (TextUtils.isEmpty(card.translate())) {
            callback.onError("No translate");
        } else {
            cardRepository.save(card);
            callback.onSuccess();
        }
    }
}
