package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.repository.MockCategoryRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.Schedule;

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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void toToday() throws Exception {
        new MoveCardToNextCategoryUseCase(
                cardRepository,
                categoryRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                Schedule.TODAY.name(),
                                LocalDate.now()),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                Schedule.TOMORROW.name(),
                                LocalDate.now()));
        verify(callback).onSuccess();
    }

    @Test
    public void overLastCategory() throws Exception {
        new MoveCardToNextCategoryUseCase(
                cardRepository,
                categoryRepository)
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