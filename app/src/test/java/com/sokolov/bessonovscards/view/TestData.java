package com.sokolov.bessonovscards.view;

import com.sokolov.bessonovscards.entity.Category;
import com.sokolov.bessonovscards.entity.ICategory;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    private TestData() {
    }

    public static final List<ICategory> CATEGORIES = new ArrayList<>();

    static {
        CATEGORIES.add(new Category("UNSET", 0, "uuidEmptySchedule"));
        CATEGORIES.add(new Category("TODAY", 1, "uuidTodaySchedule"));
        CATEGORIES.add(new Category("TOMORROW", 2, "uuidTomorrowSchedule"));
        CATEGORIES.add(new Category("ONCE_PER_WEEK", 3, "uuidOncePerWeekSchedule"));
        CATEGORIES.add(new Category("ONCE_PER_MONTH", 4, "uuidOncePerMonthSchedule"));
        CATEGORIES.add(new Category("LEARNED", 5, "uuidEmptySchedule"));
    }
}
