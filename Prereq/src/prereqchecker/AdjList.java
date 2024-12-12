package prereqchecker;

import java.util.HashMap;
import java.util.*;

/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * AdjListOutputFile name is passed through the command line as args[1]
 * Output to AdjListOutputFile with the format:
 * 1. c lines, each starting with a different course ID, then 
 *    listing all of that course's prerequisites (space separated)
 */
public class AdjList {
    public static void main(String[] args) {

        if ( args.length < 2 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.AdjList <adjacency list INput file> <adjacency list OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        
        AdjList map = new AdjList(args[0]);
        map.printAdjList(args[1]);
    }

    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public AdjList(String inputFile) {
        StdIn.setFile(inputFile);

        int x = StdIn.readInt();
        StdIn.readLine();

        for (int i = 0; i < x; i++){
            String str = StdIn.readLine();
            adjList.put(str, null);
        }

        int y = StdIn.readInt();
        StdIn.readLine();

        for (int i = 0; i < y; i++){
            String[] str = StdIn.readLine().split(" ");
            if (adjList.get(str[0]) != null) { adjList.get(str[0]).add(str[1]); }
            else {
                ArrayList<String> t = new ArrayList<>();
                t.add(str[1]);
                adjList.put(str[0], t);
            }
        }
    }

    public HashMap<String, ArrayList<String>> getAdjList() {
        return adjList;
    }

    private void printAdjList(String outputFile) {
        StdOut.setFile(outputFile);

        for (String str : adjList.keySet() ) {
            if (adjList.get(str) == null ) {
                StdOut.println(str);
            } else {
                List<String> reqList = adjList.get(str);
                String req = String.join(" ", reqList);
                StdOut.println(str + " " + req);
            }
        } 
    }
}
