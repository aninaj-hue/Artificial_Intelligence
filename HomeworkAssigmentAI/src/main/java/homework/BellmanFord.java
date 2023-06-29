package homework;

import java.util.List;

public class BellmanFord
{
    public void bellmanFord(Node source,List<Node> nodeList)
    {
        // We set the cost to 0 to make sure the algorithm starts from the source Node
        source.cost = 0;

        for(int var = 1; var <= nodeList.size()-1; var++)
        {
            // We iterate through the nodes in the city
            for(int nodeListIterator = 0; nodeListIterator < nodeList.size(); nodeListIterator++)
            {
                // check all the edges leading to neighbours
                // if the cost of the neighbour is greater than the cost of the current node plus the weight of the edge
                // leading to the neighbour, then replace the current cost of the neighbour with the new sum.
                for(Edge e : nodeList.get(nodeListIterator).adjacencyList)
                {
                    Node neighbour = e.target;
                    int edgeCost = e.cost;
                    int newCost = nodeList.get(nodeListIterator).cost + edgeCost;
                    if(neighbour.cost > newCost)
                    {
                        neighbour.cost = newCost;
                    }
                }
            }
        }
    }
}
