/**
 * Jiyao Chen
 * CS231 Spring
 * Project 9
 * Graph.java
 * maintains a list of vertices (Vertex objects)
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

// import Vertex.Direction;

public class Graph {

    private ArrayList<Vertex> vertices;

    public Graph() {
      vertices = new ArrayList<>();
    }

    /**
     * returns the number of vertices in the graph
     */
    public int vertexCount() {
        return vertices.size();
    }

    /**
     * adds v1 and v2 to the graph (if necessary)
     * adds an edge connecting v1 to v2 via @param dir and a second edge connecting v2 to v1 in the opposite direction
     * creating a bi-directional link.
     */
      public void addEdge(Vertex v1, Vertex.Direction dir, Vertex v2) {
         if (vertices.contains(v1) == false) {
             vertices.add(v1);
         }
         if (vertices.contains(v2) == false) {
             vertices.add(v2);
         }
 
         v1.connect(v2, dir);
         v2.connect(v1, Vertex.opposite(dir));
     }
    

    /**
     * implements a single-source shortest-path algorithm for the Graph
     * Dijkstra's algorithm.
     */
    public void shortestPath(Vertex v0) {
        //initialize
        for(Vertex entry : vertices) {
            entry.setCost(1000000000);
            entry.setMarked(false);
        }

        PriorityQueue<Vertex> q = new PriorityQueue<>(vertices.size(), new VertexComparator());

        v0.setCost(0);
        q.add(v0);

        while (q.isEmpty() != true) {
            Vertex v = q.remove();  //v is the lowest cost
            v.setMarked(true);
            for(Vertex w: v.getNeighbors()) {
                if(w.getMarked() != true && (v.getCost()+1) < w.getCost()) {
                    w.setCost(v.getCost()+1);
                    q.add(w);
                }
            }
        }

    }

    public static void main(String[] args) {
       Graph g = new Graph();
       Vertex test1 = new Vertex(1, 1);
       Vertex test2 = new Vertex(2, 2);
       Vertex test3 = new Vertex(3, 3);

       g.addEdge(test1, Vertex.Direction.SOUTH, test2);
       g.addEdge(test2, Vertex.Direction.WEST, test3);

       g.shortestPath(test1);
    } 
}