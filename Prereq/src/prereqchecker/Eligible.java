package prereqchecker;
import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        AdjList list = new AdjList(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);

        int j = StdIn.readInt();
        StdIn.readLine();
        String[] coursesTaken = new String[j];

        for (int i = 0; i < j; i++) { coursesTaken[i] = StdIn.readLine(); }

        HashMap<String, ArrayList<String>> hashmap = list.getAdjList();

        HashSet<String> fulfilled  = new HashSet<>();

        fulfilled = search(hashmap, coursesTaken);
        ArrayList<String> eligible = new ArrayList<>();

        for (String course : hashmap.keySet()) {
            if (!fulfilled.contains(course)) {
                if (hashmap.get(course) == null) { eligible.add(course); }
                else {
                    ArrayList<String> prereq = hashmap.get(course);
                    if (fulfilled.containsAll(prereq)) {eligible.add(course); }
                }
            }
        }

        int sz = eligible.size();
        for (int i = 0; i < sz; i++) { StdOut.println(eligible.get(i)); }
    }

    public static HashSet<String> search(HashMap<String, ArrayList<String>> hashmap, String[] courses) {
        HashSet<String> flag = new HashSet<>();
        LinkedList<String> p = new LinkedList<String>();

        for (String course : courses ) {
            flag.add(course);
            p.add(course);
        }

        while (!p.isEmpty()) {
            String requirement = p.pop(); //dequeue
            if (hashmap.get(requirement) == null) { flag.add(requirement); }
            else {
                for (String str : hashmap.get(requirement)) {
                    if (!flag.contains(str)) { p.add(str); flag.add(str); }
                }
            }
        }
        return flag;
    }
}
