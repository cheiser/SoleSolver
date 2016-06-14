/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hemtenta;

/**
 *
 * @author sa11
 */
public class Move {

    private int x;
    private int y;
    private int fromX = -1; // used to see which was the previous x cord
    private int fromY = -1; // used to see which was the previous y cord

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Move(int x, int y, int fromX, int fromY) {
        this.x = x;
        this.y = y;
        this.fromX = fromX;
        this.fromY = fromY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public void setFromX(int fromX) {
        this.fromX = fromX;
    }

    public void setFromY(int fromY) {
        this.fromY = fromY;
    }
    
    
    /*
     * returns the Move as a String
     */
    @Override
    public String toString() {
        String temp;
        if (fromX == -1) {
            temp = "" + x + ":" + y;
        } else {
            temp = "from:" + fromX + fromY + " " + x + ":" + y;
        }
        return temp;
    }
}
