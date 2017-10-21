package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        new AddNewCardUseCase(cardRepository)
                .execute("text", "translate", callback);

        ArgumentCaptor<Card> captor = ArgumentCaptor.forClass(Card.class);
        verify(cardRepository).save(captor.capture());
        Assert.assertEquals(captor.getValue().text(), "text");
        Assert.assertEquals(captor.getValue().translate(), "translate");
        verify(callback).onSuccess();
    }

    @Test
    public void noText() throws Exception {
        new AddNewCardUseCase(cardRepository)
                .execute("", "translate" , callback);

        verify(callback).onError("No text");
    }

    @Test
    public void noTranslate() throws Exception {
        new AddNewCardUseCase(cardRepository)
                .execute("text", "", callback);

        verify(callback).onError("No translate");
    }
}