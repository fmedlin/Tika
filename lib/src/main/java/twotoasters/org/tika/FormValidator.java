package twotoasters.org.tika;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import java.util.List;

public class FormValidator {

    public static Builder onForm(Form form) {
        return new Builder(form);
    }

    List<FieldError> errors;
    public FormValidator(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public static class Builder {
        Form form;
        int id;
        Field field;

        public Builder(Form form) {
            this.form = form;
        }

        public Builder validates(@IdRes int id) {
            this.id = id;
            this.field = form.getField(id);
            return this;
        }

        public Builder required(@StringRes int resId) {
            if (field == null || field.isEmpty()) {
                form.addError(id, resId);
            }
            return this;
        }

        public FormValidator build() {
            return new FormValidator(form.getErrors());
        }
    }
}
