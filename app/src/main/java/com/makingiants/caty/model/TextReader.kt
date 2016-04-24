package com.makingiants.caty.model

import android.content.Context
import android.media.AudioManager.STREAM_NOTIFICATION
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.Engine.KEY_PARAM_STREAM
import java.util.*


open class TextReader(context: Context) : TextToSpeech.OnInitListener {
  private val STREAM = STREAM_NOTIFICATION

  private val mTextToSpeech: TextToSpeech
  private var mParams: HashMap<String, String>? = null
  private var mBundle: Bundle? = null

  init {
    mTextToSpeech = TextToSpeech(context, this)

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mBundle = Bundle().apply {
        putInt(KEY_PARAM_STREAM, STREAM)
      }
    } else {
      mParams = hashMapOf<String, String>(Pair(KEY_PARAM_STREAM, STREAM.toString()));
    }
  }

  @SuppressWarnings("deprecation")
  open fun read(text: String) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, mBundle, null)
    } else {
      mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, mParams)
    }
  }

  override fun onInit(status: Int) {
    if (status != TextToSpeech.ERROR) {
      if (mTextToSpeech.isLanguageAvailable(Locale.getDefault()) == TextToSpeech.LANG_AVAILABLE) {
        mTextToSpeech.language = Locale.getDefault()
      } else {
        mTextToSpeech.language = Locale.US;
      }
    }
  }

}
