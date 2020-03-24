package model;

import java.util.NoSuchElementException;

public class Queue {
    private Tile first;
    private Tile last;
    private int n;

    public Queue(Tile src){
        first = src;
        last = null;
    }

    public Tile peek() {
        return this.first;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(Tile t){
        Tile oldLast = last;
        last = t;
        if(isEmpty()){
            first = last;
        }
        else{
            oldLast.next = last;
        }
    }

    public Tile dequeue(){
        if(isEmpty()) throw new NoSuchElementException("Queue underflow :'(");
        Tile curr = first;
        first = first.next;
        if(isEmpty()) last = null;
        return curr;
    }
}
