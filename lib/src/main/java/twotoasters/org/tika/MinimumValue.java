package twotoasters.org.tika;

import android.support.annotation.StringRes;

public class MinimumValue extends Validation<String> {

    int minimum;
    int msgId;

    public MinimumValue(int minimum, @StringRes int msgId) {
        this.minimum = minimum;
        this.msgId = msgId;
    }

    @Override
    public boolean isValid(String value) {
        int i;

        try {
            i = Integer.parseInt(value);
        } catch(NumberFormatException e) {
            setMessage(R.string.tika_error_number_required);
            return false;
        }

        if (i < minimum) {
            setMessage(msgId);
            return false;
        }
        return true;
    }
}
