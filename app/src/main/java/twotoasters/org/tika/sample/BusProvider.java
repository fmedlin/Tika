package twotoasters.org.tika.sample;

import com.squareup.otto.Bus;

public class BusProvider {
    static Bus bus;

    public static Bus getInstance() {
        if (bus == null) {
            bus = new Bus();
        }
        return bus;
    }

    public static void register(Object o) {
        getInstance().register(o);
    }

    public static void unregister(Object o) {
        getInstance().unregister(o);
    }

}
