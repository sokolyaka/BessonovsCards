package com.sokolov.bessonovscards.view.home.presenter.callback;

import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import java.util.List;

public class WithSuccessMessageGetAllCategoriesCallback implements IGetAllCategoriesUseCase.Callback {
    private final IGetAllCategoriesUseCase.Callback origin;
    private final IHomeView homeView;

    public WithSuccessMessageGetAllCategoriesCallback(IGetAllCategoriesUseCase.Callback origin, IHomeView homeView) {
        this.origin = origin;
        this.homeView = homeView;
    }

    @Override
    public void onSuccess(List<ICategory> categories) {
        origin.onSuccess(categories);
        homeView.showSuccessMessage();
    }

    @Override
    public void onError(Exception e) {
        origin.onError(e);
    }
}