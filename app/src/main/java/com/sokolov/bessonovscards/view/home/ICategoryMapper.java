package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.entity.ICategory;

import java.util.List;

public interface ICategoryMapper {
    List<ICategoryDisplayModel> toDisplayModels(List<ICategory> categories);
}
