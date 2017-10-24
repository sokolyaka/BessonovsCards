package com.sokolov.bessonovscards.view;

import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;
import com.sokolov.bessonovscards.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    private TestData() {
    }

    public static final List<ICategory> CATEGORIES = new ArrayList<>();

    static {
        CATEGORIES.add(new Category("UNSET", 0, Schedule.EMPTY));
        CATEGORIES.add(new Category("TODAY", 1, Schedule.TODAY));
        CATEGORIES.add(new Category("TOMORROW", 2, Schedule.TOMORROW));
        CATEGORIES.add(new Category("ONCE_PER_WEEK", 3, Schedule.ONCE_PER_WEEK));
        CATEGORIES.add(new Category("ONCE_PER_MONTH", 4, Schedule.ONCE_PER_MONTH));
        CATEGORIES.add(new Category("LEARNED", 5, Schedule.EMPTY));
    }
}
