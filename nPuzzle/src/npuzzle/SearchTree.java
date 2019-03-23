package npuzzle;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Orsolya
 */
public class SearchTree {

    private Node root;
    private String goalSate;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getGoalSate() {
        return goalSate;
    }

    public void setGoalSate(String goalSate) {
        this.goalSate = goalSate;
    }

    public SearchTree(Node root, String goalSate) {
        this.root = root;
        this.goalSate = goalSate;
    }

    /**
     * Find the goal using A* algorithm. The heuristic is the real cost to the
     * current node from the initial Node plus the estimated cost from the
     * current node to the goal node
     */
    //*************************************************************************************************
    public void aStar(Heuristic heuristic, int size, boolean PRINT_SOLUTION,boolean PRINT_COST) {
        // stateSet is a set that contains node that are already visited
        Set<String> stateSets = new HashSet<String>();
        int totalCost = 0;
        int time = 0;
        Node node = new Node(root.getState());
        node.setTotalCost(0);

        // the comparator compare the cost values and make the priority queue to be sorted based on cost values
        NodePriorityComparator nodePriorityComparator = new NodePriorityComparator();

        // a queue that contains nodes and their cost values sorted. 10 is the initial size
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<Node>(size, nodePriorityComparator);
        Node currentNode = node;
        while (!currentNode.getState().equals(goalSate)) {
            stateSets.add(currentNode.getState());
            List<String> nodeSuccessors = NodeUtil.getSuccessors(currentNode.getState());
            for (String n : nodeSuccessors) {
                if (stateSets.contains(n)) {
                    continue;
                }
                stateSets.add(n);
                Node child = new Node(n);
                currentNode.addChild(child);
                child.setParent(currentNode);

                if (null != heuristic) {
                    switch (heuristic) {
                        case H_ONE:
                            child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicOne(child.getState(), goalSate));
                            //System.out.println("TotalCost" + currentNode.getTotalCost() );

                            break;
                        case H_TWO:
                            child.setTotalCost(currentNode.getTotalCost() + Character.getNumericValue(child.getState().charAt(child.getParent().getState().indexOf('0'))), heuristicTwo(ConvertToArray(child.getState()), goalSate));
                            break;
                        default:
                            break;
                    }
                }
                nodePriorityQueue.add(child);

            }
            currentNode = nodePriorityQueue.poll();
            time += 1;
            if (nodePriorityQueue.isEmpty()) {
                System.out.println("There is NO Solution");
                System.exit(0);
            }
        }
       
        NodeUtil.printSolution(currentNode, stateSets, root, time,PRINT_SOLUTION,PRINT_COST);
        
    }

    //****************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by counting the number of misplaced tiles
    private int heuristicOne(String currentState, String goalSate) {
        int difference = 0;
        for (int i = 0; i < currentState.length(); i++) {
            if (currentState.charAt(i) != goalSate.charAt(i)) {
                difference++;
            }
        }
        return difference;
    }

    //*************************************************************************************************
    // This heuristic estimates the cost to the goal from current state by calculating the Manhathan distance from each misplaced
    // tile to its right position in the goal state
    private int heuristicTwo(int[][] currentState, String goalState) {
        int difference = 0;
        int n = 3;
        int[][] items = ConvertToArray(goalState);
         //TO DO 
         //itt a heurisztikanal lehet eltevesztettem a dolgokat currentState kellene
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                difference += getManhattanDistance(items[i][j], i, j, items);
            }

        }
        //System.out.println("npuzzle.SearchTree.heuristicTwo()" + " " + difference );
        return difference;
    }

    private int getManhattanDistance(int item, int row, int column, int[][] goalState) {
        //int res =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (goalState[i][j] == item) {
                    return abs(i - row) + abs(j - column);
                }
            }
        }
        return -1;
    }

    private int[][] ConvertToArray(String state) {
        int[][] newItem = new int[3][3];
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newItem[i][j] = Character.getNumericValue(state.charAt(counter));

            }
        }

        return newItem;

    }
}
