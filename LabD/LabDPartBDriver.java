public class LabDPartBDriver {

    public static void main(String args[])
    {
        NoDuplicatesArrayQueue<Integer> test1 = new NoDuplicatesArrayQueue<Integer>();
        
        test1.enqueue(1);
        test1.enqueue(3);
        test1.enqueue(2);
        test1.enqueue(0);
        test1.enqueue(-1);
        System.out.println("The queue has ");
        test1.display();
        System.out.println();
        
        test1.enqueue(test1.dequeue());
        test1.enqueue(test1.dequeue());
        test1.enqueue(test1.dequeue());
        test1.enqueue(test1.dequeue());
        test1.enqueue(test1.dequeue());
        System.out.println("The queue should be the same ");
        test1.display();
        System.out.println();
        
        test1.enqueue(1);
        test1.enqueue(3);
        test1.enqueue(2);
        test1.enqueue(0);
        test1.enqueue(-1);
        System.out.println("The queue should be the same ");
        test1.display();
        System.out.println();
        
        test1.moveToBack(3);
        System.out.println("The queue should be 1, 2, 0, -1, 3 ");
        test1.display();
        System.out.println();

        test1.moveToBack(0);
        System.out.println("The queue should be 1, 2, -1, 3, 0 ");
        test1.display();
        System.out.println();

        test1.moveToBack(1);
        System.out.println("The queue should be 2, -1, 3, 0, 1");
        test1.display();
        System.out.println();

        test1.moveToBack(5);
        System.out.println("The queue should be 2, -1, 3, 0, 1, 5");
        test1.display();
        System.out.println();
    }


}