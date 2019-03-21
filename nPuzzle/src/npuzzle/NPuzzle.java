/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle;

/**
 *
 * @author Orsolya
 */
public class NPuzzle
{
    final static private String INITIAL_STATE = "123804765";
    final static private String GOAL_STATE = "123845760";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       String rootState = INITIAL_STATE;
       long startTime = System.currentTimeMillis();

        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        
        search.aStar(Heuristic.H_TWO);
       // search.bestFirstSearch();
        

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Time  :" + totalTime);
        System.err.println(rootState);
    }
    
}
