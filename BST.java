import java.util.*;

public class BST {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String treeSize;
        String start;
        
        //Tree Size
        while(true){
            System.out.print("Input size of array: ");
            treeSize = sc.nextLine();
            
            if(isValidInput(treeSize)){
                break;
            }
        }
        
        //Start
        while(true){
            System.out.print("Input start of array: ");
            start = sc.nextLine();
            
            if(isValidInput(start)){
                break;
            }
        }
        
        //Create the array of numbers to make the tree
        int[] tree = new int[Integer.parseInt(treeSize)];
        tree[0] = Integer.parseInt(start);
        
        int index = 1;
        while(index < Integer.parseInt(treeSize)){
            tree[index] = tree[index - 1] + 1;
            index++;
        }
        
        System.out.println(Arrays.toString(tree));
        
        //Create the BST
        int targetElement;
        int root;
        
        while(true){
            try{
                System.out.print("Choose a number from the tree to be the root node: ");
                targetElement = sc.nextInt();
                if(isPresent(tree, targetElement)){
                    root = targetElement;
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid.");
                sc.next();
            } 
        }

        Node rootNode = new Node(root);

        for(int i = 0; i < tree.length; i++){
            if(tree[i] != root){
                insertNode(tree, i, rootNode);
            }
        }

        System.out.print("Final Output: ");
        printLevelOrder(rootNode);
    }

    static void insertNode(int[] tree, int value, Node currentNode){
        if(tree[value]< currentNode.value){
            if(currentNode.leftChild == null){
                currentNode.leftChild = new Node(tree[value]);
            } else{
                insertNode(tree, value, currentNode.leftChild);
            }
        } else if(tree[value] > currentNode.value){
            if(currentNode.rightChild == null){
                currentNode.rightChild = new Node(tree[value]);
            } else{
                insertNode(tree, value, currentNode.rightChild);
            }
        }
    }
    
    static boolean isPresent(int[] tree, int targetElement){
        for(int element: tree){
            if(element==targetElement){
                return true;
            }
        }
        System.out.println("Invalid");
        return false;
    }
    
    static boolean isValidInput(String input){
        for(char c: input.toCharArray()){
            if(!Character.isDigit(c)){
                System.out.println("Not a valid input.");
                return false;
            }
        }
        if(Integer.parseInt(input) < 1){
            System.out.println("Not a valid input.");
            return false;
        }
        return true;
    }

    static int getHeight(Node node){
        if(node == null){
            return 0;
        }

        int leftHeight = getHeight(node.leftChild);
        int rightHeight = getHeight(node.rightChild);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static void printLevelOrder(Node root){
        int height = getHeight(root);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int totalNodes = (int)Math.pow(2, height) - 1;

        for(int i = 0; i < totalNodes; i++){
            Node current = queue.poll();

            if(current == null){
                System.out.print("0 ");
                queue.add(null);
                queue.add(null);
            } 
            else{
                System.out.print(current.value + " ");
                queue.add(current.leftChild);
                queue.add(current.rightChild);
            }
        }
    }
}

class Node{
    int value;
    Node leftChild = null;
    Node rightChild = null;

    Node(int value){
        this.value = value;
    }
}
