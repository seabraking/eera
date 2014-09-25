import java.util.Iterator;
import java.util.Stack;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.PriorityQueue;

/**
 * A class that implements the ADT undirected graph.
 * 
 * @author Marat Pernabekov
 * @version 1.0
 */
public class UndirectedGraph<T> extends DirectedGraph<T>
{
	public UndirectedGraph()
	{
      super();
	} // end default constructor
     
   @Override
	public Stack<T> getTopologicalOrder() 
	{
      throw new UnsupportedOperationException( "There is no topological ordering for undirected graphs!" );
	} // end getTopologicalOrder
} // end UndirectedGraph