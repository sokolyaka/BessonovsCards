package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ICategory;

public class MoveCardToPreviewsCategoryUseCase implements IMoveCardToPreviewsCategoryUseCase {
    private final ICardRepository cardRepository;
    private final ICategoryRepository categoryRepository;

    public MoveCardToPreviewsCategoryUseCase(ICardRepository cardRepository, ICategoryRepository categoryRepository) {

        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(ICard card, Callback callback) {
        try {
            ICategory curCategory =
                    categoryRepository.getByName(
                            card.categoryName());


            ICategory prevCategory =
                    categoryRepository.getByOrdinal(
                            curCategory
                                    .ordinal() - 1);

            cardRepository.save(
                    new Card(
                            card.id(),
                            card.text(),
                            card.translate(),
                            prevCategory
                                    .name(),
                            card.date()));
            callback.onSuccess();
        } catch (NotFoundException e) {
            callback.onError(e);
        }
    }
}
