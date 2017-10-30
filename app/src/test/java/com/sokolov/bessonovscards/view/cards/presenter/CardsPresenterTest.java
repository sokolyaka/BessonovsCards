package com.sokolov.bessonovscards.view.cards.presenter;

import com.sokolov.bessonovscards.domain.cards.IGetShuffleCardsByCategory;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToNextCategoryUseCase;
import com.sokolov.bessonovscards.domain.cards.IMoveCardToPreviewsCategoryUseCase;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.view.cards.interactor.ICardsInteractor;
import com.sokolov.bessonovscards.view.cards.view.ICardsView;

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

    private ICardsPresenter cardsPresenter;
    @Mock
    private ICardsInteractor cardsInteractor;
    @Mock
    private ICardsView cardsView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cardsPresenter = new CardsPresenter(cardsView, cardsInteractor);
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
        verify(cardsView).setData(anyList());
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

        Card card = new Card("uuid", "text", "translate", "categoryName");
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

        Card card = new Card("uuid", "text", "translate", "categoryName");
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

        Card card = new Card("uuid", "text", "translate", "categoryName");
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

        Card card = new Card("uuid", "text", "translate", "categoryName");
        cardsPresenter.onMoveCardToPreviewsCategory(card);
        InOrder cardsViewOrder = Mockito.inOrder(cardsView);
        cardsViewOrder.verify(cardsView).showSpinner();
        cardsViewOrder.verify(cardsView).showError(anyString());
        cardsViewOrder.verify(cardsView).hideSpinner();
    }
}