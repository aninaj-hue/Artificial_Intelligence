package homework;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    public String name;

    // Every node has a list of the roads leading to nearby cities.
    public List<Edge> adjacencyList = new ArrayList<Edge>();

    // variable used to print the optimal path (found by A* algorithm)
    // on which the two friends meet in the shortest time
    public Node parent;

    // variable used in BellmanFord algorithm to determine the minimum cost of the path
    // from one of the friends' position to all the other nodes.
    public int cost = 999;

    // cost so far to reach the destination node
    public int g_cost = 999;

    // total estimated cost of path through current node
    public int f_cost = 999;

    // estimated cost from this node to destination
    public int h_cost;

    // we consider the heuristic function as h_cost = cost - cost/4;
    public void setHCost()
    {
        h_cost = cost - cost/4;
    }

    public Node()
    {

    }

    public Node(String name)
    {
        this.name = name;
    }

    // Function to build the "roads" between the cities.
    public void addNeighbour(Node neighbour, int cost)
    {
        Edge edge = new Edge(neighbour,cost);
        adjacencyList.add(edge);
    }

    public String toString()
    {
        return name;
    }
}
