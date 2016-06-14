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
public class SoleBoardRelaxed {
    boolean board[][] = new boolean[6][6];
    
    public SoleBoardRelaxed(SoleBoard sb){
        board = sb.getBoard();
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
    
    
    
    /*
     * checks i a walk operation from x, y to newX, newY is possible.
     */
    public boolean canWalk(int x, int y, int newX, int newY){
        if((newX >= 0 && newX < 6 && newY >= 0 && newX < 6) && (positiveDifferens(x, newX) < 2) && (positiveDifferens(y, newY) < 2) && !board[newX][newY]){
            return true;
        }
        return false;
    }
    
    /*
     * checks if a knock operation is possible from ball1X, ball1Y to ball2X, ball2Y
     */
    public boolean canKnock(int ball1X, int ball1Y, int ball2X, int ball2Y){
        if(!(withinBoundries(ball1X) && withinBoundries(ball1Y) && withinBoundries(ball2X) && withinBoundries(ball2Y)) || !gotBallAt(ball2X, ball2Y)){
            return false; 
        }
        else if(ball1X == ball2X && differensTwo(ball1Y, ball2Y)){
            int newY = ball2Y + ((ball1Y-ball2Y)/2);
            if(gotBallAt(ball1X, newY)){
                return false;
            }
            return true;
            // do the same for when Y is the same for both balls and do the same thing with the x as you did for the y.
        }
        else if(ball1Y == ball2Y && differensTwo(ball1X, ball2X)){
            int newX = ball2X + ((ball1X-ball2X)/2);
            if(gotBallAt(newX, ball1X)){
                return false;
            }
            return true;
        }
        return false;
    }
    
    /*
     * checks if any kind of move operation from x, y to newX, newY is possible
     */
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
     * performs a walk operation from x,y to newX, newY if possible and return true if possible and false otherwise
     */
    public boolean walk(int x, int y, int newX, int newY){
        if((newX >= 0 && newX < 6 && newY >= 0 && newX < 6) && (positiveDifferens(x, newX) < 2) && (positiveDifferens(y, newY) < 2) && !board[newX][newY]){
            setAtBoard(x, y, false);
            setAtBoard(newX, newY, true);
            return true;
        }
        return false;
    }
    /*
     * performs a knock operation from x,y to newX, newY if possible and return true if possible and false otherwise
     */
    public boolean knock(int ball1X, int ball1Y, int ball2X, int ball2Y){
        if(!(withinBoundries(ball1X) && withinBoundries(ball1Y) && withinBoundries(ball2X) && withinBoundries(ball2Y)) || !gotBallAt(ball2X, ball2Y)){
            return false; 
        }
        else if(ball1X == ball2X && differensTwo(ball1Y, ball2Y)){
            int newY = ball2Y + ((ball1Y-ball2Y)/2);
            if(gotBallAt(ball1X, newY)){
                return false;
            }
            // remove the previous two balls and set the new ball to the same x cord and the newY cord.
           // System.out.println("changing y to" + newY);
            setAtBoard(ball1X, ball1Y, false);
            setAtBoard(ball2X, ball2Y, false);
            setAtBoard(ball1X, newY, true);
            return true;
            // do the same for when Y is the same for both balls and do the same thing with the x as you did for the y.
        }
        else if(ball1Y == ball2Y && differensTwo(ball1X, ball2X)){
            int newX = ball2X + ((ball1X-ball2X)/2);
            if(gotBallAt(newX, ball1X)){
                return false;
            }
            // System.out.println("changing x to" + newX);
            setAtBoard(ball1X, ball1Y, false);
            setAtBoard(ball2X, ball2Y, false);
            setAtBoard(newX, ball1Y, true);
            return true;
        }
        return false;
    }
    
    /*
     * performes any move operation from x,y to newX,newY if possible and return true if possible false otherwise
     */
    public boolean makeMove(int x, int y, int newX, int newY){
        if(knock(x, y, newX, newY)){
           //  System.out.println("made a knock");
            return true;
        }
        else if(walk(x, y, newX, newY)){
            // System.out.println("made a walk");
            return true;
        }
        // System.out.println("could not do anything");
        return false;
    }
    
    /*
     * checks if x is within the game board
     */
    private boolean withinBoundries(int x){
        if(x >= 0 && x < 6){
            return true;
        }
        return false;
    }
    
    /*
     * check if the differens between x1 and x2 exactly two
     */
    private boolean differensTwo(int x1, int x2){
        int x = x1-x2;
        if(x == 2 || x == -2){
            return true;
        }
        return false;
    }
    
    /*
     * returns the positive differens between x1 and x2
     */
    private int positiveDifferens(int x1, int x2){
        int x = x1-x2;
        if(x < 0){
            x = -x;
        }
        return x;
    }
    
    /*
     * prints the board
     */
    public void printBoard(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                System.out.print((board[j][i]? "1":"0") + j + i + " ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------");
    }
    
    /*
     * checks if the board got the same "setup" as the board sl
     */
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
    
    /*
     * checks if the board is in a winning state
     */
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
    
    /*
     * randomizes the game board for start
     */
    public void randomizeStart(){
        java.util.Random r = new java.util.Random(1);
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                board[i][j] = r.nextBoolean();
            }
        }
    }
    
    /*
     * generates all the possible moves from the current gameboard
     */
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
    
    /*
     * get all the moves the ball at x,y can make
     */
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
        if(canKnock(x, y, x+2, y)){
            moves[currentNrOfMoves++] = new Move(x+2, y, x, y);
        }
        if(canKnock(x, y, x-2, y)){
            moves[currentNrOfMoves++] = new Move(x-2, y, x, y);
        }
        if(canKnock(x, y, x, y+2)){
            moves[currentNrOfMoves++] = new Move(x, y+2, x, y);
        }
        if(canKnock(x, y, x, y-2)){
            moves[currentNrOfMoves++] = new Move(x, y-2, x, y);
        }
        
        moves[currentNrOfMoves] = null;
        
        return moves;
        
    }
    
    /*
     * adds moves to moveList
     */
    private void appendToMoveList(ArrayList<Move> moveList, Move[] moves){
        int i = 0;
        while(moves[i] != null){
            moveList.add(moves[i++]);
        }
    }
    
    /*
     * returns a copy of the game board
     */
    public SoleBoard getCopy(){
        SoleBoard sb = new SoleBoard();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                sb.setAtBoard(i, j, board[i][j]);
            }
        }
        return sb;
    }
    
    
    /*
     * returns the game board as a string
     */
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
    
}
