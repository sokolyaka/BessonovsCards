package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.utils.Text;

import org.joda.time.LocalDate;

import java.util.UUID;

import javax.inject.Inject;

public class AddNewCardUseCase implements IAddNewCardUseCase {
    private final ICardRepository cardRepository;
    private final String categoryName;

    @Inject
    public AddNewCardUseCase(ICardRepository cardRepository, String categoryName) {
        this.cardRepository = cardRepository;
        this.categoryName = categoryName;
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
                            categoryName,
                            LocalDate.now()));

            callback.onSuccess();
        }
    }
}
