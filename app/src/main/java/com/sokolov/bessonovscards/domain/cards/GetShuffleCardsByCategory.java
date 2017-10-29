package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

import java.util.Collections;
import java.util.List;

public class GetShuffleCardsByCategory implements IGetShuffleCardsByCategory {
    private final ICardRepository cardRepository;
    private final String categoryName;

    public GetShuffleCardsByCategory(ICardRepository cardRepository, String categoryName) {
        this.cardRepository = cardRepository;
        this.categoryName = categoryName;
    }

    @Override
    public void execute(Callback callback) {
        try {
            List<ICard> answer = cardRepository.getAllByCategoryName(categoryName);
            Collections.shuffle(answer);
            callback.onSuccess(answer);
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
