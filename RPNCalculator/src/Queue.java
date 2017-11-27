
public class Queue 
{
	// fields
	public Node firstNode;
	public Node lastNode;
	public int size;
		
	// constructor
	// When constructing a new queue, everything is empty.
	public Queue()
	{
		firstNode = null;
		lastNode = null;
		size = 0;
	}
		
	// NEED TO MODIFY
	// methods
	// returns true if the queue is empty
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}
		
	// returns the number of nodes in the queue
		
	public int size()
	{
		return this.size;
	}
		
	// insert a node onto the end of the queue
	// remember to change the size of the queue after adding a node

	public void enqueue(Node node)
	{
		if (isEmpty())
			this.firstNode = this.lastNode = node;
		else
		{
			this.lastNode.nextNode = node;
			this.lastNode = this.lastNode.nextNode;
		}
		this.size++;
	}
		
	// return and remove the node that was inserted first
	// remember to change the size after you remove
	// remember to check if the queue is already empty before removing
		
	public Node dequeue()
	{
		if (isEmpty())
			return null;
		else
		{
			Node rtn = this.firstNode;
			firstNode = firstNode.nextNode;
			rtn.nextNode = null;
			this.size--;
			return rtn;
		}
	}
		
}
