package twotoasters.org.tika;

import android.support.annotation.IdRes;
import android.text.TextUtils;

public class Field<T> {

    int resId;
    T value;

    public Field(@IdRes int resId, T value) {
        this.resId = resId;
        this.value = value;
    }

    public boolean isEmpty() {
        if (value instanceof CharSequence) {
            return TextUtils.isEmpty((CharSequence) value);
        }

        return false;
    }
}
