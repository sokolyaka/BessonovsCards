package com.sokolov.bessonovscards.view.home.mapper;

import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.view.home.model.ICategoryDisplayModel;

import java.util.List;

public interface ICategoryMapper {
    List<ICategoryDisplayModel> toDisplayModels(List<ICategory> categories);

    ICategoryDisplayModel toDisplayModel(ICategory category);
}
