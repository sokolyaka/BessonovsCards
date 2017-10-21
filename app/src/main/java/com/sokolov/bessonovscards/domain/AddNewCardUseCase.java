package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.utils.Text;

import java.util.UUID;

public class AddNewCardUseCase implements IAddNewCardUseCase {
    private final ICardRepository cardRepository;

    public AddNewCardUseCase(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(String text, String translate, Callback callback) {
        if (new Text(text).isEmpty()) {
            callback.onError("No text");
        } else if (new Text(translate).isEmpty()) {
            callback.onError("No translate");
        } else {
            cardRepository.save(
                    new Card(
                            UUID.randomUUID().toString(),
                            text,
                            translate,
                            ""));

            callback.onSuccess();
        }
    }
}
