package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;

public class DeleteCardUseCase implements IDeleteCardUseCase {
    private final ICardRepository cardRepository;

    public DeleteCardUseCase(ICardRepository cardRepository) {

        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(String cardUuid, Callback callback) {
        try {
            cardRepository.delete(cardUuid);
            callback.onSuccess();
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
