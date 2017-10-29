package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

import java.util.Collections;
import java.util.List;

public class GetShuffleCardsByCategory implements IGetShuffleCardsByCategory {
    private final ICardRepository cardRepository;

    public GetShuffleCardsByCategory(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void execute(String categoryName, Callback callback) {
        List<ICard> answer = cardRepository.getAllByCategoryName(categoryName);
        Collections.shuffle(answer);
        callback.onSuccess(answer);
    }
}
