package prereqchecker;

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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        AdjList list = new AdjList(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);

        String[] target = new String[1];
        target[0] = StdIn.readLine();

        int s = StdIn.readInt();
        StdIn.readLine();

        String[] coursesTaken = new String[s];
        for (int i = 0; i < s; i++) { coursesTaken[i] = StdIn.readLine(); }

        HashMap<String, ArrayList<String>> hashmap = list.getAdjList();
        HashSet<String> NeedToTake = new HashSet<>();
        HashSet<String> taken = new HashSet<>();

        taken = search(hashmap, coursesTaken);
        NeedToTake = search(hashmap, target);
        for (String str : taken) {
            if (NeedToTake.contains(str)) { NeedToTake.remove(str); }
        }

        NeedToTake.remove(target[0]);
        for (String course : NeedToTake) { StdOut.println(course); }
    }

    public static HashSet<String> search(HashMap<String, ArrayList<String>> hashmap, String[] courses) {
        HashSet<String> flag= new HashSet<>();
        LinkedList<String> p = new LinkedList<String>();

        for (String course : courses) { flag.add(course); p.add(course); }

        while (!p.isEmpty()) {
            String requirement = p.pop(); //dequeue
            if (hashmap.get(requirement) == null) { flag. add(requirement); }
            else {
                for ( String str : hashmap.get(requirement) ) {
                    if (!flag.contains(str)) { p.add(str); flag.add(str); }
                }
            }
        }
        return flag;
    }
}
