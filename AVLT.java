package org.yourcompany.AVL;

import java.util.Arrays;
import java.util.Scanner;
class AVLTree
{
    int[] buildArray(Node node) {
        int size = 100; // maximum array size
        int[] arr = new int[size];
        Arrays.fill(arr, 0); // empty nodes = 0
        maxIndex = 0;
        fillArray(node, arr, 0);
        int k = (int) (Math.log(maxIndex + 1) / Math.log(2)) + 1;
        int finalSize = (int) Math.pow(2, k) - 1;
        return Arrays.copyOf(arr, finalSize);
    }

    static int maxIndex = 0;

    void fillArray(Node node, int[] arr, int index) {
        if (node == null || index >= arr.length) return;
            arr[index] = node.value;
        if (index > maxIndex) maxIndex = index;
            fillArray(node.left, arr, 2 * index + 1);
        fillArray(node.right, arr, 2 * index + 2);
    }

    // FIND INDEX in array
    public int findIndex(int value, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    // PRINT TREE ARRAY
    public void printTree(int[] arr) {
        System.out.print("TREE = {");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("}");
    }
    // NODE structure
    class Node
    {
        int value;
        int height;
        Node left;
        Node right;

        public Node(int value)
        {
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    //returns the height of the node
    int Height(Node key)
    {
        if (key == null)
           return 0;

        else
            return key.height;
    }


    // Balance computes the balance factor of the node
    int Balance(Node key)
    {
        if (key == null)
           return 0;

        else
            return ( Height(key.right) - Height(key.left) );
    }


    // updateHeight updates the height of the node
    void updateHeight(Node key)
    {
        int l = Height(key.left);
        int r = Height(key.right);

        key.height = Math.max(l , r) + 1;
    }

    Node rotateLeft(Node x)
    {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    Node rotateRight(Node y)
    {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // balanceTree balances the tree using rotations after an insertion or deletion
    Node balanceTree(Node root)
    {
        updateHeight(root);

        int balance = Balance(root);

        if (balance > 1) //R
        {
            if (Balance(root.right) < 0)//RL
            {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }

            else //RR
                return rotateLeft(root);
        }

        if (balance < -1)//L
        {
            if (Balance(root.left) > 0)//LR
            {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
            else//LL
                return rotateRight(root);
        }

        return root;
    }

    Node Root;


    Node BSTInsert(Node root, int key)
    {
        // Performs normal BST insertion
        if (root == null)
            return new Node(key);

        else if (key < root.value)
            root.left = BSTInsert(root.left, key);

        else
            root.right = BSTInsert(root.right, key);

        // Balances the tree after BST Insertion
        return balanceTree(root);
    }

    public Node delete(Node node, int value) {
        if (node == null) {
            System.out.println("Value not found.");
            return null;
        }

        // 🔹 STEP 1: Normal BST deletion
        if (value < node.value)
            node.left = delete(node.left, value);

        else if (value > node.value)
            node.right = delete(node.right, value);

        else {
            // Node found
            if (node.left == null && node.right == null) {
                return null;
            } 
            else if (node.left != null) {
                // Use predecessor
                Node pred = node.left;
                while (pred.right != null)
                    pred = pred.right;

                node.value = pred.value;
                node.left = delete(node.left, pred.value);
            } 
            else {
                // Use successor
                Node succ = node.right;
                while (succ.left != null)
                    succ = succ.left;

                node.value = succ.value;
                node.right = delete(node.right, succ.value);
            }
        }

        
        updateHeight(node);

        
        int balance = Balance(node);

        if (balance < -1) {
            if (Balance(node.left) > 0) { // LR
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node); // LL
        }

        // Right heavy
        if (balance > 1) {
            if (Balance(node.right) < 0) { // RL
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node); // RR
        }

        return node;
    }

    // findNode is used to search for a particular value given the root
    Node findNode(Node root, int key)
    {
        if (root == null || key==root.value)
            return root;

        if (key < root.value)
            return findNode(root.left, key);

        else
            return findNode(root.right, key);
    }

    // Utility function for insertion of node
    void add(int key)
    {
        if (findNode(Root , key) == null)
        {
            Root = BSTInsert(Root , key);
            System.out.println("Insertion successful");
        }

        else
            System.out.println("\nKey with the entered value already exists in the tree");
    }

    int search(int key)
    {
        if(findNode(Root, key) == null)
            return 0;
        else
            return 1;
    }

    void InOrder(Node root)
    {
        if(root == null)
        {
            System.out.println("\nNo nodes in the tree");
            return;
        }

        if(root.left != null)
            InOrder(root.left);
        System.out.print(root.value + " ");
        if(root.right != null)
            InOrder(root.right);

    }

    void PreOrder(Node root)
    {
        if(root == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }

        System.out.print(root.value + " ");
        if(root.left != null)
            PreOrder(root.left);
        if(root.right != null)
            PreOrder(root.right);

    }

    void PostOrder(Node key)
    {
        if(key == null)
        {
            System.out.println("No nodes in the tree");
            return;
        }


        if(key.left != null)
            PostOrder(key.left);
        if(key.right != null)
            PostOrder(key.right);
        System.out.print(key.value + " ");

    }

}

public class AVLT
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        while(true)
        {
            System.out.println("\n\n1. Insert\n2. Delete\n3. Search\n4. Exit");
            int choice = 0;
            while(true){
                try{
                    choice = scan.nextInt();
                    break;
                } catch(Exception e){
                    System.out.println("========INVALID INPUT========");
                    scan.next();
                }
            }
            if(choice == 4) {
                System.out.println("\n===============================================");
                tree.printTree(tree.buildArray(tree.Root));
                System.out.println("\nPreOrder Traversal :");
                tree.PreOrder(tree.Root);
                System.out.println("\nInOrder Traversal :");
                tree.InOrder(tree.Root);
                System.out.println("\nPostOrder Traversal :");
                tree.PostOrder(tree.Root);
                break;
            }
            switch(choice) {
                case 1:
                {
                    System.out.println("Enter the elements to add and enter -999 to stop:");
                    while(scan.hasNext()) {
                        int temp = scan.nextInt();
                        if(temp == -999)
                            break;
                        tree.add(temp);
                    }
                    System.out.println("\nInOrder Traversal :");
                    tree.InOrder(tree.Root);
                    System.out.println("\nPreOrder Traversal :");
                    tree.PreOrder(tree.Root);
                    break;
                }

                case 2:
                {
                    System.out.println("Enter the element to be deleted:");
                    int temp = scan.nextInt();
                    tree.delete(tree.Root, temp);
                    System.out.println("\nInOrder Traversal :");
                    tree.InOrder(tree.Root);
                    System.out.println("\nPreOrder Traversal :");
                    tree.PreOrder(tree.Root);
                    break;
                }

                case 3:
                {
                    System.out.println("Enter the element to be searched:");
                    int temp = scan.nextInt();
                    int c = tree.search(temp);
                    if(c==0)
                        System.out.println("\nKey not found");
                    else
                        System.out.println(temp + "found");
                }

            }
        }
    }
}
