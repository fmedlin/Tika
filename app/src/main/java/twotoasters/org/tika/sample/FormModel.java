package twotoasters.org.tika.sample;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.squareup.phrase.Phrase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import twotoasters.org.tika.FieldError;
import twotoasters.org.tika.Form;
import twotoasters.org.tika.FormValidator;
import twotoasters.org.tika.MinimumValue;
import twotoasters.org.tika.RegexValidation;
import twotoasters.org.tika.Validation;

public class FormModel {

    public List<FieldError> validate(Context context, Form form) {
        FormValidator validator = FormValidator.onForm(context, form)
                .validates(R.id.name)
                    .required(R.string.error_name_required)
                .validates(R.id.password)
                    .required(R.string.error_password_required)
                    .using(new PasswordValidation(context))
                .validates(R.id.confirm_password)
                    .matches(R.id.password, R.string.error_passwords_must_match)
                .validates(R.id.age)
                    .required(R.string.error_age_required)
                    .using(new MinimumValue(18, R.string.error_minimum_age))
                .validates(R.id.birthdate)
                    .required(R.string.error_birthdate_required)
                    .using(new DateFormatValidation(R.string.error_bad_date))
                .validates(R.id.postal_code)
                    .required(R.string.error_postal_code_required)
                    .using(new PostalCodeValidation(), R.string.error_bad_postal_code)
                .validates(R.id.terms)
                    .using(new IsCheckedValidation(), R.string.error_agreement_required)
                .build();

        return validator.getErrors();
    }

    public static class PasswordValidation extends Validation<String> {
        Context context;
        CharSequence message;

        public PasswordValidation(Context context) {
            this.context = context;
        }

        public boolean isValid(String value) {
            if (value.length() < 12) {
                setMessage(
                        Phrase.from(context, R.string.error_password_too_short)
                                .put("at_least", 12)
                                .format()
                );
                return false;
            }

            if (!value.matches("(.)*(\\d)(.)*")
                    || TextUtils.isDigitsOnly(value)) {
                setMessage(R.string.error_password_reminder);
                return false;
            }

            return true;
        }
    }

    public static class DateFormatValidation extends Validation<String> {
        static SimpleDateFormat[] formats = new SimpleDateFormat[] {
            new SimpleDateFormat("MM/dd/yyyy"),
            new SimpleDateFormat("MM-dd-yyyy")
        };

        int msgId;

        public DateFormatValidation(@StringRes int msgId) {
            this.msgId = msgId;
        }

        @Override
        public boolean isValid(String value) {
            for (SimpleDateFormat format : formats) {
                try {
                    format.parse(value);
                    return true;
                } catch (ParseException e) { }
            }

            setMessage(msgId);
            return false;
        }
    }

    // Match US or Canadian postal codes
    public static class PostalCodeValidation extends RegexValidation {
        public PostalCodeValidation() {
            super("(^\\d{5}(-\\d{4})?$)|(^[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[A-Z]{1} *\\d{1}[A-Z]{1}\\d{1}$)");
        }
    }

    public static class IsCheckedValidation extends Validation<Boolean> {
        @Override
        public boolean isValid(Boolean value) {
            // Input assumed to be value of CheckBox.isChecked()
            return value;
        }
    }
}
