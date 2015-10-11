package test.notificationreader.model;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;


public class TextReader implements TextToSpeech.OnInitListener {

    private TextToSpeech mTextToSpeech;

    public TextReader(Context context) {
        mTextToSpeech = new TextToSpeech(context, this);
    }

    public void read(String text) {
        //TODO: Search how to play for SDK < 21
        mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            Locale spanish = new Locale("es", "ES");
            mTextToSpeech.setLanguage(spanish);
        }
    }

}
