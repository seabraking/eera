import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class implements a binary tree by using an array.
 * 
 * @author Marat Pernabekov
 * @version 1.1
 */

@SuppressWarnings("unchecked")
public class ArrayBinaryTree<T> implements BinaryTreeInterface<T>,
		                                     java.io.Serializable {
	private T theData[];
	private int height; // height of tree
	private int size;   // number of locations in array for a full tree of this
						     // height
   
   /* Initializes an empty tree */
	public ArrayBinaryTree() {
      theData = (T[]) new Object[0];
      height = 0;
      size = 0;
	} // end default constructor

   /* Initializes a one-node tree */
	public ArrayBinaryTree(T rootData) {
      setTree(rootData);
	} // end constructor
   
   /* Initializes a tree with the specified root and left/right subtrees */
	public ArrayBinaryTree(T rootData, ArrayBinaryTree<T> leftTree,
			                             ArrayBinaryTree<T> rightTree) {
      if ((leftTree == null) && (rightTree == null))
         privateSetTree(rootData, null, null);
      else
         privateSetTree(rootData, leftTree, rightTree);
	} // end constructor
   
   /* Sets the tree to be a new one-node tree with the specified root */
	public void setTree(T rootData) {
      privateSetTree(rootData, null, null);
	} // end setTree

	/* Sets the tree to be a tree with the specified root and left and right subtrees */
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
			                          BinaryTreeInterface<T> rightTree)
   {
      // The subtrees have to be cast to ArrayBinaryTree objects
      privateSetTree(rootData, (ArrayBinaryTree<T>)leftTree, 
                               (ArrayBinaryTree<T>)rightTree);
	} // end setTree

	/* A helper method that can be used to set up a tree from existing subtrees */
	private void privateSetTree(T rootData, ArrayBinaryTree<T> leftTree,
                              			    ArrayBinaryTree<T> rightTree) {

      // If the subtrees are empty, create a single-level tree.
      if ((leftTree == null) && (rightTree == null)) {
         theData = (T[]) new Object[1];
         height = 1;
         size = 1;
         theData[0] = rootData;
      }
      // If the right subtree is empty, use the left subtree's structure
      else if ((leftTree != null) && (rightTree == null)) {
         // The height is dependent on the height of the left subtree
         height = leftTree.getHeight() + 1;
         
         size = getSizeFromHeight(height);
         theData = (T[]) new Object[size];
         
         // Set the root node and copy the left subtree
         setRootData(rootData);
         setLeftSubtree(leftTree);
      }   
      // If the left subtree is empty, use the right subtree's structure
      else if ((leftTree == null) && (rightTree != null)) {
         // The height is dependent on the height of the right subtree
         height = rightTree.getHeight() + 1;

         size = getSizeFromHeight(height);
         theData = (T[]) new Object[size];
         
         // Set the root node and copy the right subtree
         setRootData(rootData);            
         setRightSubtree(rightTree);
      }   
      else {  // assumes that neither left or right subtrees are empty
         assert (leftTree != null && rightTree != null); 

         // Find the longest subtree and add 1 to it to set the height of
         // the tree. 
         height = Math.max(leftTree.getHeight(), rightTree.getHeight()) + 1;
         
         size = getSizeFromHeight(height);
         theData = (T[]) new Object[size];          
 
         // Set the root node and the two 
         setRootData(rootData);
         setLeftSubtree(leftTree);
         setRightSubtree(rightTree);
      } // end if-elseIf-else
	} // end privateSetTree

	/*
	 * Copies the data values from the given subtree into the leftsubtree.
	 * Precondition: The array theData is large enough to hold the new values.
	 */
	private void setLeftSubtree(ArrayBinaryTree<T> subTree) {
        int currentIndex = 0;  // current index in a subtree
        int firstIndex = 0;
        int lastIndex = 0;
        int nodesLevel = 1;    // the # of nodes in this level (starts at 1)
        
        // Traverses the left subtree, copying each element
        for(int i = 1; i <= subTree.height; i++)
        {
            firstIndex = (2 * firstIndex) + 1;
            lastIndex = firstIndex + nodesLevel - 1;
            
            for(int index = firstIndex; index <= lastIndex; index++, currentIndex++)
               theData[index] = subTree.theData[currentIndex];
            
            nodesLevel = nodesLevel * 2;  // the number of nodes doubles on each level
        } // end for
	} // end setLeftSubtree

	/*
	 * Copies the data values from the given subtree into the rightsubtree.
	 * Precondition: The array theData is large enough to hold the new values.
	 */
	private void setRightSubtree(ArrayBinaryTree<T> subTree) {
        int currentIndex = 0;  // current index in a subtree
        int firstIndex = 0;
        int lastIndex = 0;
        int nodesLevel = 1;    // the # of nodes in this level (starts at 1)
        
        // Traverses the right subtree, copying each element
        for(int i = 1; i <= subTree.height; i++)
        {
            lastIndex = (2 * lastIndex) + 2;
            firstIndex = lastIndex - nodesLevel + 1;
    
            for(int index = firstIndex; index <= lastIndex; index++, currentIndex++)
                theData[index] = subTree.theData[currentIndex];
            
            nodesLevel = nodesLevel * 2;   // the number of nodes doubles on each level
        } // end for
	} // end setRightSubtree

	/* Finds the size of the array necessary to fit a tree of height h */
	private int getSizeFromHeight(int h) {
      return (int) (Math.pow(2, h) - 1);
	} // end getSizeFromHeight
   
   /* Returns the root of tree, or null if it is empty */
	public T getRootData() {
      T root = null;
      
      if (!isEmpty())
         root = theData[0];
          
      return root;
	} // end getRootData
   
   /* Determines if the tree is empty */
	public boolean isEmpty() {
      return (size == 0) && (height == 0);
	} // end isEmpty

   /* Empties the tree and resets the height and size values */
	public void clear() {
      for(int i = 0; i < theData.length; i++)
         theData[i] = null;

      height = 0;
      size = 0;
	} // end clear
   
   /* Sets the root of the tree to a new value */
	protected void setRootData(T rootData) {
      theData[0] = rootData;
	} // end setRootData
   
   /* Gets the height of the tree based on the size parameter */
	public int getHeight() {
		if (this == null)
         return 0;
      
      // If the tree is not empty, then height = log (size + 1) / log (2)
      return (int) (Math.log1p(size) / Math.log(2.0));
	} // end getHeight
   
   /* Returns the number of nodes in the tree */
	public int getNumberOfNodes() {
      int nodeCount = 0;
       
      for(int i = 0; i < theData.length; i++)
      {
         if(theData[i] != null)
            nodeCount++;
      } // end for
        
      return nodeCount;
	} // end getNumberOfNodes

	/*
	 * The following operations allow one to move in the tree and test to see
	 * whether a child exists. These methods have already been implemented.
	 */
	private boolean hasLeftChild(int i) {
		return nodeExists((2 * i + 1));
	}

	private int leftChild(int i) {
		return 2 * i + 1;
	}

	private boolean hasRightChild(int i) {
		return nodeExists((2 * i + 2));
	}

	private int rightChild(int i) {
		return 2 * i + 2;
	}

	private boolean nodeExists(int i) {
		return (i >= 0 && i < size) && (theData[i] != null);
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}
	private T getData(int i) {
		T result = null;

		if (nodeExists(i))
			result = theData[i];
		return result;
	}
   
   
	/* display the contents of the array */
	public void display() {
		for (int i = 0; i < size; i++) {
			if (nodeExists(i))
				System.out.println("index: " + i + " has " + getData(i));
		} // end for
	} // end display

   /*
	 * The following are iterator implementations to traverse the tree:
    * InorderIterator, PreorderIterator and PostOrderIterator
	 */
	public Iterator<T> getInorderIterator() {
		return new InorderIterator();
	}

	private class InorderIterator implements Iterator<T> {
		private Stack<Integer> nodeStack;
		private Integer currentNode;

		public InorderIterator() {
			nodeStack = new Stack<Integer>();
			currentNode = 0;
		}

		public boolean hasNext() {
			return !nodeStack.isEmpty() || nodeExists(currentNode);
		}

		public T next() {
			Integer nextNode = -1;

			// find leftmost node with no left child
			while (nodeExists(currentNode)) {
				nodeStack.push(currentNode);
				currentNode = leftChild(currentNode);
			}

			// get leftmost node, then move to its right subtree
			if (!nodeStack.isEmpty()) {
				nextNode = nodeStack.pop();
				assert nodeExists(nextNode); // since nodeStack was not empty
												     // before the pop
				currentNode = rightChild(nextNode); // right subchild
			} else
				throw new NoSuchElementException();

			return theData[nextNode];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	} // end InorderIterator

	public Iterator<T> getPreorderIterator() {
		return new PreorderIterator();
	}

	private class PreorderIterator implements Iterator<T> {
  		private Stack<Integer> nodeStack;
      private Integer nextNode;

		public PreorderIterator() {
         nodeStack = new Stack<Integer>();
         nextNode = 0;
         
         if (!isEmpty())
            nodeStack.push(nextNode);
		}

		public boolean hasNext() { 
         return !nodeStack.isEmpty();
		}

		public T next() {
         T result = null;
         if (!nodeStack.isEmpty()) {
            Integer top = nodeStack.pop();
            result = theData[top];
                
             if (hasRightChild(top))  // hasRightChild
                 nodeStack.push(rightChild(top));
 
             if (hasLeftChild(top))  // hasLeftChild
                 nodeStack.push(leftChild(top));
			} else
				throw new NoSuchElementException();
         
         return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
      }
	} // end PreorderIterator

	public Iterator<T> getPostorderIterator() {
		return new PostorderIterator();
	}

	private class PostorderIterator implements Iterator<T> {
		private Stack<PostOrderNode> nodeStack;

		public PostorderIterator() {
			nodeStack = new Stack<PostOrderNode>();
			if (!isEmpty())
				nodeStack.push(new PostOrderNode(0, PostOrderState.LEFT));
		}

		public boolean hasNext() {
			return !nodeStack.isEmpty();
		}

		public T next() {
			T result = null;
			if (nodeStack.isEmpty()) {
				throw new NoSuchElementException();
			} else {
				PostOrderNode top = nodeStack.pop();
				PostOrderState state = top.state;

				while (state != PostOrderState.TOP) {
					if (state == PostOrderState.LEFT) {
						top.state = PostOrderState.RIGHT;
						nodeStack.push(top);

						if (hasLeftChild(top.node))   // hasLeftChild
							nodeStack.push(new PostOrderNode(
									leftChild(top.node), PostOrderState.LEFT));
					} else {
						assert state == PostOrderState.RIGHT;
						top.state = PostOrderState.TOP;
						nodeStack.push(top);

						if (hasRightChild(top.node)) // hasRightChild
							nodeStack.push(new PostOrderNode(
									rightChild(top.node), PostOrderState.LEFT));
					}
					top = nodeStack.pop();
					state = top.state;
				}
				result = theData[top.node];
			}

			return result;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	} // end PostorderIterator

	private enum PostOrderState {
		TOP, LEFT, RIGHT
	};

	private class PostOrderNode {
		public Integer node;
		public PostOrderState state;

		PostOrderNode(Integer theNode, PostOrderState theState) {
			node = theNode;
			state = theState;
		}
	} // end PostOrderNode

	public Iterator<T> getLevelOrderIterator() {
		throw new UnsupportedOperationException();
	} // end getLevelOrderIterator

} // end ArrayBinaryTree