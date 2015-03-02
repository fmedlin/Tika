package twotoasters.org.tika;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;

public class Validation<T> {

    private @StringRes int msgId;
    private CharSequence errorMsg;

    public boolean isValid(T value) {
        throw new RuntimeException("Override me");
    }

    public void setMessage(@StringRes int msgId) {
        this.msgId = msgId;
    }

    public void setMessage(CharSequence errorMsg) {
        this.errorMsg = errorMsg;
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
