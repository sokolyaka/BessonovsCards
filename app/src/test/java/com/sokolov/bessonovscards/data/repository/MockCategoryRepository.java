package com.sokolov.bessonovscards.data.repository;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.entity.Schedule;

import java.util.ArrayList;
import java.util.List;


public class MockCategoryRepository implements ICategoryRepository {

    private static final List<ICategory> CATEGORIES = new ArrayList<>();

    static  {
        CATEGORIES.add(new Category("UNSET", 0, Schedule.EMPTY));
        CATEGORIES.add(new Category("TODAY", 1, Schedule.TODAY));
        CATEGORIES.add(new Category("TOMORROW", 2, Schedule.TOMORROW));
        CATEGORIES.add(new Category("ONCE_PER_WEEK", 3, Schedule.ONCE_PER_WEEK));
        CATEGORIES.add(new Category("ONCE_PER_MONTH", 4, Schedule.ONCE_PER_MONTH));
        CATEGORIES.add(new Category("LEARNED", 5, Schedule.EMPTY));
    }

    @Override
    public ICategory getByName(String name) {
        for (ICategory category : CATEGORIES) {
            if (category.name().equals(name)) {
                return category;
            }
        }
        throw new NotFoundException("category name = " + name);
    }

    @Override
    public ICategory getByOrdinal(int ordinal) {
        try {
            return CATEGORIES.get(ordinal);
        } catch (IndexOutOfBoundsException e) {
            throw new NotFoundException();
        }
    }
}
