package twotoasters.org.tika.sample;

import com.squareup.otto.Subscribe;

import java.util.List;

import twotoasters.org.tika.FieldError;
import twotoasters.org.tika.FormSubmitEvent;

public class FormPresenter {

    FormModel model;
    FormView view;

    public FormPresenter(FormModel model, FormView view) {
        this.model = model;
        this.view = view;
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onFormSubmit(FormSubmitEvent event) {
        List<FieldError> errors = model.validate(view.getActivity(), event.getForm());
        if (errors.isEmpty()) {
            // Do the success case
        } else {
            for (FieldError error : errors) {
                view.setError(error.getId(), error.getMessage(view.getActivity()));
            }
        }
    }
}
