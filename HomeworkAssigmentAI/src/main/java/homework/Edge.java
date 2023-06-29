package homework;


public class Edge
{
    // weight of the edge
    public final int cost;
    // "destination" of the road.
    public final Node target;

    public Edge(Node target, int cost)
    {
        this.target = target;
        this.cost = cost;
    }

    public String toString()
    {
        return target.name+" "+cost;
    }
}
