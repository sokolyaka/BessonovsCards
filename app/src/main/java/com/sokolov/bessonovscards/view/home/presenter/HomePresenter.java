package com.sokolov.bessonovscards.view.home.presenter;

import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.entity.ICard;
import com.sokolov.bessonovscards.view.home.interactor.IHomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.model.TodayCategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.presenter.callback.GetAllCategoriesCallback;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import java.util.List;

import javax.inject.Inject;

public class HomePresenter implements IHomePresenter {

    private final IHomeView homeView;
    private final IHomeInteractor homeInteractor;
    private final ICategoryMapper categoryMapper;

    @Inject
    public HomePresenter(IHomeView homeView, IHomeInteractor homeInteractor, ICategoryMapper categoryMapper) {
        this.homeView = homeView;
        this.homeInteractor = homeInteractor;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void onResume() {
        homeView.showSpinner();
        homeInteractor
                .getCardsForToday(
                        new IGetCardsForTodayUseCase.Callback() {
                            @Override
                            public void onSuccess(List<ICard> cards) {
                                homeInteractor.getAllCategories(
                                        new GetAllCategoriesCallback(
                                                homeView,
                                                categoryMapper,
                                                new TodayCategoryDisplayModel(cards.size())));
                            }

                            @Override
                            public void onError(Exception e) {
                                homeView.hideSpinner();
                                homeView.showError(e.toString());
                            }
                        });
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
                                homeView.hideSpinner();
                                homeView.showSuccessMessage();
                                onResume();
                            }

                            @Override
                            public void onError(String message) {
                                homeView.hideSpinner();
                                homeView.showError(message);
                            }
                        });
    }
}
