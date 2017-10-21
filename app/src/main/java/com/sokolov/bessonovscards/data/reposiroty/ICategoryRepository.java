package com.sokolov.bessonovscards.data.reposiroty;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.entity.ICategory;

public interface ICategoryRepository {
    ICategory getByName(String name) throws NotFoundException;

    ICategory getByOrdinal(int ordinal) throws NotFoundException;
}
