package com.sokolov.bessonovscards.view.home.interactor;

import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;

public class HomeInteractor implements IHomeInteractor {
    private final IAddNewCardUseCase addNewCardUseCase;
    private final IGetAllCategoriesUseCase getAllCategoriesUseCase;

    public HomeInteractor(IAddNewCardUseCase addNewCardUseCase, IGetAllCategoriesUseCase getAllCategoriesUseCase) {
        this.addNewCardUseCase = addNewCardUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
    }

    @Override
    public void getAllCategories(IGetAllCategoriesUseCase.Callback callback) {
        getAllCategoriesUseCase.execute(callback);
    }

    @Override
    public void addNewCard(String text, String translate, IAddNewCardUseCase.Callback callback) {
        addNewCardUseCase.execute(text, translate, callback);
    }
}
