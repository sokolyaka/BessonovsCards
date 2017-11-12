package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.Card;
import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.Schedule;
import com.sokolov.bessonovscards.view.home.mapper.CategoryMapper;
import com.sokolov.bessonovscards.view.home.model.CategoryDisplayModelFromEntity;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryMapperTest {
    @Mock
    private ICardRepository cardRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testToDisplayModel() {
        when(cardRepository.getAllByCategoryName("NAME"))
                .thenReturn(
                        asList(
                                new Card("uuid1", "text2", "translate3", "NAME", LocalDate.now()),
                                new Card("uuid2", "text2", "translate3", "NAME", LocalDate.now())));

        assertEquals(
                new CategoryDisplayModelFromEntity(
                        new Category("NAME", 0, Schedule.TODAY),
                        2),
                new CategoryMapper(cardRepository)
                        .toDisplayModel(
                                new Category("NAME", 0, Schedule.TODAY)));
    }

}