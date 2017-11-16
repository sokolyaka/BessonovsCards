package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.view.home.interactor.IHomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.presenter.HomePresenter;
import com.sokolov.bessonovscards.view.home.presenter.IHomePresenter;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class HomePresenterTest {

    private IHomePresenter homePresenter;
    @Mock
    private IHomeInteractor homeInteractor;
    @Mock
    private IHomeView homeView;
    @Mock
    private ICategoryMapper categoryMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homePresenter = new HomePresenter(homeView, homeInteractor, categoryMapper);
    }

    @Test
    public void testOnResume() {
        homePresenter.onResume();
        verify(homeView).showSpinner();
        verify(homeInteractor).getCardsForToday(any());
    }


    @Test
    public void testGetCategoriesOnError() {
        doAnswer(
                invocation -> {
                    ((IGetCardsForTodayUseCase.Callback)
                            invocation.getArguments()[0])
                            .onError(
                                    new RuntimeException("Exception message"));
                    return null;
                })
                .when(homeInteractor)
                .getCardsForToday(any(IGetCardsForTodayUseCase.Callback.class));

        homePresenter.onResume();

        verify(homeView).hideSpinner();
        verify(homeView).showError(new RuntimeException("Exception message").toString());
    }

    @Test
    public void testOnAddNewCard() {
        homePresenter.onAddNewCard("new text", "new translate");

        verify(homeView).showSpinner();
        verify(homeInteractor)
                .addNewCard(
                        eq("new text"),
                        eq("new translate"),
                        any(IAddNewCardUseCase.Callback.class));
    }

    @Test
    public void testOnAddNewCardSuccess() {
        doAnswer(invocation -> {
            ((IAddNewCardUseCase.Callback) invocation.getArguments()[2]).onSuccess();
            return null;
        }).when(homeInteractor).addNewCard(anyString(), anyString(), any());

        homePresenter.onAddNewCard("new text", "new translate");

        verify(homeInteractor).getCardsForToday(any());
    }

    @Test
    public void testOnAddNewCardError() {
        doAnswer(invocation -> {
            ((IAddNewCardUseCase.Callback) invocation.getArguments()[2]).onError("no text");
            return null;
        }).when(homeInteractor).addNewCard(anyString(), anyString(), any());

        homePresenter.onAddNewCard("new text", "new translate");

        verify(homeView).hideSpinner();
        verify(homeView).showError("no text");
    }
}