import java.util.*;

public class QueueStacks{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
    
        String userInput = "";
        while(true){
            System.out.println("Queues or Stacks?");
            userInput = sc.nextLine();
            if(userInput.equalsIgnoreCase("stacks")|| userInput.equalsIgnoreCase("queues")){
                break;
            }
        } 

        System.out.print("Enter Array Size(n): ");
        int arraySize = sc.nextInt();
        sc.nextLine();

        String[] arr = new String[arraySize];

        //For Stacks
       
        if(userInput.equalsIgnoreCase("stacks")){
            int pointer = -1;
            while(true){
                System.out.println("\nPush or Pop?");
                String pushPop = sc.nextLine();
                if(pushPop.equalsIgnoreCase("push")){
                    if(pointer + 1 < arr.length){
                        System.out.print("-> ");
                        push(arr, pointer);
                        pointer++;
                        System.out.println("Continue or End?");
                        String nextStep = sc.nextLine();
                        if(nextStep.equalsIgnoreCase("end")){
                            break;
                        }
                    } else{
                        System.out.println("Stack Overflow");
                    }
                } else if(pushPop.equalsIgnoreCase("pop")){
                    if(pointer == -1){
                        System.out.println("Stack Underflow");
                    } else{
                        pop(arr, pointer);
                        pointer--;
                    }
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
   
                }
            }
            System.out.print("\nResult: ");
            System.out.print("[");
            int i = 0;
            for(String elem: arr){
                if(elem == null){
                    break;
                } else if(i<0){
                    System.out.print(", ");
                }
                System.out.print( elem );
                i--;
            }
            System.out.println("]");
        }

        //For Queues
        if(userInput.equalsIgnoreCase("queues")){
            int pointerFirst = 0;
            int pointerLast = 0;
            while(true){
                System.out.println("\nEnqueue or Dequeue?");
                String enqDeq = sc.nextLine();
                if(enqDeq.equalsIgnoreCase("enq")){
                    System.out.print("-> ");
                    if(pointerLast == arr.length){
                        pointerLast = 0;
                    }
                    enqueue(arr, pointerFirst, pointerLast);
                    if(arr[pointerLast]!=null){
                        pointerLast++;
                    }
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                } else if(enqDeq.equalsIgnoreCase("deq")){
                    dequeue(arr, pointerFirst, pointerFirst);
                    if(pointerFirst == arr.length){
                        pointerFirst = 0;
                    }
                    if(arr[pointerFirst] == null){
                        System.out.println("Queue Empty");
                    } else{
                        pointerFirst++;
                    }
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                }
            }
            System.out.print("\nResult: ");
            System.out.print("[");
            if(pointerFirst == pointerLast){
                for(int i = pointerLast; i < arr.length; i++){
                    if(i >= pointerLast + 1){
                        System.out.print(", ");
                    }
                    System.out.print( arr[i] );
                }
                for(int i = 0; i < pointerFirst; i ++){
                    System.out.print(", ");
                    System.out.print(arr[i]);
                }
            } else{
                for(int i = pointerFirst; i < arr.length; i++){
                    if(arr[i] == null){
                        break;
                    }
                    if(i >= pointerFirst + 1){
                        System.out.print(", ");
                    }
                    System.out.print( arr[i] );
                }

                for(int i = pointerLast; i <= pointerFirst; i++){
                    if(arr[i+1] == null){
                        break;
                    }
                    System.out.print(", ");
                    System.out.print(arr[i]);
                }
            }
            System.out.println("]");
        }
    }

    static void push(String[] arr, int pointer){
        String elementValue = sc.nextLine();
        if(arr[pointer+1] == null){
            arr[pointer+1] = elementValue;
        }
    }

    static void pop(String[] arr, int pointer){
            arr[pointer] = null;
    }

    static void enqueue(String[] arr, int pointerFirst, int pointerLast){
        String elementValue = sc.nextLine();
        if(arr[pointerLast] == null){
            arr[pointerLast] = elementValue;
        } else{
            System.out.println("Queue Full");
        }
    }

    static void dequeue(String[] arr, int pointerFirst, int pointerLast){
        if(arr[pointerFirst] != null){
            arr[pointerFirst] = null;
        } else{
            System.out.println("Empty");
        }
    }
}
