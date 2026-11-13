import java.util.*;

public class Traversal {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tree = new ArrayList<>();
        
        Node root = new Node(null);

        while(true){
            System.out.println("Choose Option");
            System.out.println("INSERT");
            System.out.println("DELETE");
            System.out.println("EXIT");

            String userInput = sc.nextLine();

            if(userInput.equalsIgnoreCase("insert")){
                String[] numbers = sc.nextLine().split(",");
                for(String number: numbers){
                    int node = 0;
                    try{
                        node = Integer.parseInt(number);
                    } catch(InputMismatchException e){
                        System.out.println("Removed \'" + number + "\'. Not a number.");
                    }
                    insert(root, number, tree);
                }
            } else if(userInput.equalsIgnoreCase("delete")){
                ;
            } else if(userInput.equalsIgnoreCase("exit")){
                break;
            } else {
                System.out.println("\n--------Invalid input.--------\n");
            }
        }

        for(String node: tree){
            System.out.println(node);
        }
    }
    
    //insert
    static void insert(Node root, String value, ArrayList<String> tree){
        if(root.value == null){
            root.value = value;
            tree.add(value);
            return;
        }
        tree.add(value);
    }
}

class Node{
    String value;
    Node leftChild = null;
    Node rightChild = null;

    Node(String value){
        this.value = value;
    }
}
