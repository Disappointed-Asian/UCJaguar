/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.queuestacks;

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

        String[] arr = new String[arraySize];

        //For Stacks
       
        if(userInput.equalsIgnoreCase("stacks")){
            int pointer = -1;
            while(true){
                System.out.println("\nPush or Pop?");
                String pushPop = sc.nextLine();
                if(pushPop.equalsIgnoreCase("push")){
                    if(pointer < arr.length){
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
                    pop(arr, pointer);
                    pointer--;
                    System.out.println("Continue or End?");
                    String nextStep = sc.nextLine();
                    if(nextStep.equalsIgnoreCase("end")){
                        break;
                    }
                }
            }
        }

        //For Queues
        
        if(userInput.equalsIgnoreCase("queues")){
            while(true){
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
    //Methods !! Sp Iric isn't confuzzeled >.<
    static void push(String[] arr, int pointer){
        String elementValue = sc.nextLine();
        if(arr[pointer+1] == null){
            arr[pointer+1] = elementValue;
            return;
        }
    }

    static void pop(String[] arr, int pointer){
        if(pointer > -1){
            arr[pointer] = null;
            return;
        }else{
            System.out.println("Stack Underflow");
            return;
        }
    }

    static void enqueue(){

    }

    static void dequeue(){

    }
}
