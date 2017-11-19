package com.sokolov.bessonovscards.view.todayCards.presenter;

import com.sokolov.bessonovscards.domain.cards.IEditCardUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IPronounceTextUseCase;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.ITextMode;
import com.sokolov.bessonovscards.view.todayCards.interactor.ITodayCardsInteractor;
import com.sokolov.bessonovscards.view.todayCards.view.ICardsView;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class CardsPresenterTest {

    private ITodayCardsPresenter cardsPresenter;
    @Mock
    private ITodayCardsInteractor cardsInteractor;
    @Mock
    private ICardsView cardsView;
    @Mock
    private ITextMode textMode;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cardsPresenter = new TodayCardsPresenter(cardsView, cardsInteractor, textMode);
    }

    @Test
    public void testOnCreateSuccess() {
        Mockito.doAnswer(invocation -> {
            ((IGetShuffleCardsByCategory.Callback) invocation.getArguments()[0]).onSuccess(Collections.EMPTY_LIST);
            return null;
        }).when(cardsInteractor).getShuffleCardsByCategory(any());
        cardsPresenter.onCreate();
        verify(cardsInteractor)
                .getShuffleCardsByCategory(
                        any(IGetShuffleCardsByCategory.Callback.class));
        verify(cardsView).refreshData(anyList(), eq(textMode));
    }

    @Test
    public void testOnCreateError() {
        Mockito.doAnswer(invocation -> {
            ((IGetShuffleCardsByCategory.Callback) invocation.getArguments()[0]).onError(new Exception());
            return null;
        }).when(cardsInteractor).getShuffleCardsByCategory(any());
        cardsPresenter.onCreate();
        verify(cardsView).showError(anyString());
    }

    @Test
    public void testMoveCardToNextCategorySuccess() {
        doAnswer(invocation -> {
            ((IMoveCardToNextCategoryUseCase.Callback) invocation.getArguments()[1]).onSuccess();
            return null;
        }).when(cardsInteractor).onMoveCardToNextCategory(any(), any());

        Card card = new Card("uuid", "text", "translate", "categoryName", LocalDate.now());
        cardsPresenter.onMoveCardToNextCategory(card);
        verify(cardsInteractor)
                .onMoveCardToNextCategory(
                        eq(card),
                        any(IMoveCardToNextCategoryUseCase.Callback.class));
        InOrder cardsViewOrder = Mockito.inOrder(cardsView);
        cardsViewOrder.verify(cardsView).showSpinner();
        cardsViewOrder.verify(cardsView).showMoveCardSuccessMessage();
        cardsViewOrder.verify(cardsView).hideSpinner();
    }

    @Test
    public void testMoveCardToNextCategoryError() {
        doAnswer(invocation -> {
            ((IMoveCardToNextCategoryUseCase.Callback) invocation.getArguments()[1]).onError(new Exception());
            return null;
        }).when(cardsInteractor).onMoveCardToNextCategory(any(), any());

        Card card = new Card("uuid", "text", "translate", "categoryName", LocalDate.now());
        cardsPresenter.onMoveCardToNextCategory(card);
        InOrder cardsViewOrder = Mockito.inOrder(cardsView);
        cardsViewOrder.verify(cardsView).showSpinner();
        cardsViewOrder.verify(cardsView).showError(anyString());
        cardsViewOrder.verify(cardsView).hideSpinner();
    }

    @Test
    public void testMoveCardToPreviewsCategorySuccess() {
        doAnswer(invocation -> {
            ((IMoveCardToPreviewsCategoryUseCase.Callback) invocation.getArguments()[1]).onSuccess();
            return null;
        }).when(cardsInteractor).onMoveCardToPreviewsCategory(any(), any());

        Card card = new Card("uuid", "text", "translate", "categoryName", LocalDate.now());
        cardsPresenter.onMoveCardToPreviewsCategory(card);
        verify(cardsInteractor)
                .onMoveCardToPreviewsCategory(
                        eq(card),
                        any(IMoveCardToPreviewsCategoryUseCase.Callback.class));
        InOrder cardsViewOrder = Mockito.inOrder(cardsView);
        cardsViewOrder.verify(cardsView).showSpinner();
        cardsViewOrder.verify(cardsView).showMoveCardSuccessMessage();
        cardsViewOrder.verify(cardsView).hideSpinner();
    }

    @Test
    public void testMoveCardToPreviewsCategoryError() {
        doAnswer(invocation -> {
            ((IMoveCardToPreviewsCategoryUseCase.Callback) invocation.getArguments()[1]).onError(new Exception());
            return null;
        }).when(cardsInteractor).onMoveCardToPreviewsCategory(any(), any());

        Card card = new Card("uuid", "text", "translate", "categoryName", LocalDate.now());
        cardsPresenter.onMoveCardToPreviewsCategory(card);
        InOrder cardsViewOrder = Mockito.inOrder(cardsView);
        cardsViewOrder.verify(cardsView).showSpinner();
        cardsViewOrder.verify(cardsView).showError(anyString());
        cardsViewOrder.verify(cardsView).hideSpinner();
    }

    @Test
    public void testOnEditCardSuccess() {
        Card card = new Card("uuid", "text", "translate", "categoryName", LocalDate.now());
        doAnswer(invocation -> {
            invocation.getArgumentAt(1, IEditCardUseCase.Callback.class).onSuccess(card);
            return null;
        }).when(cardsInteractor).onEditCard(any(), any());

        cardsPresenter.onEditCard(card);
        verify(cardsView).showSpinner();
        verify(cardsInteractor).onEditCard(eq(card), any());
        verify(cardsView).updateCard(eq(card));
    }

    @Test
    public void testOnPronounce() {
        String text = "text";
        doAnswer(invocation -> {
            invocation.getArgumentAt(1, IPronounceTextUseCase.Callback.class).onSuccess();
            return null;
        }).when(cardsInteractor).onPronounce(eq(text), any());

        cardsPresenter.onPronounce(text);

        verify(cardsView).showSpinner();
        verify(cardsInteractor).onPronounce(eq(text), any());
        verify(cardsView).hideSpinner();
    }
}