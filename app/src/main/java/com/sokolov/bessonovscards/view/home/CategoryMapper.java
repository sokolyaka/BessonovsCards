package com.sokolov.bessonovscards.view.home;

import com.sokolov.bessonovscards.data.reposiroty.ICardRepository;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements ICategoryMapper {

    private final ICardRepository cardRepository;

    public CategoryMapper(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<ICategoryDisplayModel> toDisplayModels(List<ICategory> categories) {
        List<ICategoryDisplayModel> answer = new ArrayList<>();
        for (ICategory category : categories) {
            answer.add(toDisplayModel(category));
        }
        return answer;
    }

    @Override
    public ICategoryDisplayModel toDisplayModel(ICategory category) {
        return
                new CategoryDisplayModelFromEntity(
                        category,
                        cardRepository
                                .getAllByCategoryName(
                                        category.name())
                                .size());
    }
}
