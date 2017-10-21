package com.sokolov.bessonovscards.entity;

public class Category implements ICategory {


    private final Schedule schedule;

    public Category(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String name() {
        return schedule.name();
    }

    @Override
    public Schedule schedule() {
        return schedule;
    }

    @Override
    public int ordinal() {
        return schedule.ordinal();
    }
}
