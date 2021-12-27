package children;

import java.util.ArrayList;

public class Children {
    ArrayList<ChildStrategy> children = new ArrayList<>();

    public ArrayList<ChildStrategy> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ChildStrategy> children) {
        this.children = children;
    }
}