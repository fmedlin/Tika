package twotoasters.org.tika.sample;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
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
        Form form = new Form();
        form.addField(Tika.field(findById(R.id.name)));
        BusProvider.getInstance().post(new FormSubmitEvent(form));
    }

    private View findById(@IdRes int id) {
        return ButterKnife.findById(getActivity(), id);
    }

    protected Activity getActivity() {
        return activityRef.get();
    }

    protected CharSequence getString(@StringRes int id) {
        return getActivity().getString(id);
    }
    public void setError(@IdRes int id, @StringRes int msgId) {
        ((TextView) findById(id)).setError(getString(msgId));
    }
}
