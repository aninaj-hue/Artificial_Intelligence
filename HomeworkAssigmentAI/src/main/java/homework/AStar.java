package homework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar
{
    public List<Node> AStarSearch(Node source, Node destination, List<Node> nodeList, BufferedWriter writer)
    {
        // List in which we build the optimal path between the friends, in which they meet in minimum time.
        List<Node> path = new ArrayList<Node>();

        // Apply the heuristic function to set the h_cost of every node
        for(int i=0;i<nodeList.size();i++)
        {
            nodeList.get(i).setHCost();
        }

        // Mark the source node as starting point of A* ( source node is represented by one of the friends' position
        // and destination node is represented by the other one
        source.parent=null;
        source.g_cost = 0;
        source.f_cost = source.h_cost;

        // We consider a set in each we keep track of the visited nodes
        Set<Node> explored = new HashSet<Node>();

        // We use a priority queue in which the lower the f_cost is the higher the node has priority.
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new Comparator<Node>()
        {
            public int compare(Node node1, Node node2)
            {
                return Integer.compare(node1.f_cost, node2.f_cost);
            }
        });

        // First of all we add the source node in the priority queue.
        frontier.add(source);

        // Variable to check when the node extracted from priority queue is actually the destination node.
        boolean found = false;

        int numberOfSteps=0;

        while((!frontier.isEmpty()) && (!found))
        {
            numberOfSteps++;
            // get the node with minimum f_score
            Node current = frontier.poll();

            // We extract the node with minimum f_cost and add it to the visited set.
            explored.add(current);

            try {
                writer.newLine();
                writer.write("Current node: " + current.name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // if we have found the destination then we stop searching
            if(current.name.equals(destination.name))
            {
                found=true;
            }

            // iterate through all the neighbours of the current extracted node
            // and calculate the new f cost for them.
            for(Edge edge : current.adjacencyList)
            {
                Node neighbour = edge.target;
                int edgeCost = edge.cost;

                int newGCost = current.g_cost + edgeCost;
                int newFCost = neighbour.h_cost + newGCost;

                // if neighbour node has been evaluated and the newer f_score is higher, then skip
                if((explored.contains(neighbour)) && (newFCost >= neighbour.f_cost))
                {
                    continue;
                }
                // else if neighbour node is not in queue or newer f_score is lower
                else if((!frontier.contains(neighbour)) || (newFCost < neighbour.f_cost))
                {
                    neighbour.parent = current;
                    neighbour.g_cost = newGCost;
                    neighbour.f_cost = newFCost;

                    // If statement to make sure we don't have 2 identical nodes in the priority queue
                    if(frontier.contains(neighbour))
                    {
                        frontier.remove(neighbour);
                    }

                    frontier.add(neighbour);
                }
            }
        }
        // To reconstruct the path we begin from the destination node and we use the parent attribute
        // Since the while loop won't add the source node in the path because its parent is null we have to add it after the loop
        Node lastNodeOfPath = destination;
        while(lastNodeOfPath.parent!=null)
        {
            path.add(lastNodeOfPath);
            lastNodeOfPath = lastNodeOfPath.parent;
        }
        path.add(source);
        try {
            writer.newLine();
            writer.write("Number of steps: " + numberOfSteps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
