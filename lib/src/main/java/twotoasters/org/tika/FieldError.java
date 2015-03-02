package twotoasters.org.tika;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;

public class FieldError {
    int id;
    int msgId;
    CharSequence errorMsg;    // Custom validations may provide this

    public FieldError(@IdRes int id, @StringRes int msgId) {
        this.id = id;
        this.msgId = msgId;
    }

    public FieldError(@IdRes int id, CharSequence errorMsg) {
        this.id = id;
        this.errorMsg = errorMsg;
    }

    public int getId() {
        return id;
    }

    public CharSequence getMessage(Context context) {
        if (!TextUtils.isEmpty(errorMsg)) {
            return errorMsg;
        } else if (msgId != 0) {
            return context.getString(msgId);
        }

        return null;
    }
}
