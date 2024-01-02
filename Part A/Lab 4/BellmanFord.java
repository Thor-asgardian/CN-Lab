import java.util.Scanner;


class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

public class BellmanFord {

    static void bellmanFord(int vertices, int source, Edge[] edges) {
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] = distance[edge.source] + edge.weight;
                }
            }
        }

          //Checking for negative edge cycle
        for (Edge edge : edges) {
            if (distance[edge.source] != Integer.MAX_VALUE && distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Graph contains a negative cycle!");
                return;
            }
        }

        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To vertex " + i + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();


        Edge[] edges = new Edge[edgesCount];
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < edgesCount; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            edges[i] = new Edge(src, dest, weight);
        }

        bellmanFord(vertices, source, edges);

        scanner.close();
    }
}


 OUTPUT

 Enter the number of vertices: 6
 Enter the source vertex: 0
 Enter the number of edges: 7
 Enter edges (source destination weight):
 0 1 5
 1 3 2
 1 2 1
 2 4 1
 3 5 2
 4 3 -1
 5 4 -3
 Graph contains a negative cycle!

 Enter the number of vertices: 6
 Enter the source vertex: 0
 Enter the number of edges: 9
 Enter edges (source destination weight):
 0 1 6
 0 2 4
 0 3 5
 1 4 -1
 2 1 -2
 2 4 3
 3 2 -2
 3 5 -1
 4 5 3
 Shortest distances from source vertex 0:
 To vertex 0: 0
 To vertex 1: 1
 To vertex 2: 3
 To vertex 3: 5
 To vertex 4: 0
 To vertex 5: 3
 OUTPUT

 Enter the number of vertices: 6
 Enter the source vertex: 0
 Enter the number of edges: 7
 Enter edges (source destination weight):
 0 1 5
 1 3 2
 1 2 1
 2 4 1
 3 5 2
 4 3 -1
 5 4 -3
 Graph contains a negative cycle!

 Enter the number of vertices: 6
 Enter the source vertex: 0
/*
 Enter the number of edges: 9
 Enter edges (source destination weight):
 0 1 6
 0 2 4
 0 3 5
 1 4 -1
 2 1 -2
 2 4 3
 3 2 -2
 3 5 -1
 4 5 3
 Shortest distances from source vertex 0:
 To vertex 0: 0
 To vertex 1: 1
 To vertex 2: 3
 To vertex 3: 5
 To vertex 4: 0
 To vertex 5: 3
*/
