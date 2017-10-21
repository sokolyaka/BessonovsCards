package com.sokolov.bessonovscards.data.repository;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;
import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.entity.Schedule;


public class MockCategoryRepository implements ICategoryRepository {
    @Override
    public ICategory getByName(String name) {
        return new Category(Schedule.valueOf(name));
    }

    @Override
    public ICategory getByOrdinal(int ordinal) {
        try {
            return new Category(Schedule.values()[ordinal]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NotFoundException();
        }
    }
}
