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
public class StateNode {
    SoleBoard currentBoard;
    ArrayList<Move> movesToReachState = new ArrayList<Move>();
    
    public StateNode(SoleBoard board){
        this.currentBoard = board;
    }
    
    public StateNode(SoleBoard board, ArrayList<Move> movesToReachState){
        this.currentBoard = board;
        this.movesToReachState = movesToReachState;
    }

    public ArrayList<Move> getMovesToReachState() {
        return movesToReachState;
    }

    public SoleBoard getCurrentBoard() {
        return currentBoard;
    }
    
    public void addMove(Move move){
        this.movesToReachState.add(move);
    }
    
    /*
     * adds an move to the movesToReachState list, which is used to keep track of the moves on made to reach this state
     */
    public void addMove(int x, int y){
        Move temp = new Move(x, y);
        addMove(temp);
    }
    
    /*
     * compares this StateNode to the StateNode sn and returns the relationship between them(eg. the same).
     */
    public int compareTo(StateNode sn){
        if(this.getEstimatedCost() > sn.getEstimatedCost()){
            return 1;
        }
        if(this.getEstimatedCost() < sn.getEstimatedCost()){
            return -1;
        }
        return 0;
    }
    
    /*
     * returns the estimated cost to reach the goal from current State
     */
    public int getEstimatedCost(){
        return movesToReachState.size()+1; // when we got a proper heuristic function replace 1 with it
    }
    
    /*
     * checks if two states contains "the same" board, used to check so we dont get cycles
     */
    public boolean compare(StateNode sn){
        if(sn.getCurrentBoard().equalsBoard(currentBoard)){
            return true; // the two StateNodes got the same "state" on the board
        }
        return false; // two different boards
    }
    
    /*
     * generate all the states one can get from this state
     */
    /**/
    public ArrayList<StateNode> generateAllStates(){
        ArrayList<Move> moves = currentBoard.generateMoveList();
        ArrayList<StateNode> states = new ArrayList<StateNode>();
        int movesSize = moves.size();
        // perform all the moves on the board and create a state for it as you do it
        for(int i = 0; i < movesSize; i++){
            Move temp = moves.get(i);
            SoleBoard sb = currentBoard.getCopy();
            ArrayList<Move> tempMoveList = (ArrayList<Move>)this.movesToReachState.clone();
            tempMoveList.add(temp);
            sb.makeMove(temp.getFromX(), temp.getFromY(), temp.getX(), temp.getY());
            states.add(new StateNode(sb, tempMoveList));
        }
        return states;
    }
     /**/
    
    
    /*
     * returns the StateNode as a String, basically just returns the board as a String
     */
    @Override
    public String toString(){
        String temp = currentBoard.toString();
        return temp;
    }
    
}


