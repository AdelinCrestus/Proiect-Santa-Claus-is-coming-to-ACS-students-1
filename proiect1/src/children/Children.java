package children;

import java.util.ArrayList;

public final class Children {
    private ArrayList<ChildStrategy> children = new ArrayList<>();

    public ArrayList<ChildStrategy> getChildren() {
        return children;
    }

    public void setChildren(final ArrayList<ChildStrategy> children) {
        this.children = children;
    }
}
