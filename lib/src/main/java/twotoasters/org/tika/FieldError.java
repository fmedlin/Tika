package twotoasters.org.tika;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

public class FieldError {
    int id;
    int resId;

    public FieldError(@IdRes int id, @StringRes int resId) {
        this.id = id;
        this.resId = resId;
    }

    public int getId() {
        return id;
    }

    public int getMessage() {
        return resId;
    }
}
