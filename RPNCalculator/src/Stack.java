
public class Stack 
{
	// field
	public Node topNode;
	public int size;
	
	// constructor
	// When constructing a new stack, everything is empty.

	Stack()
	{
		topNode = null;
		size = 0;
	}
	
	// NEED TO MODIFY
	// methods
	// return true if the stack is empty
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}
	
	// return the number of nodes in the stack
	public int size()
	{
		return this.size;
	}
	
	// insert the given node to the end of the stack 
	// remember to change the size after you insert
	
	public void push(Node node)
	{
		Node tempNode = this.topNode;
		this.topNode = node;
		this.topNode.nextNode = tempNode;
		this.size++;
	}
	
	// return and remove the most recent added node from the stack
	// remember to change the size after you remove
	// remember to check if the stack is already empty before removing
	
	public Node pop()
	{
		if (isEmpty())
			return null;
		else
		{
			Node rtn = this.topNode;
			this.topNode = this.topNode.nextNode;
			rtn.nextNode = null;
			this.size--;
			return rtn;
		}
	}
	
	
}
