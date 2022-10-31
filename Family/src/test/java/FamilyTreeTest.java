import org.ceki.FamilyNode;
import org.ceki.FamilyTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;


class FamilyTreeTest {


    @Test
    public  void checkUniqueNodes() throws Exception{
        FamilyTree familyTree = new FamilyTree();

        familyTree.insertChildToParent("Adam", "Ivan");
        familyTree.insertChildToParent("Marko", "Stjepan");
        familyTree.insertChildToParent("Stjepan", "Adam");
        familyTree.insertChildToParent("Robert", "Stjepan");
        familyTree.insertChildToParent("Fran", "Ivan");
        familyTree.insertChildToParent("Leopold", "Luka");

        Map<String, FamilyNode> nodeMap = familyTree.getFamilyTrees();
        int nodesWithoutParent = familyTree.withoutParent().size();

        Assertions.assertEquals(8, nodeMap.size(), "Should have 8 nodes");

        Assertions.assertEquals(2, nodeMap.get("Ivan").getChildren().size(), "Ivan should have 2 nodes");
        Assertions.assertEquals(1, nodeMap.get("Adam").getChildren().size(), "Adam should have 1 node");
        Assertions.assertEquals(2, nodeMap.get("Stjepan").getChildren().size(), "Stjepan should have 2 nodes");
        Assertions.assertEquals(1, nodeMap.get("Luka").getChildren().size(), "Luka should have 1 node");

        Assertions.assertEquals(0, nodeMap.get("Marko").getChildren().size(), "Marko should have 0 node");
        Assertions.assertEquals(0, nodeMap.get("Robert").getChildren().size(), "Robert should have 0 node");
        Assertions.assertEquals(0, nodeMap.get("Fran").getChildren().size(), "Fran should have 0 node");
        Assertions.assertEquals(0, nodeMap.get("Leopold").getChildren().size(), "Leopold should have 0 node");


        Assertions.assertEquals(2, nodesWithoutParent, "Should be 2 nodes without any parent");

    }

    @Test
    public void test_Cyclic_insert_Child_Parent_rows_Throws_Exception() throws Exception {
        List<String> lines
                = Arrays.asList
                (
//                      Child Parent
                        "B A",
                        "C B"
                );

        FamilyTree familyTree = new FamilyTree();

        for (String line: lines) {
            String[] split = line.split(" ");
            String childName = split[0];
            String parentName = split[1];
            familyTree.insertChildToParent(childName, parentName);
        }

        Map<String, FamilyNode> nodeMap = familyTree.getFamilyTrees();
        //Get root nodes: parents without parent
        List<FamilyNode> withOutParents = familyTree.withoutParent();
        Assertions.assertEquals(1, withOutParents.size(), "Should have 1 node without any parent");
        familyTree.printTree();

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            familyTree.insertChildToParent("A", "C");
        });
    }

    @Test
    public void test_insert_Child_With_Two_Parents() throws Exception {
        List<String> lines
                = Arrays.asList
                (
//                      Child, Parent
                        "B A",
                        "B C"
                );
        FamilyTree familyTree = new FamilyTree();

        for (String line: lines) {
            String[] split = line.split(" ");
            String childName = split[0];
            String parentName = split[1];
            familyTree.insertChildToParent(childName, parentName);
        }

        Map<String, FamilyNode> nodeMap = familyTree.getFamilyTrees();

        List<FamilyNode> withOutParents = familyTree.withoutParent();
        Assertions.assertEquals(2, withOutParents.size(), "Should have 2 nodes without any parent");

        Assertions.assertEquals(1, nodeMap.get("A").getChildren().size(), "A should have 1 child");
        Assertions.assertEquals(1, nodeMap.get("C").getChildren().size(), "C should have 1 child");

        Assertions.assertEquals(2, nodeMap.get("B").getParents().size(), "B should have 2 parent");

        familyTree.printTree();
    }

}