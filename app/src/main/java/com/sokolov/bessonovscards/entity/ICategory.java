package com.sokolov.bessonovscards.entity;

import java.util.List;

public interface ICategory {
    String uuid();

    String name();

    Schedule schedule();

    List<String> cards();
}
