package com.sokolov.bessonovscards.domain.cards;

import com.sokolov.bessonovscards.entity.ITextToSpeech;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PronounceTextUseCaseTest {

    private IPronounceTextUseCase pronounceTextUseCase;
    @Mock
    private IPronounceTextUseCase.Callback callback;
    @Mock
    private ITextToSpeech textToSpeech;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pronounceTextUseCase = new PronounceTextUseCase(textToSpeech);
    }

    @Test
    public void testExecute() {
        String text = "text";
        pronounceTextUseCase.execute(text, callback);

        ArgumentCaptor<ITextToSpeech.IUtteranceProgressListener> captor =
                ArgumentCaptor.forClass(ITextToSpeech.IUtteranceProgressListener.class);

        verify(textToSpeech).setOnUtteranceProgressListener(captor.capture());
        verify(textToSpeech).speak(text, text);

        captor.getValue().onDone(text);
        verify(callback).onSuccess();
    }

}