package org.ceki;

import java.util.*;

public class FamilyTree {

    private Map<String, FamilyNode> familyTrees = new HashMap<>();


    public void insertChildToParent (String child, String parent) throws Exception {

        FamilyNode parentNode = familyTrees.get(parent);

        if (!familyTrees.containsKey(parent)) {
            parentNode = new FamilyNode(parent);
            this.familyTrees.put(parent, parentNode);
        }

        FamilyNode childNode = familyTrees.get(child);

        Set<FamilyNode> pathMembers = getPathParentMembers(parentNode);

        if (pathMembers.contains(childNode)) {
            throw new Exception("Parent can't be a child.");
        }

        if (!familyTrees.containsKey(child)) {
            childNode = new FamilyNode(child);
            this.familyTrees.put(child, childNode);
        }


        this.familyTrees.get(child).addParent(this.familyTrees.get(parent));
        this.familyTrees.get(parent).addChild(this.familyTrees.get(child));
    }

    private Set<FamilyNode> getPathParentMembers(FamilyNode node) {

        Set<FamilyNode> parentMembers = new HashSet<>();
        List<FamilyNode> todo = new ArrayList<>();
        todo.add(node);
        while (todo.size() > 0) {
            List<FamilyNode> parents = new ArrayList<>();
            for (FamilyNode n: todo) {
                parentMembers.addAll(n.getChildren());
                parentMembers.add(n);
                parents.addAll(n.getParents());
            }
            todo.clear();
            todo.addAll(parents);
        }

        return parentMembers;
    }

    public Map<String, FamilyNode> getFamilyTrees() {
        return this.familyTrees;
    }

    public List<FamilyNode> withoutParent() {

        List<FamilyNode> nodesWithoutParents = new ArrayList<>();

        for (String name: this.familyTrees.keySet()) {
            if (this.familyTrees.get(name).getParents().size() == 0) {
                nodesWithoutParents.add(this.familyTrees.get(name));
            }
        }

        return nodesWithoutParents;
    }

    public void printTree() {
        List<FamilyNode> roots = withoutParent();
        int counter = 0;

        for (FamilyNode root: roots) {
            System.out.println(root.getName());
            printChild(counter, root.getChildren());
        }
    }

    public void printChild(int blanks, Set<FamilyNode> children) {

        int spaces = 2 + (blanks * 2);

        for (FamilyNode child: children) {
            System.out.printf("%-" + spaces + "s%s\n", "", child.getName());
            printChild(spaces+1, child.getChildren());
        }
    }
}
