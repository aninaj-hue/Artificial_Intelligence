package homework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Random random = new Random();

        File inputFile;
        Scanner myScanner;

        for(int i=1;i<=10;i++)
        {
            inputFile = new File("file"+i+"in.txt");

            int numberOfVertices=0;
            int numberOfEdges=0;

            try {
                myScanner = new Scanner(inputFile);
                numberOfVertices = myScanner.nextInt();
                numberOfEdges = myScanner.nextInt();
                myScanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedWriter writer=null;
            try {
                writer = new BufferedWriter(new FileWriter("file"+i+"out.txt"));
                writer.write("The map has " + numberOfVertices + " cities");
                writer.newLine();
                writer.write("The map has " + numberOfEdges + " roads");
                writer.newLine();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            System.out.println(numberOfVertices);
            System.out.println(numberOfEdges);

            long start = System.currentTimeMillis();
            RandomMapGenerator generator = new RandomMapGenerator();
            generator.numberOfCities = numberOfVertices;
            generator.numberOfRoads = numberOfEdges;
            List<Node> graph = generator.mapGenerator();

            System.out.println(graph.size());
            System.out.println();

            int randomPositionFirstFriend = random.nextInt(graph.size());
            int randomPositionSecondFriend = random.nextInt(graph.size());
            while(randomPositionSecondFriend==randomPositionFirstFriend)
            {
                randomPositionSecondFriend = random.nextInt(graph.size());
            }
            try {
                writer.write("Position of first friend is: " + graph.get(randomPositionFirstFriend).name);
                writer.newLine();
                writer.write("Position of second friend is: " + graph.get(randomPositionSecondFriend).name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            BellmanFord bellman = new BellmanFord();
            bellman.bellmanFord(graph.get(randomPositionSecondFriend), graph);
            System.out.println();

            long startAStar = System.currentTimeMillis();
            AStar star = new AStar();
            List<Node> finalPath = star.AStarSearch(graph.get(randomPositionFirstFriend), graph.get(randomPositionSecondFriend), graph,writer);
            long end = System.currentTimeMillis();
            long endAStar = System.currentTimeMillis();
            long duration = end - start;
            long durationAStar = endAStar - startAStar;
            try {
                writer.newLine();
                writer.write("Path is: " + finalPath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            CalculateTime calculate = new CalculateTime();
            calculate.calculateTime(finalPath,writer);
            try {
                writer.newLine();
                writer.write("Time taken by A* function is: " + durationAStar + " ms");
                writer.newLine();
                writer.write("Time taken by whole program is: " + duration + " ms");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}