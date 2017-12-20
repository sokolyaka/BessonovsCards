package com.sokolov.bessonovscards.di;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.view.home.mapper.CategoryMapper;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.mapper.SortedByOrderCategoryMapper;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import java.util.Set;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private final String categoryName;
    private final Set<String> categoryNamesToIgnore;
    private final IHomeView homeView;

    public HomeModule(String categoryName, Set<String> categoryNamesToIgnore, IHomeView homeView) {
        this.categoryName = categoryName;
        this.categoryNamesToIgnore = categoryNamesToIgnore;
        this.homeView = homeView;
    }

    @Provides
    public String getCategoryName() {
        return categoryName;
    }

    @Provides
    public Set<String> getCategoryNamesToIgnore() {
        return categoryNamesToIgnore;
    }

    @Provides
    public IHomeView getHomeView() {
        return homeView;
    }

    @Provides
    public ICategoryMapper getCategoryMapper(ICardRepository cardRepository) {
        return
                new SortedByOrderCategoryMapper(
                        new CategoryMapper(cardRepository));
    }
}
