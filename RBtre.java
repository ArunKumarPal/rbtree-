
package rbtree;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.lang.*;
import java.util.Stack;

 class Node {
        int element;
        Node Right;
        Node Left;
        Node next;
        Node Parent;
        Node gdParent;
        int x;
        int y;
        int o;
        Color c;
        
        public Node(int element, Node Left, Node Right,Node Parent,Node next,Node Gdparent) {
            this.element = element;
            this.c=Color.RED;
            this.Right = Right;
            
            this.Left = Left;
            
            this.Parent=Parent;
            
            this.next = next;
            this.gdParent=Gdparent;
            
        }
 }
 class DoublyLinkedListImpl {
 
     Node head;
   
    
    
     int size;
     
    void DoublyLinkedListImpl() {
        size = 0;
    }
   
    public int size() { return size; }
     
  
    public boolean isEmpty() { return size == 0; }
     
 
    public void insertion(int element){
     
        Node z=new Node(element ,null ,null,null,null,null);
        Node x=head;
        
        
       
        Node y=null;
        
        while(x!=null){
            y=x;
            if((z.element)<=(x.element)){
                x=x.Left;
            }
            else{
                x=x.Right;
            }
        }
        z.Parent=y;
        
        if(y==null){
            head=z;
        }
        else if(z.element<=y.element){
            y.Left=z;
            z.gdParent=y.Parent;
            
             }
        else{
            y.Right=z;
            z.gdParent=y.Parent;
            
        }
       
        z.c=Color.RED;
        fixTree(z);
       
        
        }
     private void fixTree(Node node) {
         if(node.Parent!=null){
             
        while ((node.gdParent!=null)&&(node.Parent.c == Color.RED)){
            
            Node y = null;
            if (node.Parent == node.gdParent.Left) {
                y = node.gdParent.Right;

                if ((y!=null)&&(y.c == Color.RED)) {
                    node.Parent.c = Color.BLACK;
                    y.c = Color.BLACK;
                    node.Parent.Parent.c = Color.RED;
                    node = node.gdParent;
                    
                } 
                else{ 
                    if (node == node.Parent.Right) {
                    
                    node = node.Parent;
                    rotateLefts(node);
                    } 
                node.Parent.c = Color.BLACK;
                node.Parent.Parent.c = Color.RED;
              
                rotateRights(node.Parent.Parent);
            }
            }
            
            else {
                y = node.Parent.Parent.Left;
                 if ((y!=null)&&(y.c == Color.RED)) {
                    node.Parent.c = Color.BLACK;
                    y.c = Color.BLACK;
                    node.Parent.Parent.c = Color.RED;
                    node = node.Parent.Parent;
                    
                }
                 else {
                     if (node == node.Parent.Left) {
                   
                    node = node.Parent;
                    rotateRights(node);
                }
                node.Parent.c = Color.BLACK;
                node.Parent.Parent.c = Color.RED;
               
                rotateLefts(node.Parent.Parent);
            }
        }
        }
         }
         
        head.c = Color.BLACK;
    }
  
void rotateLefts(Node node){
    Node h=node.Right;
    node.Right=h.Left;
    if(h.Left!=null){
        h.Left.Parent=node;
    }
    h.Parent=node.Parent;
    if(node.Parent==null){
        head=h;
    }
    else if(node==node.Parent.Left){
        node.Parent.Left=h;
    }
    else{
        node.Parent.Right=h;
    }
    h.Left=node;
    node.Parent=h;
}
void rotateRights(Node node){
    Node h=node.Left;
    node.Left=h.Right;
    if(h.Right!=null){
        h.Right.Parent=node;
    }
    h.Parent=node.Parent;
    if(node.Parent==null){
        head=h;
    }
    else if(node==node.Parent.Left){
        node.Parent.Left=h;
    }
    else{
        node.Parent.Right=h;
    }
    h.Right=node;
    node.Parent=h;
}
   }
     
 public class RBtre extends Applet {
   
   DoublyLinkedListImpl t=new DoublyLinkedListImpl();  
       
     public void init() {
        
       t.insertion(15);
       t.insertion(12);
       t.insertion(10);
       t.insertion(20);
       t.insertion(25);
       t.insertion(18);
       t.insertion(2);
       t.insertion(1);
      
    
    }
    

   public void paint(Graphics g){
      
       
      String x;
     Stack<Node> stack = new Stack<Node>();
        Node node = t.head;
        
         while (node != null) {
            if(node==t.head){
                node.x=500;
                node.y=100;
                node.o=400;
            }
            else{
                node.o=node.Parent.o/4;
                node.x=node.Parent.x-node.o-50;
               node.y=node.Parent.y+node.o+50;
            }
            
            
            stack.push(node);
            node = node.Left;
            
        }
         
        
        while (stack.size() > 0) {
           
            
            node = stack.pop();
            if(node!=t.head){
                
                g.setColor(Color.black);
                g.drawLine(node.x, node.y, node.Parent.x, node.Parent.y);
                
                
            }
            g.setColor(node.c);
            
            g.fillOval(node.x-15, node.y-15, 30, 30);
            System.out.println(node.x+" "+node.y);
            String s=Integer.toString(node.element);
            g.setColor(Color.GREEN);
            g.drawString(s, node.x+10-15, node.y+15-15);
            x="("+String.valueOf(node.x)+","+String.valueOf(node.y)+")";
            g.setColor(Color.DARK_GRAY);
            g.drawString(x,node.x, node.y-10);
            
          
            if (node.Right != null) {
                node = node.Right;
                 if(node==t.head){
                node.x=500;
                node.y=100;
                node.o=400;
               }
                else{
                     node.o=node.Parent.o/4;
                node.x=node.Parent.x+node.o+50;
               node.y=node.Parent.y+node.o+50;
               }
                 stack.push(node);
                
               while (node.Left != null) {
                    
                  node=node.Left;
                    if(node!=null){
                     if(node==t.head){
                     node.x=500;
                      node.y=100;
                      node.o=400;
                      }
                  else{
                         node.o=node.Parent.o/4;
                       node.x=node.Parent.x-node.o-50;
                       node.y=node.Parent.y+node.o+50;
                      }
                    }
                   stack.push(node);
                 }
            }
        }
   }
     
     
}