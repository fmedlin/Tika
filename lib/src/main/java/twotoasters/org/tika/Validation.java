package twotoasters.org.tika;

import android.support.annotation.StringRes;

public class Validation<T> {

    private @StringRes int msgId;

    public boolean isValid(T value) {
        throw new RuntimeException("Override me");
    }

    public void setMessage(@StringRes int msgId) {
        this.msgId = msgId;
    }

    public int getMessage() {
        return msgId;
    }
}
