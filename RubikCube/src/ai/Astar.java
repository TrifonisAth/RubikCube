package ai;

import cube.Cube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Astar {
    private final ArrayList<Cube> frontier;
    private final HashSet<Cube> closedSet;

    public Astar(){
         this.frontier = new ArrayList<>();
        this.closedSet = new HashSet<>();
    }

    // A* algorithm with closed set.
    public Cube AstarClosedSet(Cube initialState, int heuristic){
        int counter = 0;
        if(initialState.isSolved()) return initialState;
        // step 1: put initial state in the frontier.
        this.frontier.add(initialState);
        // step 2: check for empty frontier.
        while(this.frontier.size() > 0)
        {
            // step 3: get the first node out of the frontier.
            Cube currentState = this.frontier.remove(0);
            // step 4: if final state, return.
            if(currentState.isSolved()) return currentState;

            // step 5: if the node is not in the closed set, put the children at the frontier.
            // else go to step 2.
            if(!this.closedSet.contains(currentState))
            {
                counter += 1;
                this.closedSet.add(currentState);
                this.frontier.addAll(currentState.getChildren(counter, heuristic)); // 0 for corner-edges heuristic, 1 for total distance heuristic.
                // step 6: sort the frontier based on the heuristic score to get best as first
                Collections.sort(this.frontier); // sort the frontier to get best as first
            }
        }
        return null;
    }



}
