package twotoasters.org.tika;

import android.widget.CheckBox;
import android.widget.TextView;

public class Tika {

    public static Field field(TextView v) {
        return new Field<>(
                v.getId(),
                v.getText().toString().trim()
        );
    }

    public static Field field(CheckBox cb) {
        return new Field<>(
                cb.getId(),
                cb.isChecked()
        );
    }
}
