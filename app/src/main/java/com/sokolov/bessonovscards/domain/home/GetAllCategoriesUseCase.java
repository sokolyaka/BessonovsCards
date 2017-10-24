package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.data.reposiroty.ICategoryRepository;

public class GetAllCategoriesUseCase implements IGetAllCategoriesUseCase {
    private final ICategoryRepository categoryRepository;

    public GetAllCategoriesUseCase(ICategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    @Override
    public void execute(Callback callback) {
        try {
            callback.onSuccess(
                    categoryRepository.getAll());
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}
