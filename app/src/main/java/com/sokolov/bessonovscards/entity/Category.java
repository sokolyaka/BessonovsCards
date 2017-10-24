package com.sokolov.bessonovscards.entity;

public class Category implements ICategory {


    private final String name;
    private final int ordinal;
    private final Schedule schedule;

    public Category(String name, int ordinal, Schedule schedule) {
        this.name = name;
        this.ordinal = ordinal;
        this.schedule = schedule;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Schedule schedule() {
        return schedule;
    }

    @Override
    public int ordinal() {
        return ordinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (ordinal != category.ordinal) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return schedule == category.schedule;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ordinal;
        result = 31 * result + (schedule != null ? schedule.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", ordinal=" + ordinal +
                ", schedule=" + schedule +
                '}';
    }
}
