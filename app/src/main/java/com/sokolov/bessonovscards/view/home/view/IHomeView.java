package com.sokolov.bessonovscards.view.home.view;

import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;

import java.util.List;

public interface IHomeView {
    void showSpinner();

    void hideSpinner();

    void setCategories(List<ICategoryDisplayModel> categories);

    void showError(String errorMessage);

    void showSuccessMessage();
}
