public class LabDPartADriver {

    public static void main(String[] args) 
	{
		System.out.println("Create a queue: ");
		QueueInterface<String> myQueue = new CircularLinkedQueue<String>();
		System.out.println("\n\nisEmpty() returns " + myQueue.isEmpty() + "\n");
		
		System.out.println("Add to queue to get\n" +
		                   "Joe Jess Jim Jill Jane Jerry\n");
		myQueue.enqueue("Joe");
		myQueue.enqueue("Jess");
		myQueue.enqueue("Jim");
		myQueue.enqueue("Jill");
		myQueue.enqueue("Jane");
		myQueue.enqueue("Jerry");

		System.out.println("\nisEmpty() returns " + myQueue.isEmpty());

		System.out.println("\n\nTesting getFront and dequeue:\n");
      int count = 0;
		while (!myQueue.isEmpty())
		{
			String front = myQueue.getFront();
			System.out.println(front + " is at the front of the queue.");
			
			front = myQueue.dequeue();
			System.out.println(front + " is removed from the front of the queue.\n");
         count++;
		} // end while

		System.out.print("\nThe queue should be empty: ");
		System.out.println("isEmpty() returns " + myQueue.isEmpty() + "\n\n");

		System.out.println("Add to queue to get\n" +
		                   "Joe Jess Jim\n");
		myQueue.enqueue("Joe");
		myQueue.enqueue("Jess");
		myQueue.enqueue("Jim");
		
		System.out.println("\nTesting clear:\n");
		myQueue.clear();
			
		System.out.println("\nisEmpty() returns " + myQueue.isEmpty() + "\n\n");

		System.out.println("Add to queue to get\n" +
		                   "Joe Jess Jim\n");
		myQueue.enqueue("Joe");
		myQueue.enqueue("Jess");
		myQueue.enqueue("Jim");
		
		while (!myQueue.isEmpty())
		{
			String front = myQueue.getFront();
			System.out.println(front + " is at the front of the queue.");
			
			front = myQueue.dequeue();
			System.out.println(front + " is removed from the front of the queue.\n");
		} // end while

		System.out.print("\n\nThe queue should be empty: ");
		System.out.println("isEmpty() returns " + myQueue.isEmpty() + "\n");
		
		System.out.println("myQueue.getFront() returns " +  myQueue.getFront());
		System.out.println("myQueue.dequeue() returns " +  myQueue.dequeue() + "\n");
	}  // end main


}