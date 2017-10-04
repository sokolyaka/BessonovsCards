package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.verify;

public class AddNewCardUseCaseTest {
    @Mock
    private IAddNewCardUseCase.Callback callback;
    @Mock
    private ICardRepository cardRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        Card card = new Card(UUID.randomUUID().toString(), "text", "translate", "EMPTY");
        new AddNewCardUseCase(cardRepository)
                .execute(card, callback);

        verify(cardRepository).save(card);
        verify(callback).onSuccess();
    }

    @Test
    public void noText() throws Exception {
        Card card = new Card(UUID.randomUUID().toString(), "", "translate", "EMPTY");
        new AddNewCardUseCase(cardRepository)
                .execute(card, callback);

        verify(callback).onError("No text");
    }

    @Test
    public void noTranslate() throws Exception {
        Card card = new Card(UUID.randomUUID().toString(), "text", "", "EMPTY");
        new AddNewCardUseCase(cardRepository)
                .execute(card, callback);

        verify(callback).onError("No translate");
    }
}