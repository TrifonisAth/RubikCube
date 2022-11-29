import ai.Astar;
import cube.Cube;

import java.util.ArrayList;
import java.util.Collections;

public  class Main {
        public   static   void  main(String[] args) {
            int k = 6;
            Cube initialState =  new  Cube(k); // Least number of faces with the same color to solve the cube. [1,6]
            initialState.randomize(3,4); // randomize the cube with x to z moves.
            initialState.countTotalDistance();
            Astar astar =  new  Astar();
            long start = System.currentTimeMillis();
            Cube terminalState = astar.AstarClosedSet(initialState, 0); // 0 for corner-edges heuristic, 1 for total distance heuristic.
            long end = System.currentTimeMillis();
            if (terminalState == null) System.out.println("Could not find a solution." );
            else {
                // print the path from beginning to start.
                Cube temp = terminalState;  // begin from the end.
                ArrayList<Cube> path =  new  ArrayList<>();
                path.add(terminalState);
                 while  (temp.getParent() != null)  // if father is null, then we are at the root.
                {
                    path.add(temp.getParent());
                    temp = temp.getParent();
                }
                // reverse the path and print.
                Collections.reverse(path);
                System.out.println( "Path:" );
                 for  (Cube item : path) {
                    item.printCube();
                }
                System.out.println();
                System.out.println( "Search time:"  + (double) (end - start) / 1000 +  " sec." );  // total time of searching in seconds.
            }
        }
}
