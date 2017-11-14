package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.data.reposiroty.IScheduleRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.entity.ISchedule;

public class MoveCardToNextCategoryUseCase implements IMoveCardToNextCategoryUseCase {
    private final ICardRepository cardRepository;
    private final ICategoryRepository categoryRepository;
    private final IScheduleRepository scheduleRepository;

    public MoveCardToNextCategoryUseCase(
            ICardRepository cardRepository,
            ICategoryRepository categoryRepository,
            IScheduleRepository scheduleRepository) {

        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public void execute(ICard card, Callback callback) {
        try {
            ICategory curCategory =
                    categoryRepository
                            .getByName(
                                    card.categoryName());
            ICategory nextCategory =
                    categoryRepository
                            .getByOrdinal(
                                    curCategory.ordinal() + 1);

            ISchedule nextSchedule =
                    scheduleRepository
                            .getByUuid(
                                    nextCategory.scheduleUuid());

            cardRepository.save(
                    new Card(
                            card.id(),
                            card.text(),
                            card.translate(),
                            nextCategory.name(),
                            card
                                    .date()
                                    .plusDays(
                                            nextSchedule.durationInDays())));
            callback.onSuccess();
        } catch (NotFoundException e) {
            callback.onError(e);
        }
    }
}
