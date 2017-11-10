package com.sokolov.bessonovscards.entity;

public interface ITextToSpeech {
    void speak(String text, String utteranceId);

    void setOnUtteranceProgressListener(IUtteranceProgressListener listener);

    interface IUtteranceProgressListener {
        void onDone(String utteranceId);
    }
}
