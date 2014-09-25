/**
 * This interface describes a queue of unique entries
 * (duplicates are not allowed).
 * 
 * @author Charles Hoot 
 * @version 2.0
 */
public interface NoDuplicatesQueueInterface<T>
       extends QueueInterface<T>
{
	/*
	 * Task: Moves the given entry to the back of the queue.
	 *       If the entry is not in the queue, just add it at the end.
	 * @param entry  the item to move or add
	 */
	public void moveToBack(T entry);
}
