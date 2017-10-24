package com.sokolov.bessonovscards.view.home.mapper;

import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.view.home.ICategoryDisplayModel;

import java.util.Collections;
import java.util.List;

public class SortedByOrderCategoryMapper implements ICategoryMapper {
    private final ICategoryMapper origin;

    public SortedByOrderCategoryMapper(ICategoryMapper origin) {

        this.origin = origin;
    }

    @Override
    public List<ICategoryDisplayModel> toDisplayModels(List<ICategory> categories) {
        List<ICategoryDisplayModel> models = origin.toDisplayModels(categories);
        Collections.sort(models, (o1, o2) -> Integer.valueOf(o1.ordinal()).compareTo(o2.ordinal()));
        return models;
    }

    @Override
    public ICategoryDisplayModel toDisplayModel(ICategory category) {
        return origin.toDisplayModel(category);
    }
}
