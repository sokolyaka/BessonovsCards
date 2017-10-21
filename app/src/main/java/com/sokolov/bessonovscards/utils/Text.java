package com.sokolov.bessonovscards.utils;

public class Text {

    private final CharSequence text;

    public Text(CharSequence text) {
        this.text = text;
    }

    public boolean isEmpty() {
        return text == null || text.length() == 0;
    }
}
