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
public class SoleBoard {
    boolean board[][] = new boolean[6][6];
    
    public SoleBoard(){
        
    }

    public boolean[][] getBoard() {
        return board;
    }

    public void setBoard(boolean[][] board) {
        this.board = board;
    }
    
    public boolean getAtBoard(int x, int y){
        return board[x][y];
    }
    
    public void setAtBoard(int x, int y){
        board[x][y] = (board[x][y] == true? false:true);
    }
    
    public void setAtBoard(int x, int y, boolean value){
        board[x][y] = value;
    }
    
    public boolean gotBallAt(int x, int y){
        return board[x][y];
    }
    
    
    
    
    public boolean canWalk(int x, int y, int newX, int newY){
        if((newX >= 0 && newX < 6 && newY >= 0 && newX < 6) && (positiveDifferens(x, newX) < 2) && (positiveDifferens(y, newY) < 2) && !board[newX][newY]){
            return true;
        }
        return false;
    }
    
    
    public boolean canKnock(int ball1X, int ball1Y, int ball2X, int ball2Y){
        if(!(withinBoundries(ball1X) && withinBoundries(ball1Y) && withinBoundries(ball2X) && withinBoundries(ball2Y)) || !gotBallAt(ball2X, ball2Y)){
            return false; 
        }
        else if(ball1X == ball2X && positiveDifferens(ball1Y, ball2Y) >= 2){
            int newY = ball2Y + ((ball1Y-ball2Y)/2);
            if(gotBallAt(ball1X, newY)){
                return false;
            }
            return true;
            // do the same for when Y is the same for both balls and do the same thing with the x as you did for the y.
        }
        else if(ball1Y == ball2Y && positiveDifferens(ball1X, ball2X) >= 2){
            int newX = ball2X + ((ball1X-ball2X)/2);
            if(gotBallAt(newX, ball1X)){
                return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean canMakeMove(int x, int y, int newX, int newY){
        if(canKnock(x, y, newX, newY)){
            return true;
        }
        else if(canWalk(x, y, newX, newY)){
            return true;
        }
        return false;
    }
    
    
    /*
     * lägg in koll så man inte tar ett steg längre än 1 såvida inte man knockar
     * kanske göra en mer generell funktion t.ex. makeMove(int x, int y) som antingen gör en knock eller en walk 
     * beroende på hur långt man har till den ny kordinaten, eller så returneras false om man inte kan utföra draget.
     */
    
    public boolean walk(int x, int y, int newX, int newY){
        if((newX >= 0 && newX < 6 && newY >= 0 && newX < 6) && (positiveDifferens(x, newX) < 2) && (positiveDifferens(y, newY) < 2) && !board[newX][newY]){
            setAtBoard(x, y, false);
            setAtBoard(newX, newY, true);
            return true;
        }
        return false;
    }
    
   public boolean knock(int ball1X, int ball1Y, int ball2X, int ball2Y){
        if(!(withinBoundries(ball1X) && withinBoundries(ball1Y) && withinBoundries(ball2X) && withinBoundries(ball2Y)) || !gotBallAt(ball2X, ball2Y)){
            return false; 
        }
        else if(ball1X == ball2X && positiveDifferens(ball1Y, ball2Y) >= 2){
            int newY = ball2Y + ((ball1Y-ball2Y)/2);
            if(gotBallAt(ball1X, newY)){ // check if there is free room between ball1 and ball2
                System.out.println("ball between");
                return false;
            }
            if(ball1Y-ball2Y < 0){
                newY = ball2Y-1;
            }else{
                newY = ball2Y+1;
            }
            // remove the previous two balls and set the new ball to the same x cord and the newY cord.
           // System.out.println("changing y to" + newY);
            setAtBoard(ball1X, ball1Y, false);
            setAtBoard(ball2X, ball2Y, false);
            setAtBoard(ball1X, newY, true);
            return true;
            // do the same for when Y is the same for both balls and do the same thing with the x as you did for the y.
        }
        else if(ball1Y == ball2Y && positiveDifferens(ball1X, ball2X) >= 2){
            int newX = ball2X + ((ball1X-ball2X)/2);
            if(gotBallAt(newX, ball1X)){
                System.out.println("ball between");
                return false;
            }
            if(ball1X-ball2X < 0){
                newX = ball2X-1;
            }else{
                newX = ball2X+1;
            }
            // System.out.println("changing x to" + newX);
            setAtBoard(ball1X, ball1Y, false);
            setAtBoard(ball2X, ball2Y, false);
            setAtBoard(newX, ball1Y, true);
            return true;
        }
        return false;
    }
    
    
    public boolean makeMove(int x, int y, int newX, int newY){
        if(knock(x, y, newX, newY)){
            // System.out.println("made a knock");
            return true;
        }
        else if(walk(x, y, newX, newY)){
            // System.out.println("made a walk");
            return true;
        }
        // System.out.println("could not do anything");
        return false;
    }
    
    private boolean withinBoundries(int x){
        if(x >= 0 && x < 6){
            return true;
        }
        return false;
    }
    
    private boolean differensTwo(int x1, int x2){
        int x = x1-x2;
        if(x == 2 || x == -2){
            return true;
        }
        return false;
    }
    
    private int positiveDifferens(int x1, int x2){
        int x = x1-x2;
        if(x < 0){
            x = -x;
        }
        return x;
    }
    
    
    public void printBoard(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print((board[j][i]? "1":"0") + j + i + " ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------");
    }
    
    public boolean equalsBoard(SoleBoard sl){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(board[i][j] != sl.getAtBoard(i, j)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isWinningState(){
        int nrOfBalls = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(board[i][j]){
                    nrOfBalls++;
                }
            }
        }
        
        return nrOfBalls <= 1;
    }
    
    
    public void randomizeStart(){
        java.util.Random r = new java.util.Random(1);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                board[i][j] = r.nextBoolean();
            }
        }
    }
    
    public ArrayList<Move> generateMoveList(){
        ArrayList<Move> movesList = new ArrayList<Move>();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(board[i][j]){
                    appendToMoveList(movesList, getAllMoves(i, j));
                }
            }
        }
        return movesList;
    }
    
    public Move[] getAllMoves(int x, int y){
        Move[] moves = new Move[9];
        int currentNrOfMoves = 0;
        
        if(withinBoundries(x+1) && !board[x+1][y]){ // could just use canWalk...
            moves[currentNrOfMoves++] = new Move(x+1, y, x, y);
        }
        if(withinBoundries(x-1) && !board[x-1][y]){
            moves[currentNrOfMoves++] = new Move(x-1, y, x, y);
        }
        if(withinBoundries(y+1) && !board[x][y+1]){
            moves[currentNrOfMoves++] = new Move(x, y+1, x, y);
        }
        if(withinBoundries(y-1) && !board[x][y-1]){
            moves[currentNrOfMoves++] = new Move(x, y-1, x, y);
        }
        for(int i = x; i < 6; i++){
            if(canKnock(x, y, i, y)){
                // add to moves and break
                moves[currentNrOfMoves++] = new Move(i, y, x, y);
                break;
            }
        }
        
        for(int i = x; i >= 0; i--){
            if(canKnock(x, y, i, y)){
                // add to moves and break
                moves[currentNrOfMoves++] = new Move(i, y, x, y);
                break;
            }
        }
        
        for(int i = y; i < 6; i++){
            if(canKnock(x, y, x, i)){
                // add to moves and break
                moves[currentNrOfMoves++] = new Move(x, i, x, y);
                break;
            }
        }
        
        for(int i = y; i >= 0; i--){
            if(canKnock(x, y, x, i)){
                // add to moves and break
                moves[currentNrOfMoves++] = new Move(x, i, x, y);
                break;
            }
        }
        
        moves[currentNrOfMoves] = null;
        
        return moves;
        
    }
    
    private void appendToMoveList(ArrayList<Move> moveList, Move[] moves){
        int i = 0;
        while(moves[i] != null){
            moveList.add(moves[i++]);
        }
    }
    
    public SoleBoard getCopy(){
        SoleBoard sb = new SoleBoard();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                sb.setAtBoard(i, j, board[i][j]);
            }
        }
        return sb;
    }
    
    
    @Override
    public String toString(){
        String temp = "";
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                temp = temp + (board[i][j] ? "1" : "0");
            }
            temp = temp + "\n";
        }
        return temp;
    }
    
    /*
     * check if the differens between x1 and x2 exactly two
     */
    private boolean differensAtLeastTwo(int x1, int x2){
        int x = x1-x2;
        if(x >= 2 || x <= -2){
            return true;
        }
        return false;
    }
}
