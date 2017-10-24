package com.sokolov.bessonovscards.data.reposiroty;

import com.sokolov.bessonovscards.data.exceptions.NotFoundException;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.List;

public interface ICategoryRepository {
    ICategory getByName(String name) throws NotFoundException;

    ICategory getByOrdinal(int ordinal) throws NotFoundException;

    List<ICategory> getAll();
}
