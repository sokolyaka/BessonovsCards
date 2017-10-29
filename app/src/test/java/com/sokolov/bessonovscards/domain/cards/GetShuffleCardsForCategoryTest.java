package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetShuffleCardsForCategoryTest {

    @Mock
    private IGetShuffleCardsByCategory.Callback callback;
    @Mock
    private ICardRepository cardRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnSuccess() {
        List<ICard> dbAnswer = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dbAnswer.add(new Card("uuid" + i, "text" + i, "translate" + i, "categoryName"));
        }

        when(cardRepository.getAllByCategoryName("categoryName"))
                .thenReturn(dbAnswer);

        new GetShuffleCardsByCategory(cardRepository)
                .execute("categoryName", callback);

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(callback).onSuccess(captor.capture());

        List<ICard> useCAseAnswer = captor.getValue();
        List<ICard> originalDbAnswer = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            originalDbAnswer.add(new Card("uuid" + i, "text" + i, "translate" + i, "categoryName"));
        }

        int coincidence = 0;
        for (int i = 0; i < 50; i++) {
            if (useCAseAnswer.get(i).equals(originalDbAnswer.get(i))) {
                coincidence++;
            }
        }
        Assert.assertTrue(coincidence < 25);
    }

    @Test
    public void testOnError() {
        when(cardRepository.getAllByCategoryName("categoryName"))
                .thenThrow(NotFoundException.class);

        new GetShuffleCardsByCategory(cardRepository)
                .execute("categoryName", callback);
        verify(callback).onError(any(NotFoundException.class));
    }
}