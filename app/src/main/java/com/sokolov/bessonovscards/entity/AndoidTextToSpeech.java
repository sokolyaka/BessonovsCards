package com.sokolov.bessonovscards.entity;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import java.util.HashMap;

public class AndoidTextToSpeech implements ITextToSpeech {
    private final TextToSpeech textToSpeech;

    public AndoidTextToSpeech(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
    }

    @Override
    public void speak(String text, String utteranceId) {
        HashMap<String, String> param = new HashMap<>();
        param.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, text);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, param);
    }

    @Override
    public void setOnUtteranceProgressListener(IUtteranceProgressListener listener) {
        textToSpeech
                .setOnUtteranceProgressListener(
                        new UtteranceProgressListener() {
                            @Override
                            public void onStart(String utteranceId) {

                            }

                            @Override
                            public void onDone(String utteranceId) {
                                listener.onDone(utteranceId);
                            }

                            @Override
                            public void onError(String utteranceId) {

                            }
                        });
    }
}
