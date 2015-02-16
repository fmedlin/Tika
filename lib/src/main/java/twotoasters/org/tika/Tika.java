package twotoasters.org.tika;

import android.view.View;
import android.widget.EditText;

public class Tika {

    public static Field field(View v) {
        if (v instanceof EditText) {
            return new Field<String>(
                    v.getId(),
                    ((EditText)v).getText().toString().trim()
            );
        }

        throw new RuntimeException("Unsupported view type: " + v.getClass().getSimpleName());
    }
}
