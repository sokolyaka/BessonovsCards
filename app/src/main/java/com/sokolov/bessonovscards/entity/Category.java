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
}
