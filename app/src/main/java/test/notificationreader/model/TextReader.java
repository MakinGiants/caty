package test.notificationreader.model;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;


public class TextReader implements TextToSpeech.OnInitListener {

    private TextToSpeech mTextToSpeech;

    public TextReader(Context context) {
        mTextToSpeech = new TextToSpeech(context, this);
    }

    @SuppressWarnings("deprecation")
    public void read(String text) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
//            Locale spanish = new Locale("es", "ES");
            mTextToSpeech.setLanguage(Locale.ITALIAN);
        }
    }

}
