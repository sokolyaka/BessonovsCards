package com.sokolov.bessonovscards.entity;

import org.joda.time.LocalDate;

public interface ICard {

    String id();

    String text();

    String translate();

    String categoryName();

    LocalDate date();
}
