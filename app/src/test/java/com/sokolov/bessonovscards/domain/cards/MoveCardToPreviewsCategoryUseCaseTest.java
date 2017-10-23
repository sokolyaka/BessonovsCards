package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.data.repository.MockCategoryRepository;
import com.sokolov.bessonovscards.entity.Card;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class MoveCardToPreviewsCategoryUseCaseTest {

    @Mock
    private IMoveCardToPreviewsCategoryUseCase.Callback callback;
    @Mock
    private ICardRepository cardRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSuccess1() {
        new MoveCardToPreviewsCategoryUseCase(cardRepository, new MockCategoryRepository())
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TOMORROW"),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TODAY"));
        verify(callback).onSuccess();

    }

    @Test
    public void testSuccess2() {
        new MoveCardToPreviewsCategoryUseCase(
                cardRepository,
                new MockCategoryRepository())
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "ONCE_PER_WEEK"),
                        callback);
        verify(cardRepository)
                .save(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "TOMORROW"));
        verify(callback).onSuccess();
    }

    @Test
    public void testOnError1() {
        new MoveCardToPreviewsCategoryUseCase(
                cardRepository,
                new MockCategoryRepository())
                .execute(
                        new Card(
                                "uuid",
                                "text",
                                "translate",
                                "UNSET"),
                        callback);
        verify(callback).onError(any(Exception.class));
    }
}