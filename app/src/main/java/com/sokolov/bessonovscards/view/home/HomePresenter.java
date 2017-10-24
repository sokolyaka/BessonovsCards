package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;

import java.util.List;

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
                        new IGetAllCategoriesUseCase.Callback() {
                            @Override
                            public void onSuccess(List<ICategory> categories) {
                                homeView.setCategories(
                                        categoryMapper.toDisplayModels(
                                                categories));
                                homeView.hideSpinner();
                            }

                            @Override
                            public void onError(Exception e) {
                                homeView.hideSpinner();
                                homeView.showError(e.getMessage());
                            }
                        }
                );
    }
}
