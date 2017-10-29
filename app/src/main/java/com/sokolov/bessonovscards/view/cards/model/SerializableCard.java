package com.sokolov.bessonovscards.view.cards.model;

import com.sokolov.bessonovscards.entity.ICard;

public class SerializableCard implements ISerializableCard {

    private final String id;
    private final String categoryName;
    private final String text;
    private final String translate;

    public SerializableCard(ICard card) {
        id = card.id();
        categoryName = card.categoryName();
        text = card.text();
        translate = card.translate();
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public String translate() {
        return translate;
    }

    @Override
    public String categoryName() {
        return categoryName;
    }
}
