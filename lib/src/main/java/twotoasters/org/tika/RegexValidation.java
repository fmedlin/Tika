package twotoasters.org.tika;

public class RegexValidation extends Validation<String> {

    String regex;

    public RegexValidation(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean isValid(String value) {
        return value.matches(regex);
    }
}
