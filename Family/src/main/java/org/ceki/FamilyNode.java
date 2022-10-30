package org.ceki;

import java.util.HashSet;
import java.util.Set;

public class FamilyNode {

    private static int idGenerator = 1000;
    private final Integer id;
    String name;
    private Set<FamilyNode> parents = new HashSet<>();
    private Set<FamilyNode> children = new HashSet<>();

    public FamilyNode(String name) {
        this.id = idGenerator++;
        this.name = name;
    }

    public void addChild(FamilyNode familyNode) {
        this.children.add(familyNode);
    }

    public void addParent(FamilyNode familyNode) {
        this.parents.add(familyNode);
    }

    public String getName() {
        return this.name;
    }

    public Set<FamilyNode> getParents() {
        return this.parents;
    }

    public Set<FamilyNode> getChildren() {
        return this.children;
    }


}
