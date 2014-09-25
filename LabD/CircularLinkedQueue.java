/* Class: CS111C
 * Author: Marat Pernabekov
 * Assignment: Lab D
 * File: CircularLinkedQueue.java
 */
 
public class CircularLinkedQueue<T> implements QueueInterface<T>,
                                               java.io.Serializable
{
   private Node lastNode;  // references node at the back of queue
//    private int size;    // the size counter for traversing the queue (optional)

   public CircularLinkedQueue()
   {
      lastNode = null;
//    size = 0;  // uncomment if a traversal is necessary
   } // end default constructor


   // The enqueue method adds a new entry to the back of the circular queue.
   public void enqueue(T newEntry)
   {  
      Node newNode = new Node(newEntry);            // references the new node to be added
      
      // If the lastNode is not null, then the queue is not empty
      if (lastNode != null) 
      {
        newNode.setNextNode(lastNode.getNextNode());   // copy the reference to first node
        lastNode.setNextNode(newNode);                 // let lastNode reference next node
      }
      
      // If the lastNode is null, then the queue must be empty.
      // The first node to be added must reference itself.
      else
        newNode.setNextNode(newNode);
      
      // Let lastNode reference the newly added queue element
      lastNode = newNode;
//    size++;     // increments the counter as an entry is added

   } // end enqueue


   // The getFront method returns the first entry from the circular queue.
   public T getFront()
   {
      T front = null;    // holds the value of the first element in the queue

      // If the queue isn't empty, finds the value of the first element
      if (!isEmpty())
      {
         front = lastNode.getNextNode().getData();
      }

      // Returns the value of the first element, or null for an empty queue
      return front;
   } // end getFront


   // The dequeue method removes the first element from the circular queue and
   // returns its value. Sets the value of the removed element to that of the
   // next element in the queue.
   public T dequeue()
   {
      T front = null;     // holds the value of the first element in the queue

      // Checks if the queue is empty, skip to the end if it is.
      if (!isEmpty())
      {
         // If lastNode and the next node it references return the same value,
         // assumes that this is a singleton. Sets lastNode to null.
         if (lastNode.getNextNode().getData().equals(lastNode.getData())) {
            front = lastNode.getData();
            lastNode = null;
         // If it isn't a singleton, sets the value of the first queue element to
         // the value of the next element and updates the reference to next node.
         } else {
            front = lastNode.getNextNode().getData();
            lastNode.getNextNode().setData(
                      lastNode.getNextNode().getNextNode().getData());
            lastNode.getNextNode().setNextNode(
                      lastNode.getNextNode().getNextNode().getNextNode());
         } // end if
         
         // size--;   // decremented after an element is removed
         
      } // end if

      // Returns the value of the first element, or null for an empty queue.
      return front;
   } // end dequeue


   // Checks whether the queue is empty, by considering its last node.
   public boolean isEmpty()
   {
      return (lastNode == null);
   } // end isEmpty


   // The clear method empties the queue by calling the dequeue
   // method repeatedly.
   public void clear()
   {
      while (!isEmpty())
         dequeue();
   } // end clear


//   The display method can display the contents of the queue
//   starting with the first node (or lastNode.getNextNode())
//   public void display()
//   {
//   Node currentNode = lastNode.getNextNode();
// 
//   int count = size;
// 
//   do {
//        System.out.println(currentNode.getData());
//        currentNode = currentNode.getNextNode();
//        count--;
//     } while (count > 0);
//
//  } // end display


   // Private class to hold the individual queue elements
   private class Node implements java.io.Serializable
   {
      private T    data; // entry in queue
      private Node next; // link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor

      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

      private T getData()
      {
         return data;
      } // end getData

      private void setData(T newData)
      {
         data = newData;
      } // end setData

      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node

} // end CircularLinkedQueue