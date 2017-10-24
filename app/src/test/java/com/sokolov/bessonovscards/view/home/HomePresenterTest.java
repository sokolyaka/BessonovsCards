package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;

public class HomePresenterTest {

    private IHomePresenter homePresenter;
    @Mock
    private IHomeInteractor homeInteractor;
    @Mock
    private IHomeView homeView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        homePresenter = new HomePresenter(homeView, homeInteractor);
    }

    @Test
    public void testOnResume() {
        homePresenter.onResume();
        Mockito.verify(homeView).showSpinner();
        Mockito.verify(homeInteractor).getAllCategories(any(IGetAllCategoriesUseCase.Callback.class));
    }
}