package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public class YoungAdult extends ChildStrategy{
    public YoungAdult(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        return 0.0;
    }
}
