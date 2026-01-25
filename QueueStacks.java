import java.util.*;

public class QueueStacks{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Queues or Stacks?");
        String userInput = sc.nextLine();

        System.out.print("Enter Array Size(n): ");
        int arraySize = sc.nextInt();

        int[] arr = new int[arraySize];

        //For Stacks
        while(true){
            if(userInput.equalsIgnoreCase("stacks")){
                System.out.println("\nPush or Pop?");
                String pushPop = sc.nextLine();
                if(pushPop.equalsIgnoreCase("push")){
                    push();
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                } else if(pushPop.equalsIgnoreCase("pop")){
                    pop();
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                }
            }
        }

        //For Queues
        while(true){
            if(userInput.equalsIgnoreCase("queues")){
                System.out.println("\nEnqueue or Dequeue?");
                String enqDeq = sc.nextLine();
                if(enqDeq.equalsIgnoreCase("enqueue")){
                    enqueue();
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                } else if(enqDeq.equalsIgnoreCase("dequeue")){
                    dequeue();
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    static void push(){
        
    }

    static void pop(){

    }

    static void enqueue(){

    }

    static void dequeue(){

    }
}
