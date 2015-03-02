package twotoasters.org.tika;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

public class Form {

    List<Field> fields;
    List<FieldError> errors;

    public Form addField(Field field) {
        getFields().add(field);
        return this;
    }

    public List<Field> getFields() {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        return fields;
    }

    public Field getField(int id) {
        for (Field field : getFields()) {
            if (field.resId == id) {
                return field;
            }
        }
        return null;
    }

    public Form addError(@IdRes int id, @StringRes int resId) {
        getErrors().add(new FieldError(id, resId));
        return this;
    }

    public Form addError(@IdRes int id, CharSequence errorMsg) {
        getErrors().add(new FieldError(id, errorMsg));
        return this;
    }

    protected List<FieldError> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }
}
