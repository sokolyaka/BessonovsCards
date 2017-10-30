package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DeleteCardUseCaseTest {

    @Mock
    private IDeleteCardUseCase.Callback callback;
    @Mock
    private ICardRepository cardsRep;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnSuccess() {
        new DeleteCardUseCase(cardsRep).execute("cardUuid", callback);

        verify(cardsRep).delete("cardUuid");
        verify(callback).onSuccess();
    }
}
