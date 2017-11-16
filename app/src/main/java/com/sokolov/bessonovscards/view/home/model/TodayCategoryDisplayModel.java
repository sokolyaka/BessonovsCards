package com.sokolov.bessonovscards.view.home.model;

public class TodayCategoryDisplayModel implements ICategoryDisplayModel {

    private final int cardsCount;

    public TodayCategoryDisplayModel(int cardsCount) {

        this.cardsCount = cardsCount;
    }

    @Override
    public String name() {
        return "Today";
    }

    @Override
    public String scheduleUuid() {
        return "todayCategory";
    }

    @Override
    public int ordinal() {
        return 0;
    }

    @Override
    public int cardsCount() {
        return cardsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodayCategoryDisplayModel that = (TodayCategoryDisplayModel) o;

        return cardsCount == that.cardsCount;

    }

    @Override
    public int hashCode() {
        return cardsCount;
    }
}
