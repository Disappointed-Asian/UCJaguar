package cc4.bst;
import java.util.*;

public class BST {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String treeSize;
        String start;
        
        //Edge Case where input is less than 1
        while(true){
            System.out.print("Input tree size: ");
            treeSize = sc.nextLine();
            
            if(isValidInput(treeSize)){
                break;
            }
        }
        
        while(true){
            System.out.print("Input root node: ");
            start = sc.nextLine();
            
            if(isValidInput(start)){
                break;
            }
        }
        
        //Create the BST
        int[] tree = new int[Integer.parseInt(treeSize)];
        tree[0] = Integer.parseInt(start);
        
        int index = 1;
        while(index < Integer.parseInt(treeSize)){
            tree[index] = tree[index - 1] + 1;
            index++;
        }
        
        System.out.println(Arrays.toString(tree));
        
        String targetElement;
        
        //Suspicious call. Fix this.
        while(true){
            System.out.print("Choose a number from the tree: ");
            targetElement = sc.nextLine();
            int treeElement = Integer.parseInt(targetElement);
            if(isValidInput(targetElement)){
                if(Arrays.asList(tree).contains(treeElement)){
                    //New root node
                    break;
                }
                else{
                    System.out.println("Invalid");
                }
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    static boolean isValidInput(String input){
        for(char c: input.toCharArray()){
            if(!Character.isDigit(c)){
                System.out.println("Not a valid input.");
                return false;
            }
        }
        return true;
    }
}
