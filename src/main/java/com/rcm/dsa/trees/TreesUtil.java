package com.rcm.dsa.trees;
import java.util.*;

/**
 * Use this class to get pre constructed trees, perform tree related operations etc
 */
public class TreesUtil {

    public static BinarySearchTree getTree() {

        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(25);
        tree.insert(5);
        tree.insert(4);
        tree.insert(2);
        tree.insert(1);
        tree.insert(30);
        tree.insert(26);
        tree.insert(24);
        tree.insert(28);
        tree.insert(31);

        return tree;

    }

}
