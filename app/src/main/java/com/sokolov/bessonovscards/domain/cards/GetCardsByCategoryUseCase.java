package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;

public class GetCardsByCategoryUseCase implements IGetCardsByCategoryUseCase {
    private final ICardRepository cardRepository;
    private final String categoryName;

    public GetCardsByCategoryUseCase(ICardRepository cardRepository, String categoryName) {
        this.cardRepository = cardRepository;
        this.categoryName = categoryName;
    }

    @Override
    public void execute(Callback callback) {
        try {
            callback.onSuccess(
                    cardRepository.getAllByCategoryName(
                            categoryName));
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
