package com.sokolov.bessonovscards.entity;

public class Card implements ICard {
    private final String uuid;
    private final String text;
    private final String translate;
    private final String categoryId;

    public Card(String uuid, String text, String translate, String categoryId) {
        this.uuid = uuid;
        this.text = text;
        this.translate = translate;
        this.categoryId = categoryId;
    }

    @Override
    public String id() {
        return uuid;
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
    public String categoryId() {
        return categoryId;
    }
}
