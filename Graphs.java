import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in the edges. (Type STOP to stop).");
        int[] num = new int[2];
        ArrayList<Vertex> graph = new ArrayList<>();

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

        for(Vertex vertex: graph)
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
            if(vertex.value == num[0]){
                if(!vertex.adj.contains(num[1]))
                    vertex.adj.add(num[1]);
            }

            if(vertex.value == num[1])
                if(!vertex.adj.contains(num[0]))
                    vertex.adj.add(num[0]);
        }
    }
}

class Vertex{
    int value;
    ArrayList<Integer> adj;
    Vertex(int value){
        this.value = value;
    }
}
