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

public class MoveCardToPreviewsCategoryUseCaseTest {

    @Mock
    private IMoveCardToPreviewsCategoryUseCase.Callback callback;
    @Mock
    private ICardRepository cardRepository;
    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private MockScheduleRepository scheduleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess1() {
        new MoveCardToPreviewsCategoryUseCase(cardRepository, new MockCategoryRepository(), scheduleRepository)
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
                                "TODAY",
                                LocalDate.now()));
        verify(callback).onSuccess();

    }

    @Test
    public void testSuccess2() {
        new MoveCardToPreviewsCategoryUseCase(
                cardRepository,
                new MockCategoryRepository(),
                scheduleRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "ONCE_PER_WEEK",
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
    public void testOnError1() {
        new MoveCardToPreviewsCategoryUseCase(
                cardRepository,
                new MockCategoryRepository(), scheduleRepository)
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "UNSET",
                                LocalDate.now()),
                        callback);
        verify(callback).onError(any(Exception.class));
    }
}