package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.Schedule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static java.util.Arrays.asList;

public class SortedByOrderCategoryMapperTest {

    @Mock
    private ICategoryMapper origin;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(
                origin.toDisplayModels(null))
                .thenReturn(
                        asList(
                                new CategoryDisplayModelFromEntity(new Category("TOMORROW", 2, Schedule.TOMORROW), 2),
                                new CategoryDisplayModelFromEntity(new Category("TODAY", 1, Schedule.TODAY), 1),
                                new CategoryDisplayModelFromEntity(new Category("UNSET", 0, Schedule.EMPTY), 0)));
    }

    @Test
    public void testToDisplayModels() {
        Assert.assertEquals(
                asList(new CategoryDisplayModelFromEntity(new Category("UNSET", 0, Schedule.EMPTY), 0),
                        new CategoryDisplayModelFromEntity(new Category("TODAY", 1, Schedule.TODAY), 1),
                        new CategoryDisplayModelFromEntity(new Category("TOMORROW", 2, Schedule.TOMORROW), 2)),
                new SortedByOrderCategoryMapper(origin)
                        .toDisplayModels(null));
    }

}