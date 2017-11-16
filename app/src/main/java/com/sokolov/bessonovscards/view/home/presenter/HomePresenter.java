package com.sokolov.bessonovscards.view.home.presenter;

import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.view.home.interactor.IHomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.presenter.callback.GetAllCategoriesCallback;
import com.sokolov.bessonovscards.view.home.presenter.callback.WithSuccessMessageGetAllCategoriesCallback;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

public class HomePresenter implements IHomePresenter {

    private final IHomeView homeView;
    private final IHomeInteractor homeInteractor;
    private final ICategoryMapper categoryMapper;

    public HomePresenter(IHomeView homeView, IHomeInteractor homeInteractor, ICategoryMapper categoryMapper) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void onResume() {
        homeView.showSpinner();
        homeInteractor
                .getAllCategories(
                        new GetAllCategoriesCallback(
                                homeView,
                                categoryMapper));
    }

    @Override
    public void onAddNewCard(String text, String translate) {
        homeView.showSpinner();
        homeInteractor
                .addNewCard(
                        text,
                        translate,
                        new IAddNewCardUseCase.Callback() {
                            @Override
                            public void onSuccess() {
                                homeInteractor
                                        .getAllCategories(
                                                new WithSuccessMessageGetAllCategoriesCallback(
                                                        new GetAllCategoriesCallback(
                                                                homeView,
                                                                categoryMapper),
                                                        homeView));
                            }

                            @Override
                            public void onError(String message) {
                                homeView.hideSpinner();
                                homeView.showError(message);
                            }
                        });
    }
}
