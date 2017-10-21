package com.sokolov.bessonovscards.entity;

public class Card implements ICard {
    private final String uuid;
    private final String text;
    private final String translate;
    private final String categoryName;

    public Card(String uuid, String text, String translate, String categoryName) {
        this.uuid = uuid;
        this.text = text;
        this.translate = translate;
        this.categoryName = categoryName;
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
    public String categoryName() {
        return categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (uuid != null ? !uuid.equals(card.uuid) : card.uuid != null) return false;
        if (text != null ? !text.equals(card.text) : card.text != null) return false;
        if (translate != null ? !translate.equals(card.translate) : card.translate != null)
            return false;
        return categoryName != null ? categoryName.equals(card.categoryName) : card.categoryName == null;

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (translate != null ? translate.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "categoryName='" + categoryName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", text='" + text + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
