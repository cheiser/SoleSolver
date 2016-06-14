/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hemtenta;

import java.util.ArrayList;

/**
 *
 * @author sa11
 */
public class PriorityQueue {
    
    ArrayList<StateNode> stateNodes = new ArrayList<StateNode>();
    
    /*
     * adds the StateNode sn to the PriorityQueue
     */
    public void addState(StateNode sn){
        stateNodes.add(sn);
    }
    
    /*
     * returns the StateNode with the biggest estimated cost
     */
    public StateNode getBiggest(){
        int indexOfBiggest = 0;
        for(int i = 0; i < stateNodes.size(); i++){
            if((stateNodes.get(indexOfBiggest)).getEstimatedCost() < (stateNodes.get(i)).getEstimatedCost()){
                indexOfBiggest = i;
            }
        }
        return stateNodes.remove(indexOfBiggest);
    }
    
    /*
     * returns the StateNode with the smallest estimated cost
     */
    public StateNode getSmallest(){
        int indexOfSmallest = 0;
        StateNode temp;
        // System.out.println("nr of objects: " + stateNodes.size());
        for(int i = 0; i < stateNodes.size(); i++){
            if((stateNodes.get(indexOfSmallest)).getEstimatedCost() > (stateNodes.get(i)).getEstimatedCost()){
                indexOfSmallest = i;
            }
        }
        
        try{
        temp = stateNodes.remove(indexOfSmallest);
        } catch(ArrayIndexOutOfBoundsException ex){
            return null;
        }
        
        return temp;
    }
    
}
