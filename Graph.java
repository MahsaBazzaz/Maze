import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private int vertex;
    private int[][] matrix;
    private Node[] nodes;

    public Graph(int v, Node[] node) {
        this.vertex = v;
        this.nodes = new Node[v + 1];
        matrix = new int[v + 1][v + 1];
        for (int[] e : matrix) {
            Arrays.fill(e, -1);
        }
        System.arraycopy(node, 0, this.nodes, 0, node.length);
    }

    public void addEdge(Node source, Node destination, int color) {
        matrix[source.name][destination.name] = color;
    }

    public int getColor(int node) {
        if (node < nodes.length)
            return nodes[node].color;
        else return -1;
    }

    public ArrayList<Integer> findAdjacent(int i) {
        ArrayList<Integer> arr = new ArrayList();
        for (int j = 1; j < vertex + 1; j++) {
            if (matrix[i][j] != -1)
                arr.add(j);
        }
        return arr;
    }

    public Node whereWeCanGo(int color, int person, ArrayList<Integer> adjacent) {
        Node n = new Node(-1, -1);
        for (Integer node : adjacent) {
            if (matrix[person][node] == color) {
                n = new Node(nodes[node].name, nodes[node].color);
            }
        }
        return n;
    }
}
