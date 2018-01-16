package samples.akhilesh.localizationsample.utils;

import static samples.akhilesh.localizationsample.utils.LanguageConstants.LANG_CODE_EN;
import static samples.akhilesh.localizationsample.utils.LanguageConstants.LANG_CODE_HI;
import static samples.akhilesh.localizationsample.utils.LanguageConstants.LANG_VAL_ENG;
import static samples.akhilesh.localizationsample.utils.LanguageConstants.LANG_VAL_HIN;

public class Utils {
    public static String languageToLocale(String lang) {

        switch (lang) {
            case LANG_VAL_ENG:
                return LANG_CODE_EN;
            case LANG_VAL_HIN:
                return LANG_CODE_HI;
            default:
                return LANG_CODE_EN;
        }
    }

    public static String localeToLanguage(String locale) {

        switch (locale) {
            case LANG_CODE_EN:
                return LANG_VAL_ENG;
            case LANG_CODE_HI:
                return LANG_VAL_HIN;
            default:
                return LANG_VAL_ENG;
        }
    }

}
