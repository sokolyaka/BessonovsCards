package com.sokolov.bessonovscards.di;

import com.sokolov.bessonovscards.domain.cards.GetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.cards.IGetCardsForTodayUseCase;
import com.sokolov.bessonovscards.domain.home.AddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.GetAllCategoriesUseCase;
import com.sokolov.bessonovscards.domain.home.IAddNewCardUseCase;
import com.sokolov.bessonovscards.domain.home.IGetAllCategoriesUseCase;
import com.sokolov.bessonovscards.view.home.interactor.HomeInteractor;
import com.sokolov.bessonovscards.view.home.interactor.IHomeInteractor;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BindHomeModule {

    @Binds
    public abstract IHomeInteractor bindHomeInteractor(HomeInteractor homeInteractor);

    @Binds
    public abstract IAddNewCardUseCase bindAddNewCardUseCase(AddNewCardUseCase useCase);

    @Binds
    public abstract IGetAllCategoriesUseCase bindGetAllCategoriesUseCase(GetAllCategoriesUseCase useCase);

    @Binds
    public abstract IGetCardsForTodayUseCase bindGetCardsForTodayUseCase(GetCardsForTodayUseCase useCase);
}
