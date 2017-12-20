package com.sokolov.bessonovscards.di;

import com.sokolov.bessonovscards.view.home.presenter.HomePresenter;

import dagger.Component;

@Component(modules = {ContextModule.class, HomeModule.class, BindHomeModule.class, BindRepositoryModule.class})
public interface HomeComponent {

    HomePresenter getHomePresenter();
}
