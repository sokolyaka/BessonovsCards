package com.sokolov.bessonovscards.entity;

public class TextMode implements ITextMode {
    private final String textMode;

    public TextMode(String textMode) {
        this.textMode = textMode;
    }

    @Override
    public String mode() {
        return textMode;
    }
}
