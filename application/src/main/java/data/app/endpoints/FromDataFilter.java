package data.app.endpoints;

import data.app.model.FromData;

public final class FromDataFilter {

    private FromDataFilter() {
        // Stop instantiation
    }

    public static boolean isBetweenOneAndTen(FromData value) {
        if (value.getId() > 0) {
            if (value.getId() < 11) {
                return true;
            }
        }
        return false;
    }
}
