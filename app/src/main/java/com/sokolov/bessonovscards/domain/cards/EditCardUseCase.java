package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

public class EditCardUseCase implements IEditCardUseCase {
    private final ICardRepository cardRepository;

    public EditCardUseCase(ICardRepository cardRepository) {

        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(ICard card, Callback callback) {
        try {
            cardRepository.save(card);
            callback.onSuccess(card);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
