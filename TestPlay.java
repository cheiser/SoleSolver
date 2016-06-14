/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hemtenta;

import com.sun.corba.se.spi.ior.MakeImmutable;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author sa11
 */
public class TestPlay {
    
    public static void main(String[] args){
        SoleBoard sb = new SoleBoard();
        String temp = JOptionPane.showInputDialog("enter which example you want to run(1 for example 1 and so on):");
        int choice = 0;
        
        try{
            choice = Integer.parseInt(temp);
        } catch(NumberFormatException ex){
            choice = 1;
        }
        
        switch(choice){
            case 1:
                sb.setAtBoard(0, 3, true);
                sb.setAtBoard(1, 1, true);
                break;
            case 2:
                sb.setAtBoard(2, 3, true);
                sb.setAtBoard(1, 1, true);
                sb.setAtBoard(4, 3, true);
                break;
            case 3:
                sb.setAtBoard(0, 0, true);
                sb.setAtBoard(1, 1, true);
                sb.setAtBoard(1, 3, true);
                sb.setAtBoard(2, 5, true);
                break;
            case 4:
                sb.setAtBoard(0, 0, true);
                sb.setAtBoard(2, 1, true);
                sb.setAtBoard(4, 3, true);
                sb.setAtBoard(2, 5, true);
                break;
            case 5:
                sb.setAtBoard(1, 0, true);
                sb.setAtBoard(1, 5, true);
                sb.setAtBoard(2, 1, true);
                sb.setAtBoard(2, 3, true);
                sb.setAtBoard(4, 1, true);
                sb.setAtBoard(5, 0, true);
                break;
            default:
        }
        
        sb.printBoard();
        // Move[] moves = sb.getAllMoves(0, 0);
        // printMoves(moves);
        // printMovesList(sb.generateMoveList());
        // StateNode sn = new StateNode(sb);
        // System.out.println(sn.generateAllStates());
        /*
        Scanner s = new Scanner(System.in);
        while(!sb.isWinningState()){
            int ballX = s.nextInt();
            int ballY = s.nextInt();
            int moveX = s.nextInt();
            int moveY = s.nextInt();
            sb.makeMove(ballX, ballY, moveX, moveY);
            sb.printBoard();
        }
         * 
         */
        // printMovesList(sb.generateMoveList());
        AStarSearch ast = new AStarSearch(sb);
        // BFSearch ast = new BFSearch(sb);
        // printMovesList(ast.findOptimal());
        JOptionPane.showMessageDialog(null, getMovesListString(ast.findOptimal()));
    }
    
    /*
     * prints all the moves in moves
     */
    public static void printMoves(Move[] moves){
        int i = 0;
        while(moves[i] != null){
            System.out.println("move: " + moves[i++]);
        }
    }
    
    /*
     * prints all the moves in moves
     */
    public static void printMovesList(ArrayList<Move> moves){
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i));
        }
    }
    
    public static String getMovesListString(ArrayList<Move> moves){
        String temp = "";
        for(int i = 0; i < moves.size(); i++){
            Move tm = moves.get(i);
            temp = temp + "\nfrom cord: " + (tm.getFromX()+1) + "." +  (tm.getFromY()+1) + " to cord: " + (tm.getX()+1) + "." + (tm.getY()+1);
        }
        return temp;
    }
}
