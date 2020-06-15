import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private static final int BLUE = 0;
    private static final int RED = 1;
    private static final int YELLOW = 2;
    private static final int GREEN = 3;

    static private int rocket;
    static private int lucy;
    static private int vertex;
    static private int end;
    static private Node[] nodes;
    static private int edges;
    static private Graph graph;

    public static void main(String[] args) {
        readMatrix();
        while (rocket != end || lucy != end) {
            int[] update = traverse(graph, rocket, lucy);
            if (update[0] != -1)
                rocket = update[0];
            if (update[1] != -1)
                lucy = update[1];
        }
    }

    private static void readMatrix() {
        try {
            File myObj = new File("input.txt");
            Scanner myReader = new Scanner(myObj);
            vertex = myReader.nextInt();
            nodes = new Node[vertex + 1];
            edges = myReader.nextInt();
            String data = "";
            for (int i = 1; i < vertex + 1; i++) {
                data = myReader.next();
                switch (data) {
                    case "B":
                        nodes[i] = new Node(i, BLUE);
                        break;
                    case "R":
                        nodes[i] = new Node(i, RED);
                        break;
                    case "Y":
                        nodes[i] = new Node(i, YELLOW);
                        break;
                    case "G":
                        nodes[i] = new Node(i, GREEN);
                        break;
                }
            }
            nodes[vertex] = new Node(vertex, 0); //GOAL VERTEX
            rocket = Integer.parseInt(data);
            lucy = myReader.nextInt();
            graph = new Graph(vertex, nodes);
            for (int k = 0; k < edges; k++) {
                int source = myReader.nextInt();
                int destination = myReader.nextInt();
                String c = myReader.next();
                if (!c.equals("")) {
                    switch (c) {
                        case "B":
                            graph.addEdge(nodes[source], nodes[destination], BLUE);
                            break;
                        case "R":
                            graph.addEdge(nodes[source], nodes[destination], RED);
                            break;
                        case "Y":
                            graph.addEdge(nodes[source], nodes[destination], YELLOW);
                            break;
                        case "G":
                            graph.addEdge(nodes[source], nodes[destination], GREEN);
                            break;
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    private static int[] traverse(Graph graph, int rocket, int lucy) {
        int[] ret = {-1, -1};
        ArrayList<Integer> r = graph.findAdjacent(rocket);
        ArrayList<Integer> l = graph.findAdjacent(lucy);
        Node wr = graph.whereWeCanGo(graph.getColor(lucy), rocket, r);
        Node wl = graph.whereWeCanGo(graph.getColor(rocket), lucy, l);
        if (wr.name != -1 || wl.name != -1) {
            if (wr.name != -1) {
                ret[0] = wr.name;
                System.out.println("R " + wr.name);
            }
            if (wl.name != -1) {
                ret[1] = wl.name;
                System.out.println("L " + wl.name);
            }
        } else {
            System.out.println("NO WAY");
            ret[0] = end;
            ret[1] = end;
        }
        return ret;
    }

}
