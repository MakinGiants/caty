package test.notificationreader.model;


import android.util.Log;

import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.TextObjectFactory;

import java.io.IOException;
import java.util.List;

import static com.optimaize.langdetect.text.CommonTextObjectFactories.forDetectingOnLargeText;
import static com.optimaize.langdetect.text.CommonTextObjectFactories.forIndexingCleanText;

public class Translator {

    private LanguageDetector languageDetector;

    public Translator() {
        try {
            List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();
            languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                    .withProfiles(languageProfiles)
                    .build();
        } catch (IOException e) {
            Log.e("AAA", "ERROR", e);
        }
    }

    public LdLocale recognize(String text) {
        TextObjectFactory textObjectFactory = text.length() > 10 ? forDetectingOnLargeText() : forIndexingCleanText();
        Optional<LdLocale> locale = languageDetector.detect(textObjectFactory.forText(text));
        return locale.isPresent() ? locale.get() : null;
    }
}

