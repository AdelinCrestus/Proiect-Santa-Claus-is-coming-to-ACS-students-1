package children;

import enums.Category;
import enums.Cities;

import java.util.ArrayList;

public class Baby extends ChildStrategy{
    public Baby(Integer id, String lastName, String firstName, Integer age, Cities city, Double niceScore, ArrayList<Category> giftsPreferences) {
        super(id, lastName, firstName, age, city, niceScore, giftsPreferences);
    }

    @Override
    public Double average() {
        return 10.0;
    }
}
