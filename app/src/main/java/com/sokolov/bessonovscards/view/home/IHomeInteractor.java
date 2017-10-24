package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;

public interface IHomeInteractor {
    void getAllCategories(IGetAllCategoriesUseCase.Callback callback);

    void addNewCard(String text, String translate, IAddNewCardUseCase.Callback callback);
}
