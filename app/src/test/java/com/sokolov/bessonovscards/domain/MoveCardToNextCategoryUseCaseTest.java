package com.sokolov.bessonovscards.domain;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.repository.MockCategoryRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.Schedule;

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
                                Schedule.EMPTY.name()),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                Schedule.TODAY.name()));
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
                                Schedule.values()[Schedule.values().length - 1].name()),
                        callback);
        verify(callback).onError(any(Exception.class));
    }


}