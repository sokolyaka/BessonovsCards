package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICard;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetCardsForTodayUseCase implements IGetCardsForToday {

    private final ICardRepository cardRepository;
    private final Set<String> invalidCategoryNames;

    /**
     * @param cardRepository
     * @param categoryNamesToIgnore categories what should ignore
     */
    public GetCardsForTodayUseCase(ICardRepository cardRepository, Set<String> categoryNamesToIgnore) {

        this.cardRepository = cardRepository;
        this.invalidCategoryNames = categoryNamesToIgnore;
    }


    @Override
    public void execute(Callback callback) {
        try {
            List<ICard> allByDate =
                    cardRepository.getAll();

            callback.onSuccess(
                    allByDate
                            .stream()
                            .filter(card ->
                                    !invalidCategoryNames.contains(card.categoryName()))
                            .filter(card ->
                                    card.date().isBefore(LocalDate.now())
                                            || card.date().isEqual(LocalDate.now()))
                            .collect(
                                    ArrayList::new,
                                    ArrayList::add,
                                    ArrayList::addAll));

        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
