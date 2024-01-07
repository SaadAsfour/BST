package BST;

public class Main {
    class Node {
        int key;
        Node left, right;
    
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }
    
    class BinarySearchTree {
        Node root;
    
        // Constructor for BST
        BinarySearchTree() {
            root = null;
        }
    
        // Insert a key in the BST
        void insert(int key) {
            root = insertRec(root, key);
        }
    
        private Node insertRec(Node root, int key) {
            if (root == null) {
                root = new Node(key);
                return root;
            }
            if (key < root.key)
                root.left = insertRec(root.left, key);
            else if (key > root.key)
                root.right = insertRec(root.right, key);
            return root;
        }
    
        // Remove a key from the BST
        void remove(int key) {
            root = removeRec(root, key);
        }
    
        private Node removeRec(Node root, int key) {
            if (root == null) return root;
            if (key < root.key)
                root.left = removeRec(root.left, key);
            else if (key > root.key)
                root.right = removeRec(root.right, key);
            else {
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;
                root.key = minValue(root.right);
                root.right = removeRec(root.right, root.key);
            }
            return root;
        }
    
        private int minValue(Node root) {
            int minv = root.key;
            while (root.left != null) {
                minv = root.left.key;
                root = root.left;
            }
            return minv;
        }
    
        // Search for a key in the BST
        boolean search(int key) {
            return searchRec(root, key) != null;
        }
    
        private Node searchRec(Node root, int key) {
            if (root == null || root.key == key)
                return root;
            return key < root.key ? searchRec(root.left, key) : searchRec(root.right, key);
        }
    
        // InOrder traversal of BST
        void inOrderTraversal() {
            inOrderRec(root);
            System.out.println();
        }
    
        private void inOrderRec(Node root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.print(root.key + " ");
                inOrderRec(root.right);
            }
        }
    
        // Utility function for measuring CPU time for search
        long measureSearchTime(int key, int repetitions) {
            long startTime = System.nanoTime();
            for (int i = 0; i < repetitions; i++) {
                search(key);
            }
            return System.nanoTime() - startTime;
        }
    }
    
    
    public class Main {
        public static void main(String[] args) {
            BinarySearchTree bst = new BinarySearchTree();
            
            // Insert elements 1 to 15
            for (int i = 1; i <= 15; i++) {
                bst.insert(i);
            }
            
            // Show all keys using InOrder traversal
            System.out.println("InOrder traversal of BST:");
            bst.inOrderTraversal();
    
            long timeFor1 = bst.measureSearchTime(1, 100000);
            long timeFor15 = bst.measureSearchTime(15, 100000);
            System.out.println("Time taken for searching 1: " + timeFor1 + " nanoseconds");
            System.out.println("Time taken for searching 15: " + timeFor15 + " nanoseconds");
    
    
            bst.remove(5);
            System.out.println("InOrder traversal after removing 5:");
            bst.inOrderTraversal();
            
            bst.remove(15);
            System.out.println("InOrder traversal after removing 15:");
            bst.inOrderTraversal();
            
            bst.remove(1);
            System.out.println("InOrder traversal after removing 1:");
            bst.inOrderTraversal();
            
            bst.insert(2);
            System.out.println("InOrder traversal after inserting 2:");
            bst.inOrderTraversal();
    
            BinarySearchTree newBST = new BinarySearchTree();
            int[] keys = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
            for (int key : keys) {
                newBST.insert(key);
            }
    
            // Show InOrder traversal of new tree
            System.out.println("InOrder traversal of new BST:");
            newBST.inOrderTraversal();
    
            // Measure search times in new tree
            long newTimeFor1 = newBST.measureSearchTime(1, 100000);
            long newTimeFor15 = newBST.measureSearchTime(15, 100000);
            System.out.println("New tree - Time taken for searching 1: " + newTimeFor1 + " nanoseconds");
            System.out.println("New tree - Time taken for searching 15: " + newTimeFor15 + " nanoseconds");
    
            System.out.println("Comparison of CPU times:");
            System.out.println("Old BST - Search 1: " + timeFor1 + ", Search 15: " + timeFor15);
            System.out.println("New BST - Search 1: " + newTimeFor1 + ", Search 15: " + newTimeFor15);
            System.out.println("Explanation: In the initial BST, 1 and 15 are at the extremes, leading to longer search times. In the new BST, the tree is more balanced, reducing the search times for these extremes.");
    
            // Remove key 8 from new tree and show InOrder traversal
            newBST.remove(8);
            System.out.println("InOrder traversal of new BST after removing 8:");
            newBST.inOrderTraversal();
        }
    }
}
