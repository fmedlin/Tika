package twotoasters.org.tika.sample;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.TextView;

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
                .addField(Tika.field(findById(R.id.name)))
                .addField(Tika.field(findById(R.id.password)))
                .addField(Tika.field(findById(R.id.confirm_password)))
                .addField(Tika.field(findById(R.id.age)))
                .addField(Tika.field(findById(R.id.birthdate)))
                .addField(Tika.field(findById(R.id.postal_code)));

        BusProvider.getInstance().post(new FormSubmitEvent(form));
    }

    private TextView findById(@IdRes int id) {
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

        TextView tv = findById(id);
        tv.setError(message);
        tv.requestFocus();
    }
}
