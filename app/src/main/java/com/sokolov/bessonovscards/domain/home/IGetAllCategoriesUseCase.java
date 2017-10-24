package com.sokolov.bessonovscards.domain.home;

import com.sokolov.bessonovscards.entity.ICategory;

import java.util.List;

public interface IGetAllCategoriesUseCase {
    void execute(Callback callback);

    interface Callback{
        void onSuccess(List<ICategory> categories);

        void onError(Exception e);
    }
}
