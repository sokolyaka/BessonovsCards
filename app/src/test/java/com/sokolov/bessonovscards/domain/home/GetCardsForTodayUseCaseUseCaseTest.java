package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ICard;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetCardsForTodayUseCaseUseCaseTest {

    @Mock
    private IGetCardsForTodayUseCase.Callback callback;
    @Mock
    private ICardRepository cardRepository;

    private List<ICard> CORRECT_ANSWER =
            Arrays.asList(
                    new Card(
                            "uuid1",
                            "text1",
                            "translate1",
                            "category1",
                            LocalDate.now()),
                    new Card(
                            "uuid2",
                            "text2",
                            "translate2",
                            "category2",
                            LocalDate.now().minusDays(2)));

    private List<ICard> REPOSITORY_ANSWER =
            Arrays.asList(
                    new Card(
                            "uuid0",
                            "text0",
                            "translate0",
                            "emptyCategory",
                            LocalDate.now()),
                    new Card(
                            "uuid1",
                            "text1",
                            "translate1",
                            "category1",
                            LocalDate.now()),
                    new Card(
                            "uuid2",
                            "text2",
                            "translate2",
                            "category2",
                            LocalDate.now().minusDays(2)),
                    new Card(
                            "uuid3",
                            "text3",
                            "translate3",
                            "category3",
                            LocalDate.now().plusDays(1)));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        when(cardRepository.getAll()).thenReturn(REPOSITORY_ANSWER);

        new GetCardsForTodayUseCase(
                cardRepository,
                new HashSet<>(
                        Arrays.asList(
                                "emptyCategory")))
                .execute(callback);

        verify(callback).onSuccess(CORRECT_ANSWER);
    }

}