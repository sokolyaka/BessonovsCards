package com.sokolov.bessonovscards.view.home.interactor;

import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;

public class HomeInteractor implements IHomeInteractor {
    private final IAddNewCardUseCase addNewCardUseCase;
    private final IGetAllCategoriesUseCase getAllCategoriesUseCase;
    private final IGetCardsForTodayUseCase getCardsForTodayUseCase;

    public HomeInteractor(IAddNewCardUseCase addNewCardUseCase, IGetAllCategoriesUseCase getAllCategoriesUseCase, IGetCardsForTodayUseCase getCardsForTodayUseCase) {
        this.addNewCardUseCase = addNewCardUseCase;
        this.getAllCategoriesUseCase = getAllCategoriesUseCase;
        this.getCardsForTodayUseCase = getCardsForTodayUseCase;
    }

    @Override
    public void getAllCategories(IGetAllCategoriesUseCase.Callback callback) {
        getAllCategoriesUseCase.execute(callback);
    }

    @Override
    public void addNewCard(String text, String translate, IAddNewCardUseCase.Callback callback) {
        addNewCardUseCase.execute(text, translate, callback);
    }

    @Override
    public void getCardsForToday(IGetCardsForTodayUseCase.Callback callback) {
        getCardsForTodayUseCase.execute(callback);
    }
}
