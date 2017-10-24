package com.sokolov.bessonovscards.view.home;

import java.util.List;

public interface IHomeView {
    void showSpinner();

    void hideSpinner();

    void setCategories(List<ICategoryDisplayModel> categories);

    void showError(String errorMessage);
}
