package com.sokolov.bessonovscards.view.home.model;

import com.sokolov.bessonovscards.entity.ICategory;

public class CategoryDisplayModelFromEntity implements ICategoryDisplayModel {
    private final ICategory entity;
    private final int cardsCount;

    public CategoryDisplayModelFromEntity(ICategory entity, int cardsCount) {
        this.entity = entity;
        this.cardsCount = cardsCount;
    }

    @Override
    public String name() {
        return entity.name();
    }

    @Override
    public String scheduleUuid() {
        return entity.scheduleUuid();
    }

    @Override
    public int ordinal() {
        return entity.ordinal();
    }

    @Override
    public int cardsCount() {
        return cardsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryDisplayModelFromEntity that = (CategoryDisplayModelFromEntity) o;

        if (cardsCount != that.cardsCount) return false;
        return entity != null ? entity.equals(that.entity) : that.entity == null;

    }

    @Override
    public int hashCode() {
        int result = entity != null ? entity.hashCode() : 0;
        result = 31 * result + cardsCount;
        return result;
    }

    @Override
    public String toString() {
        return "CategoryDisplayModelFromEntity{" +
                "cardsCount=" + cardsCount +
                ", entity=" + entity +
                '}';
    }
}
