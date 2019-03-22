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
    //final static private String INITIAL_STATE = "123046758";
    final static private String INITIAL_STATE = "724506831";
    final static private String GOAL_STATE = "012345678";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       String rootState = INITIAL_STATE;
       int n=3;
        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        
        search.aStar(Heuristic.H_TWO,n*n+1);
     
    }
    
}
