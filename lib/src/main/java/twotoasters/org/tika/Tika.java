package twotoasters.org.tika;

import android.widget.TextView;

public class Tika {

    public static Field field(TextView v) {
        return new Field<>(
                v.getId(),
                v.getText().toString().trim()
        );
    }
}
