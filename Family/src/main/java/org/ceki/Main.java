package org.ceki;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        List<String> lines = Arrays.asList(
                "Adam Ivan",
                "Marko Stjepan",
                "Stjepan Adam",
                "Robert Stjepan",
                "Fran Ivan",
                "Leopold Luka"
        );

        FamilyTree familyTree = new FamilyTree();

        for (String line: lines) {
            String[] bothNames = line.split(" ");

            familyTree.insertChildToParent(bothNames[0], bothNames[1]);
        }

        familyTree.printTree();
    }
}


