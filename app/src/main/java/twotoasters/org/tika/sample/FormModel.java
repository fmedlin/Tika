package twotoasters.org.tika.sample;

import java.util.List;

import twotoasters.org.tika.FieldError;
import twotoasters.org.tika.Form;
import twotoasters.org.tika.FormValidator;

public class FormModel {

    public List<FieldError> validate(Form form) {
        FormValidator validator = FormValidator.onForm(form)
                .validates(R.id.name)
                    .required(R.string.error_name_required)
                .build();

        return validator.getErrors();
    }
}
