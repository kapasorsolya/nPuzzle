/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npuzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Orsolya
 */
public class Program {

    private static String INITIAL_STATE = "";      //for example  "724506831";
    final static private String GOAL_STATE = "012345678";
    private static boolean READ_FROM_FILE = false;
    private static boolean PRINT_STDOUT_SOLUTION = false;
    private static boolean PRINT_COST = false;
    private static boolean PRINT_NUMBER_OF_VISITED_NODE = false;
    private static boolean RANDOMIZE_INPUT = false;
    private static int HEURISTIC = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 3;
        int valueOfN = 0;
        int valueOfM = 4;
        String fileName = "input";//null;
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("-input")) {
                   if (i == args.length - 1) {
                        System.out.println("You must give the fileName!");
                        System.exit(1);
                    }
                    if (i != args.length - 1) {
                        fileName = args[i + 1];
                        READ_FROM_FILE = true;

                    }
                }
                if (args[i].equals("-solseq")) {
                    PRINT_STDOUT_SOLUTION = true;
                }
                if (args[i].equals("-pcost")) {
                    PRINT_COST = true;
                }
                if (args[i].equals("-nvisited")) {
                    PRINT_NUMBER_OF_VISITED_NODE = true;
                }

                if (args[i].equals("-h")) {
                    if (i == args.length - 1) {
                        System.out.println("You must give the heuristic type (1 - number of wrong pieces" + "OR" + " 2 - Manhattan");
                        System.exit(1);
                    }
                    if (i != args.length - 1) {
                        HEURISTIC = Integer.parseInt(args[i + 1]);
                        if (HEURISTIC != 1 && HEURISTIC != 2) {
                            System.out.println("We didn't recognize the heuristic type");
                            System.exit(0);
                        }

                    }
                }
                if (args[i].equals("-rand")) {
                    if (i == args.length - 1) {
                        System.out.println("You should give N and M value");
                        System.exit(0);
                    } else {

                        if (i + 1 == args.length - 1) {
                            System.out.println("You should give M value too");
                            System.exit(0);
                        }

                        valueOfN = Integer.parseInt(args[i + 1]);
                        valueOfM = Integer.parseInt(args[i + 2]);

                        RANDOMIZE_INPUT = true;
                        System.out.println("N: " + valueOfN + "M:" + valueOfM);

                    }
                }

            }
        }
        StringBuilder s = new StringBuilder("");
        if (READ_FROM_FILE == true) {
            try {
                s.append(readInitialStateFromFile(fileName));
            } catch (FileNotFoundException ex) {
                System.err.println("The file not found or exists");
            } catch (IOException ex) {
                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            System.out.println("Please enter " + n * n + " numbers");
            Scanner scan = new Scanner(System.in);

            for (int i = 0; i < 9; i++) {
                System.out.print(" item(" + i + ") = ");
                s.append(scan.nextInt());   
            }
        }

        //Randomize input
        if (RANDOMIZE_INPUT == true) {
            Random rand = new Random();
            // Obtain a number between [0 - 4].
            int i = 0;
            while (i < valueOfM) {
                int x = rand.nextInt(4);
                int pos = 0, itempos = 0;
                switch (x) {
                    case 0:
                        pos = s.indexOf("0");
                        itempos = pos - n;
                        if (itempos < 0) {
                            break;
                        } else {
                            //swap
                            s.replace(pos, pos + 1, s.substring(itempos, itempos + 1));
                            s.replace(itempos, itempos + 1, "0");
                            i++;
                        }
                        break;
                    case 1:
                        pos = s.indexOf("0");
                        itempos = pos + n;
                        if (itempos > n * n - 1) {
                            break;
                        } else {
                            s.replace(pos, pos + 1, s.substring(itempos, itempos + 1));
                            s.replace(itempos, itempos + 1, "0");
                            i++;
                        }
                        break;
                    case 2: //LEFT
                        pos = s.indexOf("0");
                        itempos = pos - 1;
                        if (itempos == n || itempos % n == 0 || itempos == 0) {
                            break;
                        } else {
                            //swap
                            s.replace(pos, pos + 1, s.substring(itempos, itempos + 1));
                            s.replace(itempos, itempos + 1, "0");
                            i++;
                        }

                        break;
                    case 3:
                        pos = s.indexOf("0");
                        itempos = pos + 1;
                        if (itempos % n == 0) {
                            break;
                        } else {
                            //swap
                            s.replace(pos, pos + 1, s.substring(itempos, itempos + 1));
                            s.replace(itempos, itempos + 1, "0");
                            i++;

                        }
                        break;
                    default:
                        break;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            INITIAL_STATE += s.charAt(i);
        }
        String rootState = INITIAL_STATE;
        // System.out.println("INITIAL_STATE: " + rootState);

        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);

        if (HEURISTIC == 1) {
            search.aStar(Heuristic.H_ONE, n * n + 1, PRINT_STDOUT_SOLUTION, PRINT_COST, PRINT_NUMBER_OF_VISITED_NODE);
        } else {
            search.aStar(Heuristic.H_TWO, n * n + 1, PRINT_STDOUT_SOLUTION, PRINT_COST, PRINT_NUMBER_OF_VISITED_NODE);
        }
    }

    private static String readInitialStateFromFile(String fileName) throws FileNotFoundException, IOException {
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String currentLine;
            String text = "";
            while ((currentLine = bufferedReader.readLine()) != null) {
                text += currentLine;
            }
            return text;
        }
    }
}
