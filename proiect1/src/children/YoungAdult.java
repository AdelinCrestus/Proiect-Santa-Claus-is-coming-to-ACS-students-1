package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public final class YoungAdult extends ChildStrategy {
    public YoungAdult(final Integer id, final String lastName, final String firstName,
                      final Integer age, final Cities city, final Double niceScore,
                      final ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        this.setAverageScore(0.0);
        return 0.0;
    }
}
