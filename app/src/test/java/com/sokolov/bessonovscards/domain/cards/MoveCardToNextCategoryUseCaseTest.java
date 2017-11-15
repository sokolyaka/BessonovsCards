package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.repository.MockCategoryRepository;
import com.sokolov.bessonovscards.data.repository.MockScheduleRepository;
import com.sokolov.bessonovscards.entity.Card;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class MoveCardToNextCategoryUseCaseTest {

    @Mock
    private IMoveCardToNextCategoryUseCase.Callback callback;
    @Mock
    private ICardRepository cardRepository;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MockCategoryRepository categoryRepository;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MockScheduleRepository scheduleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toTomorrow() throws Exception {
        new MoveCardToNextCategoryUseCase(
                cardRepository,
                categoryRepository,
                scheduleRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TODAY",
                                new LocalDate(1987, 6, 29)),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TOMORROW",
                                LocalDate.now().plusDays(1)));
        verify(callback).onSuccess();
    }

    @Test
    public void toOncePerWeek() throws Exception {
        new MoveCardToNextCategoryUseCase(
                cardRepository,
                categoryRepository,
                scheduleRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TOMORROW",
                                new LocalDate(1987, 6, 29)),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "ONCE_PER_WEEK",
                                LocalDate.now().plusDays(7)));
        verify(callback).onSuccess();
    }

    @Test
    public void overLastCategory() throws Exception {
        new MoveCardToNextCategoryUseCase(
                cardRepository,
                categoryRepository,
                scheduleRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "LEARNED",
                                LocalDate.now()),
                        callback);
        verify(callback).onError(any(Exception.class));
    }


}