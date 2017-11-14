package com.sokolov.bessonovscards.data.repository;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MockCategoryRepository implements ICategoryRepository {

    private static final List<ICategory> CATEGORIES = new ArrayList<>();

    static {
        CATEGORIES.add(new Category("UNSET", 0, "EMPTY"));
        CATEGORIES.add(new Category("TODAY", 1, "TODAY"));
        CATEGORIES.add(new Category("TOMORROW", 2, "TOMORROW"));
        CATEGORIES.add(new Category("ONCE_PER_WEEK", 3, "ONCE_PER_WEEK"));
        CATEGORIES.add(new Category("ONCE_PER_MONTH", 4, "ONCE_PER_MONTH"));
        CATEGORIES.add(new Category("LEARNED", 5, "EMPTY"));
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

    @Override
    public List<ICategory> getAll() {
        ArrayList<ICategory> answer = new ArrayList<>();
        Collections.copy(answer, CATEGORIES);
        return answer;
    }
}
