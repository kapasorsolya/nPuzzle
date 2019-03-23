/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orsolya
 */

public class Program {

    private static String INITIAL_STATE="";//= "724506831";
    final static private String GOAL_STATE = "012345678";
    private static boolean READ_FROM_FILE = false;
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n=3;
        String fileName ="input";//null;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                //System.out.println(args[i]);
                if (args[i].equals("-input")) {
                    //System.out.println(i + " args length" + args.length);
                    if (i == args.length - 1) {
                        System.out.println("You must give the fileName!");
                        System.exit(1);
                    }
                    if (i != args.length - 1) {
                        fileName = args[i + 1];
                        //System.out.print("filename:" + args[i + 1]);
                        READ_FROM_FILE = true;

                    }
                }
            }
        }
        StringBuilder s = new StringBuilder("");
        if(READ_FROM_FILE == true)
        {
            try{
            s.append( readInitialStateFromFile(fileName));
            }
            catch(FileNotFoundException ex)
            {
                System.err.println("The file not found or exists");
            } catch (IOException ex) {
                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            
            System.out.println("Please enter "+n*n+ " numbers");
            Scanner scan = new Scanner(System.in);
            for(int i=0;i<9;i++)
            {
                 System.out.print(" item("+i+ ") = ");
                 s.append(scan.nextInt());
                 //System.out.println();
            }
            System.out.println(s);
           
        }
        for(int i=0;i<s.length();i++)
        {
            INITIAL_STATE +=s.charAt(i);
        }
        String rootState = INITIAL_STATE;
      
	System.out.println("INITIAL_STATE: " + rootState);
       
        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        
        search.aStar(Heuristic.H_ONE,n*n+1);
    }

    private static String readInitialStateFromFile(String fileName) throws FileNotFoundException, IOException{ {
       BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String currentLine;
        String text = "";
        while ((currentLine = bufferedReader.readLine()) != null) {
            text += currentLine;
        }
        //System.out.println(text);
        return  text;
	
    }

    }
}
