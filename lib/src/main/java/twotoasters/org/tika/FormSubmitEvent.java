package twotoasters.org.tika;

public class FormSubmitEvent {

    Form form;

    public FormSubmitEvent(Form form) {
        this.form = form;
    }

    public Form getForm() {
        return form;
    }
}
