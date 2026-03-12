import java.util.*;
 
public class Traversal {
 
    // BST node
    static class Node {
        int value;
        Node left, right;

        Node(int val) {
        this.value = val;
        left = right = null;
        }
    }

    static Node root = null;
    static ArrayList<Integer> searchedNodes = new ArrayList<>();
    static ArrayList<Integer> searchedIndexes = new ArrayList<>();

    // INSERT node into BST
    public static Node insert(Node node, int value) {
        if (node == null) return new Node(value);

        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);
        else
            System.out.println("Duplicate node " + value + " discarded.");
        return node;
    }

    // DELETE node from BST using predecessor/successor
    public static Node delete(Node node, int value) {
        if (node == null) {
            System.out.println("Value not found.");
        return null;
        }

        if (value < node.value)
            node.left = delete(node.left, value);
        else if (value > node.value)
            node.right = delete(node.right, value);
        else {
            // Node found
            if (node.left == null && node.right == null) {
            // Terminal node
            return null;
            } else if (node.left != null) {
                // Non-terminal, has left child -> use predecessor
                Node pred = node.left;
                while (pred.right != null)
                    pred = pred.right;
                node.value = pred.value;
                node.left = delete(node.left, pred.value);
            } else if (node.right != null) {
                // Non-terminal, no left child -> use successor
                Node succ = node.right;
                while (succ.left != null)
                    succ = succ.left;
                node.value = succ.value;
                node.right = delete(node.right, succ.value);
            }
        }
        return node;
    }

    // BUILD ARRAY representation of BST
    static int[] buildArray(Node node) {
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

    static void fillArray(Node node, int[] arr, int index) {
        if (node == null || index >= arr.length) return;
            arr[index] = node.value;
        if (index > maxIndex) maxIndex = index;
            fillArray(node.left, arr, 2 * index + 1);
        fillArray(node.right, arr, 2 * index + 2);
    }

    // FIND INDEX in array
    public static int findIndex(int value, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) return i;
        }
        return -1;
    }

    // PRINT TREE ARRAY
    public static void printTree(int[] arr) {
        System.out.print("TREE = {");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("}");
    }

    static int preIndex = 0;
    static int postIndex = 0;
    static int inIndex = 0;

    public static int[] preOrder(Node node){
        int[] arr = new int[100];
        preIndex = 0;
        preOrderHelper(node, arr);
        return Arrays.copyOf(arr, preIndex);
    }

    public static void preOrderHelper(Node node, int[] arr){
        if(node == null) return;

        arr[preIndex++] = node.value;
        preOrderHelper(node.left, arr);
        preOrderHelper(node.right, arr);
    }

    public static int[] postOrder(Node node){
        int[] arr = new int[100];
        postIndex = 0;
        postOrderHelper(node, arr);
        return Arrays.copyOf(arr, postIndex);
    }

    public static void postOrderHelper(Node node, int[] arr){
        if(node == null) return;

        if(node.left != null){
            postOrderHelper(node.left, arr);
        } if(node.right != null){
            postOrderHelper(node.right, arr);
        }
        arr[postIndex++] = node.value;
    }

    public static int[] inOrder(Node node){
        int[] arr = new int[100];
        inIndex = 0;
        inOrderHelper(node, arr);
        return Arrays.copyOf(arr, postIndex);
    }

    public static void inOrderHelper(Node node, int[] arr){
        if(node == null) return;

        if(node.left != null){
            inOrderHelper(node.left, arr);
        } 
        
        arr[inIndex++] = node.value;

        if(node.right != null){
            inOrderHelper(node.right, arr);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. insert");
            System.out.println("2. delete");
            System.out.println("3. end");
            System.out.print("enter your choice: ");
            String choice = sc.nextLine();

            // INPUT
            if (choice.equals("insert")) {
                while (true) {
                    System.out.print("\ninput nodes(if many, separated by comma, type e to exit): ");
                    String input = sc.nextLine();
                        if (input.equalsIgnoreCase("e")) break;
                    try {
                        String[] nums = input.split(",");
                        for (String n : nums) {
                            int value = Integer.parseInt(n.trim());
                            root = insert(root, value);
                        }
                        int[] arr = buildArray(root);
                        printTree(arr);
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                    }
                }
            }

            // DELETE
            else if (choice.equals("delete")) {
                while (true) {
                    System.out.print("\ndelete node(if many, separated by comma, type e to exit): ");
                    String input = sc.nextLine();
                    if (input.equalsIgnoreCase("e")) break;
                    try {
                        String[] nums = input.split(",");
                        for (String n : nums) {
                            int value = Integer.parseInt(n.trim());
                            root = delete(root, value);
                        }
                        int[] arr = buildArray(root);
                        printTree(arr);
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                    }
                }
            }

            // PRINT + SEARCH
            else if (choice.equals("end")) {
                int[] arr = buildArray(root);
                printTree(arr);
                System.out.print("PREORDER: ");
                System.out.println(Arrays.toString(preOrder(root)));
                System.out.print("POSTORDER: ");
                System.out.println(Arrays.toString(postOrder(root)));
                System.out.print("INORDER: ");
                System.out.println(Arrays.toString(inOrder(root)));
                    
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
