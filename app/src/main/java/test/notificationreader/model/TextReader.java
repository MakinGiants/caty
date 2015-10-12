package test.notificationreader.model;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import com.optimaize.langdetect.i18n.LdLocale;

import java.util.Locale;


public class TextReader implements TextToSpeech.OnInitListener {

    private TextToSpeech mTextToSpeech;
    private Translator mTranslator;

    public TextReader(Context context) {
        mTextToSpeech = new TextToSpeech(context, this);
        mTranslator = new Translator();
    }

    @SuppressWarnings("deprecation")
    public void read(String text) {
        updateLocale(text);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void updateLocale(String text) {
        LdLocale locale = mTranslator.recognize(text);
        if (locale == null) {
            mTextToSpeech.setLanguage(Locale.ENGLISH);
        } else {
            mTextToSpeech.setLanguage(new Locale(locale.getLanguage()));
        }
    }

    @Override
    public void onInit(int status) {
        if (status != TextToSpeech.ERROR) {
            mTextToSpeech.setLanguage(Locale.ENGLISH);
        }
    }

}
