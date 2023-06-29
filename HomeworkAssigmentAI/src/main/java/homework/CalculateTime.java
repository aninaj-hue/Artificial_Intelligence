package homework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class CalculateTime
{
    // Function to calculate the minimum time in which the 2 friends will meet each other
    public void calculateTime(List<Node> path,BufferedWriter writer)
    {
        // If the size of the path is 2 it means the two friends will meet in either
        // the position of first friend or the position of second friend.
        // This means that one of the friends will hold his position while the other comes to him.
        // The time taken will be equal to the weight of the edge.
        if(path.size() == 2)
        {
            for(int i = 0; i < path.get(0).adjacencyList.size(); i++)
            {
                if(path.get(0).adjacencyList.get(i).target == path.get(1))
                {
                    try {
                        writer.newLine();
                        writer.write("The friends meet in " + path.get(0).name + " after " + path.get(0).adjacencyList.get(i).cost);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        // If the size of the path is 3 the two friends will meet in the "middle" city.
        // Time taken will be the maximum between the two edges traversed simultaneously by the two friends
        else if(path.size() == 3)
        {
            int edge1 = 0;
            int edge2 = 0;

            // Iterate through the neighbours of the node in which the first friend is.
            // Find the edge leading to the "middle" city to store the cost
            for(int i = 0; i < path.get(0).adjacencyList.size(); i++)
            {
                if(path.get(0).adjacencyList.get(i).target == path.get(1))
                {
                    edge1 = path.get(0).adjacencyList.get(i).cost;
                    break;
                }
            }

            // Iterate through the neighbours of the node in which the second friend is.
            // Find the edge leading to the "middle" city to store the cost
            for(int i = 0; i < path.get(2).adjacencyList.size(); i++)
            {
                if(path.get(2).adjacencyList.get(i).target == path.get(1))
                {
                    edge2 = path.get(2).adjacencyList.get(i).cost;
                    break;
                }
            }
            try {
                writer.newLine();
                writer.write("The friends meet in " + path.get(1).name + " after " + Math.max(edge1, edge2));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // If the path has an odd size, the friends move simultaneously towards the middle city
        // In total it takes path.size() / 2 turns until they meet in the middle city.
        // At each turn we add to the sum, the maximum between the 2 roads traversed by the friends
        else if(path.size() % 2 != 0)
        {
            int sum = 0;
            int iteratorFirstFriend = 0;
            int iteratorSecondFriend = path.size()-1;

            for(int i = 1; i <= path.size() / 2; i++)
            {
                int edge1 = 0;
                int edge2 = 0;

                for(int j = 0; j < path.get(iteratorFirstFriend).adjacencyList.size(); j++)
                {
                    if(path.get(iteratorFirstFriend).adjacencyList.get(j).target == path.get(iteratorFirstFriend+1))
                    {
                        edge1 = path.get(iteratorFirstFriend).adjacencyList.get(j).cost;
                        break;
                    }
                }

                for(int j = 0; j < path.get(iteratorSecondFriend).adjacencyList.size(); j++)
                {
                    if(path.get(iteratorSecondFriend).adjacencyList.get(j).target == path.get(iteratorSecondFriend-1))
                    {
                        edge2 = path.get(iteratorSecondFriend).adjacencyList.get(j).cost;
                        break;
                    }
                }

                sum += Math.max(edge1, edge2);
                iteratorFirstFriend++;
                iteratorSecondFriend--;
            }
            try {
                writer.newLine();
                writer.write("The friends meet in " + path.get(path.size() / 2).name + " after " + sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // If the path has an even size, the friends move simultaneously towards each other.
        // At some point they will be in neighbour cities on the positions path.size() / 2 - 1 and path.size() / 2
        // It takes path.size() / 2 - 1 until the friends arrive in the neighbour cities from the middle of the path
        // At each turn we add to the sum, the maximum between the 2 roads traversed by the friends
        // In the end we add the weight of the edge connecting the cities with index path.size() / 2 - 1 and path.size() / 2
        else if(path.size() % 2 == 0)
        {
            int sum = 0;
            int iteratorFirstFriend = 0;
            int iteratorSecondFriend = path.size() - 1;

            for(int i = 1; i <= path.size() / 2 - 1; i++)
            {
                int edge1 = 0;
                int edge2 = 0;

                for(int j = 0; j < path.get(iteratorFirstFriend).adjacencyList.size(); j++)
                {
                    if(path.get(iteratorFirstFriend).adjacencyList.get(j).target == path.get(iteratorFirstFriend+1))
                    {
                        edge1 = path.get(iteratorFirstFriend).adjacencyList.get(j).cost;
                        break;
                    }
                }

                for(int j = 0;j<path.get(iteratorSecondFriend).adjacencyList.size(); j++)
                {
                    if(path.get(iteratorSecondFriend).adjacencyList.get(j).target == path.get(iteratorSecondFriend-1))
                    {
                        edge2 = path.get(iteratorSecondFriend).adjacencyList.get(j).cost;
                        break;
                    }
                }

                sum += Math.max(edge1, edge2);
                iteratorFirstFriend++;
                iteratorSecondFriend--;
            }

            for(int i = 0; i < path.get(path.size() / 2 - 1).adjacencyList.size(); i++)
            {
                if(path.get(path.size() / 2 - 1).adjacencyList.get(i).target == path.get(path.size() / 2))
                {
                    sum += path.get(path.size() / 2 - 1).adjacencyList.get(i).cost;
                    break;
                }
            }
            try {
                writer.newLine();
                writer.write("The friends meet in " + path.get(path.size() / 2 - 1).name + " after " + sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
