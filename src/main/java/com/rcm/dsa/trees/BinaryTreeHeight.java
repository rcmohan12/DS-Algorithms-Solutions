package com.rcm.dsa.trees;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeHeight {

    public static void main(String[] args) {
        System.out.println("Tree Min Height : "+getMinHeight(TreesUtil.getTree()));
        System.out.println("Tree Max Height : "+getMaxHeight(TreesUtil.getTree()));
    }

    /**
     *
     * @param tree
     * @return int : min height of the tree
     */
    static int getMinHeight(BinarySearchTree tree) {
        int levelCounter = -1;
        int minHeight = 0;
        int maxHeight = 1;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(tree.get_rootNode());

        while(!queue.isEmpty()) {
            levelCounter++;
            int l = queue.size();
            for(int i = 0; i<l; i++) {
                TreeNode node = queue.poll();

                if(node.left == null && node.right == null) {
                    if(minHeight != 0) {
                        minHeight = Math.min(minHeight, levelCounter);
                    } else {
                        minHeight = levelCounter;
                    }
                }

                if(node.left != null) {
                    queue.add(node.left);
                }

                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return minHeight;
    }

    /**
     *
     * @param tree
     * @return : max height of the tree
     */
    static int getMaxHeight(BinarySearchTree tree) {
        int levelCounter = -1;
        int maxHeight = 0;
        Queue<TreeNode> queue = new LinkedList();
        queue.add(tree.get_rootNode());

        while(!queue.isEmpty()) {
            levelCounter++;
            int l = queue.size();
            for(int i = 0; i<l; i++) {
                TreeNode node = queue.poll();

                if(node.left == null && node.right == null) {
                    if(maxHeight != 0) {
                        maxHeight = Math.max(maxHeight, levelCounter);
                    } else {
                        maxHeight = levelCounter;
                    }
                }

                if(node.left != null) {
                    queue.add(node.left);
                }

                if(node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return maxHeight;
    }

}
