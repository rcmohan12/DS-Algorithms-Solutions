package com.rcm.dsa.trees;


import java.util.*;

/**
 * The Binary search tree is a rooted binary tree whose internal nodes each store a key greater
 * than all the keys in the node's left subtree and less than those in its right subtree
 * Ex:                    25
 *                 23            27
 *             17      19    26      45
 *
 * Here, h = 3
 * No. of nodes         n = 2^h -1
 * In this example,     n = 2^3 -1
 *                        = 7
 * In general,          n = 2^h (ignoring the constant)
 *                  log n = h
 */
public class BinarySearchTree {


    private TreeNode _rootNode;

    /**
     * method to insert values to the tree
     * Time complexity : O(log n) |  O(h)
     * Worst case      : O(n)
     * @param val
     */
    public void insert(int val) {
        if(_rootNode != null) {
            TreeNode node = _rootNode;
            while(node != null) {
                if(val == node.getValue()) {
                    return;
                } else if(val < node.getValue()) {
                    if(node.getLeft() != null) {
                        node = node.getLeft();
                    } else {
                        TreeNode newNode = new TreeNode();
//                        if(val == 4){  // To test validity of the BST
//                            newNode.setValue(25);
//                        }
                        newNode.setValue(val);
                        node.setLeft(newNode);
//                        System.out.println("inserted "+val);
                        return;
                    }
                } else if (val > node.getValue()) {
                    if(node.getRight() != null) {
                        node = node.getRight();
                    } else {
                        TreeNode newNode = new TreeNode();
                        newNode.setValue(val);
                        node.setRight(newNode);
//                        System.out.println("inserted "+val);
                        return;
                    }
                }
            }
        } else {
            _rootNode = new TreeNode();
            _rootNode.setValue(val);
//            System.out.println("inserted "+val);
        }
    }

    /**
     * method to search for a value in the BST
     * Time complexity : O(log n) |  O(h)
     * Worst case      : O(n)
     * @param val
     */
    public TreeNode lookup(int val) {
        TreeNode node = _rootNode;
        while(node != null) {
            if(val == node.getValue()) {
                return node;
            } else if (val < node.getValue()){
                node = node.getLeft();
            } else if (val > node.getValue()) {
                node = node.getRight();
            }
        }
        return null;
    }

    /**
     * This method is used to remove a node from the BST
     * Case 1: where the node to be removed is a leaf node
     * The node is simply removed and needs no adjustment to the rest of the tree
     * Ex:                    25
     *                 23            27
     *             17      19    26      45 -----------> 45 to be removed
     *
     * Case 2: : where the node to be removed has a child node and the child node has a left leaf node
     * Ex:                    25
     *                 23            27       --------> 27 to be removed
     *             17      19    26      45
     *
     * Case 3 : where the node to be removed has a child node that doesn't have a left leaf node
     * Ex:                    25
     *                 23            27       --------> 27 to be removed
     *             17      19            45
     *
     * @param val
     * @return
     */

