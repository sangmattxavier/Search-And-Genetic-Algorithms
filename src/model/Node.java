package model;

import java.lang.*;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Comparator;

public class Node implements Comparator<Node>, Comparable<Node> {
    public int x;
    public int y;
    public double distance = 0;
    public Boolean isBlocked;
    public Boolean isVisited;
    public Boolean isPath = false;
    public Node next; // To be used for stacks and queues
    public Node up; // Used to keep track of neighbors
    public Node down;
    public Node right;
    public Node left;
    public ArrayList<Node> children;

    public Boolean getIsBlocked(double p){
        if(Math.random() >= p){
            return false;
        }
        else{
            return true;
        }
    }

    public Node(int x, int y, double p, Node[][] grid){
        this.x = x;
        this.y = y;
        this.isBlocked = getIsBlocked(p);
        this.isVisited = false;
        this.next = null;

        this.up = null;
        this.down = null;
        this.right = null;
        this.left = null;
        this.children = new ArrayList<Node>();
    }

    public int compareTo(Node a) {
        if (this.distance < a.distance) {
            return -1;
        } else if (this.distance == a.distance) {
            return 0;
        } else {
            return 1;
        }
    }

    public int compare(Node a, Node b) {
        if (a.distance < b.distance) {
            return -1;
        } else if (a.distance == b.distance) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
