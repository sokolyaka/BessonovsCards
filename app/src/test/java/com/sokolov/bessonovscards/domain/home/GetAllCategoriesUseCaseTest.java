package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetAllCategoriesUseCaseTest {

    private GetAllCategoriesUseCase getAllCategories;
    @Mock
    private IGetAllCategoriesUseCase.Callback callback;
    @Mock
    private ICategoryRepository categoryRepository;
    private List expectedCategories;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAllCategories = new GetAllCategoriesUseCase(categoryRepository);
        expectedCategories =
                Arrays.asList(
                        new Category("TODAY", 0, "ScheduleTODAY"),
                        new Category("TOMORROW", 1, "ScheduleTOMORROW"));
    }

    @Test
    public void testOnSuccess() {
        when(categoryRepository.getAll()).thenReturn(expectedCategories);

        getAllCategories.execute(callback);

        verify(categoryRepository).getAll();
        verify(callback).onSuccess(expectedCategories);
    }

    @Test
    public void testOnError() {
        when(categoryRepository.getAll()).thenThrow(new RuntimeException());

        getAllCategories.execute(callback);

        verify(callback).onError(any(Exception.class));
    }
}