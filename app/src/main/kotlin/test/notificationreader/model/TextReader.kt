package test.notificationreader.model

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import java.util.*


open class TextReader(context: Context) : TextToSpeech.OnInitListener {
    private val mTextToSpeech: TextToSpeech

    init {
        mTextToSpeech = TextToSpeech(context, this)
    }

    @SuppressWarnings("deprecation")
    fun read(text: String) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            val spanish = Locale("es", "ES")
            mTextToSpeech.language = spanish
        }
    }

}
