import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LinkedListCycle{
    
    //static nested member class can be instantiated
    private static class Node {
        int data;
        Node next;
    }

    //static method
    public static boolean hasCycle(Node head) {
        //references to Node instances
        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            //references are updated to point to different nodes
            slow = slow.next;          // 1 hop
            fast = fast.next.next;     // 2 hops 

            if(slow == fast)  // fast caught up to slow, so there is a loop
                return true;
        }
        return false;  // fast reached null, so the list terminates
    }

    //since Node is a static class and hasCycle is a static method, the static main method can access them
    public static void main(String[] args){
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();

        a.next = b;
        b.next = c;
        c.next = a;

        System.out.println(hasCycle(a));
    }
}