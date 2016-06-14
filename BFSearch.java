/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hemtenta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author sa11
 */
public class BFSearch {
    Queue<StateNode> open = new LinkedList<StateNode>();
    HashMap<String, StateNode> closed = new HashMap<String, StateNode>();
    
    
    public BFSearch(SoleBoard sb){
        open.add(new StateNode(sb));
    }
    
    public ArrayList<Move> findOptimal(){
        StateNode temp = open.poll();
        
        while((closed.get(temp.toString()) != null)){
            temp = open.poll();
            if(open.poll() == null){
                return null;
            }
        }
        
        if(temp.getCurrentBoard().isWinningState()){
            return temp.getMovesToReachState();
        }
        else{
            generateAndAdd(temp);
        }
        return findOptimal();
    }
    
    private void generateAndAdd(StateNode temp){
        ArrayList<StateNode> stateNodes = temp.generateAllStates();
        for(int i = 0; i < stateNodes.size(); i++){
            open.add(stateNodes.get(i));
        }
        closed.put(temp.toString(), temp);
    }
}
