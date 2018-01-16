package samples.akhilesh.localizationsample.model;


public class Language {

    private String lang;

    public Language(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return lang.equals(language.lang);
    }

    @Override
    public int hashCode() {
        return lang.hashCode();
    }
}
