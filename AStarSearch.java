/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hemtenta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sa11
 */
public class AStarSearch {
    
    PriorityQueue open = new PriorityQueue();
    private HashMap<String, StateNode> closed = new HashMap<String, StateNode>();
    
    /*
     * sets the start StateNode to the board.
     */
    public AStarSearch(SoleBoard sb){
        StateNode stateNode = new StateNode(sb);
        open.addState(stateNode);
    }
    
    // expandera "minsta" noden(alltså den med lägsta estimerad kostnad) och utforska den, börja med att kolla
    //om den är en målnod och om den inte är det lägg den i closed och lägg till de noder som genererats av den i open.
    /*
     * returns an optimal path from the current state to the goal state, returns null if the goal state is unreachable
     */
    public ArrayList<Move> findOptimal(){
        StateNode temp = open.getSmallest();
        
        while((closed.get(temp.toString()) != null)){
            temp = open.getSmallest();
            if(open.getSmallest() == null){
                return null;
            }
        }
        
        if(temp.getCurrentBoard().isWinningState()){
            // look if there is one with smaller value
            StateNode temp2;
            while(true){
                temp2 = findOptimal2(temp.getEstimatedCost());
                if(temp2 != null){
                    temp = temp2;
                } else{
                    break;
                }
            }
            return temp.getMovesToReachState();
        }
        else{
            generateAndAdd(temp);
        }
        return findOptimal();
    }
    
    public StateNode findOptimal2(int currentSmallest){
        StateNode temp = open.getSmallest();
        
        while((closed.get(temp.toString()) != null)){
            temp = open.getSmallest();
            if(open.getSmallest() == null){
                return null;
            }
        }
        if(temp.getEstimatedCost() >= currentSmallest){
            return null;
        }
        
        if(temp.getCurrentBoard().isWinningState()){
            return temp;
        }
        else{
            generateAndAdd(temp);
        }
        return findOptimal2(currentSmallest);
    }
    
    
    /*
     * generate all states from the "smallest" StateNode and adds them to open and adds the "smallest" to closed
     */
    private void generateAndAdd(StateNode temp){
        ArrayList<StateNode> stateNodes = temp.generateAllStates();
        for(int i = 0; i < stateNodes.size(); i++){
            open.addState(stateNodes.get(i));
        }
        closed.put(temp.toString(), temp);
    }
}
