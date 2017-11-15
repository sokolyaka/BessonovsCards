package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.data.reposiroty.IScheduleRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.entity.ISchedule;

import org.joda.time.LocalDate;

public class MoveCardToPreviewsCategoryUseCase implements IMoveCardToPreviewsCategoryUseCase {

    private final ICardRepository cardRepository;
    private final ICategoryRepository categoryRepository;
    private final IScheduleRepository scheduleRepository;

    public MoveCardToPreviewsCategoryUseCase(
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
                    categoryRepository.getByName(
                            card.categoryName());


            ICategory prevCategory =
                    categoryRepository.getByOrdinal(
                            curCategory
                                    .ordinal() - 1);

            ISchedule nextSchedule =
                    scheduleRepository.getByUuid(
                            prevCategory.scheduleUuid());

            cardRepository.save(
                    new Card(
                            card.id(),
                            card.text(),
                            card.translate(),
                            prevCategory
                                    .name(),
                            LocalDate
                                    .now()
                                    .plusDays(
                                            nextSchedule.durationInDays())));
            callback.onSuccess();
        } catch (NotFoundException e) {
            callback.onError(e);
        }
    }
}
