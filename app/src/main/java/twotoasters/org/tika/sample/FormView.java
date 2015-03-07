package twotoasters.org.tika.sample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.OnClick;
import twotoasters.org.tika.Form;
import twotoasters.org.tika.FormSubmitEvent;
import twotoasters.org.tika.Tika;

public class FormView {

    WeakReference<Activity> activityRef;

    public FormView(Activity activity) {
        activityRef = new WeakReference<>(activity);
        ButterKnife.inject(this, activity);
    }

    @OnClick(R.id.submit)
    @SuppressWarnings("unused")
    public void onSubmitForm() {
        Form form = new Form()
                .addField(Tika.field(findTextViewById(R.id.name)))
                .addField(Tika.field(findTextViewById(R.id.password)))
                .addField(Tika.field(findTextViewById(R.id.confirm_password)))
                .addField(Tika.field(findTextViewById(R.id.age)))
                .addField(Tika.field(findTextViewById(R.id.birthdate)))
                .addField(Tika.field(findTextViewById(R.id.postal_code)))
                .addField(Tika.field(findCheckBoxById(R.id.terms)));

        BusProvider.getInstance().post(new FormSubmitEvent(form));
    }

    private TextView findTextViewById(@IdRes int id) {
        return ButterKnife.findById(getActivity(), id);
    }

    private CheckBox findCheckBoxById(@IdRes int id) {
        return ButterKnife.findById(getActivity(), id);
    }

    protected Context getContext() {
        return getActivity();
    }

    protected Activity getActivity() {
        return activityRef.get();
    }

    protected CharSequence getString(@StringRes int id) {
        return getActivity().getString(id);
    }

    public void setError(@IdRes int id, CharSequence message) {
        if (TextUtils.isEmpty(message)) return;

        View v = findTextViewById(id);
        Log.d("here", "Set error on view: " + v);

        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            tv.setError(message);
            tv.requestFocus();
        } else {
            toast(message);
        }

        if (v instanceof CheckBox) {
            // CheckBox is aa subclass of TextView, so it will setError above.
            // However, it has no way of displaying the error message.
            // So toast it.
            // TODO: Checkbox doesn't undo setError state after it is checked
            toast(message);
        }
    }

    private void toast(CharSequence message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
