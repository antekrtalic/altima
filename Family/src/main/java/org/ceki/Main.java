package org.ceki;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get("./src/main/resources/names.txt");
        Charset charset = StandardCharsets.UTF_8;
        FamilyTree familyTree = new FamilyTree();

        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for (String line: lines) {
                String[] split = line.split(" ");
                String childName = split[0];
                String parentName = split[1];

                familyTree.insertChildToParent(childName, parentName);

            }

            familyTree.printTree();
        } catch (IOException exc) {
            System.out.println("I/O error");
        }

    }
}


