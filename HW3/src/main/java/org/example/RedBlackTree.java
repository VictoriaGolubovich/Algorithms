package org.example;

class Node {
    int value;
    Node left, right, parent;
    boolean isRed;

    Node (int value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isRed = true;
    }
}
public class RedBlackTree {
    private Node root;

    public RedBlackTree() {
        this.root = null;
    }

    public void add (int value){
        root = add(root, value);
        root.isRed = false;
    }

    private Node add(Node node, int value){
        if (node == null){
            return new Node(value);
        }

        if (value < node.value){
            node.left = add(node.left, value);
            node.left.parent = node;
        } else if (value > node.value){
            node.right = add(node.right, value);
            node.right.parent = node;
        }

        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        return node;
    }

    private boolean isRed (Node node){
        if (node == null){
            return false;
        }
        return node.isRed;
    }

    private Node rotateLeft(Node node){
        Node x = node.right;
        node.right = x.left;

        if (x.left != null){
            x.left.parent = node;
        }

        x.parent = node.parent;

        if (node.parent == null){
            root = x;
        } else if (node == node.left){
            node.parent.left = x;
        } else {
            node.parent.right = x;
        }

        x.left = node;
        node.parent = x;
        x.isRed = true;
        return x;
    }

    private Node rotateRight (Node node){
        Node x = node.left;
        node.left = x.right;
        if (x.right != null){
            x.right.parent = node;
        }
        x.parent = node.parent;

        if (node.parent == null){
            root = x;
        } else if (node == node.parent.right) {
            node.parent.right = x;
        } else {
            node.parent.left = x;
        }
        x.right = node;
        node.parent = x;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    private void flipColors (Node node){
        node.isRed = !node.isRed;
        if (node.left != null){
            node.left.isRed = !node.left.isRed;
        }

        if (node.right != null){
            node.right.isRed = !node.right.isRed;
        }
    }

    public void printTree(){
        printTree(root, "", true);
    }

    private void printTree(Node node, String indent, boolean last){
        if (node != null){
            System.out.println(indent);
            if (last){
                System.out.println("R----");
                indent += "    ";
            } else {
                System.out.println("L----");
                indent +="|   ";
            }
            System.out.println(node.value + (node.isRed ? "(R)" : "(B)"));
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(15);
        tree.add(25);
        tree.add(5);

        System.out.println("Red-Black Tree: ");
        tree.printTree();
    }
}
