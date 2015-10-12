package test.notificationreader;

import com.optimaize.langdetect.i18n.LdLocale;

import org.junit.Test;

import test.notificationreader.model.Translator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TranslatorTests {

    @Test
    public void recognizeEnglish() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("This is a text in english and should return en");

        assertThat(locale).isNotNull();
        assertThat(locale.getLanguage()).isEqualTo("en");
    }

    @Test
    public void recognizeEnglishLittle() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("This is it.");

        assertThat(locale).isNotNull();
        assertThat(locale.getLanguage()).isEqualTo("en");
    }


    @Test
    public void recognizePortuguese() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("Isso é feito de um jeito incrivel não sei por que.");

        assertThat(locale).isNotNull();
        assertThat(locale.getLanguage()).isEqualTo("pt");
    }

    @Test
    public void recognizePortugueseLittle() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("é assim que ele fode.");

        assertThat(locale).isNull();
    }

    @Test
    public void recognizeSpanish() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("Este es un texto en español y estamos probando a ver si esta cosa funciona.");

        assertThat(locale).isNotNull();
        assertThat(locale.getLanguage()).isEqualTo("es");
    }

    @Test
    public void recognizeSpanishLittle() {
        Translator translator = new Translator();
        LdLocale locale = translator.recognize("Este es un intento pequeño.");

        assertThat(locale).isNull();
    }

}