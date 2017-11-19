package com.sokolov.bessonovscards.view.home.presenter.callback;

import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.view.home.interactor.IHomeInteractor;
import com.sokolov.bessonovscards.view.home.mapper.ICategoryMapper;
import com.sokolov.bessonovscards.view.home.model.CategoryDisplayModelFromEntity;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.model.TodayCategoryDisplayModel;
import com.sokolov.bessonovscards.view.home.view.IHomeView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllCategoriesCallbackTest {

    @Mock
    private IHomeView homeView;
    @Mock
    private ICategoryMapper categoryMapper;
    @Mock
    private IHomeInteractor homeInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnSuccess() {
        List<ICategoryDisplayModel> displayModels =
                new ArrayList<>(
                        asList(new CategoryDisplayModelFromEntity(new Category("UNSET", 0, "uuidEmptySchedule"), 0),
                                new CategoryDisplayModelFromEntity(new Category("TODAY", 1, "uuidTodaySchedule"), 1),
                                new CategoryDisplayModelFromEntity(new Category("TOMORROW", 2, "uuidTomorrowSchedule"), 2)));


        when(categoryMapper.toDisplayModels(Collections.EMPTY_LIST))
                .thenReturn(
                        displayModels);

        new GetAllCategoriesCallback(
                homeView,
                categoryMapper,
                new TodayCategoryDisplayModel(50))
                .onSuccess(Collections.EMPTY_LIST);

        ArgumentCaptor<List> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(homeView).setCategories(listArgumentCaptor.capture());
        verify(homeView).hideSpinner();

        Assert.assertEquals(new TodayCategoryDisplayModel(50), listArgumentCaptor.getValue().get(0));
    }

}