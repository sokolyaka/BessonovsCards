package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.entity.ITextToSpeech;

public class PronounceTextUseCase implements IPronounceTextUseCase {

    private final ITextToSpeech textToSpeech;

    public PronounceTextUseCase(ITextToSpeech textToSpeech) {

        this.textToSpeech = textToSpeech;
    }

    @Override
    public void execute(String text, Callback callback) {
        textToSpeech.setOnUtteranceProgressListener(utteranceId -> {
            if (utteranceId.equals(text)) {
                callback.onSuccess();
            }
        });

        textToSpeech.speak(text, text);
    }
}
