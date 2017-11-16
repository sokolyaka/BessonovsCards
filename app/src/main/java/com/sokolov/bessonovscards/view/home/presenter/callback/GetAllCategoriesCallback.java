package com.sokolov.bessonovscards.view.home.presenter.callback;

import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import java.util.List;


public class GetAllCategoriesCallback implements IGetAllCategoriesUseCase.Callback {
    private final IHomeView homeView;
    private final ICategoryMapper categoryMapper;
    private final ICategoryDisplayModel todayCategory;

    public GetAllCategoriesCallback(IHomeView homeView, ICategoryMapper categoryMapper, ICategoryDisplayModel todayCategory) {
        this.homeView = homeView;
        this.categoryMapper = categoryMapper;
        this.todayCategory = todayCategory;
    }

    @Override
    public void onSuccess(List<ICategory> categories) {
        List<ICategoryDisplayModel> displayModels =
                categoryMapper.toDisplayModels(
                        categories);
        displayModels.add(0, todayCategory);

        homeView.setCategories(displayModels);
        homeView.hideSpinner();
    }

    @Override
    public void onError(Exception e) {
        homeView.hideSpinner();
        homeView.showError(e.getMessage());
    }

}