    public boolean remove(int val) {
        TreeNode node = this._rootNode;
        TreeNode previous = node;
        while (node != null) {
            if(node.getValue() == val) {
                if(node.getRight() == null && node.getLeft() == null) { // Case 1
                    if(previous.getLeft() != null && val == previous.getLeft().getValue()) { // since we don't know if the node we are removing is to the left or right of its parent node
                        previous.setLeft(null);
                        return true;
                    } else if (previous.getRight() !=null && val == previous.getRight().getValue()) {
                        previous.setRight(null);
                        return true;
                    }
                    return false;
                } else if (node.getRight() != null) {
                    // Here we need to loop through until we find the leftmost node
                    TreeNode leftNode = node.getRight().getLeft();
                    TreeNode prev = leftNode;
                    if ((node.getLeft() == null && node.getRight() != null) ||
                            (node.getRight().getLeft() == null)) { // Case 3
                        if(val == _rootNode.getValue()) { // where the node to be removed is the root node
                            node.getRight().setLeft(node.getLeft());
                            _rootNode = node.getRight();
                            node.setRight(null);
                            return true;
                        } else if(val < previous.getValue()) {
                            previous.setLeft(node.getRight());
                            return true;
                        } else if (val > previous.getValue()) {
                            previous.setRight(node.getRight());
                            return true;
                        }
                        return false;
                    } else if(leftNode != null) { // Case 2
                        while(leftNode.getLeft() != null) {
                            prev = leftNode;
                            leftNode = leftNode.getLeft();
                        }
                        if(leftNode.getValue() != prev.getValue()) {
                            prev.setLeft(null);
                        } else {
                            node.getRight().setLeft(null);
                        }
                        leftNode.setLeft(node.getLeft());
                        leftNode.setRight(node.getRight());
                        if(node.getValue() == _rootNode.getValue()) {
                            _rootNode = leftNode;
                        }
                        node.setLeft(null);
                        node.setRight(null);
                        node = null;
                        return true;
                    }
                } else if(node.getLeft() != null && node.getRight() == null) {
                    System.out.println("here?");
                    if(node.getValue() == _rootNode.getValue()) {
                        TreeNode root = _rootNode;
                        _rootNode = node.getLeft();
                        root.setLeft(null);
                        return true;
                    }
                }
            } else if (val < node.getValue()) {
                if(node.getLeft() != null) {
                    previous = node;
                    node = node.getLeft();
                } else {
                    return false;
                }
            } else if (val > node.getValue()) {
                if(node.getRight() != null) {
                    previous = node;
                    node = node.getRight();
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * //       9
     * //   4       20
     * // 1  6    15  170
     * BFS -> [9, 4, 20, 1, 6, 15, 170]
     *
     */
    public void breadthFirstSearch() {
        TreeNode currentNode = this._rootNode;
        List<TreeNode> queue = new LinkedList<>();
        List<Integer> nodes = new ArrayList<>();
        queue.add(currentNode);
        while(!queue.isEmpty()) {
            currentNode = queue.remove(0);
            System.out.println("---->"+currentNode.getValue());
            nodes.add(currentNode.getValue());
            if(currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if(currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    /**
     * //       9
     * //   4       20
     * // 1  6    15  170
     * BFS -> [9, 4, 20, 1, 6, 15, 170]
     *
     * This method uses recursion to order the values by breadth first search approach
     *
     * @param queue : queue of nodes that dtermine the traversal order
     * @param vals : values of these nodes ordered by breadth
     * @return list of ordered values
     */
    public List<Integer> breadthFirstSearchRecursive(List<TreeNode> queue, List<Integer> vals) {
        if(queue.size() != 0) {
            TreeNode currentNode = queue.remove(0);
            System.out.println("!---->"+currentNode.getValue());
            vals.add(currentNode.getValue());
            if(currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if(currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        } else {
            return vals;
        }

        return breadthFirstSearchRecursive(queue, vals);
    }

    /**
     * //       9
     * //   4       20
     * // 1  6    15  170
     * DFS InOrder -> [1, 4, 6, 9, 15, 20, 170]
     *
     * This method uses recursion to order the values in the DFS in order approach
     *
     * @param node : node under test
     * @param vals : values of each of the nodes ordered as DFSInOrder
     * @return
     */
    public List<Integer> DFSInOrder(TreeNode node, List<Integer> vals) {

        System.out.println("DFS Inorder -->"+node.getValue());
        if(node.getLeft() != null) {
            DFSInOrder(node.getLeft(), vals);
        }

        vals.add(node.getValue());
        if(node.getRight() != null) {
            DFSInOrder(node.getRight(), vals);
        }

        return vals;
    }

    /**
     * //       9
     * //   4       20
     * // 1  6    15  170
     * DFS PreOrder -> [9, 4, 1, 6, 20, 15, 170]
     *
     * This method uses recursion to order the values in the DFS pre order approach
     *
     * @param node : node under test
     * @param vals : values of each of the nodes ordered as DFSpreOrder
     * @return
     */
    public List<Integer> DFSPreOrder(TreeNode node, List<Integer> vals) {

        vals.add(node.getValue());
        if(node.getLeft() != null) {
            DFSPreOrder(node.getLeft(), vals);
        }
        if(node.getRight() != null) {
            DFSPreOrder(node.getRight(), vals);
        }

        return vals;
    }

    /**
     * //       9
     * //   4       20
     * // 1  6    15  170
     * DFS PostOrder -> [1, 6, 4, 15, 170, 20, 9]
     *
     * This method uses recursion to order the values in the DFS post order approach
     *
     * @param node : node under test
     * @param vals : values of each of the nodes ordered as DFSPostOrder
     * @return
     */
    public List<Integer> DFSPostOrder(TreeNode node, List<Integer> vals) {

        if(node.getLeft() != null) {
            DFSPostOrder(node.getLeft(), vals);
        }

        if(node.getRight() != null) {
            DFSPostOrder(node.getRight(), vals);
        }
        vals.add(node.getValue());
        return vals;
    }

    /**
     * This method is used to determine if a given BST is valid using the BFS approach
     * @param bts : the BST to be validated
     * @return : false if invalid, true otherwise
     */
    public boolean ValidateBST(BinarySearchTree bts) {
        TreeNode currentNode = bts._rootNode;
        List<TreeNode> queue = new LinkedList<>();
        List<Integer> nodes = new ArrayList<>();
        queue.add(currentNode);
        int index = 0;
        while(!queue.isEmpty()) {
            currentNode = queue.remove(0);
            System.out.println("---->"+currentNode.getValue());

            nodes.add(currentNode.getValue());
            if(currentNode.getLeft() != null) {
                if(currentNode.getLeft().getValue() < currentNode.getValue()) {
                    queue.add(currentNode.getLeft());
                } else {
                    return false;
                }

            }

            if(currentNode.getRight() != null) {
                if(currentNode.getRight().getValue() > currentNode.getValue()) {
                    queue.add(currentNode.getRight());
                } else {
                    return false;
                }
            }
            index++;
        }
        return true;
    }


    public TreeNode get_rootNode() {
        return _rootNode;
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "_rootNode=" + _rootNode +
                '}';
    }
}

class TreeNode {

    TreeNode left;
    TreeNode right;
    int value;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return value == treeNode.value &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, value);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "left=" + left +
                ", right=" + right +
                ", value=" + value +
                '}';
    }
}

class TestMyBST {
    public static void main(String[] args) {

//        BinarySearchTree tree = new BinarySearchTree();
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(26);
//        tree.insert(27);
//        System.out.println(tree);
//        System.out.println("Does 26 Exists ? ");
//        if(tree.lookup(26) != null) {
//            System.out.println("Yes");
//        }
//       if( tree.remove(26) ) {
//           System.out.println("removed");
//       }
        BinarySearchTree tree = new BinarySearchTree();

//        Case 1:
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(27);
//        System.out.println("removed ?"+tree.remove(5));
//        System.out.println(tree);
//        Case 2:
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(27);
//        tree.insert(26);
//        System.out.println(tree);
//        System.out.println("removed ?"+tree.remove(25));
//        Case 2 Part 2
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(40);
//        tree.insert(39);
//        tree.insert(38);
//        tree.insert(37);
//        tree.insert(36);
//        Case 2 part 3
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(35);
//        tree.insert(50);
//        tree.insert(49);
//        tree.insert(48);
//        tree.insert(47);
//        Case 2 part 4
//        tree.insert(25);
//        tree.insert(5);
//        System.out.println(tree);
//        System.out.println("removed ?"+tree.remove(25));
//        System.out.println(tree);
//        Case 3
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(27);
//        tree.insert(28);
//        System.out.println(tree);
//        System.out.println("removed ?"+tree.remove(25));
//        System.out.println(tree);
//        Case 3 : part 2
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(30);
//        tree.insert(26);
//        tree.insert(24);
//        tree.insert(28);
//        tree.insert(31);
//        System.out.println(tree);
//        List<TreeNode> queue = new ArrayList<>();
//        queue.add(tree.get_rootNode());
//        tree.breadthFirstSearchRecursive(queue, new ArrayList<>());
//        System.out.println("removed ?"+tree.remove(26));
//        System.out.println(tree);

        tree.insert(9);
        tree.insert(4);
        tree.insert(20);
        tree.insert(1);
        tree.insert(6);
        tree.insert(15);
        tree.insert(170);
        System.out.println(tree);
//        List<Integer> vals = new ArrayList<Integer>();
//        tree.DFSInOrder(tree.get_rootNode(), vals);
//        tree.DFSPreOrder(tree.get_rootNode(), vals);
//        tree.DFSPostOrder(tree.get_rootNode(), vals);
        System.out.println("Valid Tree? "+tree.ValidateBST(tree));

    }
}
//              25
//    5                   30
//        24          26      31
//                        28