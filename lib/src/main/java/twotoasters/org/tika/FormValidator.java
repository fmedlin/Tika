package twotoasters.org.tika;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import java.util.List;

public class FormValidator {

    public static Builder onForm(Context context, Form form) {
        return new Builder(context, form);
    }

    List<FieldError> errors;
    public FormValidator(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public static class Builder {
        Context context;
        Form form;
        Field field;
        int id;

        public Builder(Context context, Form form) {
            this.context = context;
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

        public Builder using(Validation validation) {
            if (field != null) {
                if (!validation.isValid(field.value)) {
                    form.addError(id, validation.getMessage(context));
                }
            }
            return this;
        }

        public Builder using(Validation validation, @StringRes int msgId) {
            if (field != null) {
                if (!validation.isValid(field.value)) {
                    form.addError(id, msgId);
                }
            }
            return this;
        }

        public Builder matches(@IdRes int id, @StringRes int msgId) {
            Field f = form.getField(id);
            if (!field.value.equals(f.value)) {
                form.addError(this.id, msgId);
            }
            return this;
        }

        public FormValidator build() {
            return new FormValidator(form.getErrors());
        }
    }
}
