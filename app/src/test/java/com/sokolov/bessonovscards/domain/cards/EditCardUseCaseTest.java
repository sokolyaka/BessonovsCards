package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class EditCardUseCaseTest {
    @Mock
    private IEditCardUseCase.Callback callback;
    @Mock
    private ICardRepository cardRep;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        Card card = new Card("uuid", "text", "translate", "categoryName");
        new EditCardUseCase(cardRep).execute(card, callback);

        verify(cardRep).save(card);
        verify(callback).onSuccess(card);
    }

}