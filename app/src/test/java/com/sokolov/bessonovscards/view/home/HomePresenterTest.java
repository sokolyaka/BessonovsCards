package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.sokolov.bessonovscards.view.TestData.CATEGORIES;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
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
        verify(homeInteractor).getAllCategories(any(IGetAllCategoriesUseCase.Callback.class));
    }

    @Test
    public void testGetCategoriesOnSuccess() {
        doAnswer(
                invocation -> {
                    ((IGetAllCategoriesUseCase.Callback) invocation.getArguments()[0]).onSuccess(CATEGORIES);
                    return null;
                })
                .when(homeInteractor)
                .getAllCategories(any(IGetAllCategoriesUseCase.Callback.class));

        homePresenter.onResume();

        verify(categoryMapper).toDisplayModels(CATEGORIES);
        verify(homeView).setCategories(anyList());
        verify(homeView).hideSpinner();
    }

    @Test
    public void testGetCategoriesOnError() {
        doAnswer(
                invocation -> {
                    ((IGetAllCategoriesUseCase.Callback)
                            invocation.getArguments()[0])
                            .onError(
                                    new RuntimeException("Exception message"));
                    return null;
                })
                .when(homeInteractor)
                .getAllCategories(any(IGetAllCategoriesUseCase.Callback.class));

        homePresenter.onResume();

        verify(homeView).hideSpinner();
        verify(homeView).showError("Exception message");
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

        verify(homeView).hideSpinner();
        verify(homeView).showSuccessMessage();
    }
}