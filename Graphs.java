package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in the edges. (Type STOP to stop).");
        int[] num = new int[2];
        ArrayList<Vertex> graph = new ArrayList<>();

        //Creating the tree
        while(true){
            String userInput =  scan.next();

            if(userInput.equalsIgnoreCase("stop")){
                break;
            }

            String[] inputs = userInput.split(",");

            for(int i = 0; i < 2; i++){
                num[i] = Integer.parseInt(inputs[i]);
            }
            System.out.println("(" + userInput + ")");

            for(int value: num){
                createVertex(value, graph);
            }
            connectVertex(num, graph);
        }

        //Lowest or Highest Order Priority
        while(true){
            System.out.println("1. Lowest Order\n2. Highest Order");
            String userOrder = scan.nextLine();
            if(userOrder.equalsIgnoreCase("1")){
                for(Vertex vertex: graph)
                    vertex.adj.sort(Comparator.comparing(Vertex::getValue));
                break;
            } else if(userOrder.equalsIgnoreCase("2")){
                for(Vertex vertex: graph)
                    vertex.adj.sort(Comparator.comparing(Vertex::getValue).reversed());
                break;
            }
            System.out.println("Invalid input.");
        }

        Vertex root;
        //Identify root
        while (true){
            System.out.println("Enter root");
            int userRoot = scan.nextInt();
            root = getVertex(userRoot, graph);
            if(root == null){
                continue;
            }
            break;
        }

        ArrayList<Vertex> dfs = new ArrayList<>();
        dfs.add(root);
        createDFS(root, dfs, graph);

        for(Vertex vertex: dfs)
            System.out.println(vertex.value);

        scan.close();
    }

    static void createVertex(int value, ArrayList<Vertex> graph){

        System.out.println(value);

        for(Vertex vertex: graph)
            if(vertex.value == value) return;

        graph.add(new Vertex(value));
    }

    //Which connects to which
    static void connectVertex(int[] num, ArrayList<Vertex> graph){
        for(Vertex vertex: graph){
            if (vertex.adj == null)
                vertex.adj.add(getVertex(num[1], graph));

            if(vertex.value == num[0]){
                if(!vertex.adj.contains(getVertex(num[1], graph)))
                    vertex.adj.add(getVertex(num[1], graph));
            }

            if(vertex.value == num[1])
                if(!vertex.adj.contains(getVertex(num[0], graph)))
                    vertex.adj.add(getVertex(num[0], graph));
        }
    }

    static Vertex getVertex(int value, ArrayList<Vertex> graph){
        for(Vertex vertex: graph){
            if(vertex.value == value)
                return vertex;
        }
        System.out.println("--------Vertex does not exist.--------");
        return null;
    }

    static void createDFS(Vertex root, ArrayList<Vertex> dfs, ArrayList<Vertex> graph){
        for(Vertex vertex: root.adj){
            if(!dfs.contains(vertex)){
                dfs.add(vertex);
                createDFS(vertex, dfs, graph);
            }
        }
    }
}

class Vertex{
    int value;
    ArrayList<Vertex> adj = new ArrayList<>();
    Vertex(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
