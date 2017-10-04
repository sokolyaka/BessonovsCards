package com.sokolov.bessonovscards.entity;

public class Card implements ICard {
    private final String uuid;
    private final String text;
    private final String translate;

    public Card(String uuid, String text, String translate) {
        this.uuid = uuid;
        this.text = text;
        this.translate = translate;
    }

    @Override
    public String uuid() {
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
}
