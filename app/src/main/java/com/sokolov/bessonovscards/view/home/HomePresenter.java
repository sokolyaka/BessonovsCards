package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.List;

public class HomePresenter implements IHomePresenter {

    private final IHomeView homeView;
    private final IHomeInteractor homeInteractor;

    public HomePresenter(IHomeView homeView, IHomeInteractor homeInteractor) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
    }

    @Override
    public void onResume() {
        homeView.showSpinner();
        homeInteractor
                .getAllCategories(
                        new IGetAllCategoriesUseCase.Callback() {
                            @Override
                            public void onSuccess(List<ICategory> categories) {

                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        }
                );
    }
}
